package chat;


public class Main {

	public static void main(String[] args) {
		
		SimpleSocketClient c = new SimpleSocketClient("127.0.0.1",33333);

		ReceiveThread receivce = new ReceiveThread();
		SendThread send = new SendThread();
						
		receivce.start();
		send.start();
	
	}

}