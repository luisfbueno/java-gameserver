import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

class obstaculos extends Base{
	Rectangle coor;
	obstaculos(int x, int y, int largura, int altura){
    coor= new Rectangle(x, y, largura, altura);
	}
}
class JogoBase extends Base{
	boolean d = false, e = false, c = false, b = false;
	Image img[] = new Image[5];
	int posX = 50,posY = 500, altura = 97, largura = 111,i=0;
	Rectangle player = new Rectangle(posX, posY, 111,97);
	obstaculos ob[] = new obstaculos[10];
    public void init() {
      try {
        img[0]= ImageIO.read(new File("./img/back.png"));
        img[1]= ImageIO.read(new File("./img/andando.png"));
        img[2]= ImageIO.read(new File("./img/star.png"));
        img[3]= ImageIO.read(new File("./img/plat1.png"));
				img[4]= ImageIO.read(new File("./img/andando1.png"));

      } catch (IOException e) {
        System.out.println("Erro ao carregar uma imagem!");
        System.exit(1);
      }
			ob[2] = new obstaculos(200,200,272,72);
			ob[0] = new obstaculos(450,450,272,72);
			ob[1] = new obstaculos(600,50,272,72);
    }

    public void paint(Graphics g) {
      super.paint(g);
			g.drawImage(img[0], 0, 0, getSize().width, getSize().height, this);
			g.drawImage(img[2],450,20, 60, 55, this);
			g.drawImage(img[3],450,450, 272, 72, this);
			g.drawImage(img[3],600,50, 272, 72, this);
			g.drawImage(img[3],200,200, 272, 72, this);

			if(largura > 0)
			g.drawImage(img[1],posX,posY, 111, altura, this);
			else
			g.drawImage(img[4],posX,posY, 111, altura, this);
			System.out.println(posX + "     " + posY   + "    " + player);
			if(direita){
				if(largura < 0){
					largura = -largura;
				}
				player.setLocation(posX+5, posY); // Somando +5, pois quero saber se vai colidir, não se está colidindo
				if(!colisao())
				posX += 5;
			}
			else if(esquerda){
				if(largura > 0){
				largura = -largura;
			}
				player.setLocation(posX-5, posY);// Somando -5, pois quero saber se vai colidir, não se está colidindo
				if(!colisao())
				posX -= 5;
			}
			else if (cima){
				if(largura > 0)
				player.setLocation(posX-5, posY-5);
				else
				player.setLocation(posX+5, posY-5);
				if(!colisao())
				posY -= 5;
			}
			else if (baixo){
				if(largura > 0)
				player.setLocation(posX-5, posY+5);
				else
				player.setLocation(posX+5, posY+5);
				if(!colisao())
				posY += 5;
			}

    }
		boolean colisao(){  //Verifica se está colidindo com as determinadas plataformas
			if(player.intersects(ob[0].coor) || player.intersects(ob[1].coor) || player.intersects(ob[2].coor)){
				return true;
			}
			return false;
		}
  static public void main(String[] args){
    Base canvas = new JogoBase();
    new Start(canvas);
  }

}
