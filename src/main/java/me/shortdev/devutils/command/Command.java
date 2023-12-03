package me.shortdev.devutils.command;

public class Command {
    private String name;
    private String[] hierarchy;

    private String[] exampleHierarchy = { // for "friend" command
            "add",
            "-(PLAYER):methodName1", // add single player
            "-(PLAYER)...:methodName2", // add list of players
            "remove",
            "-(PLAYER):"
    };
}
