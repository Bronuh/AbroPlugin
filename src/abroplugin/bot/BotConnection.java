package abroplugin.bot;

import abroplugin.Main;

import java.io.*;
import java.net.Socket;

class BotConnection extends Thread{
	
	private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private BufferedReader is;
    private PrintStream os;


    public BotConnection(Socket socket) throws IOException {
        this.socket = socket;
        is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        os = new PrintStream(socket.getOutputStream());
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        start();
    }
    
    @Override
    public void run() {
        String word;
        try {
            String line = null;
            while ((line = is.readLine()) != null) {
                System.out.println(Thread.currentThread() + " Server Got => " + line);
                if (line.equalsIgnoreCase("QUIT"))
                    break;
                else {
                    Bronhomunal.Bot.execute(line);
                }
            }
        } catch (IOException e) {System.out.println(e.getMessage());}
    }

    public void send(String msg) {
        try {
            os.println(msg);

            is.close();
            os.close();
            in.close();
            out.close();
            socket.close();

        } catch (Exception ignored) {}
    }
}
