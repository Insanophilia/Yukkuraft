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
 *　このクラスは「テストブロック」の定義を行います。
 *
 * @author Insanophilia
 *
 */
public class Test0 extends YuBlock
{
    public Test0(Material material, String name, boolean hasTooltip, float hardness, float resistance, SoundType soundType, float lightLevel)
    {
        super(material, name, hasTooltip, hardness, resistance, soundType, lightLevel);
    }

    // 右クリック時の処理
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        if (itemstack.getItem() == Items.STICK)
        {
            createTestField(worldIn, pos, playerIn);
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    private void createTestField(World worldIn, BlockPos pos, EntityPlayer playerIn)
    {
        IBlockState air = Blocks.AIR.getDefaultState();
        IBlockState yukariBlock = YuBlocks.YUKARI_BLOCK.getDefaultState();

        int SIZE = 10;
        int CUBE_SIZE = SIZE * 2 + 1;

        for (int i = 0; i < CUBE_SIZE; i++)
        {
            for (int j = 0; j < CUBE_SIZE; j++)
            {
                for (int k = 0; k < CUBE_SIZE; k++)
                {
                    if (i == 0 || j == 0 || k == 0 || i == CUBE_SIZE - 1 || j == CUBE_SIZE - 1 || k == CUBE_SIZE - 1)
                    {
                        worldIn.setBlockState(pos.north(-SIZE + i).east(-SIZE + j).up(k), yukariBlock);
                    } else
                    {
                        worldIn.setBlockState(pos.north(-SIZE + i).east(-SIZE + j).up(k), air);
                    }
                }
            }
        }
    }
}
