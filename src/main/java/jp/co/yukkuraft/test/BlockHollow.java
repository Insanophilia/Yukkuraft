package jp.co.yukkuraft.test;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockHollow extends YuMultiBlock
{
    public BlockHollow(String name, Material material, SoundType soundType, boolean hasTooltip)
    {
        super(name, material, soundType, hasTooltip);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileHollowMultiBlock();
    }
}
