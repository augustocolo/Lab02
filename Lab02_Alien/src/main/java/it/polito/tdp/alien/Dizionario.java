package it.polito.tdp.alien;

import java.util.*;

public class Dizionario {
	private Map<String, ArrayList<String>> parole;

	public Dizionario() {
		this.parole = new TreeMap<String, ArrayList<String>>();
	}

	public void addWord(String alien, String traduzione) {
		ArrayList<String> search = this.searchWord(alien);

		if (search == null) {
			ArrayList<String> nuovaLista = new ArrayList<String>();
			nuovaLista.add(traduzione);
			this.parole.put(alien, nuovaLista);
		} else if (!search.contains(traduzione)) {
			search.add(traduzione);
		}
	}

	public ArrayList<String> searchWord(String alien) {
		return this.parole.get(alien);
	}

}
