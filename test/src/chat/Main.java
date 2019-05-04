package chat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

	public static void main(String[] args) {
		
		new SimpleSocketClient();

		ReceiveThread t1 = new ReceiveThread();
		SendThread t2 = new SendThread();
		

		t1.start();
		t2.start();
	}
	
	SimpleSocketClient()
	  {
	    