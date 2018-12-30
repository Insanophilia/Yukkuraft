package jp.co.yukkuraft.item;

import jp.co.yukkuraft.ModCore;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

/**
 * このクラスは Mod Armor 基底クラスの定義を行います。
 *
 * @author Insanophilia
 *
 */
public class YuArmor extends ItemArmor
{
    public YuArmor(ArmorMaterial materialIn, int renderIndexIn, String name, EntityEquipmentSlot equipmentSlotIn)
    {
        super(materialIn, renderIndexIn, equipmentSlotIn);

        // クリエイティブタブ
        this.setCreativeTab(ModCore.YU_CREATIVE_TAB);
        // 非翻訳名称
        this.setUnlocalizedName(name);
        // 登録名称
        this.setRegistryName(ModCore.MOD_ID, name);
    }
}
