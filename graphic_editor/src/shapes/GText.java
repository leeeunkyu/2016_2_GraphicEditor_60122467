package shapes;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.TextArea;
import java.awt.geom.Point2D;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import contant.GConstants.EDrawingType;
import frame.GDrawingPanel;
import frame.GMainFrame;
public class GText extends JTextArea{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField tf;
	JTextArea ta;
	GMainFrame gf;
	int x1,y1;
	public GText() {
		super(1,1);
//	gf = new GMainFrame();
//	Container c = gf.getContentPane();
//		c.setLayout(null);
	//super.setLayout(null);
	//super.setBounds(100,100, 300, 200);
		//tf= new JTextField(20);
	//setLayout(null);
	System.out.println("new area");
	//add(new JLabel("�Ʒ�â�����ڿ����Է��ϰ��ư��Ŭ���ϼ���"));
	//add(tf);
	}
	public void initsize(int x,int y){
		x1=x;y1=y;
	}
	public void finshsize(int x,int y){
		super.setColumns((int)(x-x1)/22);
		super.setRows((int)(y-y1)/11);
		//ta=new JTextArea((int)(y-y1)/11,(int)(x-x1)/22);	
		//��27,��34 �� ���λ����� H=600 ���� W 400�̶�����
		//27:34=600:400
		//super.setBounds(x1,y1,y-y1,x-x1);
		//super.setLayout(null);
		//super.setLocation(x, y);
		super.add(new JScrollPane(ta));
		super.setLineWrap(true);
		System.out.println("������"+(x-x1)+"  "+(y-y1));
	}
}
