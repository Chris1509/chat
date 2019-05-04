package chat;


public class Main {

	public static void main(String[] args) {
		
		SimpleSocketClient c = new SimpleSocketClient("127.0.0.1",33333);

		ReceiveThread t1 = new ReceiveThread();
		SendThread t2 = new SendThread();
				
		t1.start();
		t2.start();
	
	}

}