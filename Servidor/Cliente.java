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

							int playerNumber = is.readInt();

							if(playerNumber == 2)
								game.setPlaying(true);

							System.out.println(playerNumber);

				}catch(IOException e){
						System.out.println(e);
						}



		}
/*
		public static void main(String[] args){
				new Cliente(null).start();
		}*/
}
