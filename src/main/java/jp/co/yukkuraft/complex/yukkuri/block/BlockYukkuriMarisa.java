package jp.co.yukkuraft.complex.yukkuri.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * このクラスは「ゆっくり魔理沙ブロック」の定義を行います。
 * ゆっくりの描画は TESR を用います。
 *
 * @author Insanophilia
 *
 */
public class BlockYukkuriMarisa extends BlockYukkuriBase
{
    public BlockYukkuriMarisa(String name, Material material, SoundType soundType)
    {
        super(name, material, soundType);
    }

    // ここから TileEntity の設定
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileYukkuriMarisa();
    }
    // ここまで TileEntity の設定
}
