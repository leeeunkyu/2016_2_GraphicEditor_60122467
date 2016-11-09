package frame;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import contant.GConstants.EDrawingType;
import shapes.Anchors;
import shapes.GShape;

public class GDrawingPanel extends JPanel {
   // attributes
   private static final long serialVersionUID = 1L;
   // object states
   private enum EState {idleTP,idleNP,drawingTP,drawingNP,NPDrawing, TPDrawing};
   private enum Anchorstate {anchorready,anchorpush};
   //components;
   public Vector<GShape> shapeVector;
   public Vector<GShape> anchorVector;
   
   Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
	Cursor hourglass = new Cursor(Cursor.DEFAULT_CURSOR);
   // working variables
   
   private GShape currentShape;
   private GShape currentanchor;
   // associative attributes
   private GShape selectedShape;
   private Anchorstate anchor;
   private EState eState=EState.idleTP;
   public void setSelectedShape(GShape selectedShape) {
      this.selectedShape = selectedShape;
      anchor = Anchorstate.anchorready;
    switch (this.selectedShape.geteDrawingType()) {
		case TP:
			eState=EState.idleTP; break;
		case NP:
			eState=EState.idleNP; break;
		default:
			break;
	}
   }
   public void setSelectedSkill(){
	   	anchor = Anchorstate.anchorpush;
   } 
   
   
   public GDrawingPanel() {
      MouseEventHandler mouseEventHandler = new MouseEventHandler();
      this.addMouseListener(mouseEventHandler);
      this.addMouseMotionListener(mouseEventHandler);
      shapeVector = new Vector<GShape>();
      anchorVector = new Vector<GShape>();
   }
   public void initialize() {
      // TODO Auto-generated method stub
      
   }
   public void paint(Graphics g) {
      for(GShape shape : this.shapeVector){
         shape.draw((Graphics2D)g);
      }
      for(GShape anc : this.anchorVector){
    	  anc.draw((Graphics2D)g);
      }
   } 
   private void initDrawing(int x, int y){
      this.currentShape = this.selectedShape.clone();
      Graphics2D g2D = (Graphics2D) this.getGraphics();
      g2D.setXORMode(this.getBackground());
      this.currentShape.initDrawing(x,y,g2D);
   }
   private void keepDrawing(int x, int y){
      Graphics2D g2D = (Graphics2D) this.getGraphics();
      g2D.setXORMode(this.getBackground());
      this.currentShape.keepDrawing(x,y,g2D);
   }
   private void continueDrawing(int x, int y){
      Graphics2D g2D = (Graphics2D) this.getGraphics();
      g2D.setXORMode(this.getBackground());
      this.currentShape.continueDrawing(x,y,g2D);
   }
   private void finishDrawing(int x, int y){
      Graphics2D g2D = (Graphics2D) this.getGraphics();
      g2D.setXORMode(this.getBackground());
      this.currentShape.finishDrawing(x,y,g2D);
      this.shapeVector.add(this.currentShape);
   }
   
   private void changeAnchors(int x, int y){
	   for (int i=0;i<shapeVector.size();i++) {
		   if(anchor == Anchorstate.anchorpush && shapeVector.get(i).contanins(x, y)){
			   System.out.println(shapeVector.get(i).hashCode());
			   System.out.println(shapeVector.size());
			   Graphics2D g2D = (Graphics2D) this.getGraphics();
			   shapeVector.get(i).drawAnchors(g2D);
			   System.out.println("test");
			   this.currentShape = this.selectedShape.clone();
			   this.shapeVector.add(this.currentShape);
			   System.out.println(this.currentShape);
			   break;  
		}
		  
	   }
   }
   
   private void changePointShape(int x, int y) {
		for (int i=0;i<shapeVector.size();i++) {
//			System.out.println(shapeVector.get(i).getClass());
			if (shapeVector.get(i).contanins(x, y)) {
//				System.out.println(shapeVector.get(i).getClass());
//				System.out.println(shapeVector.size());			
//				System.out.println("linetest");
				hourglassCursor =new Cursor(Cursor.WAIT_CURSOR);
				setCursor(hourglassCursor);
				break;
			} else {
				hourglassCursor=new Cursor(Cursor.DEFAULT_CURSOR);
				setCursor(hourglassCursor);
			}
		}
	}
   class MouseEventHandler 
      implements MouseInputListener, MouseMotionListener {
      
      @Override
      public void mouseClicked(MouseEvent e) {
         if(e.getClickCount() == 1){
            mouse1Clicked(e);
         }else if(e.getClickCount() == 2){
            mouse2Clicked(e);
         }
      }
      private void mouse1Clicked(MouseEvent e) {
         if (eState == EState.idleNP && anchor == Anchorstate.anchorready ) {
               initDrawing(e.getX(), e.getY());
               eState = EState.NPDrawing;
         }else if(eState == EState.NPDrawing){
            continueDrawing(e.getX(), e.getY());
         }else if(eState == EState.idleTP || eState == EState.idleNP && anchor == Anchorstate.anchorpush){
        	 //¿¨Ä¿
        	 changeAnchors(e.getX(), e.getY());
         }
      }
      private void mouse2Clicked(MouseEvent e) {
         if (eState == EState.NPDrawing) {      
            finishDrawing(e.getX(), e.getY());
            eState = EState.idleNP;
         }
      }
      @Override
      public void mousePressed(MouseEvent e) {
    	  if (eState == EState.idleTP && anchor == Anchorstate.anchorready) {
            initDrawing(e.getX(), e.getY());
            eState = EState.TPDrawing;
           
         }
      }
      @Override
      public void mouseReleased(MouseEvent e) {
         if (eState == EState.TPDrawing && anchor == Anchorstate.anchorready) {      
            finishDrawing(e.getX(), e.getY());
            eState = EState.idleTP;
         }
      }
      @Override
      public void mouseMoved(MouseEvent e) {
         if (eState == EState.NPDrawing) {      
            keepDrawing(e.getX(), e.getY());
         }else if (eState == EState.idleTP || eState == EState.idleNP) {
				changePointShape(e.getX(), e.getY());
			}
      }      
      @Override
      public void mouseDragged(MouseEvent e) {
         if (eState == EState.TPDrawing) {      
            keepDrawing(e.getX(), e.getY());
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



   



   
