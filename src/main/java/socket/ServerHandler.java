package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable{
	private Socket socket;
	public ServerHandler(Socket socket){
		this.socket = socket;
	}
	public void run() {
		BufferedReader reader = null;
		PrintWriter writer = null;
		String body = null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(this.socket.getOutputStream(), true);
			while(true){
				 body = reader.readLine();
	                if(body == null) break;
	                System.out.println("Server :" + body);
	                writer.println("服务器端回送响的应数据.");
			} 
		}catch (IOException e) {
					e.printStackTrace();
		}finally{
			 if(reader != null){
	                try {
	                	reader.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if(writer != null){
	                try {
	                	writer.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            if(socket != null){
	                try {
	                    socket.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            socket = null;
		}
	}
}
