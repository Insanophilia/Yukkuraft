package jp.co.yukkuraft.block;

import jp.co.yukkuraft.YuBlocks;
import jp.co.yukkuraft.block.base.YuBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * このクラスは「テストブロック」の定義を行います。
 *
 * @author Insanophilia
 *
 */
public class BlockTest0 extends YuBlock
{
    public BlockTest0(String name, Material material, SoundType soundType, boolean hasTooltip)
    {
        super(name, material, soundType, hasTooltip);
    }

    // 右クリック時の処理
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
            EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        if (itemstack.getItem() == Items.STICK)
        {
            createTestField(worldIn, pos, playerIn);
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    // ゆかりブロックで隔離されたテスト用空間生成の処理
    private void createTestField(World worldIn, BlockPos pos, EntityPlayer playerIn)
    {
        final IBlockState air = Blocks.AIR.getDefaultState();
        final IBlockState yukariBlock = YuBlocks.YUKARI_BLOCK.getDefaultState();
        // 作成する立方体のサイズ(中心からのブロック数)
        final int size = 10;
        final int posX = pos.getX();
        final int posY = pos.getY();
        final int posZ = pos.getZ();

        for (int x = posX - size; x <= posX + size; x++)
            for (int y = posY; y <= posY + size * 2; y++)
                for (int z = posZ - size; z <= posZ + size; z++)
                {
                    if ((x == posX - size) || (y == posY) || (z == posZ - size)
                            || (x == posX + size) || (y == posY + size * 2) || (z == posZ + size))
                    {
                        worldIn.setBlockState(new BlockPos(x, y, z), yukariBlock);
                    } else
                    {
                        worldIn.setBlockState(new BlockPos(x, y, z), air);
                    }
                }
    }
}
