import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		GUI frame = new GUI();
		frame.setTitle("UML editor");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
