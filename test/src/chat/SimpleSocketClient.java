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


public class SimpleSocketClient {
	
	String ServerName;
	int port;
	
	public SimpleSocketClient(String ServerName, int port) {
		this.ServerName = ServerName;
		this.port = port;
	}
	

	String message =  "dslp/1.2\r\nrequest time\r\ndslp/end\r\n";
	{

		try {
			Socket socket = openSocket(ServerName, port);			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String writeToAndReadFromSocket(Socket socket, String writeTo) throws Exception {
		try {
			// write text to the socket
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bufferedWriter.write(writeTo);
			bufferedWriter.flush();

			// read text from the socket
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str;
			StringBuilder sb = new StringBuilder();
			while ((str = bufferedReader.readLine()) != null) {
				sb.append(str + "\r\n");

				if (str.contains("dslp/1.2")) {

				}

				if (str.contains("")) {

				}

				if (str.contains("/end")) {
					break;

				}
			}

			// close the reader, and return the results as a String
			bufferedReader.close();

			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Open a socket connection to the given server on the given port. This method
	 * currently sets the socket timeout value to 10 seconds. (A second version of
	 * this method could allow the user to specify this timeout.)
	 */
	private Socket openSocket(String server, int port) throws Exception {
		Socket socket;

		// create a socket with a timeout
		try {
			InetAddress inteAddress = InetAddress.getByName(server);
			SocketAddress socketAddress = new InetSocketAddress(inteAddress, port);

			// create a socket
			socket = new Socket();

			// this method will block no more than timeout ms.
			int timeoutInMs = 10 * 1000; // 10 seconds
			socket.connect(socketAddress, timeoutInMs);

			return socket;
		} catch (SocketTimeoutException ste) {
			System.err.println("Timed out waiting for the socket.");
			ste.printStackTrace();
			throw ste;
		}
	}

	public void showGroupNotify (String groupName, Socket socket) throws Exception {
		String message = "dslp/1.2\\r\\ngroup notify\\r\\n\\"+groupName+"\\r\\\\ndslp/end\\r\\n";
		String start = writeToAndReadFromSocket(socket, message);
		String[] abc = start.split("\r\n");			
		
		for (int i = 0; i < abc.length; i++) {
			if (abc[i].contains("group notify")) {
				while(!abc[i].contains("dslp/end")){ // might be more than one line with chatmessages. so print all lines till /end
					System.out.println(abc[i]);
				}
			}
			if (abc[i].contains("dslp/end")) {
				break;
			}

		}		
	}
	
}
