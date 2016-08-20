import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Teste extends JPanel implements MouseMotionListener {
	private int miraX,miraY;
	private int playerX,playerY;
	private int health = 50;
	private boolean clicked;
	private boolean isPlaying;
	private int playerType;

	Teste() { //Construtor

			this.setSize(400,400);
			this.addMouseMotionListener(this);
			this.setVisible(true);
			this.isPlaying = false;
			miraX = miraY = playerX = playerY = -100;
	}

	//Funções do MouseMotionListener
	public void mouseMoved(MouseEvent e){
		if(playerType == 1){
			miraX = e.getX();
			miraY = e.getY();
		}

		else if(playerType == 2){
			playerX = e.getX();
			playerY = e.getY();
		}

		repaint();
	}

	public void mouseDragged(MouseEvent e){

	}

	public void paintComponent (Graphics g) { //Função de desenho
		super.paintComponent(g);


			g.drawOval(miraX,miraY,30,30);
			g.drawRect(playerX,playerY,30,30);

			System.out.println("Mira: " + miraX + " " + miraY + "Player: " + playerX + " " + playerY);


		}

	//Getters e Setters

	public void setPlayerType(int n){
		this.playerType = n;
	}

	public void setMiraX (int n){
		this.miraX = n;
		}

	public int getMiraX () {
		return miraX;
		}

	public void setMiraY (int n){
		this.miraY = n;
		}

	public int getMiraY () {
		return miraY;
		}

	public void setPlayerX (int n){
		this.playerX = n;
		}

	public int getPlayerX () {
		return playerX;
		}

	public void setPlayerY (int n){
		this.playerY = n;
		}

	public int getPlayerY () {
		return playerY;
		}

	public void setHealth (int n){
		this.health = n;
		}

	public int getHealth () {
		return health;
		}

	public void setClicked (boolean b){
		this.clicked = b;
		}

	public boolean getClicked(){
		return clicked;
		}

		public void setPlaying (boolean b){
			this.isPlaying = b;
			repaint();
			}

		public boolean getPlaying(){
			return isPlaying;
			}

	public static void main(String[] args){

			new Cliente(new Teste()).start();
	}

}
