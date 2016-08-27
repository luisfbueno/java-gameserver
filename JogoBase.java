import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

class obstaculos extends Base {
	Rectangle coor;
	obstaculos(int x, int y, int largura, int altura){
		coor= new Rectangle(x, y, largura, altura);
	}
}
class JogoBase extends Base {
	boolean d = false, e = false, c = false, b = false, pegouEstrela = false, acertou = false;
	Image img[] = new Image[11];
	int posX = 50,posY = 500, altura = 97, largura = 111, i=0 , vidaPlayer = 5, imgVida = 5 ,tempo=0;
	Rectangle player = new Rectangle(posX, posY, 111,97);
	Rectangle mira = new Rectangle(mouseX, mouseY, 1,1);
	obstaculos ob[] = new obstaculos[5];
	int playerType;

	public void init() {
		try {
			img[0]= ImageIO.read(new File("img/back.png"));
			img[1]= ImageIO.read(new File("img/andando.png"));
			img[2]= ImageIO.read(new File("img/star2.png"));
			img[3]= ImageIO.read(new File("img/plat1.png"));
			img[4]= ImageIO.read(new File("img/andando1.png"));
			img[5]= ImageIO.read(new File("img/heart.png"));
			img[6]= ImageIO.read(new File("img/mira.png"));
			img[7]= ImageIO.read(new File("img/sniper.png"));
			img[8]= ImageIO.read(new File("img/dano.png"));
			img[9]= ImageIO.read(new File("img/dano1.png"));
			img[10]= ImageIO.read(new File("img/boo-venceu.png"));

		} catch (IOException e) {
			System.out.println("Erro ao carregar uma imagem!");
			System.exit(1);
		}
		ob[2] = new obstaculos(160,230, 272, 72);
		ob[0] = new obstaculos(450,480, 272, 72);
		ob[1] = new obstaculos(600,80, 272, 72);
		ob[3] = new obstaculos(450,20,50,50);
		ob[4] = new obstaculos(450,20,60,60);

	}

