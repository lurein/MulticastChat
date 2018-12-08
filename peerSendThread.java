public class peerSendThread implements Runnable {
	peerUtilites peerU;

	public peerSendThread(peerUtilites peerU) {
		this.peerU = peerU;
	}

	public void run() {
		String fromKeyboard = null;
		try {
			while (!(fromKeyboard = peerU.readFromKeyboard()).equalsIgnoreCase("bye")) {
				System.out.println(Thread.currentThread().getName() + " sending");
				peerU.sendToSocket(fromKeyboard);
				//fromSocket = peerU.readFromSocket();
				//peerU.sendToTerminal(fromSocket);
			}

		} catch (Exception E) {
		}
	}
}
