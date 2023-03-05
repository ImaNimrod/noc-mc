package net.nimrod.noc.command.commands;

import net.nimrod.noc.Noc;
import net.nimrod.noc.command.Command;
import net.nimrod.noc.module.Module;

public class Toggle extends Command {

    public Toggle() {
        super("Toggle", "Toggles selected module");
    }

    @Override
    public void execute(String[] args) {
        super.execute(args);
        
        Module module = Noc.moduleManager.getModule(args[0]);

        if (module == null) {
            Noc.INSTANCE.LOGGER.info("Module " + args[0] + " not found");
        } else {
            module.toggle();
            Noc.INSTANCE.LOGGER.info("Module " + args[0] + " toggled " + (module.getEnabled() ? "on" : "off"));
        }
    }

}
