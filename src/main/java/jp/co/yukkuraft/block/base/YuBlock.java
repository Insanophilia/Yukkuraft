package jp.co.yukkuraft.block.base;

import java.util.List;

import javax.annotation.Nullable;

import jp.co.yukkuraft.ModCore;
import net.minecraft.block.Block;
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
 * このクラスは「MODで追加されるブロックの基底クラス」です。
 *
 * @author Insanophilia
 *
 */
public class YuBlock extends Block
{
    /** 説明文の有無 */
    public boolean hasTooltip = false;

    /** シンプルな設定でブロックを作成します。 */
    public YuBlock(String name, Material material, SoundType soundType, boolean hasTooltip)
    {
        super(material);
        this.hasTooltip = hasTooltip;

        // クリエイティブタブ
        this.setCreativeTab(ModCore.YU_CREATIVE_TAB);
        // 非翻訳名称
        this.setUnlocalizedName(name);
        // 登録名称
        this.setRegistryName(ModCore.MOD_ID, name);
        // 硬度
        this.setHardness(0.5F);
        // 爆発耐性
        this.setResistance(2.5F);
        // 効果音タイプ
        this.setSoundType(soundType);
        // 明るさ 0.0F～1.0F
        this.setLightLevel(0F);
        // DefaultState設定
        this.setDefaultState(this.blockState.getBaseState());
    }

    /**
     * このコンストラクタは指定の条件でシンプルなブロックを作成します。
     *
     * @param material 材質
     * @param name 名称
     * @param hasTooltip 説明文の有無
     * @param hardness 硬度
     * @param resistance 爆発耐性
     * @param soundType 効果音タイプ
     * @param lightLevel 明るさ
     */
    public YuBlock(Material material, String name, boolean hasTooltip, float hardness, float resistance,
            SoundType soundType, float lightLevel)
    {
        super(material);
        this.hasTooltip = hasTooltip;

        // クリエイティブタブ
        this.setCreativeTab(ModCore.YU_CREATIVE_TAB);
        // 非翻訳名称
        this.setUnlocalizedName(name);
        // 登録名称
        this.setRegistryName(ModCore.MOD_ID, name);
        // 硬度
        this.setHardness(hardness);
        // 爆発耐性
        this.setResistance(resistance);
        // 効果音タイプ
        this.setSoundType(soundType);
        // 明るさ 0.0F～1.0F
        this.setLightLevel(lightLevel);
        // DefaultState設定
        this.setDefaultState(this.blockState.getBaseState());
    }

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

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
    // ここからブロックの基本設定

    // ブロックが不透明かどうかを返却する。
    @Override
    public boolean isOpaqueCube(IBlockState iBlockState)
    {
        return true;
    }

    // ブロックが1ブロック分の空間を完全に占有できるかどうかを返却する。
    @Override
    public boolean isFullCube(IBlockState iBlockState)
    {
        return true;
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
    // ここからブロック描画の設定

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.SOLID;
    }

    // BlockContainer で INVISIBLE になるのを直しておく
    @Override
    public EnumBlockRenderType getRenderType(IBlockState iBlockState)
    {
        return EnumBlockRenderType.MODEL;
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
}
