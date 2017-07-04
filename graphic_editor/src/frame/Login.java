package frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton SUBMIT;
	  JPanel panel;
	  JLabel label1,label2;
	  GMain GM;
	  final JTextField  text1,text2;	  
	   Login()
	   {
		   label1 = new JLabel();
		   label1.setText("Username:");
		   text1 = new JTextField(15);
		   label2 = new JLabel();
		   label2.setText("Password:");
		   text2 = new JPasswordField(15);
		   SUBMIT=new JButton("SUBMIT");
		   panel=new JPanel(new GridLayout(3,1));
		   panel.add(label1);
		   panel.add(text1);
		   panel.add(label2);
		   panel.add(text2);
		   panel.add(SUBMIT);
		   add(panel,BorderLayout.CENTER);
		   SUBMIT.addActionListener(this);
		   setTitle("LOGIN FORM");
	   }
	   public void actionPerformed(ActionEvent ae)
	   {
		  boolean permitt = false;
		  String value1=text1.getText();
		  String value2=text2.getText();
	   /*
	    * Owner확인
	    */
		  GM = new GMain();
		  if(value1.equals("이은규")){
			 if(value2.equals("123")){
				  permitt = true;
			 }
		  }
		  if(permitt){
			  this.setVisible(false);
			  GM.GraphicsEditor();
		  }
		  else{
			  System.out.println("enter the valid username and password");
			  JOptionPane.showMessageDialog(this,"아이디랑 비밀번호를 확인하시오",
						  	"Error",JOptionPane.ERROR_MESSAGE);
		  }
		  }
	   public static void main(String arg[])
	   	{
		   try
		   {
			   Login frame=new Login();
			   frame.setSize(300,100);
			   frame.setBounds(200,200,300,100);
			   frame.setVisible(true);
	   }
	   catch(Exception e)
	   {JOptionPane.showMessageDialog(null, e.getMessage());}
	   }
}

