package shapes;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import contant.GConstants.EDrawingType;

public class GPolygon extends GShape {
	private static Vector<Integer> xv = new Vector<>();
	private static Vector<Integer> yv = new Vector<>();
   private Polygon polygon;
   private GShape shape;
   int x1,y1;
   public GPolygon() {
      super(EDrawingType.NP,new Polygon());
      this.polygon = (Polygon)this.getShape();
   }
   public String toString(){
	   
	   return "polygon";
   }
   @Override
   public void initDrawing(int x, int y, Graphics2D g2D) {
      this.polygon.addPoint(x, y);
      x1=x;
      y1=y;
      this.draw(g2D);
     
   }
   @Override
   public void keepDrawing(int x, int y, Graphics2D g2D) {
	  this.draw(g2D);	
      this.polygon.xpoints[polygon.npoints-1] = x;
      this.polygon.ypoints[polygon.npoints-1] = y;
      this.draw(g2D);
   }
   @Override
   public void finishDrawing(int x, int y, Graphics2D g2D) { 
   }
   public void continueDrawing(int x, int y, Graphics2D g2D){
      this.polygon.addPoint(x, y);
   }
   @Override
   public void initTransforming(int x, int y, Graphics2D g2d) {
	   // TODO Auto-generated method stub
		this.setP1(x, y);
		this.draw(g2d);
   }
   @Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
	   this.draw(g2d);
		this.polygon.translate(x - this.getP1().x,y - this.getP1().y);
		System.out.println("test");
		this.draw(g2d);
		this.setP1(x, y);
	}
	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initResizing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
		this.setP1(x, y);
		this.draw(g2d);
		switch (this.getCurrentEAnchor()) {
		case NN:
			break;
		case NE:
			break;
		case NW:
			break;
		case SS:
			break;
		case SE:
			break;
		case SW:
			break;
		case EE:
			break;
		case WW:
			break;
		default:
			break;
		}
	}
	@Override
	public void keepResizing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
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
		
		this.draw(g2d);
		switch (this.getCurrentEAnchor()) {
		case NN:
			System.out.println("test231");
			for(int i=0; i<polygon.npoints; i++){
				if(polygon.ypoints[i] == minY){		
					polygon.ypoints[i] += y- this.getP1().y;
					
				}else if(polygon.ypoints[i] == maxY){
		
				}
			}
			break;
		case NE:
			for(int i=0; i<polygon.npoints; i++){
				if(polygon.ypoints[i] == minY){		
					polygon.ypoints[i] += y- this.getP1().y;
					polygon.xpoints[i] += x- this.getP1().x;
				}else if(polygon.ypoints[i] == maxY){
		
				}
			}
			break;
		case NW:
			for(int i=0; i<polygon.npoints; i++){
				if(polygon.ypoints[i] == minY){		
					polygon.ypoints[i] += y- this.getP1().y;
					polygon.xpoints[i] += x- this.getP1().x;
				}else if(polygon.ypoints[i] == maxY){
		
				}
			}
			break;
		case SS:
			for(int i=0; i<polygon.npoints; i++){
				if(polygon.ypoints[i] == minY){		
				}else if(polygon.ypoints[i] == maxY){
					polygon.ypoints[i] += y- this.getP1().y;
				//	System.out.println("test231");
				}
			}
			break;
		case SE:
			for(int i=0; i<polygon.npoints; i++){
				if(polygon.xpoints[i] == minX){
				}else if(polygon.xpoints[i] == maxX){
					polygon.xpoints[i] += x- this.getP1().x;
					polygon.ypoints[i] += y- this.getP1().y;
				}
			}
			
			break;
		case SW:
			for(int i=0; i<polygon.npoints; i++){
				if(polygon.xpoints[i] == minX){		
					polygon.xpoints[i] += x- this.getP1().x;
					polygon.ypoints[i] += y- this.getP1().y;
				}else if(polygon.xpoints[i] == maxX){
		
				}
			}
			break;
		case EE:
			for(int i=0; i<polygon.npoints; i++){
				if(polygon.xpoints[i] == minX){
				}else if(polygon.xpoints[i] == maxX){
					polygon.xpoints[i] += x- this.getP1().x;
					
				
				}
			}
			break;
		case WW:
			for(int i=0; i<polygon.npoints; i++){
				if(polygon.xpoints[i] == minX){		
					polygon.xpoints[i] += x- this.getP1().x;
					
				}else if(polygon.xpoints[i] == maxX){
		
				}
			}
			break;
		default:
			break;
		}
		
		// redraw shape
		this.draw(g2d);
		this.setP1(x, y);
	}
	@Override
	public void finishResizing(int x, int y, Graphics2D g2d) {
		this.draw(g2d);
		// TODO Auto-generated method stub
//		for(int i=0; i<polygon.npoints; i++){
//		xv.add(this.polygon.xpoints[i]);
//		yv.add(this.polygon.ypoints[i]);
//		}
//		this.polygon.addPoint(this.getP1().x,this.getP1().y);
//		for(int i=0; i<xv.size(); i++){
//			this.polygon.xpoints[i]=xv.get(i);
//			this.polygon.ypoints[i]=yv.get(i);
//			}
//		xv.removeAllElements();
//		yv.removeAllElements();
		this.draw(g2d);
		//this.setP1(x, y);
		}
	}