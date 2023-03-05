package net.nimrod.noc.mixin;

import net.minecraft.client.MinecraftClient;
import net.nimrod.noc.Noc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onTick(CallbackInfo callbackInfo) {
        Noc.INSTANCE.onTick();
    }

}
