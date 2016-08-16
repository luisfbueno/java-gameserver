import java.net.*;
import java.io.*;

public class Servidor {

    public static void main(String[] args){
      ServerSocket serverSocket = null;

      try{
        serverSocket = new ServerSocket(80);
      }catch(IOException e){
        System.out.println(e);
        System.exit(1);
      }

      Socket player1 = null;
      Socket player2 = null;

      try{
        System.out.println("Aguardando jogador 1");
        player1 = serverSocket.accept();
      } catch(IOException e){
        System.out.println(e);
      }

      try{
        System.out.println("Aguardando jogador 2");
        player2 = serverSocket.accept();
      } catch(IOException e){
        System.out.println(e);
      }



      }

      class Servindo extends Thread { //Thread de inicio do servidor

          Socket clientSocket;

          Servindo (Socket clientSocket){
            this.clientSocket = clientSocket;
          }
      }

  }
