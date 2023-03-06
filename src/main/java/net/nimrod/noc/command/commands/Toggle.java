package net.nimrod.noc.command.commands;

import net.nimrod.noc.Noc;
import net.nimrod.noc.command.Command;
import net.nimrod.noc.command.CommandManager;
import net.nimrod.noc.module.Module;
import net.nimrod.noc.util.Log;

public class Toggle extends Command {

    public Toggle() {
        super("Toggle", "Toggles selected module");
    }

    @Override
    public void execute(String[] args) {
        super.execute(args);
        
        Module module = Noc.moduleManager.getModule(args[0]);

        if (module == null) {
            Log.chatLog("Module not found");
        } else {
            module.toggle();
            Log.chatLog("Module " + args[0] + " toggled " + (module.getEnabled() ? "on" : "off"));
        }
    }

}
