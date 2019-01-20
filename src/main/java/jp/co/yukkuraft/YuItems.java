package jp.co.yukkuraft;

import jp.co.yukkuraft.constant.YuArmorMaterials;
import jp.co.yukkuraft.item.ItemYukkuriEngine;
import jp.co.yukkuraft.item.ItemYukkuriMarisaBlock;
import jp.co.yukkuraft.item.ItemYukkuriReimuBlock;
import jp.co.yukkuraft.item.YuArmor;
import jp.co.yukkuraft.item.YuFood;
import jp.co.yukkuraft.item.YuItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * このクラスは Mod Item の登録を行います。
 *
 * @author Insanophilia
 *
 */
public class YuItems
{
    // Simple Item
    public static final Item PURPLE_DIAMOND    = new YuItem("purple_diamond", false);
    public static final Item LUMP_OF_SUGAR     = new YuItem("lump_of_sugar", false);
    public static final Item YUKKURI_MARISA    = new YuItem("yukkuri_marisa", true);
    public static final Item OKAZARI_MARISA    = new YuItem("okazari_marisa", false);
    public static final Item OKAZARI_REIMU     = new YuItem("okazari_reimu", false);
    public static final Item RING_OF_MOONLIGHT = new YuItem("ring_of_moonlight", false);

    // Food Item
    public static final Item ANKO          = new YuFood(1, 0.1F, false, true, "anko", false);
    public static final Item MATTER_ANKO   = new YuFood(10, 1.0F, false, true, "matter_anko", false);
    public static final Item SHIRATAMA     = new YuFood(1, 0.1F, false, true, "shiratama", false);
    public static final Item STRANGE_MEAT  = new YuFood(3, 0.3F, false, true, "strange_meat", false);
    public static final Item GROUND_MEAT   = new YuFood(3, 0.3F, false, true, "ground_meat", false);
    public static final Item HAMBURG_STEAK = new YuFood(8, 0.8F, false, true, "hamburg_steak", false);
    public static final Item YAKI_MANJUU   = new YuFood(6, 0.6F, false, true, "yaki_manjuu", false);
    // Armor
    public static final Item TEST_HELMET    = new YuArmor(YuArmorMaterials.TEST, 0, "test_helmet", EntityEquipmentSlot.HEAD);
    public static final Item TEST_GAUNTLETS = new YuArmor(YuArmorMaterials.TEST, 0, "test_gauntlets", EntityEquipmentSlot.CHEST);
    public static final Item TEST_LEGGINGS  = new YuArmor(YuArmorMaterials.TEST, 0, "test_leggings", EntityEquipmentSlot.LEGS);
    public static final Item TEST_GREAVES   = new YuArmor(YuArmorMaterials.TEST, 0, "test_greaves", EntityEquipmentSlot.FEET);
    // Custom Item
    public static final Item ITEM_YUKKURI_MARISA_BLOCK = new ItemYukkuriMarisaBlock("item_yukkuri_marisa_block", true);
    public static final Item ITEM_YUKKURI_REIMU_BLOCK  = new ItemYukkuriReimuBlock("item_yukkuri_reimu_block", true);
    public static final Item ITEM_YUKKURI_ENGINE       = new ItemYukkuriEngine("item_yukkuri_engine", false);

    public static void registerItems()
    {
        // Simple Item
        registerItem(PURPLE_DIAMOND);
        registerItem(LUMP_OF_SUGAR);
        registerItem(YUKKURI_MARISA);
        registerItem(OKAZARI_MARISA);
        registerItem(OKAZARI_REIMU);
        registerItem(RING_OF_MOONLIGHT);

        // Food Item
        registerItem(ANKO);
        registerItem(MATTER_ANKO);
        registerItem(SHIRATAMA);
        registerItem(STRANGE_MEAT);
        registerItem(GROUND_MEAT);
        registerItem(HAMBURG_STEAK);
        registerItem(YAKI_MANJUU);
        // Armor
        registerItem(TEST_HELMET);
        registerItem(TEST_GAUNTLETS);
        registerItem(TEST_LEGGINGS);
        registerItem(TEST_GREAVES);
        // Custom Item
        registerItem(ITEM_YUKKURI_MARISA_BLOCK);
        registerItem(ITEM_YUKKURI_REIMU_BLOCK);
        registerItem(ITEM_YUKKURI_ENGINE);

    }

    private static void registerItem(Item item)
    {
        ForgeRegistries.ITEMS.register(item);
    }

    public static void registerModels()
    {
        // Simple Item
        registerModel(PURPLE_DIAMOND);
        registerModel(LUMP_OF_SUGAR);
        registerModel(YUKKURI_MARISA);
        registerModel(OKAZARI_MARISA);
        registerModel(OKAZARI_REIMU);
        registerModel(RING_OF_MOONLIGHT);

        // Food Item
        registerModel(ANKO);
        registerModel(MATTER_ANKO);
        registerModel(SHIRATAMA);
        registerModel(STRANGE_MEAT);
        registerModel(GROUND_MEAT);
        registerModel(HAMBURG_STEAK);
        registerModel(YAKI_MANJUU);
        // Armor
        registerModel(TEST_HELMET);
        registerModel(TEST_GAUNTLETS);
        registerModel(TEST_LEGGINGS);
        registerModel(TEST_GREAVES);
        // Custom Item
        registerModel(ITEM_YUKKURI_MARISA_BLOCK);
        registerModel(ITEM_YUKKURI_REIMU_BLOCK);
        registerModel(ITEM_YUKKURI_ENGINE);

    }

    private static void registerModel(Item item)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    public static void registerSmeltings()
    {
        YuItems.registerSmelting(YUKKURI_MARISA, new ItemStack(YAKI_MANJUU, 1), 0.5F);
        YuItems.registerSmelting(GROUND_MEAT, new ItemStack(HAMBURG_STEAK, 1), 0.5F);
    }

    /**
     * @param item 精錬対象
     * @param itemStack 精錬結果
     * @param xp 経験値
     */
    private static void registerSmelting(Item item, ItemStack itemStack, float xp)
    {
        GameRegistry.addSmelting(item, itemStack, xp);
    }
}
