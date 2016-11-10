package shapes;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Polygon;

import contant.GConstants.EDrawingType;

public class GPolygon extends GShape {
   private Polygon polygon;
   private Polygon polyline;
   int check=0;
   public GPolygon() {
      super(EDrawingType.NP);
      this.polygon = new Polygon();
      this.shape = this.polygon;
      this.polyline = new Polygon();
   }
   @Override
   public void initDrawing(int x, int y, Graphics2D g2D) {
      this.polygon.addPoint(x, y);
      this.polygon.addPoint(x, y);
     
   }
   @Override
   public void keepDrawing(int x, int y, Graphics2D g2D) {
	  
	   this.draw(g2D);	
      this.polygon.xpoints[polygon.npoints-1] = x;
      this.polygon.ypoints[polygon.npoints-1] = y;
      // System.out.println("test2");
      this.draw(g2D);
   }
   @Override
   public void finishDrawing(int x, int y, Graphics2D g2D) {
	   g2D.drawPolyline(polygon.xpoints,polygon.ypoints,polygon.npoints);
	   g2D.draw(this.polygon);
	   //this.getAnchors().draw(g2D, this.polygon.getBounds());
	  
	  
	   
   }
   public void continueDrawing(int x, int y, Graphics2D g2D){
      this.polygon.addPoint(x, y);
   }
   @Override
   public void draw(Graphics2D g2D) {
      // TODO Auto-generated method stub
	
	  if(check==1){
		  g2D.draw(this.polygon);
		  this.getAnchors().draw(g2D, this.polygon.getBounds());
	  }else{
		  g2D.drawPolyline(polygon.xpoints,polygon.ypoints,polygon.npoints);
		   g2D.draw(this.polygon);
		   g2D.draw(this.polygon);
	  }
	   //this.getAnchors().draw(g2D, this.polygon.getBounds());
   }
   public void drawAnchors(Graphics2D g2D){
	   check=1;
	   g2D.draw(this.polygon);
	   this.getAnchors().draw(g2D, this.polygon.getBounds());
	}
}