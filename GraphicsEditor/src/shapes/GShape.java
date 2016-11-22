package shapes;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import contant.GConstants.EAnchors;
import contant.GConstants;
import contant.GConstants.EDrawingType;

abstract public class GShape {
	protected Shape shape;
	private EDrawingType eDrawingType;
	private EAnchors currentEAnchor;
	private Anchors anchors;
	private int check=0;
	private boolean selected;

	public Shape getShape() {return shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	public EDrawingType geteDrawingType(){return eDrawingType;}
	public void seteDrawingType(EDrawingType eDrawingType) { this.eDrawingType = eDrawingType;	}
	public Anchors getAnchors() {return anchors;}
	public void setAnchors(Anchors anchors) {this.anchors = anchors;}
	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) { this.selected = selected; }
	
	public GShape(EDrawingType eDrawingType){
		this.eDrawingType = eDrawingType;
		this.anchors=new Anchors();
		this.selected = false;
	}
	public void draw(Graphics2D g2D) {
		if(selected){
			g2D.draw(this.shape);
			this.getAnchors().draw(g2D, this.shape.getBounds());
		
		}else{
			g2D.draw(this.shape);
		}
		
	}
	public void drawAnchors(Graphics2D g2D){
		this.selected=true;
		g2D.draw(this.shape);
		this.getAnchors().draw(g2D, this.shape.getBounds());
		
	}
	abstract public void initDrawing(int x, int y, Graphics2D g2D);
	abstract public void keepDrawing(int x, int y, Graphics2D g2D);
	abstract public void finishDrawing(int x, int y, Graphics2D g2D);
	abstract public void continueDrawing(int x, int y, Graphics2D g2D);
	public GShape clone() {
		try {
			return this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	public  GConstants.EAnchors contanins(int x, int y) {
		this.currentEAnchor = null;
		if (this.selected) {
			this.currentEAnchor = this.anchors.contains(x, y);
			if (this.currentEAnchor != null)
				return this.currentEAnchor;
		}
		if (this.shape.getBounds2D().contains(x, y)) {
			this.currentEAnchor = EAnchors.MM;
		}
		return this.currentEAnchor;
		
		
	}
	public boolean contains2(int x,int y){
		//return this.shape.intersects(x,y,x,y);
		return this.shape.getBounds2D().contains(x, y);
	}
	
}
