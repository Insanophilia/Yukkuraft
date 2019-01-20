package jp.co.yukkuraft.test;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class TileStoneMultiblock extends TileMultiBlockBase
{

    @Override
    public void updateStructure()
    {
    }

    @Override
    public boolean checkStructure()
    {
        BlockPos blockPos = this.getPos();
        int xCoord = blockPos.getX();
        int yCoord = blockPos.getY();
        int zCoord = blockPos.getZ();

        // Checks to see if surrounded by stone
        int i = 0;
        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord - 1; y < yCoord + 2; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++)
                {
                    if (this.world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.STONE)
                        i++;
                }
        return i == 26;
    }

    @Override
    public void setUpStructure()
    {
        BlockPos blockPos = this.getPos();
        int xCoord = blockPos.getX();
        int yCoord = blockPos.getY();
        int zCoord = blockPos.getZ();

        // replaces stone with diamond blocks
        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord - 1; y < yCoord + 2; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++)
                {
                    BlockPos targetPos = new BlockPos(x, y, z);
                    if (this.world.getBlockState(targetPos).getBlock() == Blocks.STONE)
                        this.world.setBlockState(targetPos, Blocks.DIAMOND_BLOCK.getDefaultState());
                }
    }

    @Override
    public void tearDownStructure()
    {
    }

    @Override
    public void masterWriteToNBT(NBTTagCompound tag)
    {
    }

    @Override
    public void masterReadFromNBT(NBTTagCompound tag)
    {
    }
}