package shapes;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import contant.GConstants.EDrawingType;
import frame.GDrawingPanel;

public class GRectangle extends GShape {
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
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}
	public void move(int x, int y) {
		this.rectangle.x += x - px;
		this.rectangle.y += y - py;
		this.setPoint(x, y);
	}
	public void addPoint(int x, int y) {
	}

	public void resize(int x, int y) {
		if (this.getCurrentEAnchor() == null) {
			this.rectangle.width = x - this.rectangle.x;
			this.rectangle.height = y - this.rectangle.y;
			return;
		}
		switch (this.getCurrentEAnchor()) {
		case NN:
			this.rectangle.y += y - this.py;
			this.rectangle.height -=  y - this.py;
			break;
		case NE:
			this.rectangle.y += y - this.py;
			this.rectangle.height -=  y - this.py;
			this.rectangle.width += x - this.px;
			break;
		case NW:
			this.rectangle.y += y - this.py;
			this.rectangle.height -=  y - this.py;
			this.rectangle.x += x - this.px;
			this.rectangle.width -=  x - this.px;
			break;
		case SS:
			this.rectangle.height += (+y -this.py);
			break;
		case SE:
			this.rectangle.width += x - this.px;
			this.rectangle.height += y - this.py;
			System.out.println(x+"  "+y+"   "+this.px+"   "+this.py);
			break;
		case SW:
			this.rectangle.height += (+y -this.py);
			this.rectangle.x += x - this.px;
			this.rectangle.width -=  x - this.px;
			break;
		case EE:
			this.rectangle.width += x - this.px;
			System.out.println(this.rectangle.width);
			break;
		case WW:
			this.rectangle.x += x - this.px;
			this.rectangle.width -=  x - this.px;
			break;
		}
		this.setPoint(x, y);
	}
	@Override
	public void rotateCoordinate(double theta, Point2D rotaterAnchor){
		affineTransform.setToRotation(theta, rotaterAnchor.getX(), rotaterAnchor.getY());
		shape = affineTransform.createTransformedShape(shape);
	}
	
	
	
}
