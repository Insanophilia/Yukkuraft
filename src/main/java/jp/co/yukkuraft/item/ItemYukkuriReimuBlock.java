package jp.co.yukkuraft.item;

import jp.co.yukkuraft.YuBlocks;
import jp.co.yukkuraft.tileentity.yukkuri.TileYukkuriReimu;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * このクラスはアイテム状態の「ゆっくり霊夢ブロック」の定義を行います。
 *
 * @author Insanophilia
 */
public class ItemYukkuriReimuBlock extends YuItem
{
    public ItemYukkuriReimuBlock(String name, boolean hasTooltip)
    {
        super(name, hasTooltip);
    }

    // 右クリック時の処理
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
            EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (facing != EnumFacing.UP)
        {
            return EnumActionResult.FAIL;
        } else
        {
            // ブロックの上面に対して右クリックした場合のみ
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();
            // 対象が置き換え不可能なブロックの場合、その隣接位置を対象とする。
            boolean flag = block.isReplaceable(worldIn, pos);
            if (!flag)
            {
                pos = pos.offset(facing);
            }

            // 手持ちのアイテムを取得する。
            ItemStack itemstack = player.getHeldItem(hand);
            if (worldIn.isRemote)
            {
                return EnumActionResult.SUCCESS;
            } else
            {
                worldIn.setBlockState(pos, YuBlocks.YUKKURI_REIMU_BLOCK.getDefaultState());
                int i = MathHelper.floor((double) (player.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;

                TileEntity tile = worldIn.getTileEntity(pos);
                if (tile instanceof TileYukkuriReimu)
                {
                    // タイルエンティティに回転値を設定する。
                    TileYukkuriReimu tileYukkuriReimu = (TileYukkuriReimu) tile;
                    tileYukkuriReimu.setRotation(i);
                }

                if (player instanceof EntityPlayerMP)
                {
                    CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos, itemstack);
                }
                // アイテムを消費する。
                itemstack.shrink(1);
                return EnumActionResult.SUCCESS;
            }
        }
    }
}
