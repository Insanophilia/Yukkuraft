package jp.co.yukkuraft.item.base;

import java.util.List;

import javax.annotation.Nullable;

import jp.co.yukkuraft.ModCore;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは「MODで追加される防具アイテムの基底クラス」です。
 *
 * @author Insanophilia
 *
 */
public class YuArmor extends ItemArmor
{
    // 説明文の有無
    public boolean hasTooltip = false;

    public YuArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
    {
        super(materialIn, renderIndexIn, equipmentSlotIn);

        // クリエイティブタブ
        this.setCreativeTab(ModCore.YU_CREATIVE_TAB);
        // 非翻訳名称
        this.setUnlocalizedName(name);
        // 登録名称
        this.setRegistryName(ModCore.MOD_ID, name);
    }

    // 説明文を表示する。
    public void setTooltipVisible()
    {
        hasTooltip = true;
    }

    // 説明文追加の処理
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (hasTooltip)
        {
            int meta = stack.getMetadata();
            String name = this.getUnlocalizedName();
            tooltip.add(I18n.format("tooltip." + name, meta));
        }
    }
}
