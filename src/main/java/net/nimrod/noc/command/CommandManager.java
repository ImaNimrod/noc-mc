package net.nimrod.noc.command;

import java.util.HashMap;
import java.util.Map;
import net.nimrod.noc.command.commands.*;

public class CommandManager {

    private HashMap<String, Command> commands = new HashMap<String, Command>();

    private final String prefix = ".";

    public CommandManager() {
        commands.put("bind", new Bind());
        commands.put("info", new Info());
        commands.put("toggle", new Toggle());
    }

    public void runCommand(String input) {
        String[] split = input.split(" ");
        String commandName = split[0];
        String args = input.substring(commandName.length()).trim();

        for (Map.Entry<String, Command> entry : this.commands.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(commandName)) {
                entry.getValue().execute(args.split(" "));
                return;
            }
        }
    }

	public HashMap<String, Command> getCommands() {
		return this.commands;
	}

    public String getPrefix() {
        return this.prefix;
    }

}
