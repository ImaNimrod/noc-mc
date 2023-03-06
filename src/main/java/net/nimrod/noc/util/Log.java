package net.nimrod.noc.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    
    private static final String logName = "NOC";

    private static final Logger logger = LoggerFactory.getLogger(logName);
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void consoleLog(String message) {
        logger.info(message);
    }

    public static void chatLog(String message) {
		mc.inGameHud.getChatHud().addMessage(Text.of("§5[NOC] §f" + message));
    }

}
