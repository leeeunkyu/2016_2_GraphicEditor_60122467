package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import shapes.GShape;

public class GRotator extends GTransformer {
	
	private Point centerP;
	public GRotator(GShape shape) {
		super(shape);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		this.getShape().setOrigin(x, y);
		centerP = new Point(
				(int)shape.getBounds().getCenterX(), 
				(int)shape.getBounds().getCenterY());
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2D) {
		AffineTransform saveAT = g2D.getTransform();
		g2D.translate(this.getAnchorP().getX(), this.getAnchorP().getY());
		this.getShape().draw(g2D);
		double rotationAngle = computeRotationAngle(centerP, oldP, new Point(x, y));
		affineTransform.setToRotation(Math.toRadians(rotationAngle), centerP.getX(), centerP.getY());
		getShape().setShape(affineTransform.createTransformedShape(getShape().getShape()));
		if (getShape().isSelected()) {
			getShape().getAnchors().setTransformedShape(affineTransform);
		}
		/*
		if(getShape() instanceof CGroupManager){
			CGroupManager groupChild = (CGroupManager)getShape();
			groupChildList = groupChild.getGroupList();
			for(CShapeManager childShape : groupChildList){
				double rotationGAngle = computeRotationAngle(centerP, oldP, new Point(x, y));
				affineTransform.setToRotation(Math.toRadians(rotationGAngle), centerP.getX(), centerP.getY());
				childShape.setShape(affineTransform.createTransformedShape(childShape.getShape()));
			}
		}
		*/
		this.getShape().setOrigin(x, y);
		this.getShape().draw(g2D);
		g2D.setTransform(saveAT);
		
	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void continueTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub

	}
	private double computeRotationAngle(Point startP, Point previousP, Point currentP) {
		double startAngle = Math.toDegrees(
				Math.atan2(startP.getX()-previousP.getX(), startP.getY()-previousP.getY()));
		double endAngle = Math.toDegrees(
				Math.atan2(startP.getX()-currentP.getX(), startP.getY()-currentP.getY()));
		double angle = startAngle-endAngle;
		if (angle<0) angle += 360;
		return angle;
	}

}
