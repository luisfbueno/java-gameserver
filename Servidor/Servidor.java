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
          static DataInputStream is[] = new DataInputStream[2];
          static int playerCount = 0;
          private int playerNumber;
          private int arrayNumber;
          int mX,mY,pX,pY;

          Servindo (Socket clientSocket,int num){ //Construtor
            this.clientSocket = clientSocket;
            playerNumber = num;
            arrayNumber = num - 1;
            playerCount++;
          }

          public void run() {

            try{

              is[arrayNumber] = new DataInputStream(clientSocket.getInputStream());
              os[arrayNumber] = new DataOutputStream(clientSocket.getOutputStream());


             os[arrayNumber].writeInt(playerNumber);

             new trocaCliente().start();

            }catch(IOException e){
              System.out.println(e);
            }

         }

         class trocaCliente extends Thread {

           public void run() {

             do{

             if(playerNumber == 1){
                try{
                  mX = (is[arrayNumber]).readInt();
                  mY = (is[arrayNumber]).readInt();

                  os[arrayNumber].writeInt(pX);
                  os[arrayNumber].writeInt(pY);
                  os[arrayNumber].flush();
                  System.out.println("Player " + playerNumber + ": " + mX + " " + mY);
                }catch(IOException e){};

             }

             if(playerNumber == 2) {
               try{
                 pX = (is[arrayNumber]).readInt();
                 pY = (is[arrayNumber]).readInt();

                 os[arrayNumber].writeInt(mX);
                 os[arrayNumber].writeInt(mY);
                 os[arrayNumber].flush();
                 System.out.println("Player " + playerNumber + ": " + pX + " " + pY);
               }catch(IOException e){};

             }

             try{
 								sleep(1000/60);
 						}catch(InterruptedException e){};

           }while(true);

         }



      }

  }
