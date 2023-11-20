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
    private Collection<Player> viewers;
    private UUID uuid;
    private Location location;
    private ServerPlayer npc;
    private String signature, texture;
    private ItemStack mainHandItem, offHandItem, headItem, chestItem, legsItem, feetItem;

    public static int defaultNPCDespawnDistance = 128;
    public int despawnDistance = defaultNPCDespawnDistance;

    public NPC(Player viewer, UUID npcUUID, char colorChar, String name, Location location) {
        this.name = ChatColor.translateAlternateColorCodes(colorChar, name);
        int tempId = 1;
        while (npcMap.containsKey(tempId)) tempId++;
        id = tempId;
        npcMap.put(id, this);
        this.viewers = Collections.singletonList(viewer);
        uuid = npcUUID;
        this.location = location;
        signature = null;
        texture = null;
        mainHandItem = null;
        offHandItem = null;
        headItem = null;
        chestItem = null;
        legsItem = null;
        feetItem = null;
    }

    public NPC(Collection<Player> viewers, UUID npcUUID, char colorChar, String name, Location location) {
        this.name = ChatColor.translateAlternateColorCodes(colorChar, name);
        int tempId = 1;
        while (npcMap.containsKey(tempId)) tempId++;
        id = tempId;
        npcMap.put(id, this);
        this.viewers = viewers;
        uuid = npcUUID;
        this.location = location;
        signature = null;
        texture = null;
        mainHandItem = null;
        offHandItem = null;
        headItem = null;
        chestItem = null;
        legsItem = null;
        feetItem = null;
    }

    public NPC(Player viewer, UUID npcUUID, char colorChar, String name, Location location, String signature, String texture, ItemStack mainHandItem, ItemStack offHandItem, ItemStack headItem, ItemStack chestItem, ItemStack legsItem, ItemStack feetItem) {
        this.name = ChatColor.translateAlternateColorCodes(colorChar, name);
        int tempId = 1;
        while (npcMap.containsKey(tempId)) tempId++;
        id = tempId;
        npcMap.put(id, this);
        this.viewers = Collections.singletonList(viewer);
        uuid = npcUUID;
        this.location = location;
        this.signature = signature;
        this.texture = texture;
        this.mainHandItem = mainHandItem;
        this.offHandItem = offHandItem;
        this.headItem = headItem;
        this.chestItem = chestItem;
        this.legsItem = legsItem;
        this.feetItem = feetItem;
    }

    public NPC(Collection<Player> viewers, UUID npcUUID, char colorChar, String name, Location location, String signature, String texture, ItemStack mainHandItem, ItemStack offHandItem, ItemStack headItem, ItemStack chestItem, ItemStack legsItem, ItemStack feetItem) {
        this.name = ChatColor.translateAlternateColorCodes(colorChar, name);
        int tempId = 1;
        while (npcMap.containsKey(tempId)) tempId++;
        id = tempId;
        npcMap.put(id, this);
        this.viewers = viewers;
        uuid = npcUUID;
        this.location = location;
        this.signature = signature;
        this.texture = texture;
        this.mainHandItem = mainHandItem;
        this.offHandItem = offHandItem;
        this.headItem = headItem;
        this.chestItem = chestItem;
        this.legsItem = legsItem;
        this.feetItem = feetItem;
    }

    public void spawn() {
        for (Player player : viewers) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            ServerPlayer sp = craftPlayer.getHandle();

            MinecraftServer server = sp.getServer();
            ServerLevel level = sp.serverLevel();

            GameProfile gp = new GameProfile(uuid, name);

            if (texture != null && signature != null) gp.getProperties().put("textures", new Property("textures", texture, signature));

            npc = new ServerPlayer(Objects.requireNonNull(server), level, gp);
            npc.setPos(location.getX(), location.getY(), location.getZ());

            ServerGamePacketListenerImpl ps = sp.connection;

            ps.send(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, npc));
            ps.send(new ClientboundAddPlayerPacket(npc));

            List<Pair<EquipmentSlot, net.minecraft.world.item.ItemStack>> equipment = new ArrayList<>();
            if (mainHandItem != null) equipment.add(new Pair<>(EquipmentSlot.MAINHAND, CraftItemStack.asNMSCopy(mainHandItem)));
            if (offHandItem != null) equipment.add(new Pair<>(EquipmentSlot.OFFHAND, CraftItemStack.asNMSCopy(offHandItem)));
            if (headItem != null) equipment.add(new Pair<>(EquipmentSlot.HEAD, CraftItemStack.asNMSCopy(headItem)));
            if (chestItem != null) equipment.add(new Pair<>(EquipmentSlot.CHEST, CraftItemStack.asNMSCopy(chestItem)));
            if (legsItem != null) equipment.add(new Pair<>(EquipmentSlot.LEGS, CraftItemStack.asNMSCopy(legsItem)));
            if (feetItem != null) equipment.add(new Pair<>(EquipmentSlot.FEET, CraftItemStack.asNMSCopy(feetItem)));

            ps.send(new ClientboundSetEquipmentPacket(npc.getBukkitEntity().getEntityId(), equipment));

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

    public void despawnForPlayer(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        ServerPlayer sp = craftPlayer.getHandle();

        ServerGamePacketListenerImpl ps = sp.connection;

        ps.send(new ClientboundRemoveEntitiesPacket(npc.getBukkitEntity().getEntityId()));
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
        spawn();
    }

    public String getName() {
        return name;
    }

    public Collection<Player> getViewers() {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
        despawn();
        spawn();
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
        despawn();
        spawn();
    }

    public ItemStack getMainHandItem() {
        return mainHandItem;
    }

    public void setMainHandItem(ItemStack item) {
        mainHandItem = item;
    }

    public ItemStack getOffHandItem() {
        return offHandItem;
    }

    public void setOffHandItem(ItemStack item) {
        offHandItem = item;
    }

    public ItemStack getHeadItem() {
        return headItem;
    }

    public void setHeadItem(ItemStack item) {
        headItem = item;
    }

    public ItemStack getChestItem() {
        return chestItem;
    }

    public void setChestItem(ItemStack item) {
        chestItem = item;
    }

    public ItemStack getLegsItem() {
        return legsItem;
    }

    public void setLegsItem(ItemStack item) {
        legsItem = item;
    }

    public ItemStack getFeetItem() {
        return feetItem;
    }

    public void setFeetItem(ItemStack item) {
        feetItem = item;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public void setViewers(Collection<Player> viewers) {
        this.viewers = viewers;
    }

    public void addViewer(Player viewer) {
        viewers.add(viewer);
    }
}
