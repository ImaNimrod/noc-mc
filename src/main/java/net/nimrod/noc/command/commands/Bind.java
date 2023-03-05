package net.nimrod.noc.command.commands;

import net.minecraft.client.util.InputUtil;
import net.nimrod.noc.Noc;
import net.nimrod.noc.command.Command;
import net.nimrod.noc.module.Module;
import org.lwjgl.glfw.GLFW;

public class Bind extends Command {

    public Bind() {
        super("Bind", "Binds a module to the selected key");
    }
    
    @Override
    public void execute(String[] args) {
        super.execute(args);
        
        if (args.length < 2) return;

        if (args[0].equalsIgnoreCase("set")) {
            Module module = Noc.moduleManager.getModule(args[1]);

            if (module == null) {
                Noc.INSTANCE.LOGGER.info("Module not found");
                return;
            } else {
                int key = InputUtil.fromTranslationKey("key.keyboard." + args[2].toLowerCase()).getCode();
                
                module.setKey(key);
                Noc.INSTANCE.LOGGER.info("Bind for " + module.getName() + " set to key: " + args[2].toLowerCase());
            }
        } else if (args[0].equalsIgnoreCase("del")) {
            Module module = Noc.moduleManager.getModule(args[1]);

            if (module == null) {
                Noc.INSTANCE.LOGGER.info("Module not found");
                return;
            } else {
                module.setKey(GLFW.GLFW_KEY_UNKNOWN);
                Noc.INSTANCE.LOGGER.info("Bind removed for " + module.getName());
            }
        }
    }

}
