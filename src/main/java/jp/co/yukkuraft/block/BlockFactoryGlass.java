package jp.co.yukkuraft.block;

import jp.co.yukkuraft.YuBlocks;
import jp.co.yukkuraft.block.base.YuBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは「加工所のガラスブロック」の定義を行います。
 *
 * @author Insanophilia
 *
 */
public class BlockFactoryGlass extends YuBlock
{
    public BlockFactoryGlass(String name, Material material, SoundType soundType)
    {
        super(name, material, soundType);
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
    // ここからブロックの基本設定

    // ブロックが不透明かどうかを返却する。
    @Override
    public boolean isOpaqueCube(IBlockState iBlockState)
    {
        return false;
    }

    // ブロックが1ブロック分の空間を完全に占有できるかどうかを返却する。
    @Override
    public boolean isFullCube(IBlockState iBlockState)
    {
        return false;
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
    // ここからブロック描画の設定

    // 半透明ブロックの設定
    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    // 透明ブロックの描画省略判定の処理
    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();
        if (this == YuBlocks.FACTORY_GLASS)
        {
            if (blockState != iblockstate)
            {
                return true;
            }

            if (block == this)
            {
                return false;
            }
        }
        return false;
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
}
