package shapes;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Shape;

import contant.GConstants.EDrawingType;

abstract public class GShape {
	Cursor cursor;
	protected Shape shape;
	private EDrawingType eDrawingType;
	public EDrawingType geteDrawingType(){return eDrawingType;}
	private Anchors anchors;
	public Anchors getAnchors() {return anchors;}
	public void setAnchors(Anchors anchors) {this.anchors = anchors;}
	
	public void seteDrawingType(EDrawingType eDrawingType) {
		this.eDrawingType = eDrawingType;
	}
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
		return shape.contains(x, y);
		
		// 지정된 좌표가 Shape 의 경계내에 있을지 어떨지를 판정 합니다.
	}
	
}
