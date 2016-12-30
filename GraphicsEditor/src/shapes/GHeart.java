package shapes;

import java.awt.Polygon;
import java.awt.geom.Point2D;
import contant.GConstants.EDrawingType;

public class GHeart extends GShape{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Polygon heart;

	public GHeart() {
		super(EDrawingType.TP, new  Polygon());
		this.heart = (Polygon)this.getShape();
		// TODO Auto-generated constructor stub		
	}
	@Override
	public void setPoint(double x, double y) {
		// TODO Auto-generated method stub
		px = x;
		py = y;
	}


	@Override
	public void setOrigin(int x, int y) {
		// TODO Auto-generated method stub
		 int w = (int) ( x- px);
		 int h = (int) (y - py);
		 ((Polygon)shape).addPoint(x, y+h/3);
		 ((Polygon)shape).addPoint(x, y+h/5);
		 System.out.println(x+"  "+(y+h/4));
	     ((Polygon)shape).addPoint(x+w/5, y);
	     ((Polygon)shape).addPoint(x+w/3, y);
	     System.out.println(x+w/4+"  "+y);
	     ((Polygon)shape).addPoint(x+w/2, y+h/4);
	     ((Polygon)shape).addPoint(x+w/2, y+h/4);
	     System.out.println(x+w/2+"  "+(y+h/4));
	     ((Polygon)shape).addPoint(x+w/5*3, y);
	     ((Polygon)shape).addPoint(x+w/4*3, y);
	     System.out.println(x+w/4*3+"  "+y);
	     ((Polygon)shape).addPoint(x+w, y+h/5);
	     ((Polygon)shape).addPoint(x+w, y+h/3);
	     System.out.println(x+w+"  "+(y+h/4));
	     ((Polygon)shape).addPoint(x+w/2, y+h);
	     System.out.println(x+w+"  "+(y+h));
	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub
		//this.heart.addPoint(x, y);
	}
	public void resize(double x, double y) {
		if (this.getCurrentEAnchor() == null) {
//			this.heart.xpoints[this.heart.npoints-1] = (int)x;
//			this.heart.ypoints[this.heart.npoints-1] = (int)y;
			return;
		}
		affineTransform.setToScale(x, y);
		System.out.println("리사이즈 크기"+x+"  "+y);
		shape = affineTransform.createTransformedShape(shape);
		
//		}else
//		{
//			int minX = 1000, minY = 1000, maxX = 0, maxY = 0;
//			for(int i=0; i<heart.npoints; i++){
//				if(minX > heart.xpoints[i]){
//					minX = heart.xpoints[i];
//				}
//				if(minY > heart.ypoints[i]){
//					minY = heart.ypoints[i];
//				}
//				
//				if(maxX < heart.xpoints[i]){
//					maxX = heart.xpoints[i];
//				}
//				if(maxY < heart.ypoints[i]){
//					maxY = heart.ypoints[i];
//				}
//				
//			}
//			switch (this.getCurrentEAnchor()) {
//			case NN:
//				System.out.println("test231");
//				for(int i=0; i<heart.npoints; i++){
//					if(heart.ypoints[i] == minY){		
//						heart.ypoints[i] += y- this.py;
//					}else if(heart.ypoints[i] == maxY){
//					}else{
//						heart.ypoints[i] += (y- this.py)/3;
//					}
//				}
//				break;
//			case NE:
//				for(int i=0; i<heart.npoints; i++){
//					if(heart.ypoints[i] == minY){		
//						heart.ypoints[i] += y- this.py;
//						heart.xpoints[i] += x- this.px;
//					}else if(heart.ypoints[i] == maxY){
//					}else{
//						heart.ypoints[i] += (y- this.py)/3;
//						heart.xpoints[i] += (x- this.px)/3;
//					}
//				}
//				break;
//			case NW:
//				for(int i=0; i<heart.npoints; i++){
//					if(heart.ypoints[i] == minY){		
//						heart.ypoints[i] += y- this.py;
//						heart.xpoints[i] += x- this.px;
//					}else if(heart.ypoints[i] == maxY){
//			
//					}else{
//						heart.ypoints[i] += (y- this.py)/3;
//						heart.xpoints[i] += (x- this.px)/3;
//					}
//				}
//				break;
//			case SS:
//				for(int i=0; i<heart.npoints; i++){
//					if(heart.ypoints[i] == minY){		
//					}else if(heart.ypoints[i] == maxY){
//						heart.ypoints[i] += (y- this.py);
//					//	System.out.println("test231");
//					}else{
//						heart.ypoints[i] += (y- this.py)/3;
//					}
//				}
//				break;
//			case SE:
//				for(int i=0; i<heart.npoints; i++){
//					if(heart.xpoints[i] == minX){
//					}else if(heart.xpoints[i] == maxX){
//						heart.xpoints[i] += (x- this.px);
//						heart.ypoints[i] += (y- this.py);
//					}else{
//						heart.xpoints[i] += (x- this.px)/3;
//						heart.ypoints[i] += (y- this.py)/3;
//					}
//				}
//				break;
//			case SW:
//				for(int i=0; i<heart.npoints; i++){
//					if(heart.xpoints[i] == minX){		
//						heart.xpoints[i] += x- this.px;
//						heart.ypoints[i] += y- this.py;
//					}else if(heart.xpoints[i] == maxX){
//					}else{
//						heart.xpoints[i] += (x- this.px)/3;
//						heart.ypoints[i] += (y- this.py)/3;
//					}
//				}
//				break;
//			case EE:
//				for(int i=0; i<heart.npoints; i++){
//					if(heart.xpoints[i] == minX){
//					}else if(heart.xpoints[i] == maxX){
//						heart.xpoints[i] += x- this.px;		
//					}else{
//						heart.xpoints[i] += (x- this.px)/3;	
//					}
//				}
//				break;
//			case WW:
//				for(int i=0; i<heart.npoints; i++){
//					if(heart.xpoints[i] == minX){		
//						heart.xpoints[i] += x- this.px;
//					}else if(heart.xpoints[i] == maxX){
//					}else{
//						heart.xpoints[i] += (x- this.px)/3;
//					}
//				}
//				break;
//			default:
//				break;
//			}
			// redraw shape
			this.heart.invalidate();
			this.setPoint(x, y);

		
	}
	public void move(double x, double y) {
//		for (int i=0; i<this.heart.npoints; i++) {
//			this.heart.xpoints[i] += x - px;
//			this.heart.ypoints[i] += y - py;
//			System.out.println(this.heart.xpoints[i]+"x");
//			System.out.println(this.heart.ypoints[i]+"y");
//		}
//		this.heart.invalidate();
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
