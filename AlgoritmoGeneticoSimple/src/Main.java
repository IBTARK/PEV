import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import view.MainWindow;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {

				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					new MainWindow();
				} catch (Exception e) {}
				
			}
		});
	}
}
