package menjacnica.util;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import menjacnica.Valuta;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class KursnaListaUtility {

	public static void postaviProperty(JsonObject json, LinkedList<Valuta> valute, GregorianCalendar datum){
		json.addProperty("datum", getDate(datum));
		json.add("valute", vratiValute(valute));
	}

	
	public static String getDate(GregorianCalendar d){
		int day = d.get(GregorianCalendar.DAY_OF_MONTH);
		int month = d.get(GregorianCalendar.MONTH)+1;
		int year = d.get(GregorianCalendar.YEAR);
		
		return day+"."+month+"."+year;
	}
	
	public static JsonArray vratiValute(LinkedList<Valuta> valute){
		JsonArray valuteJson = new JsonArray();
		
		for (int i = 0; i < valute.size(); i++) {
			JsonObject valutaJson = new JsonObject();
			valutaJson.addProperty("naziv", valute.get(i).getNaziv());
			valutaJson.addProperty("kurs", valute.get(i).getKurs());
			valuteJson.add(valutaJson);
		}
		return valuteJson;
	}
	
	public static LinkedList<Valuta> prevediValute(JsonArray v){
		LinkedList<Valuta> valute = new LinkedList<>();
		for (int i = 0; i < v.size(); i++) {
			JsonObject valutaJson = (JsonObject) v.get(i);
			
			Valuta valuta = new Valuta();
			valuta.setKurs(valutaJson.get("kurs").getAsDouble());
			valuta.setNaziv(valutaJson.get("naziv").getAsString());
			valute.add(valuta);
			
		}
		return valute;
	}
}
