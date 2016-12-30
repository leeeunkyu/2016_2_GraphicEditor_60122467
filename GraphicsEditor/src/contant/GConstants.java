package contant;



import shapes.GEllipse;
import shapes.GHeart;
import shapes.GShape;
import shapes.GLine;
import shapes.GPencile;
import shapes.GPolygon;
import shapes.GRectangle;
import java.awt.Color;
import java.awt.Cursor;
import shapes.GCurSor;

public class GConstants {
	public final static int MAINFRAME_X = 100;
	public final static int MAINFRAME_Y = 100;
	public final static int MAINFRAME_W = 400;
	public final static int MAINFRAME_H = 600;
	public enum EAnchors {
		NN(GCurSor.N_RESIZE()), 
		NE(GCurSor.NE_RESIZE()), 
		NW(GCurSor.NW_RESIZE()), 
		SS(GCurSor.S_RESIZE()), 
		SE(GCurSor.SE_RESIZE()), 
		SW(GCurSor.SW_RESIZE()), 
		EE(GCurSor.E_RESIZE()), 
		WW(GCurSor.W_RESIZE()), 
		RR(GCurSor.RR()), 
		MM(GCurSor.MOVE());
		private Cursor cursor;
		private EAnchors(Cursor cursor){
			this.cursor=cursor;
		}
		public Cursor getCursor(){
			return this.cursor;
		}
	};
	public final static String MAINFRAME_TITLE = "GraphicsEditor";
	public final static String FILEMENU_TITLE = "File";
	public final static String EDITMENU_TITLE = "Edit";
	public final static String COLORMENU_TITLE = "Color";
	public static final Color COLOR_LINE_DEFAULT = Color.black;
	public static final Color COLOR_FILL_DEFAULT = Color.white;

	public static enum EMainFrame{
		X(100),	Y(100),	W(400),	H(600);
		private int value;
		private EMainFrame(int value){
			this.value = value;
		}
		public int getValue(){return this.value;}
	}
	public static enum EColorMenuItem{
		line("line"),
		fill("fill");
		private String text;
		private EColorMenuItem(String text){
			this.text=text;
		}
		public String getText(){return this.text;}
	}
	public static enum EFileMenuItem{
		newItem("new"),
		open("open"),
		close("close"),
		save("save"),
		saveAs("saveAs"),
		exit("exit"),
		shapeinfo("shapeinfo"),
		fullscreen("fullscreen");
		private String text;
		private EFileMenuItem(String text){
			this.text = text;
		}
		public String getText(){return this.text;}
		
	}
	public static enum EEditMenuItem{
		cut("cut"),
		copy("copy"),
		paste("paste"),
		redo("redo"),
		undo("undo"),
		select("select"),
		group("group"),
		ungroup("ungroup");
		private String text;
		private EEditMenuItem(String text){
			this.text = text;
		}
		public String getText(){return this.text;}
	}
	public static enum EDrawingType{
		TP, NP;
	}
	public static enum EToolBarButton{
		rectangle("rsc/rectangle.gif","rsc/rectangleSLT.gif", new GRectangle()),
		ellipse("rsc/ellipse.gif","rsc/ellipseSLT.gif",new GEllipse()),
		line("rsc/line.gif","rsc/lineSLT.gif",new GLine()),
		polygon("rsc/polygon.gif","rsc/polygonSLT.gif", new GPolygon()),
		heart("rsc/heart.gif","rsc/heartSLT.gif", new GHeart()),
		pencile("rsc/pencile.gif","rsc/pencileSLT.gif",new GPencile());
		private String iconName;
		private String selectedIconName;
		private GShape shape;
		
		private EToolBarButton(String iconName,  String selectedIconName, GShape shape){
			this.iconName = iconName;
			this.selectedIconName = selectedIconName;
			this.shape = shape;
		}
		public String getIconName(){ return this.iconName;}
		public String getSelectedIconName(){ return this.selectedIconName;}
		public GShape getShape(){return this.shape;}
	}
	public final static int ANCHORWIDTH=4;
	public final static int ANCHORHIGTH=4;
	
}