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
	//��Ʈ��ũ(Stroke)�� ������ �ܰ��� ����� �����ϴ� �Ӽ��̴�. ��Ʈ��ũ�� �����ϴ� Ŭ������ BasicStroke�� �ִ�.
	//BasicStroke ��ü�� ���� �β��� ��Ÿ��, ����(end cap)�� ���, ������(end join: ���� ���� ������ �κ�)�� ����� �����Ѵ�.
	public GTransformer(GShape shape) {
		this.shape = shape;
		affineTransform =  new AffineTransform();
		float dot[] = {4};
		LineStroke = new BasicStroke(1, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND, 10, dot, 0);	
		//width(���� �β�), cap(������ ���), join(�������� ���),
		//miterlimit(������ ������ �Ѱ�: 1�̻� ��), dash(������ ���� ���� ���),
		//dash_phase(�������� ���� �Ÿ�)�� ������ ��Ʈ��ũ ��ü�� �����
	}
	abstract public void initTransforming(int x, int y, Graphics2D g2D);
	abstract public void keepTransforming(int x, int y, Graphics2D g2D);
	abstract public void finishTransforming(int x, int y, Graphics2D g2D);
	abstract public void continueTransforming(int x, int y, Graphics2D g2D);
}

