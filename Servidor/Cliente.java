import java.net.*;
import java.io.*;

public class Cliente extends Thread {
		Socket socket = null;
	  static DataInputStream is = null;
		static DataOutputStream os = null;

		public void run(){

				try {
						socket = new Socket("127.0.0.1", 80);
				}catch(IOException e){
						System.out.println(e);
						}

		}

		public static void main(String[] args){
				new Cliente().start();
		}
}
