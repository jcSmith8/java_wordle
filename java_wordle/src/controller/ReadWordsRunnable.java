package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.WordleModel;

public class ReadWordsRunnable implements Runnable {

	private final static Logger LOGGER =
			Logger.getLogger(ReadWordsRunnable.class.getName());

	private final WordleModel model;

	public ReadWordsRunnable(WordleModel model) {
		LOGGER.setLevel(Level.INFO);

		try {
			FileHandler fileTxt = new FileHandler("./logging.txt");
			LOGGER.addHandler(fileTxt);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.model = model;
	}

	@Override
	public void run() {
		List<String> wordlist;

		try {
			wordlist = createWordList();
			LOGGER.info("Created word list of " + wordlist.size() + " words.");
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			wordlist = new ArrayList<>();
		}

		model.setWordList(wordlist);
		model.generateCurrentWord();
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
