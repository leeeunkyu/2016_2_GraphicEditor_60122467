package shapes;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import contant.GConstants.EDrawingType;

public class GEllipse extends GShape {
	private Rectangle rectangle;
	private Ellipse2D.Double ellipse;
	double w,h=0;
	public GEllipse() {
		super(EDrawingType.TP);
		this.ellipse=new Ellipse2D.Double(0,0,0,0);
		
		this.shape = this.ellipse;
	}
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		this.ellipse.setFrame(x, y, 0, 0);
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		this.draw(g2D);
		this.ellipse.width=x-this.ellipse.x;
		this.ellipse.height=y-this.ellipse.y;
		this.draw(g2D);		
	}
	@Override
	public void finishDrawing(int x, int y, Graphics2D g2D) {
	}
	public void continueDrawing(int x, int y, Graphics2D g2D){
		
	}
	@Override
	public void draw(Graphics2D g2D) {
		// TODO Auto-generated method stub
		g2D.draw(this.ellipse);
		
	}
	public void drawAnchors(Graphics2D g2D){
		this.getAnchors().draw(g2D, this.ellipse.getBounds());
	}

}
