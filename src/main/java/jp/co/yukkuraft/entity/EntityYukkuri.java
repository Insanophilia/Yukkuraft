package jp.co.yukkuraft.entity;

import java.lang.reflect.Field;

import javax.annotation.Nullable;

import jp.co.yukkuraft.constant.YuDamageSouce;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCarrot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは「ゆっくり」の基底クラスを定義します。
 *
 * @author Insanophilia
 *
 */
public class EntityYukkuri extends EntityAnimal
{
    //    private static final DataParameter<Integer> RABBIT_TYPE = EntityDataManager.<Integer> createKey(EntityYukkuri.class, DataSerializers.VARINT);
    private int     jumpTicks;
    private int     jumpDuration;
    private boolean wasOnGround;
    // 現在の移動タイプの期間
    private int currentMoveTypeDuration;
    // 人参ティック
    private int carrotTicks;

    public EntityYukkuri(World worldIn)
    {
        super(worldIn);
        this.setSize(0.4F, 0.5F);
        this.jumpHelper = new EntityYukkuri.RabbitJumpHelper(this);
        this.moveHelper = new EntityYukkuri.RabbitMoveHelper(this);
        this.setMovementSpeed(0.0D);
    }

    // TODO ゆっくり Model の確認の為、暫定的に EntityRabbit をそのまま使う。

    // inLove取得
    public int getInLove()
    {
        int _inLove = 0;
        try
        {
            Class<EntityAnimal> clazz = EntityAnimal.class;
            Field field = clazz.getDeclaredField("inLove");
            field.setAccessible(true);
            _inLove = (int) field.get(this);
        } catch (Exception e)
        {
        }
        return _inLove;
    }

