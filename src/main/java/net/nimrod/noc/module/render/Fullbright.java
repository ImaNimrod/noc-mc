package net.nimrod.noc.module.render;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.nimrod.noc.module.Module;
import org.lwjgl.glfw.GLFW;

public class Fullbright extends Module {

    public Fullbright() {
        super("Fullbright", Category.RENDER, GLFW.GLFW_KEY_B);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        super.onDisable();
    }

    @Override 
    public void onTick() {
        mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1337));
        super.onTick();
    }

}
