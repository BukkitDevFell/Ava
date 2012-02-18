package team.avateam.ava;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.topcat.npclib.NPCManager;

public class Main extends JavaPlugin {
	public ChatListener chat;
	public NPCManager nm;
	
	public void onEnable(){
		chat = new ChatListener(this);
		nm = new NPCManager(this);
		Bukkit.getPluginManager().registerEvents(chat, this);
		getLogger().info("[Ava] has woken up");
	}

	public void onDisable(){
		getLogger().info("[Ava] has gone to sleep");
	}
}
