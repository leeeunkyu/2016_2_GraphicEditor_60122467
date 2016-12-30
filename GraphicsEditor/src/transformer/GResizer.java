package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import contant.GConstants.EAnchors;
import shapes.GShape;

public class GResizer extends GTransformer {
	private Point previousP;
	private Point resizeAnchor; // 선택된 좌표에 따라 고정된 앵커가 바뀌어야 함
	public GResizer(GShape shape) {
		super(shape);
		previousP = new Point();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2d) {
		previousP.x=x;
		previousP.y=y;
		this.resizeAnchor = getResizeAnchor();
		shape.moveReverse(resizeAnchor);
		this.getShape().setPoint(x, y);
		this.getShape().draw(g2d);
	}
	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		Point2D resizeFactor = computeResizeFactor(previousP, x,y);
		AffineTransform tempAffine = g2d.getTransform();
		g2d.translate(resizeAnchor.x, resizeAnchor.y);
		g2d.setStroke(LineStroke);
		this.getShape().draw(g2d);
		this.getShape().resize(resizeFactor.getX(),resizeFactor.getY());
		this.getShape().draw(g2d);
		g2d.setTransform(tempAffine);
		previousP.x=x;
		previousP.y=y;
	}
	private Point getResizeAnchor() {
		// TODO Auto-generated method stub
		Point resizeAnchor = new Point();
		Ellipse2D.Double tempAnchor = null;
		switch (shape.getCurrentEAnchor()) {
		case NN:
			tempAnchor=shape.getAnchors().get(EAnchors.SS.ordinal());		
			//ordinal() : enum 안에 정의되어 있는 각 값들의 인덱스 번호를 반환.
			break;
		case NE:
			tempAnchor=shape.getAnchors().get(EAnchors.SW.ordinal());		
			break;
		case NW:
			tempAnchor=shape.getAnchors().get(EAnchors.SE.ordinal());		
			break;
		case SS:
			tempAnchor=shape.getAnchors().get(EAnchors.NN.ordinal());		
			break;
		case SE:
			tempAnchor=shape.getAnchors().get(EAnchors.NW.ordinal());		
			break;
		case SW:
			tempAnchor=shape.getAnchors().get(EAnchors.NE.ordinal());		
			break;
		case EE:
			tempAnchor=shape.getAnchors().get(EAnchors.WW.ordinal());		
			break;
		case WW:
			tempAnchor=shape.getAnchors().get(EAnchors.EE.ordinal());		
			break;
		}
		System.out.println(tempAnchor.y+"temp테스트");
		resizeAnchor.setLocation(tempAnchor.x, tempAnchor.y);
		return resizeAnchor;
	}
	private Point2D computeResizeFactor(Point previousP2, int x, int y) {
		double deltaW = 0;
		double deltaH = 0;
		switch (shape.getCurrentEAnchor()) {
		case NN:
			deltaW = 0;
			deltaH = -(y - previousP2.y);
			break;
		case NE:
			deltaW = x - previousP2.x;
			deltaH = -(y - previousP2.y);
			break;
		case NW:
			deltaW = -(x - previousP2.x);
			deltaH = -(y - previousP2.y);
			break;
		case SS:
			deltaW = 0;
			deltaH = y - previousP2.y;
			break;
		case SE:
			deltaW = x - previousP2.x;
			deltaH = y - previousP2.y;
			break;
		case SW:
			deltaW = -(x - previousP2.x);
			deltaH = y - previousP2.y;
			break;
		case EE:
			deltaW = x - previousP2.x;
			deltaH = 0;
			break;
		case WW:
			deltaW = -(x - previousP2.x);
			deltaH = 0;
			break;
		}
		double currentW = shape.getBounds().getWidth();
		double currentH = shape.getBounds().getHeight();
		double xFactor = 1.0;
		if (currentW > 0.0) {
			xFactor = (1.0 + deltaW / currentW);
		}
		double yFactor = 1.0;
		if (currentH > 0.0) {
			yFactor = (1.0 + deltaH / currentH);
		}
		return new Point2D.Double(xFactor, yFactor);
	}
	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		shape.move(resizeAnchor.getX(),resizeAnchor.getY());
	}
	@Override
	public void continueTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

}
