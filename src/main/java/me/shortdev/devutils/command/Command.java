package me.shortdev.devutils.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Set;

public class Command {
    public final Class<?>[] desiredParamClasses = {CommandSender.class, org.bukkit.command.Command.class, String.class, String[].class};
    private static HashMap<String, Command> commands = new HashMap<>();
    private String name = "";
    private String[] hierarchy = null;
    private Method logic = null;

    private String[] exampleHierarchy = { // for "report" command
            "(PLAYER)",
            "-hacking:PASS_CHILDREN_TO;className;methodName",
            "--killaura",
            "--reach",
            "--fly",
            "-chat:PASS_CHILDREN_TO;className;methodName",
            "--harassment",
            "--racism",
            "help"
    };

    public Command(String name, CommandExecutor executor) throws NoSuchMethodException {
        this.name = name;
        Class<?> clazz = executor.getClass();
        logic = clazz.getMethod("onCommand", CommandSender.class, org.bukkit.command.Command.class, String.class, String[].class);
        commands.put(name, this);
    }

    public Command(String name, Method logic) throws InvalidParameterException {
        this.name = name;
        Parameter[] parameters = logic.getParameters();
        if (parameters.length != 4) throw new InvalidParameterException("Method " + logic.getName() + " in class " + logic.getDeclaringClass() + " does not have the 4 necessary parameters for DevUtils to create a command.");
        Class<?>[] parameterClasses = new Class<?>[4];
        parameterClasses[0] = parameters[0].getType();
        parameterClasses[1] = parameters[1].getType();
        parameterClasses[2] = parameters[2].getType();
        parameterClasses[3] = parameters[3].getType();
        if (Set.of(parameterClasses) != Set.of(desiredParamClasses)) throw new InvalidParameterException("Method " + logic.getName() + " in class " + logic.getDeclaringClass() + " has the parameters " + parameters[0].getName() + ", " + parameters[1].getName() + ", " + parameters[2].getName() + ", " + parameters[3].getName() + ", but it requires CommandSender, org.bukkit.command.Command, String, and String[] for DevUtils to process it.");
        this.logic = logic;
        commands.put(name, this);
    }

    public Command(String name, String[] hierarchy) {
        commands.put(name, this);
    }

    public static HashMap<String, Command> getCommands() {
        return commands;
    }

    public Method getLogic() {
        return logic;
    }

    public void setLogic(Method logic) {
        this.logic = logic;
    }

    public String[] getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String[] hierarchy) {
        this.hierarchy = hierarchy;
    }
}
