package jp.co.yukkuraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * このクラスは Mod CreativeTab の定義を行います。
 *
 * @author Insanophilia
 *
 */
public class YuCreativeTab extends CreativeTabs
{
    public YuCreativeTab(String label)
    {
        super(label);
    }

    // クリエイティブタブのアイコンの設定
    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(YuItems.YUKKURI_MARISA);
    }
}
