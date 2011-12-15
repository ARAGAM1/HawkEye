package uk.co.oliwali.HawkEye.listeners;

import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.event.world.WorldListener;

import uk.co.oliwali.HawkEye.DataType;
import uk.co.oliwali.HawkEye.HawkEye;
import uk.co.oliwali.HawkEye.database.DataManager;
import uk.co.oliwali.HawkEye.entry.SimpleRollbackEntry;
import uk.co.oliwali.HawkEye.util.BlockUtil;

public class MonitorWorldListener extends WorldListener {
	
	public HawkEye plugin;

	public MonitorWorldListener(HawkEye HawkEye) {
		plugin = HawkEye;
	}
	
	public void onStructureGrow(StructureGrowEvent event) {
		if (event.isCancelled()) return;
		
		//Sort out structure type
		DataType type = DataType.TREE_GROW;
		if (event.getSpecies().name().toLowerCase().contains("mushroom")) type = DataType.MUSHROOM_GROW;
		
		//Loop through blocks
		for (BlockState block : event.getBlocks()) {
			Location loc = new Location(event.getWorld(), block.getX(), block.getY(), block.getZ());
			//If a player did it
			if (event.getPlayer() != null) {
				DataManager.addEntry(new SimpleRollbackEntry(event.getPlayer(), type, loc, BlockUtil.getBlockString(block)));
			}
			//If the environment did it
			else {
				DataManager.addEntry(new SimpleRollbackEntry("Environment", type, loc, BlockUtil.getBlockString(block)));
			}
		}
		
	}

}
