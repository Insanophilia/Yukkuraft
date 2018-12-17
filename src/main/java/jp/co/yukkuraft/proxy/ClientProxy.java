package jp.co.yukkuraft.proxy;

import jp.co.yukkuraft.YuBlocks;
import jp.co.yukkuraft.YuEntities;
import jp.co.yukkuraft.YuItems;

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
    }

    @Override
    public void postInit()
    {
        super.postInit();
    }
}
