package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

import contant.GConstants.EAnchors;

public class Anchors extends Vector<Ellipse2D.Double>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int ANCHORWIDTH = 8;
	public final static int ANCHORHEIGHT = 8;
//	enum EAnchors {NN,NE,NW,SS,SW,SE,EE,WW,RR};
	private Vector<Shape> anchors;

	public Anchors(){
		for(int i=0;i<EAnchors.values().length-1;i++){
			this.add(new Ellipse2D.Double(0,0,ANCHORWIDTH,ANCHORHEIGHT));
		}
	}
	private void computeCoordinates(Rectangle r) {
		for (int i=0; i<EAnchors.values().length-1; i++) {		
			switch (EAnchors.values()[i]) {
			case NN:
				this.get(i).x = r.x+r.width/2;
				this.get(i).y = r.y;
				break;
			case NE:
				this.get(i).x = r.x+r.width;
				this.get(i).y = r.y;
				break;
			case NW:
				this.get(i).x = r.x;
				this.get(i).y = r.y;
				break;
			case SS:
				this.get(i).x = r.x+r.width/2;
				this.get(i).y = r.y+r.height;
				break;
			case SE:
				this.get(i).x = r.x+r.width;
				this.get(i).y = r.y+r.height;
				break;
			case SW:
				this.get(i).x = r.x;
				this.get(i).y = r.y+r.height;
				break;
			case EE:
				this.get(i).x = r.x+r.width;
				this.get(i).y = r.y+r.height/2;
				break;
			case WW:
				this.get(i).x = r.x;
				this.get(i).y = r.y+r.height/2;
				break;
			case RR:
				this.get(i).x = r.x+r.width/2;
				this.get(i).y = r.y - 40;
				break;
			default:
				break;
			}
			this.get(i).x = this.get(i).x - ANCHORWIDTH/2;
			this.get(i).y = this.get(i).y - ANCHORHEIGHT/2;
		}
	}
	public void draw(Graphics2D g2D,Rectangle rect){
		computeCoordinates(rect);
		for(int i=0;i<EAnchors.values().length-1;i++){
			g2D.draw(this.get(i));
		}
	}
	public EAnchors contains(int x, int y) {
		for (int i=0; i<EAnchors.values().length-1; i++) {
			if (this.get(i).contains(x, y))
				return EAnchors.values()[i];
		}
		return null;
	}
	public void setTransformedShape(AffineTransform affineTrnasform) {
		for (int i=0; i<anchors.size(); i++) {
			Shape transformedShape = affineTrnasform.createTransformedShape(anchors.get(i));			
			double x = transformedShape.getBounds().getCenterX();
			double y = transformedShape.getBounds().getCenterY();
			Ellipse2D.Double anchor = new Ellipse2D.Double();
			anchor.setFrameFromCenter(x, y, x+ANCHORWIDTH/2, y+ANCHORHEIGHT/2);
			anchors.set(i, anchor);
		}
	}
}
