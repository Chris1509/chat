package chat;

import java.net.Socket;

public class ReceiveThread extends Thread {


	public void run() {		
		System.out.println("Starte Thread Receive");
		
		SimpleSocketClient c = new SimpleSocketClient("127.0.0.1",33333);
		
		c.showGroupNotify("Montag-Teams", socket);

	}
}
