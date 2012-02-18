package org.avateam.ava;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.topcat.npclib.NPCManager;

public class AvaPlugin extends JavaPlugin {
    private static AvaPlugin instance;

    public static AvaPlugin getInstance() {
        return instance;
    }

    public ChatListener chat;

    public NPCManager nm;

    public AvaPlugin() {
        instance = this;
    }

    public void onEnable() {
        chat = new ChatListener(this);
        nm = new NPCManager(this);
        Bukkit.getPluginManager().registerEvents(chat, this);
        getLogger().info("[Ava] has woken up");
    }

    public void onDisable() {
        getLogger().info("[Ava] has gone to sleep");
    }

}
