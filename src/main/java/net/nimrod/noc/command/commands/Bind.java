package net.nimrod.noc.command.commands;

import net.minecraft.client.util.InputUtil;
import net.nimrod.noc.Noc;
import net.nimrod.noc.command.Command;
import net.nimrod.noc.module.Module;
import net.nimrod.noc.util.Log;
import org.lwjgl.glfw.GLFW;

public class Bind extends Command {

    public Bind() {
        super("Bind", "Binds a module to the selected key", ".bind <set | del> <module> <key>");
    }
    
    @Override
    public void execute(String[] args) {
        super.execute(args);
        
        if (args.length >= 2) {
            if (args[0].equalsIgnoreCase("set") && args.length == 3) {
                Module module = Noc.moduleManager.getModule(args[1]);

                if (module == null) {
                    Log.chatLog("Module not found");
                } else {
                    if (args[2].length() == 1) {
                        int key = InputUtil.fromTranslationKey("key.keyboard." + args[2].toLowerCase()).getCode();
                        
                        module.setKey(key);
                        Log.chatLog("Bind for " + module.getName() + " set to key: " + args[2].toLowerCase());
                    } else {
                        Log.chatLog(super.getSyntax());
                    }
                }
            } else if (args[0].equalsIgnoreCase("del") && args.length == 2) {
                Module module = Noc.moduleManager.getModule(args[1]);

                if (module == null) {
                    Log.chatLog("Module not found");
                } else {
                    module.setKey(GLFW.GLFW_KEY_UNKNOWN);
                    Log.chatLog("Bind removed for " + module.getName());
                }
            } else {
                Log.chatLog(super.getSyntax());
            }
        } else {
            Log.chatLog(super.getSyntax());
        }
    }

}
