package frame;
public class GMain {

	public static void main(String[] args) {
		// object name & body created & bound
		GMainFrame mainFrame = new GMainFrame();
		mainFrame.initialize();
		mainFrame.setVisible(true);
		System.out.println("branch setting");
	}
}
