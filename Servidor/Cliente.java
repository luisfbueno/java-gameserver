import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Cliente extends Thread {
		Socket socket = null;
	  static DataInputStream is = null;
		static DataOutputStream os = null;
		JFrame programa;

		public Cliente(JFrame tela) { //Construtor
			this.programa = tela;

		}

		public void run(){

				try {
						socket = new Socket("127.0.0.1", 80);

      			is = new DataInputStream(socket.getInputStream());
						os = new DataOutputStream(socket.getOutputStream());

						int playerNumber = is.readInt();

						System.out.println(playerNumber);

				}catch(IOException e){
						System.out.println(e);
						}

				System.out.println("Cliente Conectou!");

		}

		public static void main(String[] args){
				new Cliente(null).start();
		}
}
