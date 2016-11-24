package shapes;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import contant.GConstants.EDrawingType;
public class GEllipse extends GShape {
	
	private Ellipse2D.Double ellipse;

	public GEllipse() {
		super(EDrawingType.TP,new Ellipse2D.Double(0,0,0,0));
		this.ellipse=(Ellipse2D.Double)this.getShape();
	
	}
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		this.ellipse.setFrame(x, y, 0, 0);
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		this.draw(g2D);
		this.ellipse.width=x-this.ellipse.x;
		this.ellipse.height=y-this.ellipse.y;
		this.draw(g2D);		
	}
	@Override
	public void finishDrawing(int x, int y, Graphics2D g2D) {
	}
	public void continueDrawing(int x, int y, Graphics2D g2D){
		
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
		this.ellipse.x += x - this.getP1().x;
		this.ellipse.y += y - this.getP1().y;
		// redraw shape
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
		this.draw(g2d);
		switch (this.getCurrentEAnchor()) {
		case NN:
			this.ellipse.y += y - this.getP1().y;
			this.ellipse.height -=  y - this.getP1().y;
			break;
		case NE:
			this.ellipse.y += y - this.getP1().y;
			this.ellipse.height -=  y - this.getP1().y;
			this.ellipse.width += x - this.getP1().x;
			break;
		case NW:
			this.ellipse.y += y - this.getP1().y;
			this.ellipse.height -=  y - this.getP1().y;
			this.ellipse.x += x - this.getP1().x;
			this.ellipse.width -=  x - this.getP1().x;
			break;
		case SS:
			this.ellipse.height += (+y -this.getP1().y);
			break;
		case SE:
			this.ellipse.width += x - this.getP1().x;
			this.ellipse.height += y - this.getP1().y;
			System.out.println(x+"  "+y+"   "+this.getP1().x+"   "+this.getP1().y);
			break;
		case SW:
			this.ellipse.height += (+y -this.getP1().y);
			this.ellipse.x += x - this.getP1().x;
			this.ellipse.width -=  x - this.getP1().x;
			break;
		case EE:
			this.ellipse.width += x - this.getP1().x;
			System.out.println(this.ellipse.width);
			break;
		case WW:
			this.ellipse.x += x - this.getP1().x;
			this.ellipse.width -=  x - this.getP1().x;
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
		// TODO Auto-generated method stub
		
	}
}
