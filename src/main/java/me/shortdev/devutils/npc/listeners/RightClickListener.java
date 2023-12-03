package me.shortdev.devutils.npc.listeners;

import me.shortdev.devutils.npc.NPC;
import me.shortdev.devutils.npc.NPCResponse;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Objects;

public class RightClickListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();
        CraftEntity craftEntity = (CraftEntity) entity;

        net.minecraft.world.entity.Entity nmsEntity = craftEntity.getHandle();
        if (!(nmsEntity instanceof ServerPlayer sp)) return;
        NPC npc = null;
        for (NPC n : NPC.getNPCMap().values()) {
            if (n.getServerPlayer() == sp) {
                npc = n;
                break;
            }
        }

        try {
            NPCResponse response = Objects.requireNonNull(npc).getResponse();
            response.execute(e.getPlayer());
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
    }
}
