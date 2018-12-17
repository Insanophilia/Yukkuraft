package jp.co.yukkuraft.item;

import java.util.List;

import javax.annotation.Nullable;

import jp.co.yukkuraft.ModCore;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは Mod Item 基底クラスの定義を行います。
 *
 * @author Insanophilia
 *
 */
public class YuItem extends Item
{
    // 説明文の有無
    public boolean hasTooltip = false;

    /**
     * このコンストラクタは指定の条件でシンプルなアイテムを作成します。
     *
     * @param name 名称
     * @param hasTooltip Tooltip の有無
     */
    public YuItem(String name, boolean hasTooltip)
    {
        super();
        this.hasTooltip = hasTooltip;

        // クリエイティブタブ
        this.setCreativeTab(ModCore.YU_CREATIVE_TAB);
        // 非翻訳名称
        this.setUnlocalizedName(name);
        // 登録名称
        this.setRegistryName(ModCore.MOD_ID, name);
        // 最大スタック数
        this.setMaxStackSize(64);
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
