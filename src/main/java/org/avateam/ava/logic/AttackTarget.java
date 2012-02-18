package org.avateam.ava.logic;

import java.util.List;

import org.avateam.ava.AvaNPC;
import org.avateam.ava.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class AttackTarget extends Thread implements Runnable {
	private LivingEntity target;
	private AvaNPC npc;
	private Main plugin;
	
	public AttackTarget(AvaNPC npc, Main plugin) {
		this.npc = npc;
		this.plugin = plugin;
	}
	
	public void run() {
		List<Entity> list = npc.getBukkitEntity().getNearbyEntities(3, 3, 3);
		if(!(list.contains(target))) {
			npc.lookAtPoint(target.getLocation());
			npc.walkTo(target.getLocation());
		} else {
			npc.lookAtPoint(target.getLocation());
			npc.animateArmSwing();
			target.damage(npc.getDamage(), npc.getBukkitEntity());
			if(target.isDead()) npc.stop(plugin);
		}
	}
	
	public void setTarget(LivingEntity target) {
		this.target = target;
	}
}

//TODO: run() needs repeating.