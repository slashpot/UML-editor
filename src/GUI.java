import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private JPanel buttonPanel;
	private JPanel canvasPanel;

	private JMenuBar menuBar;
	private JMenu file;
	private JMenu edit;

	private JButton[] buttons;
	private ImageIcon[] icons;

	private CanvasArea canvas;

	public GUI() {
		this.setLayout(new BorderLayout());
		
		buttons = new JButton[6];
		icons = new ImageIcon[6];
		
		SetPanels();
		SetMenuBar();
	}
	
	private void SetPanels(){
		// create panels
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		
		canvasPanel = new JPanel();
		canvasPanel.setLayout(new BorderLayout());
		
		// add buttons
		for(int i = 0 ; i < 6 ; i++){
			String itempath = "image//" + i + ".png";
			icons[i] = new ImageIcon(itempath);
			buttons[i] = new JButton(icons[i]);
            GridBagConstraints c = new GridBagConstraints();
            c.insets=new Insets(0,3,10,3);
            c.gridx = 0;
            c.gridy = i;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            c.anchor = GridBagConstraints.CENTER;
            buttonPanel.add(buttons[i], c);
		}
		
		// create canvas
		canvas = new CanvasArea();
		canvasPanel.add(canvas);
		
		// add panels
		this.add(buttonPanel, BorderLayout.WEST);
		this.add(canvasPanel, BorderLayout.CENTER);
	}
	
	private void SetMenuBar(){
		// create menu items
		menuBar = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		
		// set menubar
		menuBar.add(file);
		menuBar.add(edit);

		// add items to frame
		this.setJMenuBar(menuBar);
	}
	
}
