package jp.co.yukkuraft.test;

import java.util.List;

import javax.annotation.Nullable;

import jp.co.yukkuraft.block.base.YuBlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは MultiBlock 基底クラスの定義を行います。
 *
 * @author Insanophilia
 *
 */
public abstract class YuMultiBlock extends YuBlockContainer
{
    // 説明文の有無
    public boolean hasTooltip = false;

    public YuMultiBlock(String name, Material material, SoundType soundType, boolean hasTooltip)
    {
        super(name, material, soundType, hasTooltip);
    }

    //    public YuMultiBlock(Material material, String name, boolean hasTooltip, float hardness, float resistance,
    //            SoundType soundType, float lightLevel)
    //    {
    //        super(material);
    //        this.hasTooltip = hasTooltip;
    //
    //        // クリエイティブタブ
    //        this.setCreativeTab(ModCore.YU_CREATIVE_TAB);
    //        // 非翻訳名称
    //        this.setUnlocalizedName(name);
    //        // 登録名称
    //        this.setRegistryName(ModCore.MOD_ID, name);
    //        // 硬度
    //        this.setHardness(hardness);
    //        // 爆発耐性
    //        this.setResistance(resistance);
    //        // 効果音タイプ
    //        this.setSoundType(soundType);
    //        // 明るさ 0.0F～1.0F
    //        this.setLightLevel(lightLevel);
    //        // DefaultState設定
    //        this.setDefaultState(this.blockState.getBaseState());
    //    }

    // 説明文追加の処理
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced)
    {
        if (hasTooltip)
        {
            int meta = stack.getMetadata();
            String name = this.getUnlocalizedName();
            tooltip.add(I18n.format("tooltip." + name, meta));
        }
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    // BlockContainer で INVISIBLE になるのを直しておく
    @Override
    public EnumBlockRenderType getRenderType(IBlockState iBlockState)
    {
        return EnumBlockRenderType.MODEL;
    }

}
