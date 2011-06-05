package uk.co.oliwali.DataLog.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import uk.co.oliwali.DataLog.util.Util;

public abstract class BaseCommand {
	
	public CommandSender sender;
	public List<String> args = new ArrayList<String>();
	public String name;
	public int argLength;
	public String usage;
	public boolean bePlayer = false;
	public Player player;
	public String usedCommand;
	
	public boolean run(CommandSender csender, String[] preArgs, String cmd) {
		sender = csender;
		args.clear();
		for (String arg : preArgs)
			args.add(arg);
		args.remove(0);
		usedCommand = cmd;
		
		if (argLength > args.size()) {
			sendUsage();
			return true;
		}
		if (bePlayer && !(sender instanceof Player))
			return false;
		if (sender instanceof Player)
			player = (Player)sender;
		if (!permission())
			return false;
		return execute();
	}
	
	public abstract boolean execute();
	public abstract boolean permission();
	
	public void sendUsage() {
		Util.sendMessage(sender, "&c/"+usedCommand+" " + name + " " + usage);
	}

}
