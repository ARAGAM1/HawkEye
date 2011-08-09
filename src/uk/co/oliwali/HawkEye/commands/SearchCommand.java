package uk.co.oliwali.HawkEye.commands;

import uk.co.oliwali.HawkEye.SearchParser;
import uk.co.oliwali.HawkEye.callbacks.SearchCallback;
import uk.co.oliwali.HawkEye.database.SearchQuery.SearchDir;
import uk.co.oliwali.HawkEye.database.SearchQuery;
import uk.co.oliwali.HawkEye.util.Permission;
import uk.co.oliwali.HawkEye.util.Util;

/**
 * Searches for data according to the player's specified input.
 * Error handling for user input is done using exceptions to keep code neat.
 * @author oliverw92
 */
public class SearchCommand extends BaseCommand {

	public SearchCommand() {
		bePlayer = true;
		name = "search";
		argLength = 1;
		usage = "<parameters> <- search the HawkEye database. Type &c/dl searchhelp&7 for more info";
	}
	
	public boolean execute() {
		
		//Parse arguments
		SearchParser parser = null;
		try {
			parser = new SearchParser(player, args);
		} catch (IllegalArgumentException e) {
			Util.sendMessage(sender, "&c" + e.getMessage());
			return true;
		}
		
		//Create new SeachQuery with data
		Thread thread = new SearchQuery(new SearchCallback(), parser, SearchDir.DESC);
		thread.start();
		return true;
		
	}
	
	public boolean permission() {
		return Permission.search(sender);
	}

}