	public void paint(Graphics g) {
		super.paint(g);
		// Desenha o plano de fundo
		g.drawImage(img[0], 0, 0, getSize().width, getSize().height, this);


		System.out.println("   " + pegouEstrela);
		// System.out.println(boo + " "+ playerType + " " + direita);

		//O i é a variável de loop para controlar o print da vida do Player é usada no while seguinte.
		if(i >= vidaPlayer){
			i = 0;
			//essa variável imgVida é apenas para desenhar um pouco mais para a direita, por isso a inicio com 5
			imgVida = 5;
		}

		//esse if é para ver se o jogador está vivo (não perdeu) ou se ele não pegou a estrela (não ganhou)
		if (vidaPlayer != 0 && !pegouEstrela) {
			if (player.intersects(ob[4].coor) && playerType == 1){
				pegouEstrela = true;
			}
			//Desenha a estrela e os obstáculos
			g.drawImage(img[2],450,40, 60, 60, this);
			g.drawImage(img[3],450,480, 272, 72, this);
			g.drawImage(img[3],600,80, 272, 72, this);
			g.drawImage(img[3],160,230, 272, 72, this);

			//loop para desenhar a vida, como disse lá em cima, usa a variável i para o loop,
			while(i != vidaPlayer){
				g.drawImage(img[5],imgVida,5, 50, 50, this);
				// eu somo 60 para que a imgem não fique lado a lado, elas tem 50px então tem 10px de distância entre elas
				imgVida += 60;
				i++;
				// System.out.println(i);
			}
			//Essa é a animação do dano, como tem uma variável tempo, que determina o tempo dos tiros,
			//ela também determina quando o dano vai ser animado. Que é o intervalo entre 0 < tempo < 21
			if(tempo>=1 && tempo <=20){
				//condição para printar direita ou esquerda
				if(largura > 0)
				g.drawImage(img[8],posX,posY, 111, altura, this);
				else
				g.drawImage(img[9],posX,posY, 111, altura, this);
			}
			else{
				//condição para printar direita ou esquerda
				if(largura > 0)
				g.drawImage(img[1],posX,posY, 111, altura, this);
				else
				g.drawImage(img[4],posX,posY, 111, altura, this);
				// System.out.println(posX + "   " + posY   + "  " + player + "  " + pegouEstrela);
			}

			//andou para a direita.
			if(!boo){
			if(direita){
				if(largura < 0){
					largura = -largura;
				}
				//detrminando a localização do rectangle
				player.setLocation(posX+5, posY); // Somando +5, pois quero saber se vai colidir, não se está colidindo
				if(!colisao())
				posX += 5;
			}
			//andou para esquerda
			else if(esquerda){
				if(largura > 0){
					largura = -largura;
				}
				//detrminando a localização do rectangle
				player.setLocation(posX-5, posY);// Somando -5, pois quero saber se vai colidir, não se está colidindo
				if(!colisao())
				posX -= 5;
			}
			//andou para cima
			else if (cima){
				//detrminando a localização do rectangle
				if(largura > 0)
				player.setLocation(posX-5, posY-5);
				//detrminando a localização do rectangle
				else
				player.setLocation(posX+5, posY-5);
				if(!colisao())
				posY -= 5;
			}
			// andou para baixo
			else if (baixo){
				//detrminando a localização do rectangle
				if(largura > 0)
				player.setLocation(posX-5, posY+5);
				//detrminando a localização do rectangle
				else
				player.setLocation(posX+5, posY+5);
				if(!colisao())
				posY += 5;
			}
		}
		else{
			player.setLocation(posX, posY);
		}
			//verifica se o tiro acertou o player
			if(player.intersects(mira) && mousePressed && tempo == 0){
				acertou = true;
				vidaPlayer --;
			}
			//loop de um tiro para o outro de 110 quadros.
			if(acertou){
				tempo++;
				if(tempo == 110){
					acertou = false;
					tempo = 0;
				}
			}
			//rectangle da mira, mudando a posição
			mira.setLocation(mouseX, mouseY);
			//desenhando a mira


			g.drawImage(img[6],mouseX-50,mouseY-50, 100, 100, this);
		}

		//O jogo acabou, player ganhou ou perdeu
		else{
			//Se ele ganhou, printa a imagem de ganho
			if(pegouEstrela){
				g.drawImage(img[10], 120,100, 800, 500, this);
			}
			//se perdeu, printa a imagem de perca
			else
			g.drawImage(img[7], 120,100, 800, 500, this);
		}
		//se pressionado ESC durante qualquer momento do jogo, o jogo fecha.
		if(sair){
			System.exit(1);
		}

	}

	//Getters e Setters

	public boolean getClicked(){
		return mousePressed;
	}

	public void setClicked (boolean b){
		this.mousePressed = b;
	}

	public int getMiraX(){
		return mouseX;
	}

	public void setMiraX(int n){
		this.mouseX = n;
	}

	public int getMiraY(){
		return mouseY;

	}

	public void setMiraY(int n){
		this.mouseY = n;
	}

	public int getPlayerX(){
		return posX;
	}

	public void setPlayerX(int n){
		this.posX = n;
	}

	public int getPlayerY(){
		return posY;
	}

	public void setPlayerY(int n){
		this.posY = n;
	}

	public void setPlayerType(int n){
		setTypee(n);
		this.playerType = n;
	}

	public int getLargura (){
		return largura;
	}

	public void setLargura(int n) {
		this.largura = n;
	}

	boolean colisao(){  //Verifica se está colidindo com as determinadas plataformas
		if(player.intersects(ob[0].coor) || player.intersects(ob[1].coor) || player.intersects(ob[2].coor)){
			return true;
		}
		else if (player.intersects(ob[3].coor) && playerType == 2){
			pegouEstrela = true;
			return false;
		}
		return false;
	}
	static public void main(String[] args){
		new Cliente(new JogoBase()).start();
	}

}
