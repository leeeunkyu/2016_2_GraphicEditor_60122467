package shapes;

import java.awt.Graphics2D;

import contant.GConstants.EDrawingType;

public class GLine extends GShape {

	private int x, y, w, h;
	public GLine() {
		super(EDrawingType.TP);
		this.x = 0;
		this.y = 0;
		this.w = 0;
		this.h = 0;
	}
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		this.x = x;
		this.y = y;		
		this.w = x;
		this.h = y;
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		g2D.drawLine(this.x, this.y, this.w, this.h);
		this.w = x;
		this.h = y;
		g2D.drawLine(this.x, this.y, this.w, this.h);		
	}
	@Override
	public void finishDrawing(int x, int y, Graphics2D g2D) {
	}
	public void continueDrawing(int x, int y, Graphics2D g2D){
		
	}
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.drawLine(this.x, this.y, this.w - this.x, this.h - this.y);
		
	}

}
