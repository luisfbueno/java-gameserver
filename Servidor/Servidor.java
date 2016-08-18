import java.net.*;
import java.io.*;

public class Servidor {

    public static void main(String[] args){
      ServerSocket serverSocket = null;

      try{ //Abre socket do Servidor
        serverSocket = new ServerSocket(80);
      }catch(IOException e){
        System.out.println(e);
        System.exit(1);
        }

      Socket player1 = null;
      Socket player2 = null;

      try{ //Aguarda cliente 1
        System.out.println("Aguardando jogador 1");
        player1 = serverSocket.accept();
				System.out.println("Aguardando jogador 2");
        player2 = serverSocket.accept();
      } catch(IOException e){
        System.out.println(e);
        }

      new Servindo(player1,1).start();
      new Servindo(player2,2).start();

      try {
  			serverSocket.close();
  		} catch (IOException e) {
  			e.printStackTrace();
  		    }

        }
    }
      class Servindo extends Thread { //Thread de servidor

          Socket clientSocket;
          static DataOutputStream os[] = new DataOutputStream[2];
          static int playerCount = 0;
          private int playerNumber;
          private int arrayNumber;

          Servindo (Socket clientSocket,int num){ //Construtor
            this.clientSocket = clientSocket;
            playerNumber = num;
            arrayNumber = num - 1;
            playerCount++;
            System.out.println(playerCount);
          }

          public void run() {

            try{

              DataInputStream is = new DataInputStream(clientSocket.getInputStream());
              os[arrayNumber] = new DataOutputStream(clientSocket.getOutputStream());


             os[arrayNumber].writeInt(playerNumber);

            }catch(IOException e){
              System.out.println(e);
            }

         }


     }
