package org.avateam.ava;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.getspout.commons.ChatColor;

public class ChatListener implements Listener {
	private Main plugin;
	private HashMap<Player, Boolean> listen = new HashMap<Player, Boolean>();
	private HashMap<Player, AvaNPC> has = new HashMap<Player, AvaNPC>();
	
	public ChatListener(Main main) {
		plugin = main;
	}
	
	@EventHandler
	public void death(EntityDeathEvent event) {
		if(plugin.nm.isNPC(event.getEntity())) {
			String id = plugin.nm.getNPCIdFromEntity(event.getEntity());
			AvaNPC npc = (AvaNPC) plugin.nm.getNPC(id);
			has.remove(npc.getOwner());
		}
	}
	
	@EventHandler
	public void joined(PlayerJoinEvent event) {
		if(listen.containsKey(event.getPlayer()) == false) {
			listen.put(event.getPlayer(),true);
		}
	}
	
	@EventHandler
	public void checkMessage(PlayerChatEvent event) {
		if(event.getMessage().equalsIgnoreCase("don't listen to me ava")) {
			event.getPlayer().sendMessage("[Ava]: Okay, I will stop listening to you.");
			listen.put(event.getPlayer(), false);
		} else if (event.getMessage().equalsIgnoreCase("listen to me ava")){
			event.getPlayer().sendMessage("[Ava]: Okay, What do you want to tell me?");
			listen.put(event.getPlayer(), true);
		} 
		if(listen.containsKey(event.getPlayer()) == false) {
			listen.put(event.getPlayer(), true);
			if(event.getMessage().equalsIgnoreCase("ava come here")) {
				if(has.containsKey(event.getPlayer()) == false) {
					AvaNPC npc = (AvaNPC) plugin.nm.spawnAva(event.getPlayer().getLocation());
					npc.setOwner(event.getPlayer());
					has.put(event.getPlayer(), npc);
					npc.chat("Coming!");
				} else {
					AvaNPC npc = has.get(event.getPlayer());
					npc.moveTo(event.getPlayer().getLocation());
					npc.chat("Coming!");
				}
			} else if(event.getMessage().equalsIgnoreCase("ava chop wood")) {
				if(has.containsKey(event.getPlayer()) == false) {
					event.getPlayer().sendMessage(ChatColor.GRAY + "Wait... What am I thinking? She's not here.");
					event.setCancelled(true);
				} else {
					//TODO: Bug with this mbl111, You will need to figure out why
					//AvaNPC npc = has.get(event.getPlayer());
					//npc.chop();
				}
			} else if(event.getMessage().equalsIgnoreCase("ava attack")) {
				if(has.containsKey(event.getPlayer()) == false) {
					event.getPlayer().sendMessage(ChatColor.GRAY + "Wait... What am I thinking? She's not here.");
					event.setCancelled(true);
				} else {
					//TODO: Complete Attack Logic
					AvaNPC npc = has.get(event.getPlayer());
					npc.attck(event.getPlayer());
				}
			}
		} else if(listen.get(event.getPlayer()) == true) {
			if(event.getMessage().equalsIgnoreCase("ava come here")) {
				if(has.containsKey(event.getPlayer()) == false) {
					AvaNPC npc = (AvaNPC) plugin.nm.spawnAva(event.getPlayer().getLocation());
					npc.setOwner(event.getPlayer());
					has.put(event.getPlayer(), npc);
					npc.chat("Coming!");
				} else {
					AvaNPC npc = has.get(event.getPlayer());
					npc.moveTo(event.getPlayer().getLocation());
					npc.chat("Coming!");
				}
			}
		}
	}
}
