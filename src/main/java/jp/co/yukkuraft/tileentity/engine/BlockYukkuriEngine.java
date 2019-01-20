package jp.co.yukkuraft.tileentity.engine;

import java.util.Random;

import jp.co.yukkuraft.YuItems;
import jp.co.yukkuraft.constant.YuAABB;
import jp.co.yukkuraft.test.YuMultiBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは「ゆっくりエンジンブロック」の定義を行います。
 * 描画は TESR を用います。
 *
 * @author Insanophilia
 *
 */
public class BlockYukkuriEngine extends YuMultiBlock
{
    public BlockYukkuriEngine(Material material, String name, boolean hasTooltip, float hardness, float resistance,
            SoundType soundType, float lightLevel)
    {
        super(material, name, hasTooltip, hardness, resistance, soundType, lightLevel);
    }

    // ここから TileEntity の設定
    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileYukkuriEngine();
    }
    // ここまで TileEntity の設定

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    // ブロックは不可視、描画は全てタイルエンティティで行う。
    @Override
    public EnumBlockRenderType getRenderType(IBlockState iBlockState)
    {
        return EnumBlockRenderType.INVISIBLE;
    }

    // マウス選択の当たり判定の定義
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        TileEntity tile = source.getTileEntity(pos);
        if (tile != null & tile instanceof TileYukkuriEngine && ((TileYukkuriEngine) tile).isNBTLoaded)
        {
            TileYukkuriEngine tileEngine = (TileYukkuriEngine) tile;
            //            if (tileEngine.isMaster)
            //            {
            //                return YuAABB.MULTI_BLOCK_3_3_3;
            //            }
            return YuAABB.BIG_BLOCK_333.offset(tileEngine.masterPos.subtract(pos));
        }
        return super.getBoundingBox(state, source, pos);
    }

    // ブロック破壊時の処理
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile != null && tile instanceof TileYukkuriEngine)
        {
            TileYukkuriEngine tileEngine = (TileYukkuriEngine) tile;
            tileEngine.deleteStructure();
        }
        super.breakBlock(worldIn, pos, state);
    }

    // 破壊時のドロップ
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return YuItems.ITEM_YUKKURI_ENGINE;
    }
}
