package net.nimrod.noc.command;

import net.minecraft.client.MinecraftClient;

public abstract class Command {

    private final String name;
    private final String description;
    private final String syntax;

    protected MinecraftClient mc = MinecraftClient.getInstance();

    public Command(String name, String description, String syntax) {
        this.name = name;
        this.description = description;
        this.syntax = syntax;
    }

    public void execute(String[] args) {}

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSyntax() {
        return "Syntax: " + this.syntax;
    }

}
