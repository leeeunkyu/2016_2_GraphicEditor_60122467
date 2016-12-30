package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.Vector;

import contant.GConstants.EDrawingType;

public class GGroupManager extends GShape{
	private Vector<GShape> groupList;

	public GGroupManager() {
		super(EDrawingType.TP, new Rectangle(0, 0, 0, 0));
		// TODO Auto-generated constructor stub
		groupList = new Vector<GShape>();
		shape = (Rectangle)this.getShape();
	}
	public void add(GShape newShape) {
		groupList.add(0, newShape);
		if (groupList.size() == 1) {
			this.shape = newShape.getBounds();
		} else {
			this.shape = this.shape.getBounds().createUnion(
					newShape.getBounds());
		}
	}
	public Vector<GShape> getGroupList() {
		return groupList;
	}
	public void draw(Graphics2D g2D) {
	//	Graphics2D g2D = (Graphics2D) g;
		for (GShape shape : groupList) {
			shape.draw(g2D);
		}
		if (this.isSelected()) {
			g2D.draw(this.shape);
			this.setSelected(true);
			super.draw(g2D);
		}
	}
	
	public void setOrigin(int x, int y) {
		((Rectangle) this.shape).setLocation(x, y);
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
			((Rectangle) this.shape).width = (int) (x - ((Rectangle) this.shape).x);
			((Rectangle) this.shape).height = (int) (y - ((Rectangle) this.shape).y);
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
