/*

Lucas Spartacus
O codigo recebe a mensagem enviada pelo cliente e inverte a mesma, após isso retorna a mensagem para o cliente
*/
import java.io.*;
import java.net.*;

class Servidor
{
   private static int portaServidor = 9871;
   private static byte[] receiveData = new byte[1024];
   private static byte[] sendData = new byte[1024];
   
   private static byte[] receiveData2 = new byte[1024];
   private static byte[] sendData2 = new byte[1024];

   public static void main(String args[]) throws Exception
   {
      DatagramSocket serverSocket = new DatagramSocket(portaServidor);
		
		int pacote = 1;
	
      while(true) 
      {
		  if(pacote % 2 != 0){
		 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

         System.out.println("Aguardando datagrama do cliente....");
         serverSocket.receive(receivePacket);

         System.out.println("RECEIVED: " + new String(receivePacket.getData()));
         InetAddress ipCliente = receivePacket.getAddress();
         int portaCliente = receivePacket.getPort();
		 
         sendData = (new String(receivePacket.getData())).getBytes();

		
		 	int j=0;
		
		
		  byte[] result = new byte[sendData.length];
		 for (int i = 0; i < sendData.length; i++){
	
		
		//verifica se o byte nao e vazio e inveret para o serultado final	
		 if(sendData[sendData.length - i - 1] != 0){
				j++;
			  result[j] = sendData[sendData.length - i - 1];
		 }
			
			 
		 }

       
	 
         DatagramPacket sendPacket = new DatagramPacket(result, sendData.length, ipCliente, portaCliente);
         serverSocket.send(sendPacket);
		
		
		  }

	  else{
		  DatagramPacket receivePacket2 = new DatagramPacket(receiveData2, receiveData2.length);

         System.out.println("Aguardando datagrama do cliente....");
         serverSocket.receive(receivePacket2);

         System.out.println("RECEIVED: " + new String(receivePacket2.getData()));
         InetAddress ipCliente = receivePacket2.getAddress();
         int portaCliente = receivePacket2.getPort();
		 
		 //minusculas
        sendData2 = (new String(receivePacket2.getData())).toLowerCase().getBytes();

		
		 	int j=0;
		
		
		  byte[] result2 = new byte[sendData2.length];
		 for (int i = 0; i < sendData2.length; i++){
	    
		
		//Retira os espacos da mensagem	
		 if(sendData2[i] != 32){
				
			 result2[j] = sendData2[i];
			  j++;
		 }
		
		 }
         DatagramPacket sendPacket2 = new DatagramPacket(result2, sendData2.length, ipCliente, portaCliente);
         serverSocket.send(sendPacket2);
	
         System.out.println("Enviado...");
		  
      
       }
	   	pacote++;
	 }
   }
}
