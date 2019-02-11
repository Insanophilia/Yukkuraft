package jp.co.yukkuraft.proxy;

import jp.co.yukkuraft.YuBlocks;
import jp.co.yukkuraft.YuEntities;
import jp.co.yukkuraft.YuEventHandler;
import jp.co.yukkuraft.YuItems;
import jp.co.yukkuraft.complex.engine.TileYukkuriEngine;
import jp.co.yukkuraft.complex.yukkuri.block.TileYukkuriMarisa;
import jp.co.yukkuraft.complex.yukkuri.block.TileYukkuriReimu;
import jp.co.yukkuraft.complex.yukkuri.block.TileYukkuriRemilia;
import jp.co.yukkuraft.constant.YuLootTableList;
import jp.co.yukkuraft.test.TileHollowMultiBlock;
import jp.co.yukkuraft.test.TileStoneMultiblock;
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
    @SuppressWarnings("deprecation")
    public void preInit()
    {
        // Item 登録
        YuItems.registerItems();
        // Block 登録
        YuBlocks.registerBlocks();
        // Entity 登録
        YuEntities.registerEntities();
        // TileEntity 登録
        GameRegistry.registerTileEntity(TileYukkuriMarisa.class, "tile_yukkuri_marisa");
        GameRegistry.registerTileEntity(TileYukkuriReimu.class, "tile_yukkuri_reimu");
        GameRegistry.registerTileEntity(TileYukkuriRemilia.class, "tile_yukkuri_remilia");

        GameRegistry.registerTileEntity(TileYukkuriEngine.class, "tile_yukkuri_engine");
        GameRegistry.registerTileEntity(TileStoneMultiblock.class, "tile_stone_multi_block");
        GameRegistry.registerTileEntity(TileHollowMultiBlock.class, "tile_hollow_multi_block");
    }

    public void init()
    {
        // Item 精錬レシピ登録
        YuItems.registerSmeltings();
        // Block 精錬レシピ登録
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
