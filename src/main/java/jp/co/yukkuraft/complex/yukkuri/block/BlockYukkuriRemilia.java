package jp.co.yukkuraft.complex.yukkuri.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * このクラスは「ゆっくりレミリアブロック」の定義を行います。
 * ゆっくりの描画は TESR を用います。
 *
 * @author Insanophilia
 *
 */
public class BlockYukkuriRemilia extends BlockYukkuriBase
{
    public BlockYukkuriRemilia(String name, Material material, SoundType soundType)
    {
        super(name, material, soundType);
    }

    // ここから TileEntity の設定
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileYukkuriRemilia();
    }
    // ここまで TileEntity の設定
}
