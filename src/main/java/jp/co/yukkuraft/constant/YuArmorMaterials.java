package jp.co.yukkuraft.constant;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class YuArmorMaterials
{
    public static final ArmorMaterial TEST = EnumHelper.addArmorMaterial("test1", "yukkuraft:test_armor", 33, new int[] {
            3, 6, 8, 3
    }, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
}
