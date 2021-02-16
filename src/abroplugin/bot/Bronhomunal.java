package abroplugin.bot;

import abroplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import static org.bukkit.Bukkit.getServer;

public class Bronhomunal extends Thread {
	
	private ServerSocket serverSocket;
	private LinkedList<BotConnection> connections = new LinkedList<BotConnection>();
	public static Bronhomunal Bot;


	public Bronhomunal(){
		Bot = this;

		start();
	}


	public void run() {
		Main.Plugin.broadcast("Started Bronhomunal");
		try {
			serverSocket = new ServerSocket(25564);
			System.out.println("Started server socket @"+serverSocket.getLocalPort());
		}
		catch(Exception e) {
			System.out.println("Couldn't setup server socket: \n"+e.getMessage());
			return;
		}
		
		try {
            while (true) {
            	try {
            		Socket socket = serverSocket.accept();
                    try {
                    	connections.add(new BotConnection(socket));
                    } catch (IOException e) {
						System.out.println(e.getMessage());
                        socket.close();
                    }

            	}catch(Exception e) {System.out.println(e.getMessage());}
            }
        } finally {
        	try {
        		serverSocket.close();
        	}catch(Exception e) {}
        }
	}
	
	
	public void execute(String text) {
		String normalizedText = text.replace("<nl>", "\n");
		String[] parts = normalizedText.split("\\/\\/");
		String header = parts[0];
		String body = parts.length > 1 ? parts[1] : "";

		if(normalizedText.startsWith("@chat")){
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.sendMessage(body);
			}
			//Main.broadcast(body);
		}

		if(normalizedText.startsWith("@playerslist")){
			String respond = "@text//\n";
			for(Player player : getServer().getOnlinePlayers()){
				respond += player.getName()+"\n";
			}
			sendMessage(respond);
		}
	}


	public void sendMessage(String message) {
		String encodedMessage = message.replace("\n", "<nl>");
		for(BotConnection connection : connections) {
			connection.send(encodedMessage);
		}
	}
}
