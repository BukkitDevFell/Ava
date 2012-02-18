package org.avateam.ava;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.topcat.npclib.entity.HumanNPC;

public class ChatListener implements Listener {
	private Main plugin;
	private AvaLogic logic;
	private HashMap<String, Integer> words = new HashMap<String, Integer>();
	private HashMap<Player, Boolean> listen = new HashMap<Player, Boolean>();
	private String splits = "[ .,?!]+";
	private HumanNPC npc;
	
	public ChatListener(Main main) {
		plugin = main;
		logic = new AvaLogic(plugin);
		setUp();
	}

	private void setUp() {
		words.put("good", 100);
		words.put("bad", -100);
	}
	
	private void respond(Player player, int points) {
		if(points > 0) {
			player.sendMessage(":)");
		} else if (points < 0){
			player.sendMessage(":(");
		} else {
			player.sendMessage(":l");
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
		int points = 0;
		boolean sendMessage = false;
		String[] parts = event.getMessage().split(splits);
		if(event.getMessage().equalsIgnoreCase("don't listen to me ava")) {
			event.getPlayer().sendMessage("[Ava]: Okay, I will stop listening to you.");
			listen.put(event.getPlayer(), false);
		} else if (event.getMessage().equalsIgnoreCase("listen to me ava")){
			event.getPlayer().sendMessage("[Ava]: Okay, What do you want to tell me?");
			listen.put(event.getPlayer(), true);
		} else if(event.getMessage().equalsIgnoreCase("ava come here")) {
			event.getPlayer().sendMessage("[Ava]: Coming!");
			if(plugin.nm.getNPC("1") != null) {
				plugin.nm.getNPC("1").moveTo(event.getPlayer().getLocation());
				npc = (HumanNPC) plugin.nm.getNPC("1");
			} else {
				plugin.nm.spawnHumanNPC("Ava", event.getPlayer().getLocation(),"1");
				npc = (HumanNPC) plugin.nm.getNPC("1");
				SpoutPlayer sp = ((HumanNPC) plugin.nm.getNPC("1")).getSpoutPlayer();
				sp.setSkin("http://s3.amazonaws.com/squirt/i4ecf2a7242e7952832826103242823282382812.png");
			}
		} else if(event.getMessage().equalsIgnoreCase("ava chop wood")) {
			event.getPlayer().sendMessage(event.getPlayer().getLocation().getBlock().getBiome().name());
			logic.ChopWood(npc);
		}
		if(listen.containsKey(event.getPlayer()) == false) {
			listen.put(event.getPlayer(),true);
		}
		if(listen.get(event.getPlayer()) == true) {
			for(int i=0; i < parts.length; i++) {
				if(words.containsKey(parts[i])) {
					points += words.get(parts[i]);
					sendMessage = true;
				}
			}
			if(sendMessage) respond(event.getPlayer(),points);
		}
	}
}
