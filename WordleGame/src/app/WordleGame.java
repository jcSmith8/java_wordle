package app;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class WordleGame {

    class WordPanel extends JPanel {

        JLabel[] wordColumns = new JLabel[5];

        public WordPanel() {
            this.setLayout(new GridLayout(1, 5));
            Border blackBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
            for (int i = 0; i < 5; ++i) {
                wordColumns[i] = new JLabel();
                wordColumns[i].setOpaque(true);
                wordColumns[i].setBorder(blackBorder);
                this.add(wordColumns[i]);
            }
        }

        public void setPanelText(String charVal, int position, Color color) {
            this.wordColumns[position].setText(charVal);
            this.wordColumns[position].setBackground(color);
        }
    }

    class UserPanel extends JPanel {

        private JTextField userInput;
        private JButton okButton;

        public UserPanel() {
            this.setLayout(new GridLayout(1, 2));
            userInput = new JTextField();
            okButton = new JButton("Submit");
        }

        public JTextField getUserInput() {
            return userInput;
        }

        public JButton getOkButton() {
            return okButton;
        }
    }

    private JFrame gameFrame;
    private WordPanel[] wordPanelArray = new WordPanel[6];
    private UserPanel userPanel;
    private String wordleString;
    private int count = 0;

    public WordleGame() {
        gameFrame = new JFrame("Wordle Game");
        gameFrame.setSize(300, 300);
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setLayout(new GridLayout(7, 1));
        gameFrame.setVisible(true);
        gameFrame.setLocationRelativeTo(null);

        for (int i = 0; i < 6; ++i) {
            wordPanelArray[i] = new WordPanel();
            gameFrame.add(wordPanelArray[i]);
        }
        userPanel = new UserPanel();
        userPanel.getOkButton().addActionListener(this);
        gameFrame.add(userPanel);
        gameFrame.revalidate();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");
        new WordleGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if("Submit".equals(action)) {
            System.out.println(this.userPanel.getUserInput().getText());
        }
    }
}