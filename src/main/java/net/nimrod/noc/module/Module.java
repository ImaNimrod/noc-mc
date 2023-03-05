package net.nimrod.noc.module;

import net.minecraft.client.MinecraftClient;

public abstract class Module {

    private static String name;
    private static Category category;
    private static int key;
    private static boolean enabled;

    protected static MinecraftClient mc;        

    public enum Category {
        COMBAT,
        MOVEMENT,
        RENDER,
    }

    public Module(String name, Category category, int key) {
        this.name = name;
        this.category = category;
        this.key = key;
        this.mc = MinecraftClient.getInstance();
    }

    public void onEnable() {}
    public void onDisable() {}

    public void toggle() {
        this.enabled = !this.enabled;

        if (enabled) onEnable();
        else onDisable();
    }

    public void onTick() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if (enabled) onEnable();
        else onDisable();
    }

}
