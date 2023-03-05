package net.nimrod.noc;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.nimrod.noc.command.CommandManager;
import net.nimrod.noc.module.ModuleManager;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Noc implements ModInitializer {

    public static final String NAME = "NOC";
    public static final String VERSION = "1.0.0";

    public static final Noc INSTANCE = new Noc();
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static final CommandManager commandManager = new CommandManager();
    public static final ModuleManager moduleManager = new ModuleManager();

    protected static final MinecraftClient mc = MinecraftClient.getInstance();

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing " + NAME + " version: " + VERSION);
	}

    public void onTick() {
        if (mc.player != null) moduleManager.onTick();
    }

    public void onKeyPress(int key, int action) {
        if (mc.currentScreen != null && mc.getOverlay() != null) return;

        if (action == GLFW.GLFW_PRESS && commandManager.getPrefix().equals(GLFW.glfwGetKeyName(key, 0))) 
            mc.setScreen(new ChatScreen(""));

        moduleManager.onKeyPress(key, action);
    }

}
