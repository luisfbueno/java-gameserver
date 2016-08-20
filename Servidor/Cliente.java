import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Cliente extends Thread {
		Socket socket = null;
	  static DataInputStream is = null;
		static DataOutputStream os = null;
		JFrame window;
		Teste game;
		int playerNumber;

		public Cliente(Teste tela) { //Construtor
			game = tela; //Recebe JPanel
			//Cria frame para guardar o panel
			window = new JFrame("Jogo");
			window.add(tela);
			window.setVisible(true);
			window.setSize(400,400);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		}

		public void run(){

				try {
						socket = new Socket("127.0.0.1", 80);

						//Pega streams do servidor
      			is = new DataInputStream(socket.getInputStream());
						os = new DataOutputStream(socket.getOutputStream());

						playerNumber = is.readInt(); //pega numero do player

						game.setPlayerType(playerNumber); //Manda para o jogo qual o tipo de player

						new trocaServidor().start(); //Inicia thread de troca de dados entre servidor-cliente

				}catch(IOException e){
						System.out.println(e);
						}
					}

					class trocaServidor extends Thread {


						public void run() {

						do{

						if(playerNumber == 1){ //Se for player 1, manda posições da mira e recebe do player

							try{
								os.writeInt(game.getMiraX());
								os.writeInt(game.getMiraY());

								//os.flush();

								game.setPlayerX(is.readInt());
								game.setPlayerY(is.readInt());
								game.repaint();
								//System.out.println("Player " + playerNumber + ": " + game.getMiraX(); + " " + game.getMiraY());
							}catch(IOException e){};
						}

						else if(playerNumber == 2){ //Se for player 2, manda posições do player e recebe da mira
							try{
								os.writeInt(game.getPlayerX());
								os.writeInt(game.getPlayerY());

								//os.flush();

								game.setMiraX(is.readInt());
								game.setMiraY(is.readInt());
								game.repaint();
								//System.out.println("Player " + playerNumber + ": " + game.getPlayerX(); + " " + game.getMiraY());
							}catch(IOException e){};
						}

						try{
								sleep(1000/60);
						}catch(InterruptedException e){};

					}while(true);

					}

			}
}
