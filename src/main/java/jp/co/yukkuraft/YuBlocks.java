package jp.co.yukkuraft;

import jp.co.yukkuraft.block.BeltConveyor;
import jp.co.yukkuraft.block.BlockHouraku;
import jp.co.yukkuraft.block.FactoryGlass;
import jp.co.yukkuraft.block.Mincer;
import jp.co.yukkuraft.block.Test0;
import jp.co.yukkuraft.block.YuBlock;
import jp.co.yukkuraft.tileentity.BlockYukkuriMarisa;
import jp.co.yukkuraft.tileentity.BlockYukkuriReimu;
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
 * このクラスは Mod Block の登録を行います。
 *
 * @author Insanophilia
 *
 */
public class YuBlocks
{
    // Simple Block
    public static final Block YUKARI_BLOCK       = new YuBlock(Material.IRON, "yukari_block", true, 5.0F, 6000000.0F, SoundType.METAL, 1.0F);
    public static final Block SUGAR_BLOCK        = new YuBlock(Material.SAND, "sugar_block", false, 0.5F, 2.5F, SoundType.SAND, 0.0F);
    public static final Block BEEF_BLOCK         = new YuBlock(Material.CLAY, "beef_block", false, 0.5F, 2.5F, SoundType.SLIME, 0.0F);
    public static final Block STRANGE_MEAT_BLOCK = new YuBlock(Material.CLAY, "strange_meat_block", false, 0.5F, 2.5F, SoundType.SLIME, 0.0F);
    public static final Block COOKED_MEAT_BLOCK  = new YuBlock(Material.CLAY, "cooked_meat_block", false, 0.5F, 2.5F, SoundType.PLANT, 0.0F);
    public static final Block MUGSHOT_BACKGROUND = new YuBlock(Material.IRON, "mugshot_background", false, 5.0F, 10.0F, SoundType.METAL, 0.0F);
    // Custom Block
    public static final Block MINCER        = new Mincer(Material.IRON, "mincer", false, 5.0F, 10.0F, SoundType.METAL, 0.0F);
    public static final Block BELT_CONVEYOR = new BeltConveyor(Material.IRON, "belt_conveyor", false, 5.0F, 10.0F, SoundType.METAL, 0.0F);
    public static final Block HOURAKU_OFF   = new BlockHouraku(Material.IRON, "block_houraku_off", false, 5.0F, 10.0F, SoundType.METAL, 0.0F, false);
    public static final Block HOURAKU_ON    = new BlockHouraku(Material.IRON, "block_houraku_on", false, 5.0F, 10.0F, SoundType.METAL, 1.0F, true);

    // Clear Block
    public static final Block FACTORY_GLASS = new FactoryGlass(Material.GLASS, "factory_glass", false, 5.0F, 10.0F, SoundType.GLASS, 0.0F);
    // Test
    public static final Block TEST0 = new Test0(Material.IRON, "test0", false, 5.0F, 6000000.0F, SoundType.METAL, 1.0F);
    // TileEntity Block
    public static final Block YUKKURI_MARISA_BLOCK = new BlockYukkuriMarisa(Material.CLAY, "yukkuri_marisa_block", false, 0.5F, 2.5F, SoundType.SLIME, 0.0F);
    public static final Block YUKKURI_REIMU_BLOCK  = new BlockYukkuriReimu(Material.CLAY, "yukkuri_reimu_block", false, 0.5F, 2.5F, SoundType.SLIME, 0.0F);

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

        // Clear Block
        YuBlocks.registerBlock(FACTORY_GLASS);
        // Test
        YuBlocks.registerBlock(TEST0);
        // TileEntity Block
        YuBlocks.registerBlockWithOutItemBlock(YUKKURI_MARISA_BLOCK);
        YuBlocks.registerBlockWithOutItemBlock(YUKKURI_REIMU_BLOCK);
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
        // Test
        YuBlocks.registerModel(TEST0);
        // TileEntity Block
        // YuBlocks.registerModel(YUKKURI_REIMU_BLOCK);
    }

    private static void registerModel(Block block)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

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
