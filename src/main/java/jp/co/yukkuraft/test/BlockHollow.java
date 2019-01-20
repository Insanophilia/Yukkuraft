package jp.co.yukkuraft.test;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockHollow extends YuMultiBlock
{
    public BlockHollow(Material material, String name, boolean hasTooltip, float hardness, float resistance, SoundType soundType, float lightLevel)
    {
        super(material, name, hasTooltip, hardness, resistance, soundType, lightLevel);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileHollowMultiBlock();
    }
}