    // AI 設定
    protected void initEntityAI()
    {
        // 水泳
        //        this.tasks.addTask(1, new EntityAISwimming(this));
        // パニック
        this.tasks.addTask(1, new EntityYukkuri.AIPanic(this, 2.2D));
        // 繁殖
        this.tasks.addTask(2, new YuEntityAIMate(this, 0.8D));
        // 引き付ける
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, Items.SUGAR, false));
        // 水を避ける
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        // 一番近いプレーヤーを見る
        //        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
    }

    // ドロップの設定
    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return null;
    }

    // 繁殖時に発生する子ゆっくりを設定
    @Override
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        return null;
    }

    // 右クリック時の処理
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        if (!itemstack.isEmpty())
        {
            int _inLove = getInLove();
            if (this.isBreedingItem(itemstack) && this.getGrowingAge() == 0 && _inLove <= 0)
            {
                this.consumeItemFromStack(player, itemstack);
                this.setInLove(player);
                return true;
            }

            if (this.isChild() && this.isBreedingItem(itemstack))
            {
                this.consumeItemFromStack(player, itemstack);
                this.ageUp((int) ((float) (-this.getGrowingAge() / 20) * 0.1F), true);
                return true;
            }
        }

        return super.processInteract(player, hand);
    }

    // 繁殖可能な相手か
    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
            //        } else if (otherAnimal.getClass() != this.getClass())
        } else if (!(otherAnimal instanceof EntityYukkuri))
        {
            return false;
        } else
        {
            return this.isInLove() && otherAnimal.isInLove();
        }
    }

    protected float getJumpUpwardsMotion()
    {
        if (!this.collidedHorizontally && (!this.moveHelper.isUpdating() || this.moveHelper.getY() <= this.posY + 0.5D))
        {
            Path path = this.navigator.getPath();

            if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
            {
                Vec3d vec3d = path.getPosition(this);

                if (vec3d.y > this.posY + 0.5D)
                {
                    return 0.5F;
                }
            }

            return this.moveHelper.getSpeed() <= 0.6D ? 0.2F : 0.3F;
        } else
        {
            return 0.5F;
        }
    }

    // エンティティをジャンプさせます。
    protected void jump()
    {
        super.jump();
        double d0 = this.moveHelper.getSpeed();

        if (d0 > 0.0D)
        {
            double d1 = this.motionX * this.motionX + this.motionZ * this.motionZ;

            if (d1 < 0.010000000000000002D)
            {
                this.moveRelative(0.0F, 0.0F, 1.0F, 0.1F);
            }
        }

        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte) 1);
        }
    }

    @SideOnly(Side.CLIENT)
    public float setJumpCompletion(float p_175521_1_)
    {
        return this.jumpDuration == 0 ? 0.0F : ((float) this.jumpTicks + p_175521_1_) / (float) this.jumpDuration;
    }

    public void setMovementSpeed(double newSpeed)
    {
        this.getNavigator().setSpeed(newSpeed);
        this.moveHelper.setMoveTo(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), newSpeed);
    }

    public void setJumping(boolean jumping)
    {
        super.setJumping(jumping);

        if (jumping)
        {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
        }
    }

    public void startJumping()
    {
        this.setJumping(true);
        this.jumpDuration = 10;
        this.jumpTicks = 0;
    }

    protected void entityInit()
    {
        super.entityInit();
        //        this.dataManager.register(RABBIT_TYPE, Integer.valueOf(0));
    }

    public void updateAITasks()
    {
        if (this.currentMoveTypeDuration > 0)
        {
            --this.currentMoveTypeDuration;
        }

        if (this.carrotTicks > 0)
        {
            this.carrotTicks -= this.rand.nextInt(3);

            if (this.carrotTicks < 0)
            {
                this.carrotTicks = 0;
            }
        }

        if (this.onGround)
        {
            if (!this.wasOnGround)
            {
                this.setJumping(false);
                this.checkLandingDelay();
            }

            // キーラーラビットの処理は不要
            //            if (this.getRabbitType() == 99 && this.currentMoveTypeDuration == 0)
            //            {
            //                EntityLivingBase entitylivingbase = this.getAttackTarget();
            //
            //                if (entitylivingbase != null && this.getDistanceSq(entitylivingbase) < 16.0D)
            //                {
            //                    this.calculateRotationYaw(entitylivingbase.posX, entitylivingbase.posZ);
            //                    this.moveHelper.setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, this.moveHelper.getSpeed());
            //                    this.startJumping();
            //                    this.wasOnGround = true;
            //                }
            //            }

            EntityYukkuri.RabbitJumpHelper entityrabbit$rabbitjumphelper = (EntityYukkuri.RabbitJumpHelper) this.jumpHelper;

            if (!entityrabbit$rabbitjumphelper.getIsJumping())
            {
                if (this.moveHelper.isUpdating() && this.currentMoveTypeDuration == 0)
                {
                    Path path = this.navigator.getPath();
                    Vec3d vec3d = new Vec3d(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ());

                    if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
                    {
                        vec3d = path.getPosition(this);
                    }

                    this.calculateRotationYaw(vec3d.x, vec3d.z);
                    this.startJumping();
                }
            } else if (!entityrabbit$rabbitjumphelper.canJump())
            {
                this.enableJumpControl();
            }
        }

        this.wasOnGround = this.onGround;
    }

    // スプリント時のパーティクル表示
    @Override
    public void spawnRunningParticles()
    {
    }

    private void calculateRotationYaw(double x, double z)
    {
        this.rotationYaw = (float) (MathHelper.atan2(z - this.posZ, x - this.posX) * (180D / Math.PI)) - 90.0F;
    }

    private void enableJumpControl()
    {
        ((EntityYukkuri.RabbitJumpHelper) this.jumpHelper).setCanJump(true);
    }

    private void disableJumpControl()
    {
        ((EntityYukkuri.RabbitJumpHelper) this.jumpHelper).setCanJump(false);
    }

    private void updateMoveTypeDuration()
    {
        if (this.moveHelper.getSpeed() < 2.2D)
        {
            this.currentMoveTypeDuration = 10;
        } else
        {
            this.currentMoveTypeDuration = 1;
        }
    }

    private void checkLandingDelay()
    {
        this.updateMoveTypeDuration();
        this.disableJumpControl();
    }

    public int wetDamageDuration = 100;
    public int wetDamageTick     = 0;

    // ティック毎の処理
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.jumpTicks != this.jumpDuration)
        {
            ++this.jumpTicks;
        } else if (this.jumpDuration != 0)
        {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.setJumping(false);
        }
        // 水に濡れるとダメージを受ける。
        if (this.isWet())
        {
            if (wetDamageTick++ >= wetDamageDuration)
            {
                this.attackEntityFrom(YuDamageSouce.WET, 1.0F);
                wetDamageTick = 0;
                spawnWetDamageParticles();
            }
        } else
        {
            wetDamageTick = 0;
        }
    }

    // 水しぶきを発生させる。
    public void spawnWetDamageParticles()
    {
        float f2 = (float) MathHelper.floor(this.getEntityBoundingBox().minY);
        for (int j = 0; (float) j < 1.0F + this.width * 20.0F; ++j)
        {
            float f5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
            float f6 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
            this.world.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX + (double) f5, (double) (f2 + 1.0F), this.posZ + (double) f6, this.motionX, this.motionY,
                    this.motionZ);
        }
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
    }

    public static void registerFixesRabbit(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityYukkuri.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        //        compound.setInteger("RabbitType", this.getRabbitType());
        compound.setInteger("MoreCarrotTicks", this.carrotTicks);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        //        this.setRabbitType(compound.getInteger("RabbitType"));
        this.carrotTicks = compound.getInteger("MoreCarrotTicks");
    }

    protected SoundEvent getJumpSound()
    {
        return SoundEvents.ENTITY_RABBIT_JUMP;
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_RABBIT_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_RABBIT_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_RABBIT_DEATH;
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        //        if (this.getRabbitType() == 99)
        //        {
        //            this.playSound(SoundEvents.ENTITY_RABBIT_ATTACK, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
        //            return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);
        //        } else
        {
            return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
        }
    }

    public SoundCategory getSoundCategory()
    {
        //        return this.getRabbitType() == 99 ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
        return SoundCategory.NEUTRAL;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return this.isEntityInvulnerable(source) ? false : super.attackEntityFrom(source, amount);
    }

    //    public EntityRabbit createChild(EntityAgeable ageable)
    //    {
    //        EntityRabbit entityrabbit = new EntityRabbit(this.world);
    //        int i = this.getRandomRabbitType();
    //
    //        if (this.rand.nextInt(20) != 0)
    //        {
    //            if (ageable instanceof EntityRabbit && this.rand.nextBoolean())
    //            {
    //                i = ((EntityRabbit) ageable).getRabbitType();
    //            } else
    //            {
    //                i = this.getRabbitType();
    //            }
    //        }
    //
    //        entityrabbit.setRabbitType(i);
    //        return entityrabbit;
    //    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack stack)
    {
        Item item = stack.getItem();
        return item == Item.getItemFromBlock(Blocks.BROWN_MUSHROOM) || item == Item.getItemFromBlock(Blocks.RED_MUSHROOM);
    }

    // ▼ ラビットタイプは使わない
    //    public int getRabbitType()
    //    {
    //        return ((Integer) this.dataManager.get(RABBIT_TYPE)).intValue();
    //    }
    //
    //    public void setRabbitType(int rabbitTypeId)
    //    {
    //        if (rabbitTypeId == 99)
    //        {
    //            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
    //            this.tasks.addTask(4, new EntityYukkuri.AIEvilAttack(this));
    //            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
    //            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    //            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWolf.class, true));
    //
    //            if (!this.hasCustomName())
    //            {
    //                this.setCustomNameTag(I18n.translateToLocal("entity.KillerBunny.name"));
    //            }
    //        }
    //
    //        this.dataManager.set(RABBIT_TYPE, Integer.valueOf(rabbitTypeId));
    //    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        int i = this.getRandomRabbitType();
        boolean flag = false;

        //        if (livingdata instanceof EntityYukkuri.RabbitTypeData)
        //        {
        //            i = ((EntityYukkuri.RabbitTypeData) livingdata).typeData;
        //            flag = true;
        //        } else
        //        {
        //            livingdata = new EntityYukkuri.RabbitTypeData(i);
        //        }

        //        this.setRabbitType(i);

        if (flag)
        {
            this.setGrowingAge(-24000);
        }

        return livingdata;
    }

    private int getRandomRabbitType()
    {
        Biome biome = this.world.getBiome(new BlockPos(this));
        int i = this.rand.nextInt(100);

        if (biome.isSnowyBiome())
        {
            return i < 80 ? 1 : 3;
        } else if (biome instanceof BiomeDesert)
        {
            return 4;
        } else
        {
            return i < 50 ? 0 : (i < 90 ? 5 : 2);
        }
    }

    /**
     * Returns true if {@link net.minecraft.entity.passive.EntityRabbit#carrotTicks carrotTicks} has reached zero
     */
    private boolean isCarrotEaten()
    {
        return this.carrotTicks == 0;
    }

    protected void createEatingParticles()
    {
        BlockCarrot blockcarrot = (BlockCarrot) Blocks.CARROTS;
        IBlockState iblockstate = blockcarrot.withAge(blockcarrot.getMaxAge());
        this.world.spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width,
                this.posY + 0.5D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, 0.0D,
                0.0D, 0.0D, Block.getStateId(iblockstate));
        this.carrotTicks = 40;
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 1)
        {
            this.createRunningParticles();
            this.jumpDuration = 10;
            this.jumpTicks = 0;
        } else
        {
            super.handleStatusUpdate(id);
        }
    }

    static class AIAvoidEntity<T extends Entity> extends EntityAIAvoidEntity<T>
    {
        private final EntityYukkuri rabbit;

        public AIAvoidEntity(EntityYukkuri entityYukkuri, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_)
        {
            super(entityYukkuri, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
            this.rabbit = entityYukkuri;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            //            return this.rabbit.getRabbitType() != 99 && super.shouldExecute();
            return super.shouldExecute();
        }
    }

    static class AIEvilAttack extends EntityAIAttackMelee
    {
        public AIEvilAttack(EntityYukkuri entityYukkuri)
        {
            super(entityYukkuri, 1.4D, true);
        }

        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return (double) (4.0F + attackTarget.width);
        }
    }

    static class AIPanic extends EntityAIPanic
    {
        private final EntityYukkuri rabbit;

        public AIPanic(EntityYukkuri entityYukkuri, double speedIn)
        {
            super(entityYukkuri, speedIn);
            this.rabbit = entityYukkuri;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            super.updateTask();
            this.rabbit.setMovementSpeed(this.speed);
        }
    }

    // AI 農場を襲撃
    static class AIRaidFarm extends EntityAIMoveToBlock
    {
        private final EntityYukkuri rabbit;
        private boolean             wantsToRaid;
        private boolean             canRaid;

        public AIRaidFarm(EntityYukkuri rabbitIn)
        {
            super(rabbitIn, 0.699999988079071D, 16);
            this.rabbit = rabbitIn;
        }

        // AI Task の実行判定
        @Override
        public boolean shouldExecute()
        {
            if (this.runDelay <= 0)
            {
                if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.rabbit.world, this.rabbit))
                {
                    return false;
                }

                this.canRaid = false;
                this.wantsToRaid = this.rabbit.isCarrotEaten();
                this.wantsToRaid = true;
            }

            return super.shouldExecute();
        }

        // AI Task の実行継続判定
        @Override
        public boolean shouldContinueExecuting()
        {
            return this.canRaid && super.shouldContinueExecuting();
        }

        // AI Task の更新処理
        @Override
        public void updateTask()
        {
            super.updateTask();
            this.rabbit.getLookHelper().setLookPosition((double) this.destinationBlock.getX() + 0.5D, (double) (this.destinationBlock.getY() + 1),
                    (double) this.destinationBlock.getZ() + 0.5D, 10.0F, (float) this.rabbit.getVerticalFaceSpeed());

            if (this.getIsAboveDestination())
            {
                World world = this.rabbit.world;
                BlockPos blockpos = this.destinationBlock.up();
                IBlockState iblockstate = world.getBlockState(blockpos);
                Block block = iblockstate.getBlock();

                if (this.canRaid && block instanceof BlockCarrot)
                {
                    Integer integer = (Integer) iblockstate.getValue(BlockCarrot.AGE);

                    if (integer.intValue() == 0)
                    {
                        world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
                        world.destroyBlock(blockpos, true);
                    } else
                    {
                        world.setBlockState(blockpos, iblockstate.withProperty(BlockCarrot.AGE, Integer.valueOf(integer.intValue() - 1)), 2);
                        world.playEvent(2001, blockpos, Block.getStateId(iblockstate));
                    }

                    this.rabbit.createEatingParticles();
                }

                this.canRaid = false;
                this.runDelay = 10;
            }
        }

        /**
         * Return true to set given position as destination
         */
        protected boolean shouldMoveTo(World worldIn, BlockPos pos)
        {
            Block block = worldIn.getBlockState(pos).getBlock();

            if (block == Blocks.FARMLAND && this.wantsToRaid && !this.canRaid)
            {
                pos = pos.up();
                IBlockState iblockstate = worldIn.getBlockState(pos);
                block = iblockstate.getBlock();

                if (block instanceof BlockCarrot && ((BlockCarrot) block).isMaxAge(iblockstate))
                {
                    this.canRaid = true;
                    return true;
                }
            }

            return false;
        }
    }

    public class RabbitJumpHelper extends EntityJumpHelper
    {
        private final EntityYukkuri rabbit;
        private boolean             canJump;

        public RabbitJumpHelper(EntityYukkuri entityYukkuri)
        {
            super(entityYukkuri);
            this.rabbit = entityYukkuri;
        }

        public boolean getIsJumping()
        {
            return this.isJumping;
        }

        public boolean canJump()
        {
            return this.canJump;
        }

        public void setCanJump(boolean canJumpIn)
        {
            this.canJump = canJumpIn;
        }

        /**
         * Called to actually make the entity jump if isJumping is true.
         */
        public void doJump()
        {
            if (this.isJumping)
            {
                this.rabbit.startJumping();
                this.isJumping = false;
            }
        }
    }

    static class RabbitMoveHelper extends EntityMoveHelper
    {
        private final EntityYukkuri rabbit;
        private double              nextJumpSpeed;

        public RabbitMoveHelper(EntityYukkuri entityYukkuri)
        {
            super(entityYukkuri);
            this.rabbit = entityYukkuri;
        }

        public void onUpdateMoveHelper()
        {
            if (this.rabbit.onGround && !this.rabbit.isJumping && !((EntityYukkuri.RabbitJumpHelper) this.rabbit.jumpHelper).getIsJumping())
            {
                this.rabbit.setMovementSpeed(0.0D);
            } else if (this.isUpdating())
            {
                this.rabbit.setMovementSpeed(this.nextJumpSpeed);
            }

            super.onUpdateMoveHelper();
        }

        /**
         * Sets the speed and location to move to
         */
        public void setMoveTo(double x, double y, double z, double speedIn)
        {
            if (this.rabbit.isInWater())
            {
                speedIn = 1.5D;
            }

            super.setMoveTo(x, y, z, speedIn);

            if (speedIn > 0.0D)
            {
                this.nextJumpSpeed = speedIn;
            }
        }
    }

    //    public static class RabbitTypeData implements IEntityLivingData
    //    {
    //        public int typeData;
    //
    //        public RabbitTypeData(int type)
    //        {
    //            this.typeData = type;
    //        }
    //    }
}
