package jp.co.yukkuraft.tileentity;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは「ゆっくり霊夢ブロック」のタイルエンティティ定義を行います。
 *
 * @author Insanophilia
 *
 */
public class TileEntityYukkuriReimu extends TileEntity
{
    // 回転値
    private int rotation;

    public void setRotation(int rotation)
    {
        this.rotation = rotation;
    }

    @SideOnly(Side.CLIENT)
    public int getRotation()
    {
        return this.rotation;
    }

    // ここからサーバとの通信処理
    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        int metadata = getBlockMetadata();
        return new SPacketUpdateTileEntity(this.pos, metadata, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag)
    {
        this.readFromNBT(tag);
    }
    // ここまでサーバとの通信処理

    // NBT 書込処理
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound parentNBTTagCompound)
    {
        super.writeToNBT(parentNBTTagCompound);
        parentNBTTagCompound.setByte("Rot", (byte) (this.rotation & 255));
        return parentNBTTagCompound;
    }

    // NBT 読込処理
    @Override
    public void readFromNBT(NBTTagCompound parentNBTTagCompound)
    {
        super.readFromNBT(parentNBTTagCompound);
        this.rotation = parentNBTTagCompound.getByte("Rot");
    }

    // TESR の最大描画距離の二乗を取得。
    @SideOnly(Side.CLIENT)
    @Override
    public double getMaxRenderDistanceSquared()
    {
        final int MAXIMUM_DISTANCE_IN_BLOCKS = 64;
        return MAXIMUM_DISTANCE_IN_BLOCKS * MAXIMUM_DISTANCE_IN_BLOCKS;
    }

    // TESR のバウンティングボックスを取得。
    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB aabb = new AxisAlignedBB(getPos(), getPos().add(1, 1, 1));
        return aabb;
    }
}
