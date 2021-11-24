package cheaters.get.banned.features;

import cheaters.get.banned.Shady;
import cheaters.get.banned.config.Config;
import cheaters.get.banned.events.ClickEvent;
import cheaters.get.banned.utils.KeybindUtils;
import cheaters.get.banned.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class GhostBlocks {

    public GhostBlocks() {
        KeybindUtils.register("Create Ghost Block", Keyboard.KEY_G);
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if(Config.ghostBlockKeybind && KeybindUtils.get("Create Ghost Block").isKeyDown()) {
            BlockPos lookingAtPos = Shady.mc.thePlayer.rayTrace(Shady.mc.playerController.getBlockReachDistance(), 1).getBlockPos();
            if(lookingAtPos != null) {
                Block lookingAtblock = Shady.mc.theWorld.getBlockState(lookingAtPos).getBlock();
                if(!Utils.isInteractable(lookingAtblock)) {
                    Shady.mc.theWorld.setBlockToAir(lookingAtPos);
                }
            }
        }
    }

    @SubscribeEvent
    public void onRightClick(ClickEvent.Right event) {
        if(Utils.inSkyBlock && Config.stonkGhostBlock && Shady.mc.objectMouseOver != null && Shady.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && !Utils.isInteractable(Shady.mc.theWorld.getBlockState(Shady.mc.objectMouseOver.getBlockPos()).getBlock())) {
            String itemId = Utils.getSkyBlockID(Shady.mc.thePlayer.getHeldItem());
            if(itemId.equals("STONK_PICKAXE") || itemId.equals("GOLD_PICKAXE")) {
                Shady.mc.theWorld.setBlockToAir(Shady.mc.objectMouseOver.getBlockPos());
                event.setCanceled(true);
            }
        }
    }

}
