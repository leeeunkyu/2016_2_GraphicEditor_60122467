package shapes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.Serializable;

import contant.GConstants.EAnchors;
import contant.GConstants;
import contant.GConstants.EDrawingType;

abstract public class GShape implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Shape shape;
	private EDrawingType eDrawingType;
	private EAnchors currentEAnchor;
	private Anchors anchors;
	private boolean selected;
	protected int px,py;
	protected AffineTransform affineTransform;
	private Color lineColor, fillColor;
	public EAnchors getCurrentEAnchor() { return currentEAnchor; }
	public Shape getShape() {return shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	
	public Color getLineColor() {
		return lineColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public Anchors getAnchors() {
		return anchors;
	}
	public void setAnchors(Anchors anchors) {
		this.anchors = anchors;
	}
	public EDrawingType geteDrawingType(){return eDrawingType;}
	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) { this.selected = selected; }
	
	public GShape(EDrawingType eDrawingType,Shape shape){
		this.eDrawingType = eDrawingType;
		this.anchors=new Anchors();
		this.selected = false;
		this.shape = shape;
		this.currentEAnchor = null;
		this.px=0;
		this.py=0;
		
		affineTransform = new AffineTransform();

	}
	public void setFillColor(Color fillColor) {
		// TODO Auto-generated method stub
		System.out.println(fillColor+"222");
		this.fillColor = fillColor;
	}
	public void setLineColor(Color lineColor) {
		// TODO Auto-generated method stub
		this.lineColor = lineColor;

	}
	public Rectangle getBounds() {return shape.getBounds();}	
	public void draw(Graphics2D g2D) {
		if (fillColor != null) {
			g2D.setColor(fillColor);
			g2D.fill(shape);
		}
		if (lineColor != null) {
			g2D.setColor(lineColor);
			//g2D.draw(shape);
		}
		if(selected){
			g2D.draw(this.shape);
			this.anchors.draw(g2D, this.shape.getBounds());
		}else{
			g2D.draw(this.shape);
			System.out.println(fillColor+"777");
		}
	}
	public void drawAnchors(Graphics2D g2D){
		if (fillColor != null) {
			g2D.setColor(fillColor);
			g2D.fill(shape);
		}
		if (lineColor != null) {
			g2D.setColor(lineColor);
		//	g2D.draw(shape);
		}
		System.out.println(fillColor+"444");

		this.selected=true;
		System.out.println("ºÒ¸®³ª?");
		g2D.draw(this.shape);
		this.anchors.draw(g2D, this.shape.getBounds());
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
	abstract public void setOrigin(int x, int y);
	abstract public void resize(int x, int y);
	abstract public void addPoint(int x, int y); 
	abstract public void move(int x, int y);
	abstract public void setPoint(int x, int y);
	abstract public void rotateCoordinate(double theta, Point2D rotaterAnchor);

		

	
}
