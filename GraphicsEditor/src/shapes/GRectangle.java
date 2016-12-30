package shapes;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import contant.GConstants.EDrawingType;
import frame.GDrawingPanel;

public class GRectangle extends GShape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rectangle rectangle;
	private GDrawingPanel drawingPanel;
	public GRectangle() {
		super(EDrawingType.TP, new Rectangle(0, 0, 0, 0));
		this.rectangle = (Rectangle)this.getShape();
		this.drawingPanel=new GDrawingPanel();
	}
	public void setOrigin(int x, int y) {
		this.rectangle.setLocation(x, y);
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
			this.rectangle.width = (int) (x - this.rectangle.x);
			this.rectangle.height = (int) (y - this.rectangle.y);
			return;
		}
		affineTransform.setToScale(x, y);
		System.out.println("리사이즈 크기"+x+"  "+y);
		shape = affineTransform.createTransformedShape(shape);
		this.setPoint(x, y);
	}
	@Override
	public void rotateCoordinate(double theta, Point2D rotaterAnchor){
		affineTransform.setToRotation(theta, rotaterAnchor.getX(), rotaterAnchor.getY());
		shape = affineTransform.createTransformedShape(shape);
	}

	
	
	
}
