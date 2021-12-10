/*

Aluno: Lucas Spartacus(655455)
Projeto 
Redes I - PUC - Minas
Nosso codigo recebe a mensagem enviada pelo cliente e inverte a mesma, após isso retorna a mensagem para o cliente
*/
import java.io.*;
import java.net.*;

class Cliente
{
   private static int portaServidor = 9871;
   private static byte[] sendData = new byte[1024];
   private static byte[] receiveData = new byte[1024];
   
   private static byte[] sendData2 = new byte[1024];
   private static byte[] receiveData2 = new byte[1024];

   public static byte[] lerString () throws Exception {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      return in.readLine().getBytes();
   }

   public static void main(String args[]) throws Exception
   {
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress ipServidor = InetAddress.getByName("192.168.100.26");
	  
      sendData = lerString();

      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipServidor, portaServidor);
      clientSocket.send(sendPacket);

	  
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);	  
	  sendData2 = lerString();

      DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, ipServidor, portaServidor);
      clientSocket.send(sendPacket2);

      DatagramPacket receivePacket2 = new DatagramPacket(receiveData2, receiveData2.length);
      clientSocket.receive(receivePacket2);
      clientSocket.close();

      System.out.println("Retorno do primeiro pacote do servidor:" + new String(receivePacket.getData()));
	  System.out.println("Retorno do segundo pacote do servidor: " + new String(receivePacket2.getData()));
   }
}
