package shapes;

import java.awt.Polygon;
import java.awt.geom.Point2D;

import contant.GConstants.EDrawingType;

public class GPolygon extends GShape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Polygon polygon;
	public GPolygon() {
		super(EDrawingType.NP, new Polygon());
		this.polygon = (Polygon)this.getShape();
	}
	public void setOrigin(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	public void setPoint(double x, double y) {
		px = x;
		py = y;
	}
	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}
	public void resize(double x, double y) {
		if (this.getCurrentEAnchor() == null) {
			this.polygon.xpoints[this.polygon.npoints-1] = (int)x;
			this.polygon.ypoints[this.polygon.npoints-1] = (int)y;
			return;
		}
		affineTransform.setToScale(x, y);
		System.out.println("리사이즈 크기"+x+"  "+y);
		shape = affineTransform.createTransformedShape(shape);
//		}else
//		{
//			int minX = 1000, minY = 1000, maxX = 0, maxY = 0;
//			for(int i=0; i<polygon.npoints; i++){
//				if(minX > polygon.xpoints[i]){
//					minX = polygon.xpoints[i];
//				}
//				if(minY > polygon.ypoints[i]){
//					minY = polygon.ypoints[i];
//				}
//				
//				if(maxX < polygon.xpoints[i]){
//					maxX = polygon.xpoints[i];
//				}
//				if(maxY < polygon.ypoints[i]){
//					maxY = polygon.ypoints[i];
//				}
//				
//			}
//			switch (this.getCurrentEAnchor()) {
//			case NN:
//				System.out.println("test231");
//				for(int i=0; i<polygon.npoints; i++){
//					if(polygon.ypoints[i] == minY){		
//						polygon.ypoints[i] += y- this.py;
//					}else if(polygon.ypoints[i] == maxY){
//					}else{
//						polygon.ypoints[i] += (y- this.py)/3;
//					}
//				}
//				break;
//			case NE:
//				for(int i=0; i<polygon.npoints; i++){
//					if(polygon.ypoints[i] == minY){		
//						polygon.ypoints[i] += y- this.py;
//						polygon.xpoints[i] += x- this.px;
//					}else if(polygon.ypoints[i] == maxY){
//					}else{
//						polygon.ypoints[i] += (y- this.py)/3;
//						polygon.xpoints[i] += (x- this.px)/3;
//					}
//				}
//				break;
//			case NW:
//				for(int i=0; i<polygon.npoints; i++){
//					if(polygon.ypoints[i] == minY){		
//						polygon.ypoints[i] += y- this.py;
//						polygon.xpoints[i] += x- this.px;
//					}else if(polygon.ypoints[i] == maxY){
//			
//					}else{
//						polygon.ypoints[i] += (y- this.py)/3;
//						polygon.xpoints[i] += (x- this.px)/3;
//					}
//				}
//				break;
//			case SS:
//				for(int i=0; i<polygon.npoints; i++){
//					if(polygon.ypoints[i] == minY){		
//					}else if(polygon.ypoints[i] == maxY){
//						polygon.ypoints[i] += (y- this.py);
//					//	System.out.println("test231");
//					}else{
//						polygon.ypoints[i] += (y- this.py)/3;
//					}
//				}
//				break;
//			case SE:
//				for(int i=0; i<polygon.npoints; i++){
//					if(polygon.xpoints[i] == minX){
//					}else if(polygon.xpoints[i] == maxX){
//						polygon.xpoints[i] += (x- this.px);
//						polygon.ypoints[i] += (y- this.py);
//					}else{
//						polygon.xpoints[i] += (x- this.px)/3;
//						polygon.ypoints[i] += (y- this.py)/3;
//					}
//				}
//				break;
//			case SW:
//				for(int i=0; i<polygon.npoints; i++){
//					if(polygon.xpoints[i] == minX){		
//						polygon.xpoints[i] += x- this.px;
//						polygon.ypoints[i] += y- this.py;
//					}else if(polygon.xpoints[i] == maxX){
//					}else{
//						polygon.xpoints[i] += (x- this.px)/3;
//						polygon.ypoints[i] += (y- this.py)/3;
//					}
//				}
//				break;
//			case EE:
//				for(int i=0; i<polygon.npoints; i++){
//					if(polygon.xpoints[i] == minX){
//					}else if(polygon.xpoints[i] == maxX){
//						polygon.xpoints[i] += x- this.px;		
//					}else{
//						polygon.xpoints[i] += (x- this.px)/3;	
//					}
//				}
//				break;
//			case WW:
//				for(int i=0; i<polygon.npoints; i++){
//					if(polygon.xpoints[i] == minX){		
//						polygon.xpoints[i] += x- this.px;
//					}else if(polygon.xpoints[i] == maxX){
//					}else{
//						polygon.xpoints[i] += (x- this.px)/3;
//					}
//				}
//				break;
//			default:
//				break;
//			}
			// redraw shape
			this.polygon.invalidate();
			this.setPoint(x, y);

		}
	
	public void move(double x, double y) {
//		for (int i=0; i<this.polygon.npoints; i++) {
//			this.polygon.xpoints[i] += x - px;
//			this.polygon.ypoints[i] += y - py;
//			System.out.println(this.polygon.xpoints[i]+"x");
//			System.out.println(this.polygon.ypoints[i]+"y");
//		}
//		this.polygon.invalidate();
//		px = x;
//		py = y;
		affineTransform.setToTranslation( x - px, y - py);
		shape = affineTransform.createTransformedShape(shape);
		px=x;
		py=y;
	}
	@Override
	public void rotateCoordinate(double theta, Point2D rotaterAnchor) {
		// TODO Auto-generated method stub
		affineTransform.setToRotation(theta, rotaterAnchor.getX(), rotaterAnchor.getY());
		shape = affineTransform.createTransformedShape(shape);
	}
}
