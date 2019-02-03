package jp.co.yukkuraft.complex.flamethrower;

import java.util.Random;

import jp.co.yukkuraft.item.base.YuItemBow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/**
 * このクラスは「火炎放射器」の定義を行います。
 *
 * @author Insanophilia
 */
public class ItemFlamethrower extends YuItemBow
{
    public ItemFlamethrower(String name)
    {
        super(name);
    }

    /** 右クリック時の処理 */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        return super.onItemRightClick(worldIn, playerIn, handIn);

    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        boolean canFire = (stack.getItemDamage() < stack.getMaxDamage() - 1);
        if (canFire)
        {
            this.shot(worldIn, entityLiving, stack);

            if (entityLiving instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer) entityLiving;
                worldIn.playSound((EntityPlayer) null, entityplayer.posX, entityplayer.posY, entityplayer.posZ,
                        SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.PLAYERS, 1.0F,
                        1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);
            }
            // ダメージ値を追加
            stack.setItemDamage(stack.getItemDamage() + 1);
        } else
        {
            if (entityLiving instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer) entityLiving;
                worldIn.playSound((EntityPlayer) null, entityplayer.posX, entityplayer.posY, entityplayer.posZ,
                        SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.PLAYERS, 1.0F,
                        1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);
            }
        }
    }

    private void shot(World worldIn, EntityLivingBase playerIn, ItemStack itemstack)
    {
        final int fireNum = 5;
        final float f = 1.0F;

        if (!worldIn.isRemote)
        {
            Random rand = new Random();
            for (int i = 0; i < fireNum; i++)
            {
                EntityFlame entityFlame = new EntityFlame(worldIn, playerIn);
                entityFlame.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, f * 3.0F, 1.0F);
                entityFlame.posX += rand.nextFloat() * 0.5F - 0.25F;
                entityFlame.posY += rand.nextFloat() * 0.5F - 0.25F;
                entityFlame.posZ += rand.nextFloat() * 0.5F - 0.25F;
                entityFlame.prevPosX = entityFlame.posX;
                entityFlame.prevPosY = entityFlame.posY;
                entityFlame.prevPosZ = entityFlame.posZ;
                worldIn.spawnEntity(entityFlame);
            }
        }
    }
}
