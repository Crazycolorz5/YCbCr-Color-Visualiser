
public class MainClass {
	public static void main(String[] args) {
		YCbCrView view = new YCbCrView();
		System.out.println("View created.");
		YCbCrModel model = new YCbCrModel();
		System.out.println("Model created.");
		YCbCrController controller = new YCbCrController(view, model);
		System.out.println("Controller created.");
		view.setController(controller);
		view.setModel(model);
		System.out.println("Initialized.");
	}
}
