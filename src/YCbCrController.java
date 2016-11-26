import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class YCbCrController implements ChangeListener {

	YCbCrView myView;
	YCbCrModel myModel;
	
	public YCbCrController(YCbCrView view, YCbCrModel model) {
		myView = view;
		myModel = model;
		model.setLuma(myView.readLuma());
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		myModel.setLuma(myView.readLuma());
		//myModel.updateCbCrField(myView.getField().getGraphics());
		myView.paint(myView.getGraphics());
		//myView.repaint(0, 0, 256, 256);
		//myView.paintComponents(myView.getGraphics());
		//		myView.paintComponents(myModel.updateCbCrField(myView.getField().getGraphics()));
		if(!myView.getSlider().getValueIsAdjusting()) {
			myView.repaint();
		}
	}

}
