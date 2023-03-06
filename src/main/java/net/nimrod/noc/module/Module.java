package net.nimrod.noc.module;

import java.util.ArrayList;
import net.minecraft.client.MinecraftClient;
import net.nimrod.noc.util.Setting;

public abstract class Module {

    private final String name;
    private final String description;
    private final Category category;
    private int key;
    private boolean enabled;

    private ArrayList<Setting> settings = new ArrayList<Setting>();

    protected MinecraftClient mc = MinecraftClient.getInstance();

    public Module(String name, String description, Category category, int key) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.key = key;
        this.enabled = false;
    }

    public void onEnable() {}
    public void onDisable() {}

    public void toggle() {
        this.enabled = !this.enabled;

        if (this.enabled) onEnable();
        else onDisable();
    }

    public void onTick() {}

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Category getCategory() {
        return this.category;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if (this.enabled) onEnable();
        else onDisable();
    }

    public ArrayList<Setting> getSettings() {
        return this.settings;
    }

    public void addSetting(Setting<?> setting) {
        this.settings.add(setting);
    }

	public Setting<?> getSetting(String name) { 
        return this.settings.stream().filter(s -> s.getName()
                                .equalsIgnoreCase(name))
                                .findFirst()
                                .orElse(null);
    }
}
