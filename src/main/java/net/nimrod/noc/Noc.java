package net.nimrod.noc;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.nimrod.noc.command.CommandManager;
import net.nimrod.noc.module.ModuleManager;
import net.nimrod.noc.util.ConfigManager;
import net.nimrod.noc.util.Log;
import org.lwjgl.glfw.GLFW;

public class Noc implements ModInitializer {

    public static final String NAME = "NOC";
    public static final String VERSION = "1.0.0";

    public static final Noc INSTANCE = new Noc();

    public static final CommandManager commandManager = new CommandManager();
    public static final ConfigManager configManager = new ConfigManager();
    public static final ModuleManager moduleManager = new ModuleManager();

    protected final MinecraftClient mc = MinecraftClient.getInstance();

	@Override
	public void onInitialize() {
		Log.consoleLog("Initializing " + NAME + " version: " + VERSION);

        configManager.loadConfig();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> configManager.saveConfig()));
	}

    public void onTick() {
        if (mc.player != null) moduleManager.onTick();
    }

    public void onKeyPress(int key, int action) {
        if (mc.currentScreen == null && mc.getOverlay() == null &&
            action == GLFW.GLFW_RELEASE && commandManager.getPrefix().equals(GLFW.glfwGetKeyName(key, 0)))
            mc.setScreen(new ChatScreen(commandManager.getPrefix()));

        moduleManager.onKeyPress(key, action);
    }

}
