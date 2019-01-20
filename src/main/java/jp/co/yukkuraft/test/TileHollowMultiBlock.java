package jp.co.yukkuraft.test;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileHollowMultiBlock extends TileMultiBlockBase
{

    @Override
    public void updateStructure()
    {
        BlockPos blockPos = this.getPos();
        int xCoord = blockPos.getX();
        int yCoord = blockPos.getY();
        int zCoord = blockPos.getZ();

        BlockPos targetPos = new BlockPos(xCoord, yCoord + 6, zCoord);
        if (this.world.isAirBlock(targetPos))
            this.world.setBlockState(targetPos, Blocks.DIAMOND_BLOCK.getDefaultState());
    }

    @Override
    public boolean checkStructure()
    {
        BlockPos blockPos = this.getPos();
        int xCoord = blockPos.getX();
        int yCoord = blockPos.getY();
        int zCoord = blockPos.getZ();

        int i = 0;
        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord; y < yCoord + 3; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++)
                {
                    BlockPos targetPos = new BlockPos(x, y, z);
                    TileEntity tile = this.world.getTileEntity(targetPos);
                    if (tile != null && (tile instanceof TileHollowMultiBlock))
                    {
                        if (this.isMaster)
                        {
                            if (((TileHollowMultiBlock) tile).isStructureCompleted)
                                i++;
                        } else if (!((TileHollowMultiBlock) tile).isStructureCompleted)
                            i++;
                    }
                }
        return i > 25 && this.world.isAirBlock(blockPos.up());
    }

    @Override
    public void setUpStructure()
    {
        BlockPos blockPos = this.getPos();
        int xCoord = blockPos.getX();
        int yCoord = blockPos.getY();
        int zCoord = blockPos.getZ();

        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord; y < yCoord + 3; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++)
                {
                    BlockPos targetPos = new BlockPos(x, y, z);
                    TileEntity tile = this.world.getTileEntity(targetPos);
                    boolean master = (x == xCoord && y == yCoord && z == zCoord);
                    if (tile != null && (tile instanceof TileHollowMultiBlock))
                    {
                        ((TileHollowMultiBlock) tile).masterPos = new BlockPos(xCoord, yCoord, zCoord);
                        ((TileHollowMultiBlock) tile).isStructureCompleted = true;
                        ((TileHollowMultiBlock) tile).isMaster = master;
                    }
                }
    }

    @Override
    public void tearDownStructure()
    {
        BlockPos blockPos = this.getPos();
        int xCoord = blockPos.getX();
        int yCoord = blockPos.getY();
        int zCoord = blockPos.getZ();

        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord; y < yCoord + 3; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++)
                {
                    BlockPos targetPos = new BlockPos(x, y, z);
                    TileEntity tile = this.world.getTileEntity(targetPos);
                    if (tile != null && (tile instanceof TileHollowMultiBlock))
                        ((TileHollowMultiBlock) tile).reset();
                }
    }

    @Override
    public void masterWriteToNBT(NBTTagCompound tag)
    {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void masterReadFromNBT(NBTTagCompound tag)
    {
        // TODO 自動生成されたメソッド・スタブ

    }

}
