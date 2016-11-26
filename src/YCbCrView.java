import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Hashtable;

import javax.swing.*;

public class YCbCrView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8736475792393525381L;

	//private YCbCrModel model;
	private JSlider luma;
	private JPanel CbCrField;
	private YCbCrController myGodtroller;
	private YCbCrModel myModel;
	
	public YCbCrView() {
		super("YCbCr Color Space");
		
		luma = new JSlider(JSlider.VERTICAL, 0, 2550, 1275);
		
		
		//Set the labels on the luminance slider
		Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
		labelTable.put( new Integer( 0 ), new JLabel("0.0") );
		labelTable.put( new Integer( 1275 ), new JLabel("Luma") );
		labelTable.put( new Integer( 2550 ), new JLabel("1.0") );
		luma.setLabelTable( labelTable );
		luma.setMajorTickSpacing(1275);
		luma.setMinorTickSpacing(255);
		luma.setPaintTicks(true);
		luma.setPaintLabels(true);
		
		luma.setVisible(true);
		
		CbCrField = new JPanel();
		CbCrField.setVisible(true);
		CbCrField.setSize(new Dimension(240,240));
		
		this.setSize(295, 270);
		this.setLocationByPlatform(true);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
		this.add(CbCrField);
		this.add(Box.createRigidArea(new Dimension(0,5)));
		this.add(luma);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	/**
	 * Scales a byte from 0 to 255 to a value between 0 and 1.
	 * 
	 * @requires 0<=i<=256
	 * 
	 * @ensures 0<=scaleByte<=1.0
	 */
	public static float scaleByte(int i) { return i/256.0f; }
	
	public float readLuma() {
		return scaleByte(luma.getValue()/10);
	}
	
	public void setController(YCbCrController god) {
		this.myGodtroller = god;
		
		luma.addChangeListener(myGodtroller);
	}
	public void setModel(YCbCrModel model) {
		this.myModel = model;
	}
	
	/*@Override
	public void paintComponents(Graphics g) {
		System.out.println("Painting.");
		super.paintComponents(g);
		
		myModel.updateCbCrField(CbCrField.getGraphics());
	}*/
	@Override
	public void paint(Graphics g) {
		System.out.println("Painting.");
		super.paintComponents(g);
		
		myModel.updateCbCrField(CbCrField.getGraphics());
	}
	
	
	public JPanel getField() {
		return CbCrField;
	}
	public JSlider getSlider() {
		return luma;
	}
}
