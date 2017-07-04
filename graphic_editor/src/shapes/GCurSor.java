package shapes;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class GCurSor {
	Cursor cursor;
	static Cursor cursor2;
	 Image img;
	public GCurSor() {
//		  Toolkit tk = Toolkit.getDefaultToolkit();
//		  img = tk.getImage("rsc/rectangle.gif");
//		  Point point = new Point(0,0);
//		  cursor2=tk.createCustomCursor(img,point,"rsc/rectangle");
		  
		// TODO Auto-generated constructor stub
	}

	public static Cursor N_RESIZE(){
		return new Cursor(Cursor.N_RESIZE_CURSOR);
	}
	public static Cursor NE_RESIZE(){
		return new Cursor(Cursor.NE_RESIZE_CURSOR);
	}
	public static Cursor NW_RESIZE(){
		return new Cursor(Cursor.NW_RESIZE_CURSOR);
	}
	public static Cursor S_RESIZE(){
		return new Cursor(Cursor.S_RESIZE_CURSOR);
	}
	public static Cursor SW_RESIZE(){
		return new Cursor(Cursor.SW_RESIZE_CURSOR);
	}
	public static Cursor SE_RESIZE(){
		return new Cursor(Cursor.SE_RESIZE_CURSOR);
	}
	public static Cursor E_RESIZE(){
		return new Cursor(Cursor.E_RESIZE_CURSOR);
	}
	public static Cursor W_RESIZE(){
		return new Cursor(Cursor.W_RESIZE_CURSOR);
	}
	public static Cursor MOVE(){
		return new Cursor(Cursor.MOVE_CURSOR);
	}
	public static Cursor RR(){
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		return  toolKit.createCustomCursor(toolKit.getImage("rotateCursor.png"), new Point(16,16), "rotation");
	}
	public static Cursor defaultcursor(){
		return  new Cursor(Cursor.DEFAULT_CURSOR);
	}
}
