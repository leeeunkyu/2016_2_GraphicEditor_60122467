package frame;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import contant.GConstants;
import contant.GConstants.EAnchors;
import contant.GConstants.EDrawingType;
import shapes.GGroupManager;
import shapes.GShape;
import shapes.GText;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
import transformer.GRotator;
import transformer.GTransformer;

public class GDrawingPanel extends JPanel implements Serializable{
   // attributes
   private static final long serialVersionUID = 1L;
   // object states
   private enum EState {idle,drawingTP,drawingNP,transforming};
   private enum Anchorstate {anchorready,anchorpush};
   public enum Textstate {textready,textepush};
   //components;
   public Vector<GShape> shapeVector;
   public Vector<GShape> anchorVector;
   private Stack<Vector<GShape>> redo;
   private Stack<Vector<GShape>> undo;
	private Vector<GShape> groupList;

   public Vector<GShape> getShapeVector() { repaint(); return this.shapeVector; }
	public void setShapeManagers(Vector<GShape> shapeVector) {
		this.shapeVector = shapeVector;
		repaint();
	}
   MouseEventHandler mouseEventHandler;
   Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
   Cursor hourglass = new Cursor(Cursor.DEFAULT_CURSOR);
   // working variables
   public GShape currentShape;
   private GTransformer currentTransformer;
   GShape consape = null; //허공에다 엥커기능쓰면 에러처리해주는거
   boolean shapeexist=false;
   // associative attributes
   private GShape selectedShape;
   private Anchorstate anchor;
   public Textstate textstate;
   protected GText text;
   private EState eState=EState.idle;
private Color lineColor;
   public void setSelectedShape(GShape selectedShape) {
      this.selectedShape = selectedShape;
      anchor = Anchorstate.anchorready;
   }
   public void setSelectedSkill(String action){
	   if(action.equals("anchor")){
	   	anchor = Anchorstate.anchorpush;
		textstate=Textstate.textready;

	   }else{
		textstate=Textstate.textepush;
		anchor=Anchorstate.anchorready;
	   }
   } 
	public boolean isShapeexist() {
		return shapeexist;
	}
	public void setShapeexist(boolean shapeexist) {
		this.shapeexist = shapeexist;
	}
   @SuppressWarnings("unchecked")
public GDrawingPanel() {
	  super();
	  //attribute
	  //components
	  shapeVector = new Vector<GShape>();
      anchorVector = new Vector<GShape>();
      redo = new Stack<Vector<GShape>>();
      undo = new Stack<Vector<GShape>>();
      undo.add((Vector<GShape>) shapeVector.clone());
      this.mouseEventHandler = new MouseEventHandler();
      this.addMouseListener(mouseEventHandler);
      this.addMouseMotionListener(mouseEventHandler);
      this.selectedShape = null;
      this.currentShape = null;
      this.currentTransformer = null;
      groupList = new Vector<GShape>();
   }
   public void group(GGroupManager group) {
		boolean check = false;
   	for(int i = shapeVector.size(); i > 0; i--) {
   		GShape cShapeManager = shapeVector.get(i - 1);
   		if(cShapeManager.isSelected()) {
   			cShapeManager.setSelected(false);
   			group.add(cShapeManager);
   			shapeVector.remove(cShapeManager);	
   			check = true;
   		}
   	}
   	if(check) {
   		group.setSelected(true);
   		shapeVector.add(group);
   	}
   	groupList = group.getGroupList();
   	System.out.println(shapeVector+"test");
   	repaint();
	}
   @SuppressWarnings("unchecked")
public void redo() {
		if(redo.size() != 0) {
			undo.add((Vector<GShape>)shapeVector.clone());
			shapeVector = redo.pop();
			repaint();
		}
	}
	@SuppressWarnings("unchecked")
	public void undo() {
		if(undo.size() != 0) {
			redo.add((Vector<GShape>)shapeVector.clone());
			shapeVector = undo.pop();
			repaint();		
		}
	}
	public void setFillColor(Color fillColor) {
		if (currentShape != null) {
			currentShape.setFillColor(fillColor);
			repaint();
		} else {
		}
	}
	public boolean colorSelection(boolean flag, Color color) {
		boolean returnValue = false;
		for (GShape colorShape : shapeVector) {
			if (colorShape.isSelected()) {
				if (flag) {
					colorShape.setLineColor(color);
				} else {
					colorShape.setFillColor(color);
				}
				returnValue = true;
			}
		}
		repaint();
		return returnValue;
	}
	public void setLineColor(Color lineColor) {
		if (colorSelection(true, lineColor)) {
			return;
		}
		this.lineColor = lineColor;
	}

   public void intitpanel() {
		// TODO Auto-generated method stub
	   shapeVector.clear();
	   repaint();
	   this.setShapeexist(false);
	}
   public void initialize() {
      // TODO Auto-generated method stub

   }
   public void paint(Graphics g) {
	  super.paint(g);
	        for(GShape shape : this.shapeVector){
         shape.draw((Graphics2D)g);
      }
   } 
	public void resetSelected() {
		for (GShape shape: this.shapeVector) {
			shape.setSelected(false);
		}
		this.repaint();
	}
	private void initTransforming(int x, int y) {
  	  System.out.println("init");
  	  if (this.currentShape == null) {
			this.currentShape= this.selectedShape.clone();
			this.currentTransformer = new GDrawer(this.currentShape);
		} else if (this.currentShape.getCurrentEAnchor() == EAnchors.MM) {
			this.currentTransformer = new GMover(this.currentShape);
		} else if (this.currentShape.getCurrentEAnchor() == EAnchors.RR) {
			this.currentTransformer = new GRotator(this.currentShape);
		} else {			
			this.currentTransformer = new GResizer(this.currentShape);
		}
		this.resetSelected();
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTransformer.initTransforming(x, y, g2D);
	}

