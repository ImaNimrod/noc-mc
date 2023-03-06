package net.nimrod.noc.module.render;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.nimrod.noc.module.Category;
import net.nimrod.noc.module.Module;
import org.lwjgl.glfw.GLFW;

public class XRay extends Module {

    private ArrayList<Block> blocks = new ArrayList<Block>();
    
    public XRay() {
        super("XRay", "Allows player to only see selected blocks", Category.RENDER, GLFW.GLFW_KEY_X);

        blocks.add(Blocks.COAL_ORE);
        blocks.add(Blocks.IRON_ORE);
        blocks.add(Blocks.GOLD_ORE);
    } 

    @Override
    public void onEnable() {
        super.onEnable();

        if (mc.worldRenderer == null) return;
        mc.worldRenderer.reload();
    }

    @Override
    public void onDisable() {
        super.onDisable();

        if (mc.worldRenderer == null) return;
        mc.worldRenderer.reload();
    }

    public boolean isXRayBlock(Block block) {
		return this.blocks.contains(block);
    }

}
