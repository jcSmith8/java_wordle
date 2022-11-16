package view;

import java.awt.Color;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

public class WinnerPopup extends AbstractAction {

    private static final long serialVersionUID = -2158866431327158865L;
        Popup p;
     
        // constructor
        WinnerPopup()
        {
            // create a frame
            JFrame f = new JFrame("pop");
     
            // create a label
            JLabel l = new JLabel("This is a popup");
     
            f.setSize(400, 400);
     
            PopupFactory pf = new PopupFactory();
     
            // create a panel
            JPanel p2 = new JPanel();
     
            // set Background of panel
            p2.setBackground(Color.red);
     
            p2.add(l);
     
            // create a popup
            p = pf.getPopup(f, p2, 180, 100);
     
            // create a button
            JButton b = new JButton("click");
     
            // add action listener
            b.addActionListener(this);
     
            // create a panel
            JPanel p1 = new JPanel();
     
            p1.add(b);
            f.add(p1);
            f.show();
        }
}