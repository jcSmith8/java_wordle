package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import controller.Keyboard;
import model.MyColors;
import model.WordleModel;

public class KeysPanel {

	private int buttonIndex, buttonCount;

	private final JButton[] buttons;

	private final JPanel panel;

	private final Keyboard action;

	private final WordleModel model;

	public KeysPanel(WordleFrame view, WordleModel model) {
		this.model = model;
		this.buttonIndex = 0;
		this.buttonCount = firstRow().length + secondRow().length
				+ thirdRow().length;
		this.buttons = new JButton[buttonCount];
		this.action = new Keyboard(view, model);
		this.panel = createMainPanel();
	}

	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1, 0, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));

		panel.add(createFirstPanel());
		panel.add(createSecondPanel());
		panel.add(createThirdPanel());
		panel.add(createTotalPanel());

		return panel;
	}

	private JPanel createFirstPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		Font textfont = AppFonts.getTextFont();

		String[] letters = firstRow();

		for (int index = 0; index < letters.length; index++) {
			JButton button = new JButton(letters[index]);
			setKeyBinding(button, letters[index]);
			button.addActionListener(action);
			button.setFont(textfont);
			buttons[buttonIndex++] = button;
			panel.add(button);
		}

		return panel;
	}

	private String[] firstRow() {
		String[] letters = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
				"Backspace" };
		return letters;
	}

	private JPanel createSecondPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		Font textfont = AppFonts.getTextFont();

		String[] letters = secondRow();

		for (int index = 0; index < letters.length; index++) {
			JButton button = new JButton(letters[index]);
			setKeyBinding(button, letters[index]);
			button.addActionListener(action);
			button.setFont(textfont);
			buttons[buttonIndex++] = button;
			panel.add(button);
		}

		return panel;
	}

	private String[] secondRow() {
		String[] letters = { "A", "S", "D", "F", "G", "H", "J", "K", "L",
				"Enter" };
		return letters;
	}

	private JPanel createThirdPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		Font textfont = AppFonts.getTextFont();

		String[] letters = thirdRow();

		for (int index = 0; index < letters.length; index++) {
			JButton button = new JButton(letters[index]);
			setKeyBinding(button, letters[index]);
			button.addActionListener(action);
			button.setFont(textfont);
			buttons[buttonIndex++] = button;
			panel.add(button);
		}

		return panel;
	}

	private String[] thirdRow() {
		String[] letters = { "Z", "X", "C", "V", "B", "N", "M" };
		return letters;
	}

	private void setKeyBinding(JButton button, String text) {
		InputMap inputMap = button.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
		if (text.equalsIgnoreCase("Backspace")) {
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0),
					"action");
		} else {
			inputMap.put(KeyStroke.getKeyStroke(text.toUpperCase()), "action");
		}
		ActionMap actionMap = button.getActionMap();
		actionMap.put("action", action);
	}

	private JPanel createTotalPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		Font footerFont = AppFonts.getFooterFont();

		String text = String.format("%,d", model.getTotalWordCount());
		text += " possible " + model.getColCount() + "-letter words!";
		JLabel label = new JLabel(text);
		label.setFont(footerFont);
		panel.add(label);

		return panel;
	}

	public void setColor(String letter, Color bgColor,
			Color fgColor) {
		for (JButton button : buttons) {
			if (button.getActionCommand().equals(letter)) {
				Color color = button.getBackground();
				if (color.equals(MyColors.GREEN)) {
					// Do nothing
				} else if (color.equals(MyColors.YELLOW)
						&& bgColor.equals(MyColors.GREEN)) {
					button.setBackground(bgColor);
					button.setForeground(fgColor);
				} else {
					button.setBackground(bgColor);
					button.setForeground(fgColor);
				}
				break;
			}
		}
	}

	public void resetDefaultColors() {
		for (JButton button : buttons) {
			button.setBackground(null);
			button.setForeground(null);
		}
	}

	public JPanel getPanel() {
		return panel;
	}

}