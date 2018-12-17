package jp.co.yukkuraft;

import org.apache.logging.log4j.Logger;

import jp.co.yukkuraft.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * このクラスは Mod 中心的なクラスです、  Mod の初期化を行います。
 *
 * @author Insanophilia
 *
 */
@Mod(
        modid = ModCore.MOD_ID,
        name = ModCore.MOD_NAME,
        version = ModCore.MOD_VERSION)
public class ModCore
{
    // ModConstants
    public static final String MOD_ID      = "yukkuraft";
    public static final String MOD_NAME    = "Yukkuraft";
    public static final String MOD_VERSION = "1.0";
    // ModInstance
    @Mod.Instance(ModCore.MOD_ID)
    public static ModCore instance;
    // CreativeTab
    public static final CreativeTabs YU_CREATIVE_TAB = new YuCreativeTab(ModCore.MOD_NAME);
    // Logger
    public static Logger logger;
    // Proxy
    @SidedProxy(
            clientSide = "jp.co.yukkuraft.proxy.ClientProxy",
            serverSide = "jp.co.yukkuraft.proxy.ServerProxy")
    public static CommonProxy proxy;

    // ここから初期化処理
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }
    // ここまで初期化処理
}
