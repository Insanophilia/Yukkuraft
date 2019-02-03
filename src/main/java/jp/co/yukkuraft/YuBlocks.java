package jp.co.yukkuraft;

import jp.co.yukkuraft.base.block.YuBlock;
import jp.co.yukkuraft.complex.engine.BlockYukkuriEngine;
import jp.co.yukkuraft.complex.roller.BlockRoller;
import jp.co.yukkuraft.complex.yukkuri.block.BlockYukkuriMarisa;
import jp.co.yukkuraft.complex.yukkuri.block.BlockYukkuriReimu;
import jp.co.yukkuraft.simple.block.BlockBeltConveyor;
import jp.co.yukkuraft.simple.block.BlockFactoryGlass;
import jp.co.yukkuraft.simple.block.BlockHouraku;
import jp.co.yukkuraft.simple.block.BlockMincer;
import jp.co.yukkuraft.simple.block.BlockTest0;
import jp.co.yukkuraft.test.BlockHollow;
import jp.co.yukkuraft.test.BlockStone;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * このクラスは「MODで追加されるブロック」の登録を行います。
 *
 * @author Insanophilia
 *
 */
public class YuBlocks
{
    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
    // ここからブロックのインスタンス

    // Simple Block
    public static final Block YUKARI_BLOCK;;
    public static final Block SUGAR_BLOCK;
    public static final Block BEEF_BLOCK;
    public static final Block STRANGE_MEAT_BLOCK;
    public static final Block COOKED_MEAT_BLOCK;
    public static final Block MUGSHOT_BACKGROUND;
    // Custom Block
    public static final Block MINCER;
    public static final Block BELT_CONVEYOR;
    public static final Block HOURAKU_OFF;
    public static final Block HOURAKU_ON;
    public static final Block FACTORY_GLASS;
    // Test Block
    public static final Block TEST0;
    public static final Block TEST1;
    public static final Block TEST2;
    // TileEntity Block
    public static final Block YUKKURI_MARISA_BLOCK;
    public static final Block YUKKURI_REIMU_BLOCK;
    public static final Block YUKKURI_ENGINE;
    public static final Block ROLLER;

