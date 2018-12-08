import java.net.*;
import java.io.*;
import java.util.*;

public class Server{
	
	static DatagramSocket socket;
	static byte[] buf = new byte[256];
	static String[] chatroomsArray = {"233.1.2.3", "5555", "Elections Meddling", "233.1.2.4", "5556", "Easier Majors??", "233.1.2.5", "5557", "Fire Addazio"};
	static String chatroomsString = "233.1.2.3, 5555, Elections Meddling, 233.1.2.4, 5556, Easier Majors??, 233.1.2.5, 5557, Fire Addazio ";

	public static void main(String args[]) throws IOException {
		socket = new DatagramSocket(4445);
		while(true) {
			
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			System.out.println("Server Running...");
			socket.receive(packet);
			InetAddress address = packet.getAddress();
            int port = packet.getPort();
            
			
			byte[] sendBuf = chatroomsString.getBytes();
            packet = new DatagramPacket(sendBuf, sendBuf.length, address, port);
           
            String received = new String(packet.getData(), 0, packet.getLength());
           
          if (received.equals("end")) {
              break;
          }
          socket.send(packet);
          System.out.println("Chat Room Table Sent");
		}
		socket.close();
	}
	
}