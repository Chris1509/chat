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

public class SimpleSocketClient {
	
	String testServerName = "localhost";
    int port = 33333;
    try
    {
	
      Socket socket = openSocket(testServerName, port);
	
      String start = writeToAndReadFromSocket(socket, "dslp/1.2\r\nrequest time\r\ndslp/end\r\n");
      String[] abc = start.split("\r\n");
      for(int i=0; i<abc.length; i++){
        if(abc[i].contains("dslp/1.2")){
			
          System.out.println("Verbunden zu " + socket.getInetAddress() +":"+ socket.getPort());
        }
        if(abc[i].contains("response time")){
        String input = abc[i+1];
		ZonedDateTime z = ZonedDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
		String output = z.format(DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy"));
        System.out.println("Aktuelle Zeit auf dem Server: " + output);
        }
        if(abc[i].contains("dslp/end")) {
          break;
        }

      }

    //  System.out.println(start);
   //  System.out.println(result);
   //  System.out.println(end);
      // close the socket, and we're done
      socket.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  private String writeToAndReadFromSocket(Socket socket, String writeTo) throws Exception
  {
    try
    {
      // write text to the socket
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      bufferedWriter.write(writeTo);
      bufferedWriter.flush();

      // read text from the socket
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String str;
      StringBuilder sb = new StringBuilder();
      while ((str = bufferedReader.readLine()) != null)
      {
        sb.append(str + "\r\n");

       if(str.contains("dslp/1.2")){

        }

        if(str.contains("")){

        }

        if(str.contains("/end")){
            break;

        }
      }

      // close the reader, and return the results as a String
      bufferedReader.close();

      return sb.toString();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * Open a socket connection to the given server on the given port.
   * This method currently sets the socket timeout value to 10 seconds.
   * (A second version of this method could allow the user to specify this timeout.)
   */
  private Socket openSocket(String server, int port) throws Exception
  {
    Socket socket;


    // create a socket with a timeout
    try
    {
      InetAddress inteAddress = InetAddress.getByName(server);
      SocketAddress socketAddress = new InetSocketAddress(inteAddress, port);

      // create a socket
      socket = new Socket();

      // this method will block no more than timeout ms.
      int timeoutInMs = 10*1000;   // 10 seconds
      socket.connect(socketAddress, timeoutInMs);

      return socket;
    }
    catch (SocketTimeoutException ste)
    {
      System.err.println("Timed out waiting for the socket.");
      ste.printStackTrace();
      throw ste;
    }
  }

}


}


}
