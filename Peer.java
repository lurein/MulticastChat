import java.net.*;
import java.io.*;
import java.util.*;

public class Peer {
	
	static DatagramSocket socket;
	static InetAddress address;
	static String[] chatroomsArray = new String[10]; 

	public static void main(String args[]) throws Exception {
		peerSendThread PST;
		peerReceiveThread PRT;
		byte[] buf;
		address = InetAddress.getByName("localhost");
		socket = new DatagramSocket();
		String msg = "hi";
		
		buf = msg.getBytes();
		DatagramPacket packet  = new DatagramPacket(buf, buf.length, address, 4445);
		socket.send(packet);
		byte[] recvBuf = new byte[256];
        packet = new DatagramPacket(recvBuf, recvBuf.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
		System.out.println("Multicast IP Address, Port Number, Chat Room Title:");
		stringFormatter(received);
		
		Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Multicast IP Address");
        String chosenAddress = keyboard.nextLine();
        System.out.println("Enter Port #");
        int chosenPort = keyboard.nextInt();
        
		
		System.setProperty("java.net.preferIPv4Stack", "true");
		peerUtilites peerU = new peerUtilites(chosenAddress,chosenPort);
		peerU.joinGroup();
		
		PST = new peerSendThread(peerU);
		Thread T = new Thread(PST);
		T.start();
		PRT = new peerReceiveThread(peerU);
		Thread TT = new Thread(PRT);
		TT.setDaemon(true);
		TT.start();
		T.join();   //wait for send thread to go away
		peerU.leaveGroup();
		keyboard.close();
	} 
	
	static String[] stringFormatter(String s) {
		String[] lines = s.split(";");
		for(String line : lines) {
			System.out.println(line);
		}
		return lines;
	}
} 
