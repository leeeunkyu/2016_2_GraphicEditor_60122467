package shapes;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Line2D;

import contant.GConstants.EDrawingType;

public class GPolygon extends GShape {
   private Polygon polygon;
   public GPolygon() {
      super(EDrawingType.NP,new Polygon());
      this.polygon = (Polygon)this.getShape();

   }
   @Override
   public void initDrawing(int x, int y, Graphics2D g2D) {
      this.polygon.addPoint(x, y);
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
//   @Override
//   public void draw(Graphics2D g2D) {
//      // TODO Auto-generated method stub
//	  if(check==1){
//		  g2D.draw(this.polygon);
//		  this.getAnchors().draw(g2D, this.polygon.getBounds());
//	  }else{
//		  g2D.drawPolyline(polygon.xpoints,polygon.ypoints,polygon.npoints);
//		   g2D.draw(this.polygon);
//		   g2D.draw(this.polygon);
//	  }
//	   //this.getAnchors().draw(g2D, this.polygon.getBounds());
//   }
//   public void drawAnchors(Graphics2D g2D){
//	   check=1;
//	   g2D.draw(this.polygon);
//	   this.getAnchors().draw(g2D, this.polygon.getBounds());
//	}
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
		this.polygon.translate(this.polygon.xpoints[polygon.npoints-1]+x - this.getP1().x,this.polygon.ypoints[polygon.npoints-1] +y - this.getP1().y);
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
		
	}
	@Override
	public void keepResizing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void finishResizing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	}