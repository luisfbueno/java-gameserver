import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Cliente extends Thread {
		Socket socket = null;
	  static DataInputStream is = null;
		static DataOutputStream os = null;
		JogoBase game;
		JFrame gameWindow;
		int playerNumber;

		public Cliente(JogoBase tela) { //Construtor
			game = tela;
			gameWindow = new Start(game);
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
								os.writeBoolean(game.getClicked());

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
								game.setClicked(is.readBoolean());
								game.repaint();
								//System.out.println("Player " + playerNumber + ": " + game.getPlayerX(); + " " + game.getMiraY());
							}catch(IOException e){};
						}

						try{
								sleep(1000/480);
						}catch(InterruptedException e){};

					}while(true);

					}

			}
}
