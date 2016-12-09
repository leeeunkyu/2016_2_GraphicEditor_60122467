package shapes;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

import contant.GConstants.EDrawingType;

public class GHeart extends GShape{
	private Polygon heart;

	public GHeart() {
		super(EDrawingType.TP, new  Polygon());
		this.heart = (Polygon)this.getShape();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setOrigin(int x, int y) {
		// TODO Auto-generated method stub
		//this.roundrect
	}

	@Override
	public void resize(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotateCoordinate(double theta, Point2D rotaterAnchor) {
		// TODO Auto-generated method stub
		
	}

}
