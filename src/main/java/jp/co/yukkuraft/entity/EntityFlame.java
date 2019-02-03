package jp.co.yukkuraft.entity;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * このクラスは「火炎放射器の弾」を定義します。
 *
 * @author Insanophilia
 *
 */
public class EntityFlame extends EntityArrow
{

    public EntityFlame(World worldIn)
    {
        super(worldIn);
    }

    public EntityFlame(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public EntityFlame(World worldIn, EntityLivingBase shooter)
    {
        super(worldIn, shooter);
    }

    @Override
    public void onUpdate()
    {
        // 雨でも火が消えない様に
        this.setFire(100);
        super.onUpdate();
        if (this.world.isRemote && !this.inGround)
        {
            this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D,
                    new int[0]);
        }
    }

    // ヒット時の処理
    @Override
    protected void onHit(RayTraceResult raytraceResultIn)
    {
        // 基本的には普通の矢と同様
        super.onHit(raytraceResultIn);
        // 着弾音
        this.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

        Entity entity = raytraceResultIn.entityHit;
        if (entity == null)
        {
            // ブロック衝突時
            BlockPos blockpos = raytraceResultIn.getBlockPos();
            IBlockState iblockstate = this.world.getBlockState(blockpos);
            if (iblockstate.getMaterial() != Material.AIR)
            {
                // ブロックの衝突面の座標
                BlockPos targetPos;
                EnumFacing enumFacing = raytraceResultIn.sideHit;
                switch (enumFacing)
                {
                    default:
                    case UP:
                        targetPos = blockpos.up();
                        break;
                    case DOWN:
                        targetPos = blockpos.down();
                        break;
                    case NORTH:
                        targetPos = blockpos.north();
                        break;
                    case SOUTH:
                        targetPos = blockpos.south();
                        break;
                    case EAST:
                        targetPos = blockpos.east();
                        break;
                    case WEST:
                        targetPos = blockpos.west();
                        break;
                }
                IBlockState targetstate = this.world.getBlockState(targetPos);
                if (targetstate.getMaterial() == Material.AIR && this.isBurning())
                {
                    this.world.setBlockState(targetPos, Blocks.FIRE.getDefaultState());
                }
            }
        }
    }

    @Override
    protected ItemStack getArrowStack()
    {
        // 矢の回収時は回収できない。
        return Items.AIR.getDefaultInstance();
    }
}