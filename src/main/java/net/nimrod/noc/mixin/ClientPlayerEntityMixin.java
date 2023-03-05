package net.nimrod.noc.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.nimrod.noc.Noc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    
    @Inject(method = "sendChatMessage", at = @At(value="HEAD"), cancellable=true)
    private void onChatMessage(String message, Text text, CallbackInfo callbackInfo) {
        if (message.startsWith(Noc.commandManager.getPrefix())) {
            Noc.commandManager.runCommand(message.substring(Noc.commandManager.getPrefix().length()));
            callbackInfo.cancel();
        }
    }

}
