package uk.co.oliwali.DataLog.commands;

import uk.co.oliwali.DataLog.DataType;
import uk.co.oliwali.DataLog.SearchParser;
import uk.co.oliwali.DataLog.database.SearchQuery.SearchDir;
import uk.co.oliwali.DataLog.database.SearchQuery.SearchType;
import uk.co.oliwali.DataLog.database.SearchQuery;
import uk.co.oliwali.DataLog.util.Permission;
import uk.co.oliwali.DataLog.util.Util;

/**
 * Rolls back actions according to the player's specified input.
 * Error handling for user input is done using exceptions to keep code neat.
 * @author oliverw92
 */
public class RollbackCommand extends BaseCommand {

	public RollbackCommand() {
		name = "rollback";
		argLength = 1;
		bePlayer = true;
		usage = "<parameters> <- rollback actions. Type &c/dl rollbackhelp&7 for more info";
	}
	
	public boolean execute() {
		
		//Check if player already has a rollback processing
		if (session.getRollbackResults() != null) {
			Util.sendMessage(sender, "&cYou already have a rollback command processing!");
			return true;
		}
		
		//Parse arguments
		SearchParser parser = null;
		try {
			
			parser = new SearchParser(player, args);
			parser.loc = null;
			
			//Check that supplied actions can rollback
			if (parser.actions.size() > 0) {
				for (DataType type : parser.actions)
					if (!type.canRollback()) throw new IllegalArgumentException("You cannot rollback that action type: &7" + type.getConfigName());
			}
			//If none supplied, add in all rollback types
			else {
				for (DataType type : DataType.values())
					if (type.canRollback()) parser.actions.add(type);
			}
			
		} catch (IllegalArgumentException e) {
			Util.sendMessage(sender, "&c" + e.getMessage());
			return true;
		}
		
		//Create new SearchQuery with data
		Thread thread = new SearchQuery(SearchType.SEARCH, parser, SearchDir.ASC);
		thread.start();
		return true;
		
	}
	
	public boolean permission() {
		return Permission.rollback(sender);
	}

}