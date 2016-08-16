import java.net.*;
import java.io.*;

public class Cliente {

		public static void main(String[] args){
				Socket socket = null;
				DataInputStream input;

				try {
           socket = new Socket("192.168.0.12", 80);
    		}

				try {
       			input = new DataInputStream(socket.getInputStream());
    		}
    		catch (IOException e) {
       		System.out.println(e);
    		}



		}

}
