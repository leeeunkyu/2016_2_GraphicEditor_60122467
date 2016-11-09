package shapes;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import contant.GConstants.EDrawingType;

public class GRectangle extends GShape {
	private Rectangle rectangle;
	
	
	public GRectangle() {
		super(EDrawingType.TP);
		this.rectangle=new Rectangle(0,0,0,0);
		this.shape = this.rectangle;
	}
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		this.rectangle.setLocation(x,y);
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		this.draw(g2D);
		this.rectangle.width= x - this.rectangle.x;
		this.rectangle.height=y - this.rectangle.y;
		this.draw(g2D);		
	}
	@Override
	public void finishDrawing(int x, int y, Graphics2D g2D) {
	}
	public void continueDrawing(int x, int y, Graphics2D g2D){
		
		if(rectangle.contains(x,y)){
			
		}
	}
	@Override
	public void draw(Graphics2D g2D) {
		g2D.draw(this.rectangle);
	
		
	}
	public void drawAnchors(Graphics2D g2D){
		this.getAnchors().draw(g2D, this.rectangle.getBounds());
	}
}
