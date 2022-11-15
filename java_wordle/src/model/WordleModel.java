package model;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import controller.ReadWordsRunnable;

public class WordleModel {
    private char[] currentWord, guess;
	
	private final int columnCount, maximumRows;
	private int currentColumn, currentRow;
	
	private List<String> wordList;

	private final Statistics statistics;

    private final Random random;

    private WordleResponse[][] wordleGrid;

    public WordleModel() {
		this.currentColumn = -1;
		this.currentRow = 0;
		this.columnCount = 5;
		this.maximumRows = 6;
		this.random = new Random();
		
		createWordList();
		
		this.wordleGrid = initializeWordleGrid();
		this.guess = new char[columnCount];
		this.statistics = new Statistics();
	}

	private void createWordList() {
		ReadWordsRunnable runnable = new ReadWordsRunnable(this);
		new Thread(runnable).start();
	}
	
	public void initialize() {
		this.wordleGrid = initializeWordleGrid();
		this.currentColumn = -1;
		this.currentRow = 0;
		generateCurrentWord();
		this.guess = new char[columnCount];
	}

	public void generateCurrentWord() {
		String word = getCurrentWord();
		System.out.println("Current word is: "+word);
		this.currentWord = word.toUpperCase().toCharArray();
	}

	private String getCurrentWord() {
		return wordList.get(getRandomIndex());
	}

	private int getRandomIndex() {
		int size = wordList.size();
		return random.nextInt(size);
	}
	
	private WordleResponse[][] initializeWordleGrid() {
		WordleResponse[][] wordleGrid = new WordleResponse[maximumRows][columnCount];

		for (int row = 0; row < wordleGrid.length; row++) {
			for (int column = 0; column < wordleGrid[row].length; column++) {
				wordleGrid[row][column] = null;
			}
		}

		return wordleGrid;
	}
	
	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
	}
	
	public void setCurrentWord() {
		int index = getRandomIndex();
		currentWord = wordList.get(index).toCharArray();
	}
	
	public void changeCurrentCol(int num){
		currentColumn = num;
	}

	public void setCurrentColumn(char c) {
		currentColumn++;
		currentColumn = Math.min(currentColumn, (columnCount - 1));
		guess[currentColumn] = c;
		wordleGrid[currentRow][currentColumn] = new WordleResponse(c,
				Color.WHITE, Color.BLACK);
	}
	
	public void backspace() {
		// System.out.println("GRID BEFORE: "+wordleGrid[currentRow][currentColumn]);
		wordleGrid[currentRow][currentColumn] = null;
		// System.out.println("GRID AFTER: "+wordleGrid[currentRow][currentColumn]);
		// System.out.println("GUESS BEFORE: "+guess[currentColumn]);
		guess[currentColumn] = ' ';
		// System.out.println("GUESS AFTER: "+guess[currentColumn]);
		this.currentColumn--;
		this.currentColumn = Math.max(currentColumn, -1);
	}
	
	public WordleResponse[] getCurrentRow() {
		return wordleGrid[currentRow];
	}

	public boolean setCurrentRow() {		
		for (int column = 0; column < guess.length; column++) {
			Color backgroundColor = AppColors.GRAY;
			Color foregroundColor = Color.WHITE;
			if (guess[column] == currentWord[column]) {
				backgroundColor = AppColors.GREEN;
			} else if (contains(currentWord, guess, column)) {
				backgroundColor = AppColors.YELLOW;
			}
			
			wordleGrid[currentRow][column] = new WordleResponse(guess[column],
					backgroundColor, foregroundColor);
		}
		
		currentColumn = -1;
		currentRow++;
		guess = new char[columnCount];
		
		return currentRow < maximumRows;
	}
	
	private boolean contains(char[] currentWord, char[] guess, int column) {
		for (int index = 0; index < currentWord.length; index++) {
			if (index != column && guess[column] == currentWord[index]) {
				return true;
			}
		}
		
		return false;
	}

	public boolean search(String guess){
		for(String str : this.wordList){
			if(str.contains(guess)){
				return true;
			}
		}
		return false;
	}

	public WordleResponse[][] getWordleGrid() {
		return wordleGrid;
	}
	
	public int getMaximumRows() {
		return maximumRows;
	}

	public int getColumnCount() {
		return columnCount;
	}
	
	public int getCurrentColumn() {
		return currentColumn;
	}

	public int getTotalWordCount() {
		return wordList.size();
	}

	public Statistics getStatistics() {
		return statistics;
	}

}
