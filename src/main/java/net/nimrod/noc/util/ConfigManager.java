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
            e.printStackTrace();
		}
    }

    public void loadConfig() {
		try {
			loadModules();
		} catch (IOException e) {
            e.printStackTrace();
		}
    }

    public void saveConfig() { 
		try {
			saveModules();
		} catch (IOException e) {
            e.printStackTrace();
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

            for (JsonElement moduleElement : modulesArray) {
                JsonObject moduleJson = (JsonObject) moduleElement;

                Module module = Noc.moduleManager.getModule(moduleJson.get("name").getAsString());
                
                if (module == null) continue;

                module.setKey(moduleJson.get("key").getAsInt());
                module.setEnabled(moduleJson.get("enabled").getAsBoolean());

				JsonArray settingsArray = moduleJson.getAsJsonArray("settings");
                
                for (JsonElement settingsElement : settingsArray) {
					JsonObject settingJson = (JsonObject) settingsElement;

					Setting setting = module.getSetting(settingJson.get("name").getAsString());

					if (setting == null) throw new IllegalStateException("Cannot find parsed setting!");

                    if (setting.getValue() instanceof Boolean)
						setting.setValue(settingJson.get("value").getAsBoolean());
					else if (setting.getValue() instanceof Number) {
						Double raw = settingJson.get("value").getAsDouble();
						if (setting.getValue() instanceof Integer)
							setting.setValue(raw.intValue());
						else if (setting.getValue() instanceof Long)
							setting.setValue(raw.longValue());
						else if (setting.getValue() instanceof Float)
							setting.setValue(raw.floatValue());
						else if (setting.getValue() instanceof Double)
							setting.setValue(raw.doubleValue());
						else
							throw new IllegalStateException("Illegal setting type!");
					} else if (setting.getValue() instanceof String)
						setting.setValue(settingJson.get("value").getAsString());
					else if (setting.getValue() instanceof Enum) {
						setting.setValue(Enum.valueOf(((Enum<?>) setting.getValue()).getClass(), settingJson.get("value").getAsString()));
					} else
						throw new IllegalStateException("Illegal setting type!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveModules() throws IOException {
        JsonArray modulesArray = new JsonArray();

        for (Module module: Noc.moduleManager.getModules()) {
            JsonObject moduleJson = new JsonObject();

            moduleJson.addProperty("name", module.getName());
            moduleJson.addProperty("key", module.getKey());
            moduleJson.addProperty("enabled", module.getEnabled());

			JsonArray settingsArray = new JsonArray();

            for (Setting<?> setting : module.getSettings()) {
                JsonObject settingJson = new JsonObject();

                settingJson.addProperty("name", setting.getName());

				if (setting.getValue() instanceof Boolean)
					settingJson.addProperty("value", (Boolean) setting.getValue());
				else if (setting.getValue() instanceof Number)
					settingJson.addProperty("value", ((Number) setting.getValue()).doubleValue());
				else if (setting.getValue() instanceof String)
					settingJson.addProperty("value", (String) setting.getValue());
				else if (setting.getValue() instanceof Enum)
					settingJson.addProperty("value", ((Enum) setting.getValue()).name());
				else
					throw new IllegalStateException("Illegal setting type! " + setting.getName());

				settingsArray.add((settingJson));
            }

			moduleJson.add("settings", settingsArray);
			modulesArray.add(moduleJson);
        }

		try (FileWriter writer = new FileWriter(modulesFile, Charset.forName("UTF8"))) {
			writer.write(modulesArray.toString());
            writer.close();
		} catch (IOException e) {
            e.printStackTrace();
		}
    }

}
