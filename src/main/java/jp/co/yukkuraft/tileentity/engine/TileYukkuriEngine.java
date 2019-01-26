package jp.co.yukkuraft.tileentity.engine;

import jp.co.yukkuraft.entity.model.ModelYukkuriReimu;
import jp.co.yukkuraft.test.TileBigBlock;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは「ゆっくりエンジンブロック」のタイルエンティティ定義を行います。
 *
 * @author Insanophilia
 *
 */
public class TileYukkuriEngine extends TileBigBlock
{
    //
    public ModelYukkuriReimu yukkuri = new ModelYukkuriReimu();
    //
    public float  yukkuriPosX = 0;
    public double yukkuriPosY = 0;
    // エンジンの回転値
    public float engineRotation = 0;
    //
    // 回転値
    public int rotation = 0;

    public TileYukkuriEngine()
    {
    }

    // TESR の最大描画距離の二乗を返す。
    @SideOnly(Side.CLIENT)
    @Override
    public double getMaxRenderDistanceSquared()
    {
        if (this.isMaster)
            return 64 * 64; // 64 Block
        return 0;
    }

    // TESR のバウンティングボックスを返す。描画の判定に使われるらしい。
    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        BlockPos pos1 = getPos().add(-1, 0, -1);
        BlockPos pos2 = getPos().add(2, 3, 2);
        AxisAlignedBB aabb = new AxisAlignedBB(pos1, pos2);
        return aabb;
    }

    @Override
    public void updateStructure()
    {
        // エンジンの回転値を設定
        this.engineRotation = (++this.engineRotation) % (360F * 10F);
    }

    @Override
    public void writeToNBT_Master(NBTTagCompound nbtTagCompound)
    {
        nbtTagCompound.setByte("rotation", (byte) (this.rotation & 255));
    }

    @Override
    public void readFromNBT_Master(NBTTagCompound nbtTagCompound)
    {
        this.rotation = nbtTagCompound.getByte("rotation");
    }

    @Override
    public void deleteStructure()
    {
        BlockPos blockPos = this.getPos();
        if (!this.isMaster)
        {
            blockPos = this.masterPos;
        }
        final int posX = blockPos.getX();
        final int posY = blockPos.getY();
        final int posZ = blockPos.getZ();

        for (int x = posX - 1; x < posX + 2; x++)
            for (int y = posY; y < posY + 3; y++)
                for (int z = posZ - 1; z < posZ + 2; z++)
                {
                    BlockPos targetPos = new BlockPos(x, y, z);
                    this.world.setBlockState(targetPos, Blocks.AIR.getDefaultState());
                }
    }
}
