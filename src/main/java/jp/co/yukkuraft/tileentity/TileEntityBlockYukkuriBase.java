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
 * このクラスは「ゆっくりブロック」のタイルエンティティ定義を行います。
 *
 * @author Insanophilia
 *
 */
public abstract class TileEntityBlockYukkuriBase extends TileEntity
{
    // 回転値
    private int rotation;
    // 表情
    private int face;
    // 子供
    private boolean isChild;

    public TileEntityBlockYukkuriBase()
    {
        this.rotation = 0;
        this.face = 0;
        this.isChild = false;
    }

    // ここから setter / getter
    public void setRotation(int rotation)
    {
        this.rotation = rotation;
    }

    @SideOnly(Side.CLIENT)
    public int getRotation()
    {
        return this.rotation;
    }

    public void setFace(int face)
    {
        this.face = face;
    }

    @SideOnly(Side.CLIENT)
    public int getFace()
    {
        return this.face;
    }

    public void setChild(boolean isChild)
    {
        this.isChild = isChild;
    }

    @SideOnly(Side.CLIENT)
    public boolean isChild()
    {
        return this.isChild;
    }
    // ここまで setter / getter

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
        parentNBTTagCompound.setByte("rotation", (byte) (this.rotation & 255));
        parentNBTTagCompound.setByte("face", (byte) (this.face & 255));
        parentNBTTagCompound.setByte("isChild", (byte) (this.isChild ? 1 : 0));
        return parentNBTTagCompound;
    }

    // NBT 読込処理
    @Override
    public void readFromNBT(NBTTagCompound parentNBTTagCompound)
    {
        super.readFromNBT(parentNBTTagCompound);
        this.rotation = parentNBTTagCompound.getByte("rotation");
        this.face = parentNBTTagCompound.getByte("face");
        this.isChild = parentNBTTagCompound.getByte("isChild") == 1 ? true : false;
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
