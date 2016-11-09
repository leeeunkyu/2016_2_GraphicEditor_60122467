package contant;



import shapes.GEllipse;
import shapes.GShape;
import shapes.GLine;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.Anchors;

public class GConstants {
	public final static int MAINFRAME_X = 100;
	public final static int MAINFRAME_Y = 100;
	public final static int MAINFRAME_W = 400;
	public final static int MAINFRAME_H = 600;
	
	public final static String MAINFRAME_TITLE = "GraphicsEditor";
	public final static String FILEMENU_TITLE = "File";
	public final static String EDITMENU_TITLE = "Edit";

	public static enum EMainFrame{
		X(100),	Y(100),	W(400),	H(600);
		private int value;
		private EMainFrame(int value){
			this.value = value;
		}
		public int getValue(){return this.value;}
	}
	public static enum EFileMenuItem{
		newItem("new"),
		open("open"),
		close("close"),
		save("save"),
		saveAs("saveAs"),
		print("print"),
		exit("exit");
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
		delete("delete"),
		redo("redo"),
		undo("undo"),
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
		polygon("rsc/polygon.gif","rsc/polygonSLT.gif", new GPolygon());
		
		
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