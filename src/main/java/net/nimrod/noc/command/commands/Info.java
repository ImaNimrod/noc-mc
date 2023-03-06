package net.nimrod.noc.command.commands;

import net.nimrod.noc.Noc;
import net.nimrod.noc.command.Command;
import net.nimrod.noc.util.Log;

public class Info extends Command {

    public Info() {
        super("Info", "Displays client information", ".info");
    }

    @Override
    public void execute(String[] args) {
        super.execute(args);
        
        if (args.length == 1 && args[0].equals("")) {
            Log.chatLog("version: " + Noc.VERSION + " created by nimrod");
        } else {
            Log.chatLog(super.getSyntax());
        }
    }

}
