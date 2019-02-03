package jp.co.yukkuraft;

import jp.co.yukkuraft.complex.engine.ItemYukkuriEngine;
import jp.co.yukkuraft.complex.flamethrower.ItemFlamethrower;
import jp.co.yukkuraft.constant.YuArmorMaterials;
import jp.co.yukkuraft.item.ItemYukkuriMarisaBlock;
import jp.co.yukkuraft.item.ItemYukkuriReimuBlock;
import jp.co.yukkuraft.item.base.YuArmor;
import jp.co.yukkuraft.item.base.YuFood;
import jp.co.yukkuraft.item.base.YuItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * このクラスは「MODで追加されるアイテム」の登録を行います。
 *
 * @author Insanophilia
 *
 */
public class YuItems
{
    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
    // ここからアイテムのインスタンス

    // Simple Item
    public static final Item PURPLE_DIAMOND;
    public static final Item LUMP_OF_SUGAR;
    public static final Item YUKKURI_MARISA;
    public static final Item OKAZARI_MARISA;
    public static final Item OKAZARI_REIMU;
    public static final Item RING_OF_MOONLIGHT;

    // Custom Item
    public static final Item ITEM_YUKKURI_MARISA_BLOCK;
    public static final Item ITEM_YUKKURI_REIMU_BLOCK;
    public static final Item ITEM_YUKKURI_ENGINE;
    public static final Item ITEM_FLAMETHROWER;

    // Food Item
    public static final Item ANKO;
    public static final Item MATTER_ANKO;
    public static final Item SHIRATAMA;
    public static final Item STRANGE_MEAT;
    public static final Item GROUND_MEAT;
    public static final Item HAMBURG_STEAK;
    public static final Item YAKI_MANJUU;

    // Armor Item
    public static final Item TEST_HELMET;
    public static final Item TEST_GAUNTLETS;
    public static final Item TEST_LEGGINGS;
    public static final Item TEST_GREAVES;

    static
    {
        // Simple Item
        PURPLE_DIAMOND = new YuItem("purple_diamond");
        LUMP_OF_SUGAR = new YuItem("lump_of_sugar");
        YUKKURI_MARISA = new YuItem("yukkuri_marisa").setTooltipVisible();
        OKAZARI_MARISA = new YuItem("okazari_marisa");
        OKAZARI_REIMU = new YuItem("okazari_reimu");
        RING_OF_MOONLIGHT = new YuItem("ring_of_moonlight").setMaxStackSize(1);
        // Custom Item
        ITEM_YUKKURI_MARISA_BLOCK = new ItemYukkuriMarisaBlock("item_yukkuri_marisa_block").setTooltipVisible();
        ITEM_YUKKURI_REIMU_BLOCK = new ItemYukkuriReimuBlock("item_yukkuri_reimu_block").setTooltipVisible();
        ITEM_YUKKURI_ENGINE = new ItemYukkuriEngine("item_yukkuri_engine");
        ITEM_FLAMETHROWER = new ItemFlamethrower("flamethrower")
                .setMaxStackSize(1).setMaxDamage(256);

        // Food Item
        ANKO = new YuFood("anko", 1, 0.1F, false).setAlwaysEdible();
        MATTER_ANKO = new YuFood("matter_anko", 10, 1.0F, false).setAlwaysEdible();
        SHIRATAMA = new YuFood("shiratama", 1, 0.1F, false).setAlwaysEdible();
        YAKI_MANJUU = new YuFood("yaki_manjuu", 6, 0.6F, false).setAlwaysEdible();
        STRANGE_MEAT = new YuFood("strange_meat", 3, 0.3F, false);
        GROUND_MEAT = new YuFood("ground_meat", 3, 0.3F, false);
        HAMBURG_STEAK = new YuFood("hamburg_steak", 8, 0.8F, false);
        // Armor Item
        TEST_HELMET = new YuArmor("test_helmet", YuArmorMaterials.TEST, 0, EntityEquipmentSlot.HEAD);
        TEST_GAUNTLETS = new YuArmor("test_gauntlets", YuArmorMaterials.TEST, 0, EntityEquipmentSlot.CHEST);
        TEST_LEGGINGS = new YuArmor("test_leggings", YuArmorMaterials.TEST, 0, EntityEquipmentSlot.LEGS);
        TEST_GREAVES = new YuArmor("test_greaves", YuArmorMaterials.TEST, 0, EntityEquipmentSlot.FEET);
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
    // ここからアイテムの登録

    public static void registerItems()
    {
        // Simple Item
        registerItem(PURPLE_DIAMOND);
        registerItem(LUMP_OF_SUGAR);
        registerItem(YUKKURI_MARISA);
        registerItem(OKAZARI_MARISA);
        registerItem(OKAZARI_REIMU);
        registerItem(RING_OF_MOONLIGHT);

        // Custom Item
        registerItem(ITEM_YUKKURI_MARISA_BLOCK);
        registerItem(ITEM_YUKKURI_REIMU_BLOCK);
        registerItem(ITEM_YUKKURI_ENGINE);
        registerItem(ITEM_FLAMETHROWER);

        // Food Item
        registerItem(ANKO);
        registerItem(MATTER_ANKO);
        registerItem(SHIRATAMA);
        registerItem(YAKI_MANJUU);
        registerItem(STRANGE_MEAT);
        registerItem(GROUND_MEAT);
        registerItem(HAMBURG_STEAK);

        // Armor Item
        registerItem(TEST_HELMET);
        registerItem(TEST_GAUNTLETS);
        registerItem(TEST_LEGGINGS);
        registerItem(TEST_GREAVES);

    }

    private static void registerItem(Item item)
    {
        ForgeRegistries.ITEMS.register(item);
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
    // ここからモデルの登録

    public static void registerModels()
    {
        // Simple Item
        registerModel(PURPLE_DIAMOND);
        registerModel(LUMP_OF_SUGAR);
        registerModel(YUKKURI_MARISA);
        registerModel(OKAZARI_MARISA);
        registerModel(OKAZARI_REIMU);
        registerModel(RING_OF_MOONLIGHT);

        // Custom Item
        registerModel(ITEM_YUKKURI_MARISA_BLOCK);
        registerModel(ITEM_YUKKURI_REIMU_BLOCK);
        registerModel(ITEM_YUKKURI_ENGINE);
        registerModel(ITEM_FLAMETHROWER);

        // Food Item
        registerModel(ANKO);
        registerModel(MATTER_ANKO);
        registerModel(SHIRATAMA);
        registerModel(YAKI_MANJUU);
        registerModel(STRANGE_MEAT);
        registerModel(GROUND_MEAT);
        registerModel(HAMBURG_STEAK);

        // Armor Item
        registerModel(TEST_HELMET);
        registerModel(TEST_GAUNTLETS);
        registerModel(TEST_LEGGINGS);
        registerModel(TEST_GREAVES);

    }

    private static void registerModel(Item item)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
    // ここから精錬レシピの登録

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
