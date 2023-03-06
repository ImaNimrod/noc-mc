package net.nimrod.noc.util;

import com.google.gson.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import net.nimrod.noc.Noc;
import net.nimrod.noc.module.Module;

public class ConfigManager {

	private final String mainDirectory = "noc/";
	private final String configDirectory = mainDirectory + "config/";
	private final String modulesFile = configDirectory + "modules.json";

	public ConfigManager() {
		try {
			createFolders();
			createFiles();
		} catch (IOException e) {
			Noc.INSTANCE.LOGGER.error(e.getMessage());
		}
    }

    public void loadConfig() {
		try {
			loadModules();
		} catch (IOException e) {
			Noc.INSTANCE.LOGGER.error(e.getMessage());
		}
    }

    public void saveConfig() { 
		try {
			saveModules();
		} catch (IOException e) {
			Noc.INSTANCE.LOGGER.error(e.getMessage());
		}
    }

	private void createFolders() throws IOException {
		if (!Files.exists(Paths.get(mainDirectory))) Files.createDirectory(Paths.get(mainDirectory));
		if (!Files.exists(Paths.get(configDirectory))) Files.createDirectory(Paths.get(configDirectory));
	}

	private void createFiles() throws IOException {
		if (!Files.exists(Paths.get(modulesFile))) Files.createFile(Paths.get(modulesFile));
	}

    private void loadModules() throws IOException {
        try (FileReader reader = new FileReader(modulesFile, Charset.forName("UTF8"))) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement instanceof JsonNull) return;

            JsonArray modulesArray = jsonElement.getAsJsonArray();

            reader.close();

            for (JsonElement element : modulesArray) {
                JsonObject moduleJson = (JsonObject) element;

                Module module = Noc.moduleManager.getModule(moduleJson.get("name").getAsString());
                
                if (module == null) continue;

                module.setKey(moduleJson.get("key").getAsInt());
                module.setEnabled(moduleJson.get("enabled").getAsBoolean());
            }
        } catch (IOException e) {
            Noc.INSTANCE.LOGGER.error(e.getMessage());
        }
    }

    private void saveModules() throws IOException {
        JsonArray modulesArray = new JsonArray();

        for (Module module: Noc.moduleManager.getModules()) {
            JsonObject moduleJson = new JsonObject();

            moduleJson.addProperty("name", module.getName());
            moduleJson.addProperty("key", module.getKey());
            moduleJson.addProperty("enabled", module.getEnabled());

			modulesArray.add(moduleJson);
        }

		try (FileWriter writer = new FileWriter(modulesFile, Charset.forName("UTF8"))) {
			writer.write(modulesArray.toString());
            writer.close();
		} catch (IOException e) {
			Noc.INSTANCE.LOGGER.error(e.getMessage());
		}
    }

}
