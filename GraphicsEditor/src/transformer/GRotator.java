package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import shapes.GShape;

public class GRotator extends GTransformer {
	private Point2D.Double ROrigin;
	private double theta;
	private Point centerP;
	public GRotator(GShape shape) {
		super(shape);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		ROrigin = new Point2D.Double(shape.getBounds().getCenterX(), shape.getBounds().getCenterY());
		theta = Math.atan2(ROrigin.y - y, ROrigin.x -x);
		this.getShape().setPoint(x, y);
		this.getShape().draw(g2d);
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		double theta2 = theta - Math.atan2(ROrigin.y - y, x - ROrigin.x);
		shape.draw(g2d);
		shape.rotateCoordinate(theta2, ROrigin);
		shape.draw(g2d);
		theta = Math.atan2(ROrigin.y - y, x - ROrigin.x);

	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void continueTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub

	}
	
}
