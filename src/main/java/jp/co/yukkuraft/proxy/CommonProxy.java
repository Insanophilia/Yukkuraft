package jp.co.yukkuraft.proxy;

import jp.co.yukkuraft.YuBlocks;
import jp.co.yukkuraft.YuEntities;
import jp.co.yukkuraft.YuEventHandler;
import jp.co.yukkuraft.YuItems;
import net.minecraftforge.common.MinecraftForge;

/**
 * このクラスは Client と Server 共通処理の登録を行います。
 *
 * @author Insanophilia
 *
 */
public abstract class CommonProxy
{
    public void preInit()
    {
        // Item 登録
        YuItems.registerItems();
        // Block 登録
        YuBlocks.registerBlocks();
        // Entity 登録
        YuEntities.registerEntities();
    }

    public void init()
    {
        //　Item 精錬レシピ登録
        YuItems.registerSmeltings();
        //　Block 精錬レシピ登録
        YuBlocks.registerSmeltings();
        // EventHandler 登録
        MinecraftForge.EVENT_BUS.register(new YuEventHandler());
    }

    public void postInit()
    {
    }
}
