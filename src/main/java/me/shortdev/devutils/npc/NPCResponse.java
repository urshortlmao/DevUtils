package me.shortdev.devutils.npc;

import me.shortdev.devutils.DevUtils;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

public class NPCResponse {
    private static HashMap<Integer, NPCResponse> responseMap;
    private final int npcId;
    private Method method;

    public NPCResponse(int npcId, Method method) {
        this.npcId = npcId;
        this.method = method;
        responseMap.put(npcId, this);
    }

    public static void setup() {
        for (Set<Class<?>> classSet : DevUtils.getPluginResponseClassMap().values()) {
            for (Class<?> clazz : classSet) {
                Method[] methods = clazz.getMethods();
                for (Method m : methods) {
                    if (m.isAnnotationPresent(ResponseHandler.class)) {
                        ResponseHandler responseHandler = m.getAnnotation(ResponseHandler.class);
                        int id = responseHandler.npcId();
                        NPCResponse npcResponse = new NPCResponse(id, m);
                        NPC npc = NPC.getNPCMap().get(id);
                        npc.setResponse(npcResponse);
                    }
                }
            }
        }
    }

    public void execute(Player player) {
        try {
            method.invoke(player);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public int getNpcId() {
        return npcId;
    }

    public static HashMap<Integer, NPCResponse> getResponseMap() {
        return responseMap;
    }
}
