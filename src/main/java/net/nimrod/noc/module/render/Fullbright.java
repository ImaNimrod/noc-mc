package net.nimrod.noc.module.render;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.nimrod.noc.module.Category;
import net.nimrod.noc.module.Module;
import org.lwjgl.glfw.GLFW;

public class Fullbright extends Module {

    public Fullbright() {
        super("Fullbright", "Brightens your day (and night)", Category.RENDER, GLFW.GLFW_KEY_B);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();

        if (mc.player == null) return;
        mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }

    @Override 
    public void onTick() {
        super.onTick();

        mc.player.setStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 999999, 1), mc.player);
    }

}
