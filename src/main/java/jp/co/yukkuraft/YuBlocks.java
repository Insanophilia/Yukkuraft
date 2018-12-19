package jp.co.yukkuraft;

import jp.co.yukkuraft.block.FactoryGlass;
import jp.co.yukkuraft.block.Mincer;
import jp.co.yukkuraft.block.YuBlock;
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
    public static final Block STRANGE_MEAT_BLOCK = new YuBlock(Material.CLAY, "strange_meat_block", false, 0.5F, 2.5F, SoundType.SLIME, 0.0F);
    public static final Block COOKED_MEAT_BLOCK  = new YuBlock(Material.CLAY, "cooked_meat_block", false, 0.5F, 2.5F, SoundType.PLANT, 0.0F);
    public static final Block MUGSHOT_BACKGROUND = new YuBlock(Material.IRON, "mugshot_background", false, 5.0F, 10.0F, SoundType.METAL, 0.0F);
    public static final Block MINCER             = new Mincer(Material.IRON, "mincer", false, 5.0F, 10.0F, SoundType.METAL, 0.0F);
    // Clear Block
    public static final Block FACTORY_GLASS = new FactoryGlass(Material.GLASS, "factory_glass", false, 5.0F, 10.0F, SoundType.GLASS, 0.0F);

    public static void registerBlocks()
    {
        // Simple Block
        YuBlocks.registerBlock(YUKARI_BLOCK);
        YuBlocks.registerBlock(SUGAR_BLOCK);
        YuBlocks.registerBlock(STRANGE_MEAT_BLOCK);
        YuBlocks.registerBlock(COOKED_MEAT_BLOCK);
        YuBlocks.registerBlock(MUGSHOT_BACKGROUND);
        YuBlocks.registerBlock(MINCER);
        // Clear Block
        YuBlocks.registerBlock(FACTORY_GLASS);
    }

    private static void registerBlock(Block block)
    {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    public static void registerModels()
    {
        // Simple Block
        YuBlocks.registerModel(YUKARI_BLOCK);
        YuBlocks.registerModel(SUGAR_BLOCK);
        YuBlocks.registerModel(STRANGE_MEAT_BLOCK);
        YuBlocks.registerModel(COOKED_MEAT_BLOCK);
        YuBlocks.registerModel(MUGSHOT_BACKGROUND);
        YuBlocks.registerModel(MINCER);
        // Clear Block
        YuBlocks.registerModel(FACTORY_GLASS);
    }

    private static void registerModel(Block block)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

    public static void registerSmeltings()
    {
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