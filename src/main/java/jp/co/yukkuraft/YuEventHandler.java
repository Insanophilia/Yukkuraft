package jp.co.yukkuraft;

import net.minecraft.item.Item;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * このクラスは Mod Event 処理を定義します。
 *
 * @author Insanophilia
 *
 */
public class YuEventHandler
{

    /**
     * このメソッドは Mod Item に燃焼時間を設定します。
     *
     * @param event
     */
    @SubscribeEvent(
            priority = EventPriority.NORMAL,
            receiveCanceled = true)
    public void hookFurnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event)
    {
        // Item
        setBurnTime(event, YuItems.PURPLE_DIAMOND, 500);
        setBurnTime(event, YuItems.OKAZARI_MARISA, 500);
        // Block
        setBurnTime(event, Item.getItemFromBlock(YuBlocks.SUGAR_BLOCK), 1000);
    }

    private void setBurnTime(FurnaceFuelBurnTimeEvent event, Item item, int burnTime)
    {
        if (event.getItemStack().getItem() == item)
        {
            event.setBurnTime(burnTime);
        }
    }
}
