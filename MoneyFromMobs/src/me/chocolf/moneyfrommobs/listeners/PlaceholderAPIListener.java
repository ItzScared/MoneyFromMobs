package me.chocolf.moneyfrommobs.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.chocolf.moneyfrommobs.MoneyFromMobs;
import me.chocolf.moneyfrommobs.events.GiveMoneyEvent;

public class PlaceholderAPIListener implements Listener{
	
	private MoneyFromMobs plugin;
	public HashMap<UUID, String> latestPickedUp = new HashMap<>();
	
	
	public PlaceholderAPIListener(MoneyFromMobs plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPickUpMoney(GiveMoneyEvent e) {
		String itemName = plugin.getManager().getItemName();
		String strAmount = String.format("%.2f", e.getAmount());
		itemName = itemName.replace("%amount%", strAmount);
		
		
		UUID uuid = e.getPlayer().getUniqueId();
		if (latestPickedUp.containsKey(uuid)) {
			latestPickedUp.remove(uuid);
		}
		latestPickedUp.put(uuid, itemName);
		
	}

}