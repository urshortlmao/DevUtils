package me.shortdev.devutils.npc.listeners;

import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import me.shortdev.devutils.npc.NPC;

public class MovementListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        for (NPC npc : NPC.getNPCMap().values()) {
            ServerPlayer sp = npc.getServerPlayer();
            Location location = sp.getBukkitEntity().getLocation();
            Vector delta = e.getPlayer().getLocation().subtract(location).toVector();
            if (delta.length() >= npc.despawnDistance) {
                npc.despawnForPlayer(e.getPlayer());
                continue;
            }
            location.setDirection(delta);
            ServerGamePacketListenerImpl ps = ((CraftPlayer) e.getPlayer()).getHandle().connection;
            ps.send(new ClientboundRotateHeadPacket(sp, (byte) (location.getYaw() % 360 * 0.7111)));
            ps.send(new ClientboundMoveEntityPacket.Rot(sp.getBukkitEntity().getEntityId(), (byte) (location.getYaw() % 360 * 0.7111), (byte) (location.getPitch() % 360 * 0.7111), false));
        }
    }
}
