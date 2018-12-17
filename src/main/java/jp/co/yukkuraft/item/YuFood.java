package jp.co.yukkuraft.item;

import java.util.List;

import javax.annotation.Nullable;

import jp.co.yukkuraft.ModCore;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは Mod ItemFood 基底クラスの定義を行います。
 *
 * @author Insanophilia
 *
 */
public class YuFood extends ItemFood
{
    // 説明文の有無
    public boolean hasTooltip = false;

    /**
     * このコンストラクタは指定の条件でシンプルな食べ物を作成します。
     *
     * @param amount 満腹度の回復量
     * @param saturation 腹持ち値
     * @param isWolfFood 狼が食べるか
     * @param isAlwaysEdible 満腹時でも使用できるか
     * @param name 名称
     * @param hasTooltip 説明文の有無
     */
    public YuFood(int amount, float saturation, boolean isWolfFood, boolean isAlwaysEdible, String name,
            boolean hasTooltip)
    {
        super(amount, saturation, isWolfFood);
        this.hasTooltip = hasTooltip;

        // クリエイティブタブ
        this.setCreativeTab(ModCore.YU_CREATIVE_TAB);
        // 非翻訳名称
        this.setUnlocalizedName(name);
        // 登録名称
        this.setRegistryName(ModCore.MOD_ID, name);
        // 満腹時でも使用できるか
        if (isAlwaysEdible)
        {
            this.setAlwaysEdible();
        }
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
