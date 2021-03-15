package me.chocolf.moneyfrommobs.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import me.chocolf.moneyfrommobs.MoneyFromMobs;
import me.chocolf.moneyfrommobs.events.AttemptToDropMoneyEvent;
import me.chocolf.moneyfrommobs.integrations.DropMoneyFlag;

public class WorldGuardListener implements Listener{
	
	
	public WorldGuardListener(MoneyFromMobs plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onAttemptToDropMoney(AttemptToDropMoneyEvent e) {
		Location loc = e.getEntity().getLocation();
		// if drop-money flag is deny cancel the drop
		if (!checkWorldGuardFlag(loc)) {
			e.setCancelled(true);
		}
	}
	
	public boolean checkWorldGuardFlag(Location location) {
		try {
			RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
			RegionQuery query = container.createQuery();
			ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(location));
			
			if (set.testState(null, DropMoneyFlag.MONEY_DROPS)) {
				return true;
			}
			else return false;
			
		}
		catch(Exception e) {
			return false;
		}		
	}

}