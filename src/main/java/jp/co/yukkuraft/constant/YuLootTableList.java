package jp.co.yukkuraft.constant;

import jp.co.yukkuraft.ModCore;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class YuLootTableList
{
    public static final ResourceLocation YUKKURI_MARISA = new ResourceLocation(ModCore.MOD_ID,
            "entities/yukkuri_marisa");
    public static final ResourceLocation YUKKURI_REIMU  = new ResourceLocation(ModCore.MOD_ID,
            "entities/yukkuri_reimu");

    public static void registerLootTableList()
    {
        LootTableList.register(YUKKURI_MARISA);
        LootTableList.register(YUKKURI_REIMU);
    }
}
