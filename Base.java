import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
class Window {
  int height, width;
}
class Start extends JFrame{
  Base canvas;

  public Start(Base c) {
    canvas = c;
    add(canvas);
    canvas.setSize(1000,700);
    canvas.init();
    pack();
    setResizable(false);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    KeyListener keyListener = new KeyAdapter(){
      @Override
      public void keyPressed(KeyEvent e){
        canvas.keyPressed = true;
        switch (e.getKeyCode()){
          case KeyEvent.VK_LEFT:
          canvas.esquerda = true;
          break;
          case KeyEvent.VK_RIGHT:
          canvas.direita = true;
          break;
          case KeyEvent.VK_UP:
          canvas.cima = true;
          break;
          case KeyEvent.VK_DOWN:
          canvas.baixo = true;
          break;
          case KeyEvent.VK_ESCAPE :
          canvas.sair = true;
        }
      }
      public void keyReleased(KeyEvent e) {
        canvas.keyPressed = false;
        canvas.cima = false;
        canvas.direita = false;
        canvas.esquerda = false;
        canvas.baixo = false;
      }
    };

    addKeyListener(keyListener);
    canvas.addKeyListener(keyListener);


  }
}


public class Base extends Canvas {
  boolean keyPressed;
  boolean cima = false;
  boolean direita = false;
  boolean esquerda = false;
  boolean baixo = false;
  boolean sair = false;
  /** indica se um bot&atilde;o do mouse est&aacute; pressionado */
	boolean mousePressed = false;
	/** coordenada atual do cursor mouse */
	int mouseY, mouseX;
  Window window = new Window();
  Image offscreen = null;
  Graphics offgraphics = null;
  public MouseAdapter mouseAdapter;

  public Base(){
    Timer t = new Timer(15, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        repaint();
      }
    });
    t.setCoalesce(false);
    t.start();

    mouseAdapter = new MouseAdapter() {

      void mouse(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        // System.out.println(mouseX +"  "+mouseY );
      }

      @Override
      public void mouseMoved(MouseEvent e) {
        mouse(e);
      }

      @Override
      public void mouseDragged(MouseEvent e) {
        mouse(e);
      }

      @Override
      public void mousePressed(MouseEvent e) {
        mouse(e);
        mousePressed = true;
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        mouse(e);
        mousePressed = false;
      }
    };

    addMouseListener(mouseAdapter);
    addMouseMotionListener(mouseAdapter);
  }

  public void init(){};
  public void paint(Graphics g){};
  @Override

  public void setSize(int w, int h) {
    setPreferredSize(new Dimension(w, h));
    window.width = w;
    window.height = h;
  }
  @Override
  public void update(Graphics g) {
    if (window.height != getHeight() || window.width != getWidth() || offscreen == null) {
      window.height = getHeight();
      window.width = getWidth();
      offscreen = createImage(window.width, window.height);
      if (offgraphics != null) {
        offgraphics.dispose();
      }
      offgraphics = offscreen.getGraphics();
    }
    super.update(offgraphics);
    g.drawImage(offscreen, 0, 0, null);
  }
}
