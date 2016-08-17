import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Teste extends JPanel implements MouseMotionListener {
	private int posX,posY;

	Teste() { //Construtor

			this.setSize(400,400);
			this.addMouseMotionListener(this);
			this.setVisible(true);
	}

	public void mouseMoved(MouseEvent e){
		posX = e.getX();
		posY = e.getY();
		System.out.println(posX + "  " + posY);
		repaint();
	}

	public void mouseDragged(MouseEvent e){

	}

	public void paintComponent (Graphics g) { //Função de desenho
		super.paintComponent(g);
		g.drawOval(posX,posY,30,30);
	}

	public static void main(String[] args){
			JFrame f = new JFrame("Jogo");
			f.add(new Teste());
			f.setVisible(true);
			f.setSize(400,400);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
