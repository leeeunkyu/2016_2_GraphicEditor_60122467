package transformer;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import shapes.GShape;

abstract public class GTransformer {
	protected GShape shape;
	protected GShape getShape() { return this.shape; }
	protected AffineTransform affineTransform;
	
	BasicStroke LineStroke;
	//스트로크(Stroke)란 도형의 외각선 모양을 결정하는 속성이다. 스트로크를 구현하는 클래스로 BasicStroke가 있다.
	//BasicStroke 객체는 선의 두께와 스타일, 끝점(end cap)의 모양, 꼭지점(end join: 선과 선이 만나는 부분)의 모양을 설정한다.
	public GTransformer(GShape shape) {
		this.shape = shape;
		affineTransform =  new AffineTransform();
		float dot[] = {4};
		LineStroke = new BasicStroke(1, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND, 10, dot, 0);	
		//width(선의 두께), cap(끝점의 모양), join(꼭지점의 모양),
		//miterlimit(꼭지점 길이의 한계: 1이상 값), dash(점선의 패턴 길이 모양),
		//dash_phase(점선간의 공백 거리)가 설정된 스트로크 객체를 만든다
	}
	abstract public void initTransforming(int x, int y, Graphics2D g2D);
	abstract public void keepTransforming(int x, int y, Graphics2D g2D);
	abstract public void finishTransforming(int x, int y, Graphics2D g2D);
	abstract public void continueTransforming(int x, int y, Graphics2D g2D);
}

