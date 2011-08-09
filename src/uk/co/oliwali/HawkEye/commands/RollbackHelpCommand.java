package uk.co.oliwali.HawkEye.commands;

import uk.co.oliwali.HawkEye.util.Permission;
import uk.co.oliwali.HawkEye.util.Util;

/**
 * Displays help information for the {@link RollbackCommand} command
 * @author oliverw92
 */
public class RollbackHelpCommand extends BaseCommand {

	public RollbackHelpCommand() {
		name = "rollbackhelp";
		argLength = 0;
		bePlayer = false;
		usage = " <- displays information about the rollback command";
	}
	
	public boolean execute() {
		Util.sendMessage(sender, "&c-------------------- &7Rollback Help&c -------------------");
		Util.sendMessage(sender, "&7There are 6 parameters you can use - &ca: p: w: r: f: t:");
		Util.sendMessage(sender, "&8Action &ca:&7 - list of actions separated by commas. Select from the following: &8block-break block-place sign-place chat command join quit teleport lava-bucket water-bucket open-chest door-interact pvp-death flint-steel lever button other");
		Util.sendMessage(sender, "&8Player &cp:&7 - list of players. &8World &cw:&7 - list of worlds");
		Util.sendMessage(sender, "&8Filter &cf:&7 - list of keywords (e.g. block id)");
		Util.sendMessage(sender, "&8Radius &cr:&7 - radius to search around given location");
		Util.sendMessage(sender, "&8Time &ct:&7 - time bracket in the following format:");
		Util.sendMessage(sender, "&7  -&c t:10h45m10s &7-back specified amount of time");
		Util.sendMessage(sender, "&7  -&c t:2011-06-02,10:45:10 &7-from given date");
		Util.sendMessage(sender, "&7  -&c t:2011-06-02,10:45:10,2011-07-04,18:15:00 &7-between dates");
		return true;
	}
	
	public boolean permission() {
		return Permission.search(sender);
	}
	
}