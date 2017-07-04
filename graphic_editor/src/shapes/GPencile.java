package shapes;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import contant.GConstants.EDrawingType;

public class GPencile extends GShape {
	private Line2D line;
	public GPencile() {
		super(EDrawingType.TP, new Line2D.Double(0, 0, 0, 0));
		this.line = (Line2D.Double)this.getShape();
	}
	public void setOrigin(int x, int y) {
		//line.setLine(x, y, x, y);
		this.px = x;
		this.py = y;
	}
	public void setPoint(double x, double y) {
		this.px = x;
		this.py = y;
	}
	public void addPoint(int x, int y) {
	}
	public void resize(double x, double y) {
		if (this.getCurrentEAnchor() == null) {
			this.line.setLine(px,py, x, y);
			return;
		}
		affineTransform.setToScale(x, y);
		System.out.println("리사이즈 크기"+x+"  "+y);
		shape = affineTransform.createTransformedShape(shape);

		this.setPoint(x, y);
	}
	public void move(double x, double y) {
		affineTransform.setToTranslation( x - px, y - py);
		shape = affineTransform.createTransformedShape(shape);
		this.setPoint(x, y);
	}
	@Override
	public void rotateCoordinate(double theta, Point2D rotaterAnchor) {
		// TODO Auto-generated method stub
		affineTransform.setToRotation(theta, rotaterAnchor.getX(), rotaterAnchor.getY());
		shape = affineTransform.createTransformedShape(shape);
	}
}
