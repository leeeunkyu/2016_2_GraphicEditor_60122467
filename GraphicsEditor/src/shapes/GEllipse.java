package shapes;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import contant.GConstants.EDrawingType;
public class GEllipse extends GShape {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ellipse2D.Double ellipse;

	public GEllipse() {
		super(EDrawingType.TP,new Ellipse2D.Double(0,0,0,0));
		this.ellipse=(Ellipse2D.Double)this.getShape();
	
	}
	public void setOrigin(int x, int y) {
		this.ellipse.setFrame(x, y,0,0);
	}
	public void setPoint(double x, double y) {
		this.px = x;
		this.py = y;
	}
	public void move(double x, double y) {
		affineTransform.setToTranslation( x - px, y - py);
		shape = affineTransform.createTransformedShape(shape);
		this.setPoint(x, y);
	}
	public void addPoint(int x, int y) {
	}

	public void resize(double x, double y) {
		if (this.getCurrentEAnchor() == null) {
			this.ellipse.width = x - this.ellipse.x;
			this.ellipse.height = y - this.ellipse.y;
			return;
		}
		affineTransform.setToScale(x, y);
		System.out.println("리사이즈 크기"+x+"  "+y);
		shape = affineTransform.createTransformedShape(shape);
//		switch (this.getCurrentEAnchor()) {
//		case NN:
//			this.ellipse.y += y - this.py;
//			this.ellipse.height -=  y - this.py;
//			break;
//		case NE:
//			this.ellipse.y += y - this.py;
//			this.ellipse.height -=  y - this.py;
//			this.ellipse.width += x - this.px;
//			break;
//		case NW:
//			this.ellipse.y += y - this.py;
//			this.ellipse.height -=  y - this.py;
//			this.ellipse.x += x - this.px;
//			this.ellipse.width -=  x - this.px;
//			break;
//		case SS:
//			this.ellipse.height += (+y -this.py);
//			break;
//		case SE:
//			this.ellipse.width += x - this.px;
//			this.ellipse.height += y - this.py;
//			System.out.println(x+"  "+y+"   "+this.px+"   "+this.py);
//			break;
//		case SW:
//			this.ellipse.height += (+y -this.py);
//			this.ellipse.x += x - this.px;
//			this.ellipse.width -=  x - this.px;
//			break;
//		case EE:
//			this.ellipse.width += x - this.px;
//			System.out.println(this.ellipse.width);
//			break;
//		case WW:
//			this.ellipse.x += x - this.px;
//			this.ellipse.width -=  x - this.px;
//			break;
//		}
		this.setPoint(x, y);
	}
	@Override
	public void rotateCoordinate(double theta, Point2D rotaterAnchor) {
		// TODO Auto-generated method stub
		affineTransform.setToRotation(theta, rotaterAnchor.getX(), rotaterAnchor.getY());
		shape = affineTransform.createTransformedShape(shape);
	}
}