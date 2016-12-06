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
	public void setOrigin(int x, int y) {
		this.ellipse.setFrame(x, y,0,0);
	}
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}
	public void move(int x, int y) {
		this.ellipse.x += x - px;
		this.ellipse.y += y - py;
		this.setPoint(x, y);
	}
	public void addPoint(int x, int y) {
	}

	public void resize(int x, int y) {
		if (this.getCurrentEAnchor() == null) {
			this.ellipse.width = x - this.ellipse.x;
			this.ellipse.height = y - this.ellipse.y;
			return;
		}
		switch (this.getCurrentEAnchor()) {
		case NN:
			this.ellipse.y += y - this.py;
			this.ellipse.height -=  y - this.py;
			break;
		case NE:
			this.ellipse.y += y - this.py;
			this.ellipse.height -=  y - this.py;
			this.ellipse.width += x - this.px;
			break;
		case NW:
			this.ellipse.y += y - this.py;
			this.ellipse.height -=  y - this.py;
			this.ellipse.x += x - this.px;
			this.ellipse.width -=  x - this.px;
			break;
		case SS:
			this.ellipse.height += (+y -this.py);
			break;
		case SE:
			this.ellipse.width += x - this.px;
			this.ellipse.height += y - this.py;
			System.out.println(x+"  "+y+"   "+this.px+"   "+this.py);
			break;
		case SW:
			this.ellipse.height += (+y -this.py);
			this.ellipse.x += x - this.px;
			this.ellipse.width -=  x - this.px;
			break;
		case EE:
			this.ellipse.width += x - this.px;
			System.out.println(this.ellipse.width);
			break;
		case WW:
			this.ellipse.x += x - this.px;
			this.ellipse.width -=  x - this.px;
			break;
		}
		this.setPoint(x, y);
	}
}