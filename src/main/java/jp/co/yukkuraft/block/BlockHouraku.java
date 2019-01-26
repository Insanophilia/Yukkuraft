package jp.co.yukkuraft.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import jp.co.yukkuraft.YuBlocks;
import jp.co.yukkuraft.block.base.YuBlock;
import jp.co.yukkuraft.constant.YuAABB;
import jp.co.yukkuraft.constant.YuDamageSouce;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは「炮烙ブロック」の定義を行います。
 *
 * @author Insanophilia
 *
 */
public class BlockHouraku extends YuBlock
{
    /** 有効／無効 */
    public final boolean                isOn;
    /** 熱レベル */
    public static final PropertyInteger HEAT = PropertyInteger.create("heat", 0, 15);

    public BlockHouraku(String name, Material material, SoundType soundType, boolean hasTooltip, boolean isOn)
    {
        super(name, material, soundType, hasTooltip);
        this.isOn = isOn;
        this.setTickRandomly(true);

        if (isOn)
        {
            // 熱レベル
            setDefaultState(this.blockState.getBaseState().withProperty(HEAT, Integer.valueOf(15)));
        } else
        {
            // 熱レベル
            setDefaultState(this.blockState.getBaseState().withProperty(HEAT, Integer.valueOf(0)));
        }
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*
    // ここからメタデータの設定

    // メタデータ -> プロパティ
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(HEAT, Integer.valueOf(meta));
    }

    // プロパティ -> メタデータ
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int metaData = ((Integer) state.getValue(HEAT)).intValue();
        return metaData;
    }

    // blockstates 定義
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {
                HEAT
        });
    }

    // ---------*---------*---------*---------*---------*---------*---------*---------*---------*---------*

    // 更新間隔
    @Override
    public int tickRate(World worldIn)
    {
        return 30;
    }

    // ブロック追加時の処理
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        // 更新処理のスケジューリング
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    // 更新処理
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        // 熱レベル
        int heatLevel = state.getValue(HEAT).intValue();
        // 熱レベル目標値
        int targetHeatLevel = this.getTargetHeatLevel(worldIn, pos);
        targetHeatLevel = (targetHeatLevel < 0) ? 0 : targetHeatLevel;
        // 熱レベル変更
        if (heatLevel < targetHeatLevel)
        {
            heatLevel++;
        } else if (heatLevel > targetHeatLevel)
        {
            heatLevel--;
        }

        if (heatLevel >= 4)
        {
            worldIn.setBlockState(pos, YuBlocks.HOURAKU_ON.getDefaultState().withProperty(HEAT, heatLevel));
        } else
        {
            worldIn.setBlockState(pos, YuBlocks.HOURAKU_OFF.getDefaultState().withProperty(HEAT, heatLevel));
        }
        // 更新処理のスケジューリング
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    // 熱レベル目標値を取得する。
    private int getTargetHeatLevel(World worldIn, BlockPos pos)
    {
        if (worldIn.getBlockState(pos.down()).getMaterial() == Material.FIRE)
        {
            // 下から火ブロックで加熱した場合
            return 15;
        }

        // 炮烙ブロックと接触している場合
        Block up = worldIn.getBlockState(pos.up()).getBlock();
        Block down = worldIn.getBlockState(pos.down()).getBlock();
        Block north = worldIn.getBlockState(pos.north()).getBlock();
        Block south = worldIn.getBlockState(pos.south()).getBlock();
        Block east = worldIn.getBlockState(pos.east()).getBlock();
        Block west = worldIn.getBlockState(pos.west()).getBlock();

        // 周辺ブロックの最大熱レベル
        int aroundMaxHeatLevel = 0;

        if (up == YuBlocks.HOURAKU_ON || up == YuBlocks.HOURAKU_OFF)
        {
            int tmp = worldIn.getBlockState(pos.up()).getValue(HEAT).intValue();
            aroundMaxHeatLevel = (aroundMaxHeatLevel < tmp) ? tmp : aroundMaxHeatLevel;
        }
        if (down == YuBlocks.HOURAKU_ON || down == YuBlocks.HOURAKU_OFF)
        {
            int tmp = worldIn.getBlockState(pos.down()).getValue(HEAT).intValue();
            aroundMaxHeatLevel = (aroundMaxHeatLevel < tmp) ? tmp : aroundMaxHeatLevel;
        }
        if (north == YuBlocks.HOURAKU_ON || north == YuBlocks.HOURAKU_OFF)
        {
            int tmp = worldIn.getBlockState(pos.north()).getValue(HEAT).intValue();
            aroundMaxHeatLevel = (aroundMaxHeatLevel < tmp) ? tmp : aroundMaxHeatLevel;
        }
        if (south == YuBlocks.HOURAKU_ON || south == YuBlocks.HOURAKU_OFF)
        {
            int tmp = worldIn.getBlockState(pos.south()).getValue(HEAT).intValue();
            aroundMaxHeatLevel = (aroundMaxHeatLevel < tmp) ? tmp : aroundMaxHeatLevel;
        }
        if (east == YuBlocks.HOURAKU_ON || east == YuBlocks.HOURAKU_OFF)
        {
            int tmp = worldIn.getBlockState(pos.east()).getValue(HEAT).intValue();
            aroundMaxHeatLevel = (aroundMaxHeatLevel < tmp) ? tmp : aroundMaxHeatLevel;
        }
        if (west == YuBlocks.HOURAKU_ON || west == YuBlocks.HOURAKU_OFF)
        {
            int tmp = worldIn.getBlockState(pos.west()).getValue(HEAT).intValue();
            aroundMaxHeatLevel = (aroundMaxHeatLevel < tmp) ? tmp : aroundMaxHeatLevel;
        }
        // 熱抵抗
        int RESISTANCE = 1;
        return aroundMaxHeatLevel - RESISTANCE;
    }

    // 発光レベルに熱レベルを設定する。
    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        int heatLevel = state.getValue(HEAT).intValue();
        return heatLevel;
    }

    // 衝突判定の定義
    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
            List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn,
            boolean isActualState)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, YuAABB.GAP_BLOCK);
    }

    // Entity 接触時の処理
    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (entityIn instanceof EntityItem)
        {
            // アイテム接触時は何もしない
            return;
        }
        int heatLevel = ((Integer) state.getValue(HEAT)).intValue();
        if (heatLevel < 4)
        {
            // no damage
        } else if (heatLevel < 8)
        {
            entityIn.setFire(15);
            entityIn.attackEntityFrom(YuDamageSouce.HOURAKU, 1.0F);
        } else if (heatLevel < 12)
        {
            entityIn.setFire(15);
            entityIn.attackEntityFrom(YuDamageSouce.HOURAKU, 2.0F);
        } else
        {
            entityIn.setFire(15);
            entityIn.attackEntityFrom(YuDamageSouce.HOURAKU, 3.0F);
        }
    }

    // ランダムに実行される描画処理
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (isOn)
        {
            double x = (double) pos.getX() + rand.nextDouble();
            double y = (double) pos.getY() + rand.nextDouble();
            double z = (double) pos.getZ() + rand.nextDouble();
            if (rand.nextDouble() < 0.1D)
            {
                worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D,
                        SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F,
                        1.0F, false);
            }
            // 煙の描画
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }
}
