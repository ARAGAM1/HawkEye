package uk.co.oliwali.DataLog.commands;

import uk.co.oliwali.DataLog.DataManager;
import uk.co.oliwali.DataLog.util.Permission;
import uk.co.oliwali.DataLog.util.Util;

public class PageCommand extends BaseCommand {

	public PageCommand() {
		name = "page";
		argLength = 1;
		usage = "<page> <- display a page from your last search results";
	}
	
	public boolean execute() {
		if (!Util.isInteger(args.get(0)) || Integer.parseInt(args.get(0)) < 1)
			Util.sendMessage(sender, "&cPlease supply a valid page number to display!");
		else if (!DataManager.displayPage(sender, Integer.parseInt(args.get(0))))
			Util.sendMessage(sender, "&cYou have no recent results to display! Type &7/dl search&c to search");
		return true;
	}
	
	public boolean permission() {
		return Permission.search(sender);
	}
	
}