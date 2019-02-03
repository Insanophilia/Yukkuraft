package jp.co.yukkuraft.base.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * このクラスは「向きを持つブロックの基底クラス」です。
 *
 * @author Insanophilia
 *
 */
public class YuFacingBlock extends YuBlock
{
    /** 向き */
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public YuFacingBlock(String name, Material material, SoundType soundType)
    {
        super(name, material, soundType);
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
    // ここからメタデータの設定

    // メタデータ -> プロパティ
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing facing = EnumFacing.getHorizontal(meta);
        return this.getDefaultState().withProperty(FACING, facing);
    }

    // プロパティ -> メタデータ
    @Override
    public int getMetaFromState(IBlockState state)
    {
        EnumFacing facing = (EnumFacing) state.getValue(FACING);
        int metaData = facing.getHorizontalIndex();
        return metaData;
    }

    // blockstates 定義
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {
                FACING
        });
    }

    // ブロック設置時にプロパティを設定する。
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX,
            float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
}