    static
    {
        // Simple Block
        YUKARI_BLOCK = new YuBlock("yukari_block", Material.IRON, SoundType.METAL)
                .setTooltipVisible()
                .setHardness(5.0F).setResistance(6000000.0F).setLightLevel(1.0F);
        SUGAR_BLOCK = new YuBlock("sugar_block", Material.SAND, SoundType.SAND);
        BEEF_BLOCK = new YuBlock("beef_block", Material.CLAY, SoundType.SLIME);
        STRANGE_MEAT_BLOCK = new YuBlock("strange_meat_block", Material.CLAY, SoundType.SLIME);
        COOKED_MEAT_BLOCK = new YuBlock("cooked_meat_block", Material.CLAY, SoundType.PLANT);
        MUGSHOT_BACKGROUND = new YuBlock("mugshot_background", Material.IRON, SoundType.METAL)
                .setHardness(5.0F).setResistance(10.0F);
        // Custom Block
        MINCER = new BlockMincer("mincer", Material.IRON, SoundType.METAL)
                .setHardness(5.0F).setResistance(10.0F);
        BELT_CONVEYOR = new BlockBeltConveyor("belt_conveyor", Material.IRON, SoundType.METAL)
                .setHardness(5.0F).setResistance(10.0F);
        HOURAKU_OFF = new BlockHouraku("block_houraku_off", Material.IRON, SoundType.METAL, false)
                .setHardness(5.0F).setResistance(10.0F);
        HOURAKU_ON = new BlockHouraku("block_houraku_on", Material.IRON, SoundType.METAL, true)
                .setHardness(5.0F).setResistance(10.0F).setLightLevel(1.0F);
        FACTORY_GLASS = new BlockFactoryGlass("factory_glass", Material.GLASS, SoundType.GLASS)
                .setHardness(5.0F).setResistance(10.0F);
        // TileEntity Block
        YUKKURI_MARISA_BLOCK = new BlockYukkuriMarisa("yukkuri_marisa_block", Material.CLAY, SoundType.SLIME);
        YUKKURI_REIMU_BLOCK = new BlockYukkuriReimu("yukkuri_reimu_block", Material.CLAY, SoundType.SLIME);
        YUKKURI_ENGINE = new BlockYukkuriEngine("yukkuri_engine", Material.IRON, SoundType.METAL)
                .setHardness(5.0F).setResistance(10.0F);
        ROLLER = new BlockRoller("roller", Material.IRON, SoundType.METAL)
                .setHardness(5.0F).setResistance(10.0F);
        // Test Block
        TEST0 = new BlockTest0("test0", Material.IRON, SoundType.METAL);
        TEST1 = new BlockStone("test1", Material.IRON, SoundType.METAL);
        TEST2 = new BlockHollow("test2", Material.IRON, SoundType.METAL);
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
    // ここからブロックの登録

    public static void registerBlocks()
    {
        // Simple Block
        YuBlocks.registerBlock(YUKARI_BLOCK);
        YuBlocks.registerBlock(SUGAR_BLOCK);
        YuBlocks.registerBlock(BEEF_BLOCK);
        YuBlocks.registerBlock(STRANGE_MEAT_BLOCK);
        YuBlocks.registerBlock(COOKED_MEAT_BLOCK);
        YuBlocks.registerBlock(MUGSHOT_BACKGROUND);
        // Custom Block
        YuBlocks.registerBlock(MINCER);
        YuBlocks.registerBlock(BELT_CONVEYOR);
        YuBlocks.registerBlock(HOURAKU_OFF);
        YuBlocks.registerBlockWithOutItemBlock(HOURAKU_ON);
        YuBlocks.registerBlock(FACTORY_GLASS);
        // TileEntity Block
        YuBlocks.registerBlockWithOutItemBlock(YUKKURI_MARISA_BLOCK);
        YuBlocks.registerBlockWithOutItemBlock(YUKKURI_REIMU_BLOCK);
        YuBlocks.registerBlock(YUKKURI_ENGINE);
        YuBlocks.registerBlock(ROLLER);
        // Test Block
        YuBlocks.registerBlock(TEST0);
        YuBlocks.registerBlock(TEST1);
        YuBlocks.registerBlock(TEST2);
    }

    // ブロックとブロックアイテムを登録する。
    private static void registerBlock(Block block)
    {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    // ブロックのみ登録する。
    private static void registerBlockWithOutItemBlock(Block block)
    {
        ForgeRegistries.BLOCKS.register(block);
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
    // ここからモデルの登録

    public static void registerModels()
    {
        // Simple Block
        YuBlocks.registerModel(YUKARI_BLOCK);
        YuBlocks.registerModel(SUGAR_BLOCK);
        YuBlocks.registerModel(BEEF_BLOCK);
        YuBlocks.registerModel(STRANGE_MEAT_BLOCK);
        YuBlocks.registerModel(COOKED_MEAT_BLOCK);
        YuBlocks.registerModel(MUGSHOT_BACKGROUND);
        // Custom Block
        YuBlocks.registerModel(MINCER);
        YuBlocks.registerModel(BELT_CONVEYOR);
        YuBlocks.registerModel(HOURAKU_OFF);
        YuBlocks.registerModel(HOURAKU_ON);
        // Clear Block
        YuBlocks.registerModel(FACTORY_GLASS);
        // TileEntity Block
        // YuBlocks.registerModel(YUKKURI_REIMU_BLOCK);
        YuBlocks.registerModel(YUKKURI_ENGINE);
        YuBlocks.registerModel(ROLLER);
        // Test Block
        YuBlocks.registerModel(TEST0);
        YuBlocks.registerModel(TEST1);
        YuBlocks.registerModel(TEST2);
    }

    private static void registerModel(Block block)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
                new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
    // ここから精錬レシピの登録

    public static void registerSmeltings()
    {
        YuBlocks.registerSmelting(BEEF_BLOCK, new ItemStack(COOKED_MEAT_BLOCK, 1), 0.5F);
        YuBlocks.registerSmelting(STRANGE_MEAT_BLOCK, new ItemStack(COOKED_MEAT_BLOCK, 1), 0.5F);
    }

    /**
     * @param block 精錬対象
     * @param itemStack 精錬結果
     * @param xp 経験値
     */
    private static void registerSmelting(Block block, ItemStack itemStack, float xp)
    {
        GameRegistry.addSmelting(block, itemStack, xp);
    }
}
