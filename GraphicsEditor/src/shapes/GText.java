package shapes;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.TextArea;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import contant.GConstants.EDrawingType;
import frame.GDrawingPanel;

public class GText extends  GDrawingPanel{
	JTextField tf;
	JTextArea ta;
	int x1,y1;
	public GText() {
	//tf= new JTextField(20);

	System.out.println("new area");
	//add(new JLabel("�Ʒ�â�����ڿ����Է��ϰ��ư��Ŭ���ϼ���"));
	//add(tf);
	}
	public void initsize(int x,int y){
		x1=x;y1=y;
	}
	public void finshsize(int x,int y){
		ta=new JTextArea((int)(x-x1)/22,(int)(y-y1)/11);
		//��27,��34 �� ���λ����� H=600 ���� W 400�̶�����
		//27:34=600:400
		ta.setBounds(0,0,0,0);
		add(new JScrollPane(ta));
		
		System.out.println("������"+(x-x1)+"  "+(y-y1));
	}
}
