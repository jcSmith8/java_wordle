package model;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import controller.ReadWordsRunnable;

public class WordleModel {
    private char[] currentWord, guess;
	
	private final int colCount, maxRows;
	private int currentCol, currentRow;
	
	private List<String> wordList;

	// private final Statistics statistics;

    private final Random random;

    private WordleResponse[][] wordleGrid;
	public boolean winner;

    public WordleModel() {
		this.currentCol = -1;
		this.currentRow = 0;
		this.colCount = 5;
		this.maxRows = 6;
		this.winner = false;
		this.random = new Random();
		
		createWordList();
		
		this.wordleGrid = initGuessGrid();
		this.guess = new char[colCount];
		// this.statistics = new Statistics();
	}

	private void createWordList() {
		ReadWordsRunnable runnable = new ReadWordsRunnable(this);
		new Thread(runnable).start();
	}
	
	public void initialize() {
		this.wordleGrid = initGuessGrid();
		this.currentCol = -1;
		this.currentRow = 0;
		generateWord();
		this.guess = new char[colCount];
	}

	public void generateWord() {
		String word = getCurrentWord();
		System.out.println("Current word is: "+word);
		this.currentWord = word.toUpperCase().toCharArray();
	}

	private String getCurrentWord() {
		return wordList.get(getRandInd());
	}

	private int getRandInd() {
		int size = wordList.size();
		return random.nextInt(size);
	}
	
	private WordleResponse[][] initGuessGrid() {
		WordleResponse[][] wordleGrid = new WordleResponse[maxRows][colCount];

		for (int row = 0; row < wordleGrid.length; row++) {
			for (int column = 0; column < wordleGrid[row].length; column++) {
				wordleGrid[row][column] = null;
			}
		}

		return wordleGrid;
	}
	
	public void setList(List<String> wordList) {
		this.wordList = wordList;
	}
	
	public void setCurrentWord() {
		int index = getRandInd();
		currentWord = wordList.get(index).toCharArray();
	}
	
	public void changeCurrentCol(int num){
		currentCol = num;
	}

	public void setCol(char c) {
		currentCol++;
		currentCol = Math.min(currentCol, (colCount - 1));
		guess[currentCol] = c;
		wordleGrid[currentRow][currentCol] = new WordleResponse(c,
				Color.WHITE, Color.BLACK);
	}
	
	public void backspace() {
		// System.out.println("GRID BEFORE: "+wordleGrid[currentRow][currentCol]);
		wordleGrid[currentRow][currentCol] = null;
		// System.out.println("GRID AFTER: "+wordleGrid[currentRow][currentCol]);
		// System.out.println("GUESS BEFORE: "+guess[currentCol]);
		guess[currentCol] = ' ';
		// System.out.println("GUESS AFTER: "+guess[currentCol]);
		this.currentCol--;
		this.currentCol = Math.max(currentCol, -1);
	}
	
	public WordleResponse[] getCurrentRow() {
		return wordleGrid[currentRow];
	}

	public boolean setRow() {		
		for (int column = 0; column < guess.length; column++) {
			Color bgColor = MyColors.GRAY;
			Color fgColor = Color.WHITE;
			if (guess[column] == currentWord[column]) {
				bgColor = MyColors.GREEN;
			} else if (contains(currentWord, guess, column)) {
				bgColor = MyColors.YELLOW;
			}
			
			wordleGrid[currentRow][column] = new WordleResponse(guess[column],
					bgColor, fgColor);
		}
		
		currentCol = -1;
		currentRow++;
		guess = new char[colCount];
		
		return currentRow < maxRows;
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
		return maxRows;
	}

	public int getColCount() {
		return colCount;
	}
	
	public int getCurrentCol() {
		return currentCol;
	}

	public int getTotalWordCount() {
		return wordList.size();
	}

	// public Statistics getStatistics() {
	// 	return statistics;
	// }

}
