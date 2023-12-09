package me.shortdev.devutils.packages.command.listeners;

import me.shortdev.devutils.packages.command.Command;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.lang.reflect.Parameter;
import java.util.Arrays;

public class CommandListener implements Listener {
    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent e) {
        String message = e.getMessage();
        String[] argsTemp = message.split(" ");
        String label = argsTemp[0];
        String[] args = Arrays.copyOfRange(argsTemp, 1, argsTemp.length);
        Command command = Command.getCommands().get(label);
        Parameter[] parameters = command.getLogic().getParameters();

    }
}
