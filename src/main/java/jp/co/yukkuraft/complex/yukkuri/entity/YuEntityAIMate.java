package jp.co.yukkuraft.complex.yukkuri.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class YuEntityAIMate extends EntityAIBase
{
    private final EntityAnimal                  animal;
    private final Class<? extends EntityAnimal> mateClass;
    World                                       world;
    private EntityAnimal                        targetMate;
    // 子ゆっくりの再生産までのクール時間
    int spawnBabyDelay;
    // 交尾中の速度？
    double moveSpeed;

    public YuEntityAIMate(EntityAnimal animal, double speedIn)
    {
        this(animal, speedIn, animal.getClass());
    }

    public YuEntityAIMate(EntityAnimal p_i47306_1_, double p_i47306_2_, Class<? extends EntityAnimal> p_i47306_4_)
    {
        this.animal = p_i47306_1_;
        this.world = p_i47306_1_.world;
        this.mateClass = p_i47306_4_;
        this.moveSpeed = p_i47306_2_;
        this.setMutexBits(3);
    }

    // AI Task の実行判定
    public boolean shouldExecute()
    {
        if (!this.animal.isInLove())
        {
            return false;
        } else
        {
            this.targetMate = this.getNearbyMate();
            return this.targetMate != null;
        }
    }

    // AI Task の実行継続判定
    public boolean shouldContinueExecuting()
    {
        return this.targetMate.isEntityAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60;
    }

    // AI Task の初期化処理
    public void resetTask()
    {
        this.targetMate = null;
        this.spawnBabyDelay = 0;
    }

    // AI Task の更新処理
    public void updateTask()
    {
        this.animal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float) this.animal.getVerticalFaceSpeed());
        this.animal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
        ++this.spawnBabyDelay;

        if (this.spawnBabyDelay >= 60 && this.animal.getDistanceSq(this.targetMate) < 9.0D)
        {
            this.spawnBaby();
        }
    }

    // 近場の番を取得する。
    private EntityAnimal getNearbyMate()
    {
        List<EntityAnimal> list = this.world.<EntityAnimal> getEntitiesWithinAABB(EntityYukkuri.class, this.animal.getEntityBoundingBox().grow(8.0D));
        System.out.println("entityList:" + list.size());
        double d0 = Double.MAX_VALUE;
        EntityAnimal entityanimal = null;

        for (EntityAnimal entityanimal1 : list)
        {
            if (this.animal.canMateWith(entityanimal1) && this.animal.getDistanceSq(entityanimal1) < d0)
            {
                entityanimal = entityanimal1;
                d0 = this.animal.getDistanceSq(entityanimal1);
            }
        }

        return entityanimal;
    }

    // 子ゆっくりは両親の種類を1匹ずつ発生させる。
    private void spawnBaby()
    {
        EntityAgeable entityageable = this.animal.createChild(this.targetMate);
        EntityAgeable entityageable2 = this.targetMate.createChild(this.animal);

        final net.minecraftforge.event.entity.living.BabyEntitySpawnEvent event = new net.minecraftforge.event.entity.living.BabyEntitySpawnEvent(animal, targetMate,
                entityageable);
        final net.minecraftforge.event.entity.living.BabyEntitySpawnEvent event2 = new net.minecraftforge.event.entity.living.BabyEntitySpawnEvent(animal, targetMate,
                entityageable2);

        final boolean cancelled = net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
        final boolean cancelled2 = net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event2);

        entityageable = event.getChild();
        entityageable2 = event2.getChild();

        if (cancelled || cancelled2)
        {
            // イベントがキャンセルされた場合
            this.animal.setGrowingAge(6000);
            this.targetMate.setGrowingAge(6000);
            this.animal.resetInLove();
            this.targetMate.resetInLove();
            return;
        }

        if (entityageable != null && entityageable2 != null)
        {
            EntityPlayerMP entityplayermp = this.animal.getLoveCause();

            if (entityplayermp == null && this.targetMate.getLoveCause() != null)
            {
                entityplayermp = this.targetMate.getLoveCause();
            }

            if (entityplayermp != null)
            {
                entityplayermp.addStat(StatList.ANIMALS_BRED);
                CriteriaTriggers.BRED_ANIMALS.trigger(entityplayermp, this.animal, this.targetMate, entityageable);
                CriteriaTriggers.BRED_ANIMALS.trigger(entityplayermp, this.targetMate, this.animal, entityageable2);
            }

            this.animal.setGrowingAge(6000);
            this.targetMate.setGrowingAge(6000);
            this.animal.resetInLove();
            this.targetMate.resetInLove();

            entityageable.setGrowingAge(-24000);
            entityageable.setLocationAndAngles(this.animal.posX, this.animal.posY, this.animal.posZ, 0.0F, 0.0F);
            entityageable2.setGrowingAge(-24000);
            entityageable2.setLocationAndAngles(this.animal.posX, this.animal.posY, this.animal.posZ, 0.0F, 0.0F);

            this.world.spawnEntity(entityageable);
            this.world.spawnEntity(entityageable2);

            Random random = this.animal.getRNG();

            for (int i = 0; i < 7; ++i)
            {
                double d0 = random.nextGaussian() * 0.02D;
                double d1 = random.nextGaussian() * 0.02D;
                double d2 = random.nextGaussian() * 0.02D;
                double d3 = random.nextDouble() * (double) this.animal.width * 2.0D - (double) this.animal.width;
                double d4 = 0.5D + random.nextDouble() * (double) this.animal.height;
                double d5 = random.nextDouble() * (double) this.animal.width * 2.0D - (double) this.animal.width;
                this.world.spawnParticle(EnumParticleTypes.HEART, this.animal.posX + d3, this.animal.posY + d4, this.animal.posZ + d5, d0, d1, d2);
            }

            if (this.world.getGameRules().getBoolean("doMobLoot"))
            {
                this.world.spawnEntity(new EntityXPOrb(this.world, this.animal.posX, this.animal.posY, this.animal.posZ, random.nextInt(7) + 1));
                this.world.spawnEntity(new EntityXPOrb(this.world, this.animal.posX, this.animal.posY, this.animal.posZ, random.nextInt(7) + 1));
            }
        }
    }
}