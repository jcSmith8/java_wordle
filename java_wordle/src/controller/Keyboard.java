package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import model.AppColors;
import model.WordleModel;
import model.WordleResponse;
import view.WordleFrame;

public class Keyboard extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private final WordleFrame view;
	
	private final WordleModel model;

	public Keyboard(WordleFrame view, WordleModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("Action detected"+event.getSource());
		JButton button = (JButton) event.getSource();
		String text = button.getActionCommand();
		switch (text) {
		case "Enter":
			System.out.println("PRESSED ENTER");
			if (model.getCurrentColumn() >= (model.getColumnCount() - 1)) {
				boolean moreRows = model.setCurrentRow();
				WordleResponse[] currentRow = model.getCurrentRow();
				int greenCount = 0;
				for (WordleResponse wordleResponse : currentRow) {
					//view.setColor(Character.toString(wordleResponse.getChar()),
					 //		wordleResponse.getBackgroundColor(), 
					 	//	wordleResponse.getForegroundColor());
					if (wordleResponse.getBackgroundColor().equals(AppColors.GREEN)) {
						greenCount++;
					} 
				}
				
				if (greenCount >= model.getColumnCount()) {
					view.repaintWordleGridPanel();
				} else if (!moreRows) {
					view.repaintWordleGridPanel();
				} else {
					view.repaintWordleGridPanel();
				}
			}
			break;
		case "Backspace":
			System.out.println("PRESSED BACKSPACE");
			model.backspace();
			view.repaintWordleGridPanel();
			break;
		default:
			System.out.println("PRESSED OTHER");
			model.setCurrentColumn(text.charAt(0));
			view.repaintWordleGridPanel();
			break;
		}
		
	}

}