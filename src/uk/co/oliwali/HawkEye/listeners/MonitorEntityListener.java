package uk.co.oliwali.HawkEye.listeners;

import java.util.Arrays;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.inventory.ItemStack;

import uk.co.oliwali.HawkEye.HawkEye;
import uk.co.oliwali.HawkEye.DataType;
import uk.co.oliwali.HawkEye.PlayerSession;
import uk.co.oliwali.HawkEye.database.DataManager;
import uk.co.oliwali.HawkEye.entry.BlockEntry;
import uk.co.oliwali.HawkEye.entry.DataEntry;
import uk.co.oliwali.HawkEye.util.Config;
import uk.co.oliwali.HawkEye.util.Util;

/**
 * Entity listener class for HawkEye
 * Contains system for managing player deaths
 * @author oliverw92
 */
public class MonitorEntityListener extends EntityListener {
	
	public HawkEye plugin;

	public MonitorEntityListener(HawkEye HawkEye) {
		plugin = HawkEye;
	}
	
	/**
	 * Uses the lastAttacker field in the players {@link PlayerSession} to log the death and cause
	 */
	public void onEntityDeath(EntityDeathEvent event) {
		
		Entity entity = event.getEntity();
		//Only interested if it is a player death
		if (entity instanceof Player) {
			
			Player victim = (Player) entity;
			EntityDamageEvent attackEvent = victim.getLastDamageCause();
			//Mob or PVP death
			if (attackEvent instanceof EntityDamageByEntityEvent || attackEvent instanceof EntityDamageByProjectileEvent) {
				Entity damager;
				if (attackEvent instanceof EntityDamageByEntityEvent) damager = ((EntityDamageByEntityEvent)attackEvent).getDamager();
				else damager = ((EntityDamageByProjectileEvent)attackEvent).getDamager();
				if (damager instanceof Player) {
					DataManager.addEntry(new DataEntry(victim, DataType.PVP_DEATH, victim.getLocation(), Util.getEntityName(damager)));
				} else {
					DataManager.addEntry(new DataEntry(victim, DataType.MOB_DEATH, victim.getLocation(), Util.getEntityName(damager)));
				}
			//Other death
			} else {
				String cause = victim.getLastDamageCause().getCause().name();
				String[] words = cause.split("_");
				for (int i = 0; i < words.length; i++)
					words[i] = words[i].substring(0,1).toUpperCase() + words[i].substring(1).toLowerCase();
				cause = Util.join(Arrays.asList(words), " ");
				DataManager.addEntry(new DataEntry(victim, DataType.OTHER_DEATH, victim.getLocation(), cause));
			}
            
			//Log item drops
			if (Config.LogDeathDrops) {
				String data = null;
				for (ItemStack stack : event.getDrops()) {
					if (stack.getData() != null)
						data = stack.getAmount() + "x " + stack.getTypeId() + ":" + stack.getData().getData();
				    else
				    	data = stack.getAmount() + "x " + stack.getTypeId();
				    DataManager.addEntry(new DataEntry(victim, DataType.ITEM_DROP, victim.getLocation(), data));                           
				}
			}
	
		}
	}
	
	public void onEntityExplode(EntityExplodeEvent event) {
		if (event.isCancelled()) return;
		for (Block b : event.blockList().toArray(new Block[0]))
			DataManager.addEntry(new BlockEntry("Environment", DataType.EXPLOSION, b));
	}

}
