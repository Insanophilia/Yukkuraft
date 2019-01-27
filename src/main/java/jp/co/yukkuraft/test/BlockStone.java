package jp.co.yukkuraft.test;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockStone extends YuMultiBlock
{
    public BlockStone(String name, Material material, SoundType soundType)
    {
        super(name, material, soundType);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileStoneMultiblock();
    }
}
