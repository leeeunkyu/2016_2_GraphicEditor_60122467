package shapes;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
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
	private boolean selected;
	private Point p0, p1;
	
	public Point getP0() { return p0; }
	public void setP0(int x, int y) { this.p0.x = x; this.p0.y = y; }
	public Point getP1() { 	return p1;}
	public void setP1(int x, int y) {this.p1.x = x; this.p1.y = y; }
	public EAnchors getCurrentEAnchor() { return currentEAnchor; }
	public void setCurrentEAnchor(EAnchors currentEAnchor) { this.currentEAnchor = currentEAnchor;}
	public Shape getShape() {return shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	public EDrawingType geteDrawingType(){return eDrawingType;}
	public void seteDrawingType(EDrawingType eDrawingType) { this.eDrawingType = eDrawingType;	}
	public Anchors getAnchors() {return anchors;}
	public void setAnchors(Anchors anchors) {this.anchors = anchors;}
	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) { this.selected = selected; }
	
	public GShape(EDrawingType eDrawingType,Shape shape){
		this.eDrawingType = eDrawingType;
		this.anchors=new Anchors();
		this.selected = false;
		this.shape = shape;
		this.currentEAnchor = null;
		this.p0 = new Point(0, 0);
		this.p1 = new Point(0, 0);
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
		System.out.println("ºÒ¸®³ª?");
		g2D.draw(this.shape);
		this.getAnchors().draw(g2D, this.shape.getBounds());
	}
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
		return this.shape.getBounds2D().contains(x, y);
	}
	abstract public void initDrawing(int x, int y, Graphics2D g2D);
	abstract public void keepDrawing(int x, int y, Graphics2D g2D);
	abstract public void finishDrawing(int x, int y, Graphics2D g2D);
	abstract public void continueDrawing(int x, int y, Graphics2D g2D);
	
	abstract public void initTransforming(int x, int y, Graphics2D g2d);
	abstract public void keepTransforming(int x, int y, Graphics2D g2d);
	abstract public void finishTransforming(int x, int y, Graphics2D g2d);
	
	abstract public void initResizing(int x, int y, Graphics2D g2d);
	abstract public void keepResizing(int x, int y, Graphics2D g2d);
	abstract public void finishResizing(int x, int y, Graphics2D g2d);
}
