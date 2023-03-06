package net.nimrod.noc.mixin;

import net.minecraft.client.render.LightmapTextureManager;
import net.nimrod.noc.Noc;
import net.nimrod.noc.module.render.Fullbright;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {

    // thanks meteor
    @ModifyArgs(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/NativeImage;setColor(III)V"))
    private void update(Args args) {
        Fullbright fullbright = (Fullbright) Noc.moduleManager.getModule("Fullbright");
        
        if (fullbright.getEnabled() && fullbright.isGamma()) 
            args.set(2, -1);
    }

}
