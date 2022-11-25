package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.WordleModel;

public class LoadWords implements Runnable {

	 private final WordleModel model;

	 public LoadWords(WordleModel model) {
	 	this.model = model;
	 }

	@Override
	public void run() {
		List<String> wordlist;

		try {
			wordlist = createWordList();
			System.out.println("Created word list of " + wordlist.size() + " words.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			wordlist = new ArrayList<>();
		}

		model.setList(wordlist);
		model.generateWord();
	}

	private List<String> createWordList() throws IOException {

		List<String> wordList = new ArrayList<>();

		String text = "sgb-words.txt";

		BufferedReader bufReader;
        bufReader = new BufferedReader(new FileReader(text));
        String line = bufReader.readLine(); 

        while (line != null) { 
            wordList.add(line); 
            line = bufReader.readLine(); 
        } 
		bufReader.close();

		//Making sure our words loaded correctly
		System.out.println("All "+ (wordList.size())+ " words loaded");

		return wordList;
	}
	
}
