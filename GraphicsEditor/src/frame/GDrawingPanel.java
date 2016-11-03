package frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import contant.GConstants.EDrawingType;
import shapes.GShape;


public class GDrawingPanel extends JPanel {
   // attributes
   private static final long serialVersionUID = 1L;
   // object states
   private enum EState {idle, NPDrawing, TPDrawing};
   //components;
   private Vector<GShape> shapeVector;
   
   // working variables
   
   private GShape currentShape;
   // associative attributes
//   private EToolBarButton eSelectedTool;
   private GShape selectedShape;
   private EState eState;
   
   public void setSelectedShape(GShape selectedShape) {
      this.selectedShape = selectedShape;
      this.eState = EState.idle;
   }
   
   public GDrawingPanel() {
      MouseEventHandler mouseEventHandler = new MouseEventHandler();
      this.addMouseListener(mouseEventHandler);
      this.addMouseMotionListener(mouseEventHandler);
      shapeVector = new Vector<GShape>();
   }
   public void initialize() {
      // TODO Auto-generated method stub
      
   }
   public void paint(Graphics g) {
      for(GShape shape : this.shapeVector){
         shape.draw((Graphics2D)g);
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
         if (eState == EState.idle) {
            if(selectedShape.geteDrawingType() == EDrawingType.NP){
               initDrawing(e.getX(), e.getY());
               eState = EState.NPDrawing;
            }
         }else if(eState == EState.NPDrawing){
            continueDrawing(e.getX(), e.getY());
         }
      }
      private void mouse2Clicked(MouseEvent e) {
         if (eState == EState.NPDrawing) {      
            finishDrawing(e.getX(), e.getY());
            eState = EState.idle;
         }
      }
      @Override
      public void mousePressed(MouseEvent e) {
         if (eState == EState.idle) {
            initDrawing(e.getX(), e.getY());
            eState = EState.TPDrawing;
         }
      }
      @Override
      public void mouseReleased(MouseEvent e) {
         if (eState == EState.TPDrawing) {      
            finishDrawing(e.getX(), e.getY());
            eState = EState.idle;
         }
      }
      @Override
      public void mouseMoved(MouseEvent e) {
         if (eState == EState.NPDrawing) {      
            keepDrawing(e.getX(), e.getY());
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



   



   
