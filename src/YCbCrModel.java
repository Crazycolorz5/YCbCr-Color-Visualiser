import java.awt.Color;
import java.awt.Graphics;

public class YCbCrModel {
	private float luma;
	private float cb;
	private float cr;
	
	public YCbCrModel() {
		setLuma(0);
		setCb(0);
		setCr(0);
	}

	public float getLuma() {
		return luma;
	}

	public void setLuma(float luma) {
		this.luma = luma;
		System.out.println("Luma set to: " + luma);
	}

	public float getCb() {
		return cb;
	}

	public void setCb(float cb) {
		this.cb = cb;
	}

	public float getCr() {
		return cr;
	}

	public void setCr(float cr) {
		this.cr = cr;
	}
	
	public Graphics updateCbCrField(Graphics g) {
		Graphics CbCrField = g;
		
		final int xMin = 16; //Todo: calculate based on luma
		final int xMax = 240;
		final int yMin = 16;
		final int yMax = 240;
		float localLuma = 256*luma-16;
		for(int x = xMin; x <= xMax; x++) {
			for(int y = yMin; y <= yMax; y++) {
				float localCr = y-128;///256;
				float localCb = x-128;///256;
				
				/*
				int sr = (int)(298.082*luma 			          + 408.583*localCr/256 - 222.921);
				int sg = (int)(298.082*luma - 100.291*localCb/256 + 208.120*localCr/256 + 135.576);
				int sb = (int)(298.082*luma + 516.412*localCb/256 						- 276.836);
				*/
				
				int sr = (int)(1.164*localLuma + 1.793f*localCr);
				int sg = (int)(1.164*localLuma - 0.213f*localCb - 0.533f*localCr);
				int sb = (int)(1.164*localLuma + 2.112f*localCb);
				
				if(validRGBCode(sr)&&validRGBCode(sg)&&validRGBCode(sb)) {
					//System.out.println(sr +" "+sg+" "+sb);
					CbCrField.setColor(new Color(sr, sg, sb));					
					CbCrField.drawLine(x-xMin, yMax-y, x-xMin, yMax-y); //draws a single pixel. 
				}
			}
		}
		
		return g;
	}
	private static boolean validRGBCode(int c) {
		return 0<=c&&c<=255;
	}
}
