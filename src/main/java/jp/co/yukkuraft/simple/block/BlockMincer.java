package jp.co.yukkuraft.simple.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import jp.co.yukkuraft.base.block.YuBlock;
import jp.co.yukkuraft.constant.YuAABB;
import jp.co.yukkuraft.constant.YuDamageSouce;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは「挽き肉製造機ブロック」の定義を行います。
 *
 * @author Insanophilia
 *
 */
public class BlockMincer extends YuBlock
{
    public BlockMincer(String name, Material material, SoundType soundType)
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

    // 衝突判定の定義
    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
            List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn,
            boolean p_185477_7_)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, YuAABB.WALL_NORTH);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, YuAABB.WALL_SOUTH);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, YuAABB.WALL_EAST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, YuAABB.WALL_WEST);
    }

    // マウス選択の当たり判定の定義
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return YuAABB.FULL_BLOCK;
    }

    // Entity 接触時の処理
    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (entityIn instanceof EntityItem)
        {
            // アイテムの場合は何もしない。
            return;
        }
        // 刃の部分の当たり判定内の Entity を取得する。
        AxisAlignedBB mincerBladeAABB = YuAABB.WALL_DOWN.offset(pos);
        List<? extends Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity) null, mincerBladeAABB);
        if (!list.isEmpty())
        {
            for (Entity entity : list)
            {
                if (entity == entityIn)
                {
                    entity.attackEntityFrom(YuDamageSouce.MINCER, 1.0F);
                    return;
                }
            }
        }
    }

    // ランダムに実行される描画処理
    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double r0 = rand.nextDouble();
        if (r0 < 0.25D)
        {
            // ブロック下部に溶岩の水滴を表示する。
            double r1 = rand.nextDouble() * (12.0D / 16.0D) + (2.0D / 16.0D);
            double r2 = rand.nextDouble() * (12.0D / 16.0D) + (2.0D / 16.0D);
            double d0 = (double) pos.getX() + r1;
            double d1 = (double) pos.getY() - (1.0D / 16.D);
            double d2 = (double) pos.getZ() + r2;
            worldIn.spawnParticle(EnumParticleTypes.DRIP_LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
}
