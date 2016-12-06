package shapes;

import java.awt.Polygon;
import java.awt.geom.Point2D;

import contant.GConstants.EDrawingType;

public class GPolygon extends GShape {
	private Polygon polygon;
	public GPolygon() {
		super(EDrawingType.NP, new Polygon());
		this.polygon = (Polygon)this.getShape();
	}
	public void setOrigin(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	public void setPoint(int x, int y) {
		px = x;
		py = y;
	}
	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}
	public void resize(int x, int y) {
		if (this.getCurrentEAnchor() == null) {
			this.polygon.xpoints[this.polygon.npoints-1] = x;
			this.polygon.ypoints[this.polygon.npoints-1] = y;
			return;
		}else
		{
			int minX = 1000, minY = 1000, maxX = 0, maxY = 0;
			for(int i=0; i<polygon.npoints; i++){
				if(minX > polygon.xpoints[i]){
					minX = polygon.xpoints[i];
				}
				if(minY > polygon.ypoints[i]){
					minY = polygon.ypoints[i];
				}
				
				if(maxX < polygon.xpoints[i]){
					maxX = polygon.xpoints[i];
				}
				if(maxY < polygon.ypoints[i]){
					maxY = polygon.ypoints[i];
				}
				
			}
			switch (this.getCurrentEAnchor()) {
			case NN:
				System.out.println("test231");
				for(int i=0; i<polygon.npoints; i++){
					if(polygon.ypoints[i] == minY){		
						polygon.ypoints[i] += y- this.py;
						
					}else if(polygon.ypoints[i] == maxY){
			
					}
				}
				break;
			case NE:
				for(int i=0; i<polygon.npoints; i++){
					if(polygon.ypoints[i] == minY){		
						polygon.ypoints[i] += y- this.py;
						polygon.xpoints[i] += x- this.px;
					}else if(polygon.ypoints[i] == maxY){
			
					}
				}
				break;
			case NW:
				for(int i=0; i<polygon.npoints; i++){
					if(polygon.ypoints[i] == minY){		
						polygon.ypoints[i] += y- this.py;
						polygon.xpoints[i] += x- this.px;
					}else if(polygon.ypoints[i] == maxY){
			
					}
				}
				break;
			case SS:
				for(int i=0; i<polygon.npoints; i++){
					if(polygon.ypoints[i] == minY){		
					}else if(polygon.ypoints[i] == maxY){
						polygon.ypoints[i] += y- this.py;
					//	System.out.println("test231");
					}
				}
				break;
			case SE:
				for(int i=0; i<polygon.npoints; i++){
					if(polygon.xpoints[i] == minX){
					}else if(polygon.xpoints[i] == maxX){
						polygon.xpoints[i] += x- this.px;
						polygon.ypoints[i] += y- this.py;
					}
				}
				break;
			case SW:
				for(int i=0; i<polygon.npoints; i++){
					if(polygon.xpoints[i] == minX){		
						polygon.xpoints[i] += x- this.px;
						polygon.ypoints[i] += y- this.py;
					}else if(polygon.xpoints[i] == maxX){
					}
				}
				break;
			case EE:
				for(int i=0; i<polygon.npoints; i++){
					if(polygon.xpoints[i] == minX){
					}else if(polygon.xpoints[i] == maxX){
						polygon.xpoints[i] += x- this.px;		
					}
				}
				break;
			case WW:
				for(int i=0; i<polygon.npoints; i++){
					if(polygon.xpoints[i] == minX){		
						polygon.xpoints[i] += x- this.px;
					}else if(polygon.xpoints[i] == maxX){
					}
				}
				break;
			default:
				break;
			}
			// redraw shape
			this.polygon.invalidate();
			this.setPoint(x, y);

		}
	}
	public void move(int x, int y) {
		for (int i=0; i<this.polygon.npoints; i++) {
			this.polygon.xpoints[i] += x - px;
			this.polygon.ypoints[i] += y - py;
		}
		this.polygon.invalidate();
		px = x;
		py = y;
	}
	@Override
	public void rotateCoordinate(double theta, Point2D rotaterAnchor) {
		// TODO Auto-generated method stub
		affineTransform.setToRotation(theta, rotaterAnchor.getX(), rotaterAnchor.getY());
		shape = affineTransform.createTransformedShape(shape);
	}
}
