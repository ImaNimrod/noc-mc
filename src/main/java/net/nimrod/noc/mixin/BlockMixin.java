package net.nimrod.noc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.nimrod.noc.Noc;
import net.nimrod.noc.module.render.XRay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "shouldDrawSide", at = @At("HEAD"), cancellable = true)
    private static void onShouldDrawSide(BlockState state, BlockView world, BlockPos pos, Direction direction, BlockPos otherPos, CallbackInfoReturnable<Boolean> cir) {
        XRay xray = (XRay) Noc.moduleManager.getModule("XRay");

        if (xray.getEnabled()) {
            boolean isXRay = xray.isXRayBlock(state.getBlock());
            cir.setReturnValue(isXRay);
        }
    } 

}
