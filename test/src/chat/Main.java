package chat;


public class Main {

	public static void main(String[] args) {
		
		new SimpleSocketClient();

		ReceiveThread t1 = new ReceiveThread();
		SendThread t2 = new SendThread();
		

		t1.start();
		t2.start();
	}

}