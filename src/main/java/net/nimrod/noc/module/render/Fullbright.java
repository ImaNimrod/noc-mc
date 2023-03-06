package net.nimrod.noc.module.render;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.nimrod.noc.module.Category;
import net.nimrod.noc.module.Module;
import net.nimrod.noc.util.Setting;
import org.lwjgl.glfw.GLFW;

public class Fullbright extends Module {

    private Setting<Mode> mode = new Setting<Mode>("Mode", "Mode to use Fullbright", Mode.Gamma, null, null);

    private double prevGamma;

    public Fullbright() {
        super("Fullbright", "Brightens your day (and night)", Category.RENDER, GLFW.GLFW_KEY_B);

        addSetting(mode);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();

        if (!isGamma()) {
            if (mc.player == null) return;
            mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        }
    }

    @Override 
    public void onTick() {
        super.onTick();

        if (!isGamma()) mc.player.setStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 999999, 1), mc.player);
    }

    public boolean isGamma() {
        return getSetting("mode").getValue() == Mode.Gamma;
    }

    public enum Mode {
        Gamma,
        Potion
    }

}
