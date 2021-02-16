package abroplugin;

import java.util.logging.Logger;

import abroplugin.bot.Bronhomunal;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	public static Logger Log;
	public static Main Plugin;
	public static Server Core;

	@Override
	public void onLoad(){

	}

	@Override
	public void onEnable(){

		Plugin = this;
		Log = getLogger();

		Log.info("AbroPlugin started in [DEBUG3]");

		Core = getServer();


		getCommand("sethome").setExecutor(new ChatListener());

		new Thread(()->{new Bronhomunal();}).start();
		broadcast("So much chat tests");
	}

	public void broadcast(String message){
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(message);
		}
	}

}