	private void keepTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTransformer.keepTransforming(x, y, g2D);
	}
	private void continueTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTransformer.continueTransforming(x, y, g2D);
	}
	@SuppressWarnings("unchecked")
	private void finishTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTransformer.finishTransforming(x, y, g2D);
		if (this.currentTransformer.getClass().getSimpleName().equals("GDrawer")) {
			undo.add((Vector<GShape>) shapeVector.clone());
			redo.clear();
			this.shapeVector.add(this.currentShape);
		}else{
			undo.add((Vector<GShape>) shapeVector.clone());
			redo.clear();			
		}
		this.setShapeexist(true);
		this.currentShape.setSelected(true);
		this.repaint();
	}
	private GShape onShape(int x, int y) {
		for (GShape shape: this.shapeVector) {
			GConstants.EAnchors eAnchor = shape.contanins(x, y);
			if (eAnchor != null)
				return shape;
		}
		return null;
	}
   private void changeAnchors(int x, int y){
	   for (int i=0;i<shapeVector.size();i++) {
		   if(anchor == Anchorstate.anchorpush && shapeVector.get(i).contains2(x, y)){
			   shapeVector.get(i).setSelected(true);
			   System.out.println("true로만들어줌");
			   this.shapeVector.add(this.currentShape);
			   break;  
		}  
	   }
	   repaint();
   }
   private void textinit(int x,int y){
	   text=new GText();
	   text.initsize(x, y);
   }
   private void textfinsh(int x,int y){
	   this.add(text);
	   super.revalidate();			//리페인트같은거
	   text.finshsize(x, y);
	 //  text.setBounds(100,100,100,400);
   }
   private void changeCursor(GShape shape) {
		if (shape == null) {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		this.setCursor(shape.getCurrentEAnchor().getCursor());
	/*
	 * this.setCursor(shape.getCurrentEAnchor.getCursor);
	 */
	}
   
   class MouseEventHandler implements MouseInputListener, MouseMotionListener,Serializable {

      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
      public void mouseClicked(MouseEvent e) {
    	 if(e.getClickCount() == 1){
            mouse1Clicked(e);
         }else if(e.getClickCount() == 2){
            mouse2Clicked(e);
         }
      }
	private void mouse1Clicked(MouseEvent e) {
         if (eState == EState.idle && anchor == Anchorstate.anchorready ) {
               	if(selectedShape.geteDrawingType() == EDrawingType.NP){
               		initTransforming(e.getX(), e.getY());
               		eState = EState.drawingNP;
               	}
         }else if(eState == EState.drawingNP){
            continueTransforming(e.getX(), e.getY());
         }else if((eState == EState.idle) && anchor == Anchorstate.anchorpush){
        	 //엥커
        	 changeAnchors(e.getX(), e.getY());
         }
         
      }
      private void mouse2Clicked(MouseEvent e) {
         if (eState == EState.drawingNP) {      
            finishTransforming(e.getX(), e.getY());
            eState = EState.idle;
         }
      }
      @Override
      public void mousePressed(MouseEvent e) {
    	if(textstate==Textstate.textepush){
    		textinit(e.getX(),e.getY());
    	}
    	else{
    	if (eState == EState.idle) {
    		currentShape = onShape(e.getX(), e.getY());
    	  if (currentShape == null && anchor == Anchorstate.anchorready) {
             	if(selectedShape.geteDrawingType() == EDrawingType.TP){
             		initTransforming(e.getX(), e.getY());
             		eState = EState.drawingTP;
    		  }
    	  }else if(anchor == Anchorstate.anchorpush){
				initTransforming(e.getX(), e.getY());
				eState = EState.transforming;
			}
    	}else if(anchor == Anchorstate.anchorpush){
    			initTransforming(e.getX(), e.getY());
  				eState = EState.transforming;
  			
    	}
    	}
    	
      }
      @Override
      public void mouseReleased(MouseEvent e) {
         if(textstate==Textstate.textepush){
        	 textfinsh(e.getX(),e.getY());
         }else{
    	  if (eState == EState.drawingTP && anchor == Anchorstate.anchorready) {      
        	 finishTransforming(e.getX(), e.getY());
            eState = EState.idle;
         }else if (eState == EState.transforming && anchor == Anchorstate.anchorpush) {
        		finishTransforming(e.getX(), e.getY());
				eState = EState.idle;
			}
         }
      }
      @Override
      public void mouseMoved(MouseEvent e) {
         if (eState == EState.drawingNP) {      
            keepTransforming(e.getX(), e.getY());
         }else if (eState == EState.idle) {
        	 GShape shape = onShape(e.getX(), e.getY());
        	 changeCursor(shape);
			}
      }      
      @Override
      public void mouseDragged(MouseEvent e) {
         if (eState == EState.drawingTP) {      
            keepTransforming(e.getX(), e.getY());
         }else if (eState == EState.transforming &&  anchor == Anchorstate.anchorpush) {
				keepTransforming(e.getX(), e.getY());				
			}
      }
      @Override
      public void mouseEntered(MouseEvent arg0) {
      }
      @Override
      public void mouseExited(MouseEvent arg0) {
      }
   }


}



   



   
