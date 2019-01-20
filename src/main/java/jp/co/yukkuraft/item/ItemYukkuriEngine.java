package jp.co.yukkuraft.item;

import jp.co.yukkuraft.YuBlocks;
import jp.co.yukkuraft.tileentity.engine.TileYukkuriEngine;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemYukkuriEngine extends YuItem
{
    public ItemYukkuriEngine(String name, boolean hasTooltip)
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
            if (!canPlaceBlockAt(worldIn, pos))
            {
                // 設置スペースがない場合
                return EnumActionResult.FAIL;
            }

            final int posX = pos.getX();
            final int posY = pos.getY();
            final int posZ = pos.getZ();

            for (int x = posX - 1; x < posX + 2; x++)
                for (int y = posY; y < posY + 3; y++)
                    for (int z = posZ - 1; z < posZ + 2; z++)
                    {
                        BlockPos targetPos = new BlockPos(x, y, z);
                        worldIn.setBlockState(targetPos, YuBlocks.YUKKURI_ENGINE.getDefaultState());
                        TileEntity tile = worldIn.getTileEntity(targetPos);
                        TileYukkuriEngine tileEngine = (TileYukkuriEngine) tile;
                        tileEngine.isMaster = (x == posX && y == posY && z == posZ);
                        tileEngine.masterPos = new BlockPos(posX, posY, posZ);
                        tileEngine.isNBTLoaded = true;
                    }

            {
                // タイルエンティティに回転値を設定する。
                int i = MathHelper.floor((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
                BlockPos masterPos = new BlockPos(posX, posY, posZ);
                TileEntity tile = worldIn.getTileEntity(masterPos);
                TileYukkuriEngine tileEngine = (TileYukkuriEngine) tile;
                tileEngine.rotation = i;
            }

            // 手持ちのアイテムを取得する。
            ItemStack itemstack = player.getHeldItem(hand);
            // アイテムを消費する。
            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
    }

    private boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        final int posX = pos.getX();
        final int posY = pos.getY();
        final int posZ = pos.getZ();

        int goodCount = 0;
        for (int x = posX - 1; x < posX + 2; x++)
            for (int y = posY; y < posY + 3; y++)
                for (int z = posZ - 1; z < posZ + 2; z++)
                {
                    BlockPos targetPos = new BlockPos(x, y, z);
                    if (YuBlocks.YUKKURI_ENGINE.canPlaceBlockAt(worldIn, targetPos))
                        goodCount++;
                }
        return (goodCount == 3 * 3 * 3);
    }
}
