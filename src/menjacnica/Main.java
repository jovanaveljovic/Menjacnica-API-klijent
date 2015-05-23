package menjacnica;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		AzuriranjeKursneListe a = new AzuriranjeKursneListe();
		try {
			a.azurirajValute();
		} catch (IOException e) {
			System.out.println("Greska: " + e.getMessage());
		}

	}

}
