package shapes;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import contant.GConstants.EDrawingType;

abstract public class GShape {
	protected Shape shape;
	private EDrawingType eDrawingType;
	private Anchors anchors;
	
	public Shape getShape() {return shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	public EDrawingType geteDrawingType(){return eDrawingType;}
	public void seteDrawingType(EDrawingType eDrawingType) { this.eDrawingType = eDrawingType;	}
	public Anchors getAnchors() {return anchors;}
	public void setAnchors(Anchors anchors) {this.anchors = anchors;}
	
	
	public GShape(EDrawingType eDrawingType){
		this.eDrawingType = eDrawingType;
		this.anchors=new Anchors();
	}
	abstract public void draw(Graphics2D g2D);
	abstract public void initDrawing(int x, int y, Graphics2D g2D);
	abstract public void keepDrawing(int x, int y, Graphics2D g2D);
	abstract public void finishDrawing(int x, int y, Graphics2D g2D);
	abstract public void continueDrawing(int x, int y, Graphics2D g2D);
	abstract public void drawAnchors(Graphics2D g2D);
	public GShape clone() {
		try {
			return this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean contanins(int x, int y) {
//		System.out.println(x+"  "+y);
//		System.out.println(shape.getClass().getName());
//		System.out.println(shape.contains(x, y));
		return shape.contains(x, y);
		
		// 지정된 좌표가 Shape 의 경계내에 있을지 어떨지를 판정 합니다.
	}
	
}
