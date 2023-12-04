package me.shortdev.devutils.command;

public class Command {
    private String name;
    private String[] hierarchy;

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

    public Object[] hierarchyImplementation(String[] hierarchy) {
        for (int i = 0; i < hierarchy.length; i++) {
            String prevLine = hierarchy[i-1];
            String line = hierarchy[i];
            char[] lineChars = line.toCharArray();
            int depth = 0;
            for (int j = 0; j < line.length(); j++) {
                if (lineChars[j] != '-') break;
                depth++;
            }

        }
    }
}
