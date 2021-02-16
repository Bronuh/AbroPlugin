package abroplugin;

import abroplugin.bot.Bronhomunal;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatListener implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        Player ply = (Player)sender;
        if(command.getName().contentEquals("sethome")){
            Main.Log.info("Trying to send message...");
            Bronhomunal.Bot.sendMessage(ply.getName()+" setting his home. Yay!");
        }

        return false;
    }
}