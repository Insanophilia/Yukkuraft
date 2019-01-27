package jp.co.yukkuraft.tileentity.yukkuri;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * このクラスは「ゆっくり霊夢ブロック」の定義を行います。
 * ゆっくりの描画は TESR を用います。
 *
 * @author Insanophilia
 *
 */
public class BlockYukkuriReimu extends BlockYukkuriBase
{
    public BlockYukkuriReimu(String name, Material material, SoundType soundType)
    {
        super(name, material, soundType);
    }

    // ここから TileEntity の設定
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileYukkuriReimu();
    }
    // ここまで TileEntity の設定
}
