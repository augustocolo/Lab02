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
	
	public ArrayList<String> searchWildcard(String text){
		ArrayList<String> res = new ArrayList<String>();
		
		String[] pezzi = text.split("\\?");
		String alfabeto = "abcdefghijklmnopqrstuvwxyz";
		for (char c: alfabeto.toCharArray()) {
			String word = pezzi[0] + c + pezzi[1];
			if (this.searchWord(word) != null) {
				res.add(word);
			}
		}
		return res;
	}

}
