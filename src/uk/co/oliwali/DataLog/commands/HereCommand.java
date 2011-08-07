package uk.co.oliwali.DataLog.commands;

import uk.co.oliwali.DataLog.DataType;
import uk.co.oliwali.DataLog.SearchParser;
import uk.co.oliwali.DataLog.database.SearchQuery.SearchDir;
import uk.co.oliwali.DataLog.database.SearchQuery.SearchType;
import uk.co.oliwali.DataLog.database.SearchQuery;
import uk.co.oliwali.DataLog.util.Config;
import uk.co.oliwali.DataLog.util.Permission;
import uk.co.oliwali.DataLog.util.Util;

/**
 * Searches around the player for 'here' {@link DataType}s
 * @author oliverw92
 */
public class HereCommand extends BaseCommand {

	public HereCommand() {
		name = "here";
		argLength = 0;
		bePlayer = true;
		usage = "[radius] [player] <- search around you for data";
	}
	
	public boolean execute() {
		
		//Create new parser
		SearchParser parser = null;
		try {
			
			//Check for valid integer
			if (!Util.isInteger(args.get(0))) throw new IllegalArgumentException("Invalid integer supplied for radius!");
			int integer = Integer.parseInt(args.get(0));
			if (integer > Config.MaxRadius || integer < 0)
				throw new IllegalArgumentException("Invalid radius supplied supplied!");
			parser = new SearchParser(player, integer);
			
			//Add in DataTypes
			for (DataType type : DataType.values())
				if (type.canHere()) parser.actions.add(type);
			
			//Check if players were supplied
			if (args.size() > 1)
				parser.players = args.get(1).split(",");
			
			//Add in 'here' actions
			for (DataType type : DataType.values())
				if (type.canHere()) parser.actions.add(type);
			
		} catch (IllegalArgumentException e) {
			Util.sendMessage(sender, "&c" + e.getMessage());
			return true;
		}
		
		//Run the search query
		Thread thread = new SearchQuery(SearchType.SEARCH, parser, SearchDir.DESC);
		thread.start();
		return true;
		
	}

	public boolean permission() {
		return Permission.search(sender);
	}
	
}