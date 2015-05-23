package menjacnica;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import menjacnica.util.KursnaListaUtility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AzuriranjeKursneListe {

	final String putanjaDoFajlaKursnaLista = "data/kursnaLista.json";
	
	public LinkedList<Valuta> ucitajValute() throws IOException{
		LinkedList<Valuta> valute = new LinkedList<>();
		
		FileReader in = new FileReader(putanjaDoFajlaKursnaLista);
		
		Gson gson = new GsonBuilder().create();
		JsonObject kursnaListaJson = gson.fromJson(in, JsonObject.class);
		JsonArray valuteJson = kursnaListaJson.get("valute").getAsJsonArray();
		
		valute = KursnaListaUtility.prevediValute(valuteJson);
		
		return valute;
	}
	
	public void upisiValute(LinkedList<Valuta> valute, GregorianCalendar datum){
		JsonObject kursnaListaJson = new JsonObject();
		
		KursnaListaUtility.postaviProperty(kursnaListaJson, valute, datum);
		
		try {
			PrintWriter out = new PrintWriter(
								new BufferedWriter(
									new FileWriter(putanjaDoFajlaKursnaLista)));
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			out.println(gson.toJson(kursnaListaJson));
			out.close();
			
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}
	}
	
	
	
}
