package jp.co.yukkuraft.complex.yukkuri.block;

import jp.co.yukkuraft.YuItems;
import jp.co.yukkuraft.base.block.YuBlock;
import jp.co.yukkuraft.constant.YuAABB;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは「ゆっくりブロック基底クラス」の定義を行います。
 * ゆっくりの描画は TESR を用います。
 *
 * @author Insanophilia
 *
 */
public abstract class BlockYukkuriBase extends YuBlock
{
    public BlockYukkuriBase(String name, Material material, SoundType soundType)
    {
        super(name, material, soundType);
    }

    // ここから TileEntity の設定
    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Override
    public abstract TileEntity createTileEntity(World world, IBlockState state);
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
        TileEntity tileentity = source.getTileEntity(pos);
        if (tileentity instanceof TileYukkuriBase)
        {
            TileYukkuriBase tileEntityBlockYukkuriBase = (TileYukkuriBase) tileentity;
            boolean isChild = tileEntityBlockYukkuriBase.isChild();
            if (isChild)
            {
                return YuAABB.CHILD_YUKKURI;
            }
        }
        return YuAABB.YUKKURI;
    }

    // 右クリック時の処理
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
            EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileYukkuriBase)
        {
            TileYukkuriBase tileEntityBlockYukkuriBase = (TileYukkuriBase) tileentity;
            // 手持ちアイテムによる判定
            ItemStack itemstack = playerIn.getHeldItem(hand);
            if (itemstack.getItem() == YuItems.RING_OF_MOONLIGHT)
            {
                int face = tileEntityBlockYukkuriBase.getFace();
                face++;
                face = face % 4; // TODO 表情は暫定4種
                tileEntityBlockYukkuriBase.setFace(face);
            }
            if (itemstack.getItem() == YuItems.MATTER_ANKO)
            {
                boolean isChild = tileEntityBlockYukkuriBase.isChild();
                isChild = !isChild;
                tileEntityBlockYukkuriBase.setChild(isChild);
            }
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
