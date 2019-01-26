package jp.co.yukkuraft.block;

import java.util.List;

import javax.annotation.Nullable;

import jp.co.yukkuraft.block.base.YuFacingBlock;
import jp.co.yukkuraft.constant.YuAABB;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * このクラスは「ベルトコンベアブロック」の定義を行います。
 *
 * @author Insanophilia
 *
 */
public class BlockBeltConveyor extends YuFacingBlock
{
    public BlockBeltConveyor(String name, Material material, SoundType soundType, boolean hasTooltip)
    {
        super(name, material, soundType, hasTooltip);
    }

    // 衝突判定の定義
    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
            List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn,
            boolean p_185477_7_)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, YuAABB.WALL_DOWN);
    }

    // マウス選択の当たり判定の定義
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return YuAABB.WALL_DOWN;
    }

    // Entity 接触時の処理
    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        EnumFacing facing = (EnumFacing) state.getValue(FACING);
        // S-W-N-E (0-3)
        int facingbits = facing.getHorizontalIndex();

        // コンベアの接地領域に存在する Entity を取得
        AxisAlignedBB aabb = YuAABB.ON_FLOOR.offset(pos);
        List<? extends Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity) null, aabb);
        if (!list.isEmpty())
        {
            for (Entity entity : list)
            {
                if (entity == entityIn)
                {
                    switch (facingbits)
                    {
                        case 0:
                            entity.motionZ += 0.1D;
                            break;
                        case 1:
                            entity.motionX -= 0.1D;
                            break;
                        case 2:
                            entity.motionZ -= 0.1D;
                            break;
                        case 3:
                            entity.motionX += 0.1D;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
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
}
