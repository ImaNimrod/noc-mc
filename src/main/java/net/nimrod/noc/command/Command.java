package net.nimrod.noc.command;

import net.minecraft.client.MinecraftClient;

public abstract class Command {

    private String name;
    private String description;

    protected MinecraftClient mc = MinecraftClient.getInstance();

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void execute(String[] args) {}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
