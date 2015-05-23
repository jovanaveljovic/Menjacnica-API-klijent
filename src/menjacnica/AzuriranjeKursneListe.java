package menjacnica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import menjacnica.util.KursnaListaUtility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.JsonObject;

public class AzuriranjeKursneListe {

	final String putanjaDoFajlaKursnaLista = "data/kursnaLista.json";
	
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
