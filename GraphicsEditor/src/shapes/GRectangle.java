package shapes;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import contant.GConstants.EDrawingType;

public class GRectangle extends GShape {
	private Rectangle rectangle;
	public GRectangle() {
		super(EDrawingType.TP, new Rectangle(0, 0, 0, 0));
		this.rectangle = (Rectangle)this.getShape();
	}
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		this.rectangle.setLocation(x,y);
		this.draw(g2D);
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		this.draw(g2D);
		this.rectangle.setSize(new Dimension ((x - this.rectangle.x),(y-this.rectangle.y)));
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
		this.rectangle.x += x - this.getP1().x;
		this.rectangle.y += y - this.getP1().y;
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
			this.rectangle.y += y - this.getP1().y;
			this.rectangle.height -=  y - this.getP1().y;
			break;
		case NE:
			this.rectangle.y += y - this.getP1().y;
			this.rectangle.height -=  y - this.getP1().y;
			this.rectangle.width += x - this.getP1().x;
			break;
		case NW:
			this.rectangle.y += y - this.getP1().y;
			this.rectangle.height -=  y - this.getP1().y;
			this.rectangle.x += x - this.getP1().x;
			this.rectangle.width -=  x - this.getP1().x;
			break;
		case SS:
			this.rectangle.height += (+y -this.getP1().y);
			break;
		case SE:
			this.rectangle.width += x - this.getP1().x;
			this.rectangle.height += y - this.getP1().y;
			System.out.println(x+"  "+y+"   "+this.getP1().x+"   "+this.getP1().y);
			break;
		case SW:
			this.rectangle.height += (+y -this.getP1().y);
			this.rectangle.x += x - this.getP1().x;
			this.rectangle.width -=  x - this.getP1().x;
			break;
		case EE:
			this.rectangle.width += x - this.getP1().x;
			System.out.println(this.rectangle.width);
			break;
		case WW:
			this.rectangle.x += x - this.getP1().x;
			this.rectangle.width -=  x - this.getP1().x;
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
	
}
