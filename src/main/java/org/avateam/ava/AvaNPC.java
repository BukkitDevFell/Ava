package org.avateam.ava;

import org.avateam.ava.action.AttackTarget;
import org.avateam.ava.action.WoodCutting;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.topcat.npclib.entity.HumanNPC;
import com.topcat.npclib.nms.NPCEntity;
import org.avateam.ava.action.AvaAction;

public class AvaNPC extends HumanNPC {
    private SpoutPlayer player;

    private String texture = "http://s3.amazonaws.com/squirt/i4ecf2a7242e7952832826103242823282382812.png";

    private Player owner;

    private AttackTarget at;

    private WoodCutting wc;

    private boolean inAction = false;

    private LivingEntity target = null;

    public AvaNPC(NPCEntity npcEntity) {
        super(npcEntity);
        player = this.getSpoutPlayer();
        player.setSkin(texture);
    }

    public void chat(String message) {
        player.chat(message);
    }

    public void setOwner(Player player) {
        owner = player;
    }

    public Player getOwner() {
        return owner;
    }

    public void doAction(AvaAction action) {
        action.run(this);
    }
    
    public void doAction(AvaAction action, Object... args) {
        action.run(this, args);
    }

    public void build() {
        //TODO: Add Code
    }

    public int getDamage() {
        int damage = 1;
        Material mat = player.getItemInHand().getType();
        if ((mat == Material.WOOD_PICKAXE || mat == Material.GOLD_PICKAXE) || mat == Material.STONE_SPADE) {
            damage = 2;
        }
        if ((mat == Material.WOOD_AXE || mat == Material.GOLD_AXE) || (mat == Material.STONE_PICKAXE || mat == Material.IRON_SPADE)) {
            damage = 3;
        }
        if ((mat == Material.WOOD_SWORD || mat == Material.GOLD_SWORD) || (mat == Material.STONE_AXE || (mat == Material.IRON_PICKAXE || mat == Material.DIAMOND_SPADE))) {
            damage = 4;
        }
        if (mat == Material.STONE_SWORD || (mat == Material.IRON_AXE || mat == Material.DIAMOND_PICKAXE)) {
            damage = 5;
        }
        if (mat == Material.IRON_SWORD || mat == Material.DIAMOND_AXE) {
            damage = 6;
        }
        if (mat == Material.DIAMOND_SWORD) {
            damage = 7;
        }
        return damage;
    }

    public void stop(AvaPlugin plugin) {
        Bukkit.getScheduler().cancelTasks(plugin);
        inAction = false;
    }

    public LivingEntity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

}
