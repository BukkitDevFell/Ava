package org.avateam.ava.action;

import java.util.List;

import org.avateam.ava.AvaNPC;
import org.avateam.ava.AvaPlugin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class AttackTarget extends SimpleAction {
	public void run(AvaNPC npc) {
		LivingEntity target = npc.getTarget();

		List<Entity> list = npc.getBukkitEntity().getNearbyEntities(3, 3, 3);
		if (!(list.contains(target))) {
			npc.lookAtPoint(target.getLocation());
			npc.walkTo(target.getLocation());
		} else {
			npc.lookAtPoint(target.getLocation());
			npc.animateArmSwing();
			target.damage(npc.getDamage(), npc.getBukkitEntity());
			if (target.isDead()) {
				npc.stop(AvaPlugin.getInstance());

			}
		}

	}

}
// TODO: run() needs repeating.