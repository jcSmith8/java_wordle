package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import model.MyColors;
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
		JButton button = (JButton) event.getSource();
		String text = button.getActionCommand();
		switch (text) {
		case "Enter":
			if (model.getCurrentCol() >= (model.getColCount() - 1)) {
				WordleResponse[] currentRow = model.getCurrentRow();
				String guess = "";
				for (WordleResponse wordleResponse : currentRow) {
					guess+=wordleResponse.getLetter();
				}
				guess = guess.toLowerCase();
				boolean isRealWord = model.search(guess);
				if(isRealWord){
					boolean moreRows = model.setRow();
					int greenCount = 0;
					for (WordleResponse wordleResponse : currentRow) {
						if (wordleResponse.getBGColor().equals(MyColors.GREEN)) {
							greenCount++;
						} 
					}
					
					if (greenCount >= model.getColCount()) {
						//view.repaintGrid();
						//view.WinnerPopup();
						view.repaintGrid();
						model.winner = true;
						//view.getFrame().setVisible(true);
					} else if (!moreRows) {
						view.repaintGrid();
					} else {
						view.repaintGrid();
					}
				}
				else{
					model.changeCurrentCol(4);
					for(int i = 0; i<5; i++){
						model.backspace();
					}
					view.repaintGrid();
				}
			}
			break;
		case "Backspace":
			model.backspace();
			view.repaintGrid();
			break;
		default:
			if(!model.winner){
				model.setCol(text.charAt(0));
				view.repaintGrid();
			}
			break;
		}
		
	}


}