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
			game = tela;
			window = new JFrame("Jogo");
			window.add(tela);
			window.setVisible(true);
			window.setSize(400,400);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		}

		public void run(){

				try {
						socket = new Socket("127.0.0.1", 80);

      			is = new DataInputStream(socket.getInputStream());
						os = new DataOutputStream(socket.getOutputStream());

							playerNumber = is.readInt();

							game.setPlayerType(playerNumber);

							new trocaServidor().start();

				}catch(IOException e){
						System.out.println(e);
						}
					}

					class trocaServidor extends Thread {


						public void run() {

						do{

						if(playerNumber == 1){
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

						else if(playerNumber == 2){
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
