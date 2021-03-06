package transformer;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import shapes.GRectangle;
import shapes.GShape;

public class GDrawer extends GTransformer {
	public GDrawer(GShape shape) {
		super(shape);
	}
	public void initTransforming(int x, int y, Graphics2D g2d) {
		this.getShape().setOrigin(x, y);
		this.getShape().draw(g2d);
	}
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		g2d.setStroke(LineStroke);
		this.getShape().draw(g2d);
		this.getShape().resize(x, y);
		this.getShape().draw(g2d);
	}
	public void finishTransforming(int x, int y, Graphics2D g2d) {
	}
	public void continueTransforming(int x, int y, Graphics2D g2d) {
		this.getShape().addPoint(x, y);
	}
}
