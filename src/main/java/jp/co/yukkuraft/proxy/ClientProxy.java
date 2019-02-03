package jp.co.yukkuraft.proxy;

import jp.co.yukkuraft.YuBlocks;
import jp.co.yukkuraft.YuEntities;
import jp.co.yukkuraft.YuItems;
import jp.co.yukkuraft.complex.engine.TesrYukkuriEngine;
import jp.co.yukkuraft.complex.engine.TileYukkuriEngine;
import jp.co.yukkuraft.complex.yukkuri.block.TesrYukkuriMarisa;
import jp.co.yukkuraft.complex.yukkuri.block.TesrYukkuriReimu;
import jp.co.yukkuraft.complex.yukkuri.block.TileYukkuriMarisa;
import jp.co.yukkuraft.complex.yukkuri.block.TileYukkuriReimu;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * このクラスは Client 専用処理の登録を行います。
 *
 * @author Insanophilia
 *
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit()
    {
        super.preInit();
        // Item Model 登録
        YuItems.registerModels();
        // Block Model 登録
        YuBlocks.registerModels();
        // Entity　Renderer 登録
        YuEntities.registerEntityRenderers();
    }

    @Override
    public void init()
    {
        super.init();
        // TileEntity Renderer 登録
        ClientRegistry.bindTileEntitySpecialRenderer(TileYukkuriMarisa.class, new TesrYukkuriMarisa());
        ClientRegistry.bindTileEntitySpecialRenderer(TileYukkuriReimu.class, new TesrYukkuriReimu());
        ClientRegistry.bindTileEntitySpecialRenderer(TileYukkuriEngine.class, new TesrYukkuriEngine());
    }

    @Override
    public void postInit()
    {
        super.postInit();
    }
}
