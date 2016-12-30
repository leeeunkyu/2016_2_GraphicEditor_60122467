package shapes;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import contant.GConstants.EDrawingType;

public class GLine extends GShape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Line2D line;
	public GLine() {
		super(EDrawingType.TP, new Line2D.Double(0, 0, 0, 0));
		this.line = (Line2D.Double)this.getShape();
	}
	public void setOrigin(int x, int y) {
		line.setLine(x, y, x, y);
	}
	public void setPoint(double x, double y) {
		this.px = x;
		this.py = y;
	}
	public void addPoint(int x, int y) {
	}
	public void resize(double x, double y) {
		//this.line.setLine(this.line.getX1(), this.line.getY1(), x, y);
		if (this.getCurrentEAnchor() == null) {
			this.line.setLine(this.line.getX1(), this.line.getY1(), x, y);
			return;
		}
		affineTransform.setToScale(x, y);
		System.out.println("리사이즈 크기"+x+"  "+y);
		shape = affineTransform.createTransformedShape(shape);
//		switch (this.getCurrentEAnchor()) {
//		case NN:
//			this.line.setLine(this.line.getX1(), this.line.getY1()+y - this.py, this.line.getX2(), this.line.getY2());
//			break;
//		case NE:
//			this.line.setLine(this.line.getX1(), this.line.getY1()+y - this.py,  this.line.getX2()+x- this.px, this.line.getY2());
//			break;
//		case NW:
//			this.line.setLine( this.line.getX1()+x- this.px, this.line.getY1()+y - this.py, this.line.getX2(), this.line.getY2());
//			break;
//		case SS:
//			this.line.setLine(this.line.getX1(), this.line.getY1(), this.line.getX2(), this.line.getY2()+y - this.py);
//			break;
//		case SE:
//			this.line.setLine(this.line.getX1(), this.line.getY1(), this.line.getX2()+x- this.px, this.line.getY2()+y - this.py);
//			break;
//		case SW:
//			this.line.setLine(this.line.getX1()+x- this.px, this.line.getY1(), this.line.getX2(), this.line.getY2()+y - this.py);
//			break;
//		case EE:
//			this.line.setLine(this.line.getX1(), this.line.getY1(), this.line.getX2()+x- this.px, this.line.getY2());
//			break;
//		case WW:
//			this.line.setLine(this.line.getX1()+x- this.px, this.line.getY1(), this.line.getX2(), this.line.getY2());
//			break;
//		default:
//			break;
//		}
		this.setPoint(x, y);
	}
	public void move(double x, double y) {
		affineTransform.setToTranslation( x - px, y - py);
		shape = affineTransform.createTransformedShape(shape);
		//this.line.setLine(this.line.getX1()+x-px,this.line.getY1()+y-py,this.line.getX2()+x-px,this.line.getY2()+y-py);
		this.setPoint(x, y);
	}
	@Override
	public void rotateCoordinate(double theta, Point2D rotaterAnchor) {
		// TODO Auto-generated method stub
		affineTransform.setToRotation(theta, rotaterAnchor.getX(), rotaterAnchor.getY());
		shape = affineTransform.createTransformedShape(shape);
	}
}
