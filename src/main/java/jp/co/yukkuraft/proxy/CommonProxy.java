package jp.co.yukkuraft.proxy;

import jp.co.yukkuraft.YuBlocks;
import jp.co.yukkuraft.YuEntities;
import jp.co.yukkuraft.YuEventHandler;
import jp.co.yukkuraft.YuItems;
import jp.co.yukkuraft.constant.YuLootTableList;
import jp.co.yukkuraft.tileentity.TileEntityBlockYukkuriMarisa;
import jp.co.yukkuraft.tileentity.TileEntityBlockYukkuriReimu;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
        // TileEntity 登録
        GameRegistry.registerTileEntity(TileEntityBlockYukkuriMarisa.class, "tile_entity_yukkuri_marisa");
        GameRegistry.registerTileEntity(TileEntityBlockYukkuriReimu.class, "tile_entity_yukkuri_reimu");
    }

    public void init()
    {
        //　Item 精錬レシピ登録
        YuItems.registerSmeltings();
        //　Block 精錬レシピ登録
        YuBlocks.registerSmeltings();
        // LootTableList 登録
        YuLootTableList.registerLootTableList();
        // EventHandler 登録
        MinecraftForge.EVENT_BUS.register(new YuEventHandler());
    }

    public void postInit()
    {
    }
}
