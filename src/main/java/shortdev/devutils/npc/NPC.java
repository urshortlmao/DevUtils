package shortdev.devutils.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.util.Pair;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.EquipmentSlot;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class NPC {
    private static HashMap<Integer, NPC> npcMap = new HashMap<>();
    private static List<ServerPlayer> npcs = new ArrayList<>();
    private String name;
    private final int id;
    private Collection<? extends Player> viewers;
    private UUID uuid;
    private Location location;
    private ServerPlayer npc;

    public static int defaultNPCDespawnDistance = 128;
    public static boolean spawnNPCSOnRestart = true;
    public int despawnDistance = defaultNPCDespawnDistance;

    public NPC(Player viewer, String name, Location location) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        int tempId = 1;
        while (npcMap.containsKey(tempId)) tempId++;
        id = tempId;
        npcMap.put(id, this);
        viewers = Collections.singletonList(viewer);
        uuid = UUID.randomUUID();
        this.location = location;
    }

    public NPC(Player viewer, UUID npcUUID, String name, Location location) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        int tempId = 1;
        while (npcMap.containsKey(tempId)) tempId++;
        id = tempId;
        npcMap.put(id, this);
        viewers = Collections.singletonList(viewer);
        uuid = npcUUID;
        this.location = location;
    }

    public NPC(Player viewer, char alternateColorChar, String name, Location location) {
        this.name = ChatColor.translateAlternateColorCodes(alternateColorChar, name);
        int tempId = 1;
        while (npcMap.containsKey(tempId)) tempId++;
        id = tempId;
        npcMap.put(id, this);
        viewers = Collections.singletonList(viewer);
        uuid = UUID.randomUUID();
        this.location = location;
    }

    public NPC(Player viewer, UUID npcUUID, char alternateColorChar, String name, Location location) {
        this.name = ChatColor.translateAlternateColorCodes(alternateColorChar, name);
        int tempId = 1;
        while (npcMap.containsKey(tempId)) tempId++;
        id = tempId;
        npcMap.put(id, this);
        viewers = Collections.singletonList(viewer);
        uuid = npcUUID;
        this.location = location;
    }

    public NPC(Collection<? extends Player> viewers, String name, Location location) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        int tempId = 1;
        while (npcMap.containsKey(tempId)) tempId++;
        id = tempId;
        npcMap.put(id, this);
        this.viewers = viewers;
        uuid = UUID.randomUUID();
        this.location = location;
    }

    public NPC(Collection<? extends Player> viewers, UUID npcUUID, String name, Location location) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        int tempId = 1;
        while (npcMap.containsKey(tempId)) tempId++;
        id = tempId;
        npcMap.put(id, this);
        this.viewers = viewers;
        uuid = npcUUID;
        this.location = location;
    }

    public NPC(Collection<? extends Player> viewers, UUID npcUUID, char alternateColorChar, String name, Location location) {
        this.name = ChatColor.translateAlternateColorCodes(alternateColorChar, name);
        int tempId = 1;
        while (npcMap.containsKey(tempId)) tempId++;
        id = tempId;
        npcMap.put(id, this);
        this.viewers = viewers;
        uuid = npcUUID;
        this.location = location;
    }

    public NPC(Collection<? extends Player> viewers, char alternateColorChar, String name, Location location) {
        this.name = ChatColor.translateAlternateColorCodes(alternateColorChar, name);
        int tempId = 1;
        while (npcMap.containsKey(tempId)) tempId++;
        id = tempId;
        npcMap.put(id, this);
        this.viewers = viewers;
        uuid = UUID.randomUUID();
        this.location = location;
    }

    public void spawn() {
        for (Player player : viewers) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            ServerPlayer sp = craftPlayer.getHandle();

            MinecraftServer server = sp.getServer();
            ServerLevel level = sp.serverLevel();

            GameProfile gp = new GameProfile(uuid, name);

            npc = new ServerPlayer(Objects.requireNonNull(server), level, gp);
            npc.setPos(location.getX(), location.getY(), location.getZ());

            ServerGamePacketListenerImpl ps = sp.connection;

            ps.send(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, npc));
            ps.send(new ClientboundAddPlayerPacket(npc));

            npcs.add(npc);
        }
    }

    public void spawn(String signature, String texture) {
        for (Player player : viewers) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            ServerPlayer sp = craftPlayer.getHandle();

            MinecraftServer server = sp.getServer();
            ServerLevel level = sp.serverLevel();

            GameProfile gp = new GameProfile(uuid, name);

            gp.getProperties().put("textures", new Property("textures", texture, signature));

            npc = new ServerPlayer(Objects.requireNonNull(server), level, gp);
            npc.setPos(location.getX(), location.getY(), location.getZ());

            ServerGamePacketListenerImpl ps = sp.connection;

            ps.send(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, npc));
            ps.send(new ClientboundAddPlayerPacket(npc));

            npcs.add(npc);
        }
    }

    public void spawn(ItemStack mainHandItem, ItemStack offHandItem, ItemStack headItem, ItemStack chestItem, ItemStack legsItem, ItemStack feetItem) {
        for (Player player : viewers) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            ServerPlayer sp = craftPlayer.getHandle();

            MinecraftServer server = sp.getServer();
            ServerLevel level = sp.serverLevel();

            GameProfile gp = new GameProfile(uuid, name);

            npc = new ServerPlayer(Objects.requireNonNull(server), level, gp);
            npc.setPos(location.getX(), location.getY(), location.getZ());

            ServerGamePacketListenerImpl ps = sp.connection;

            ps.send(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, npc));
            ps.send(new ClientboundAddPlayerPacket(npc));

            ps.send(new ClientboundSetEquipmentPacket(npc.getBukkitEntity().getEntityId(), List.of(
                    new Pair<>(EquipmentSlot.MAINHAND, CraftItemStack.asNMSCopy(mainHandItem)),
                    new Pair<>(EquipmentSlot.OFFHAND, CraftItemStack.asNMSCopy(offHandItem)),
                    new Pair<>(EquipmentSlot.HEAD, CraftItemStack.asNMSCopy(headItem)),
                    new Pair<>(EquipmentSlot.CHEST, CraftItemStack.asNMSCopy(chestItem)),
                    new Pair<>(EquipmentSlot.LEGS, CraftItemStack.asNMSCopy(legsItem)),
                    new Pair<>(EquipmentSlot.FEET, CraftItemStack.asNMSCopy(feetItem))
            )));

            npcs.add(npc);
        }
    }

    public void spawn(String signature, String texture, ItemStack mainHandItem, ItemStack offHandItem, ItemStack headItem, ItemStack chestItem, ItemStack legsItem, ItemStack feetItem) {
        for (Player player : viewers) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            ServerPlayer sp = craftPlayer.getHandle();

            MinecraftServer server = sp.getServer();
            ServerLevel level = sp.serverLevel();

            GameProfile gp = new GameProfile(uuid, name);

            gp.getProperties().put("textures", new Property("textures", texture, signature));

            npc = new ServerPlayer(Objects.requireNonNull(server), level, gp);
            npc.setPos(location.getX(), location.getY(), location.getZ());

            ServerGamePacketListenerImpl ps = sp.connection;

            ps.send(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, npc));
            ps.send(new ClientboundAddPlayerPacket(npc));

            ps.send(new ClientboundSetEquipmentPacket(npc.getBukkitEntity().getEntityId(), List.of(
                    new Pair<>(EquipmentSlot.MAINHAND, CraftItemStack.asNMSCopy(mainHandItem)),
                    new Pair<>(EquipmentSlot.OFFHAND, CraftItemStack.asNMSCopy(offHandItem)),
                    new Pair<>(EquipmentSlot.HEAD, CraftItemStack.asNMSCopy(headItem)),
                    new Pair<>(EquipmentSlot.CHEST, CraftItemStack.asNMSCopy(chestItem)),
                    new Pair<>(EquipmentSlot.LEGS, CraftItemStack.asNMSCopy(legsItem)),
                    new Pair<>(EquipmentSlot.FEET, CraftItemStack.asNMSCopy(feetItem))
            )));

            npcs.add(npc);
        }
    }

    public void despawn() {
        for (Player player : viewers) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            ServerPlayer sp = craftPlayer.getHandle();

            ServerGamePacketListenerImpl ps = sp.connection;

            ps.send(new ClientboundRemoveEntitiesPacket(npc.getBukkitEntity().getEntityId()));
        }
    }

    public void delete() {
        despawn();
        npcs.remove(npc);
        npcMap.remove(id);
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
        despawn();

    }

    public String getName() {
        return name;
    }

    public Collection<? extends Player> getViewers() {
        return viewers;
    }

    public static List<ServerPlayer> getSpawnedNPCS() {
        return npcs;
    }

    public ServerPlayer getServerPlayer() {
        return npc;
    }

    public static HashMap<Integer, NPC> getNPCMap() {
        return npcMap;
    }

}
