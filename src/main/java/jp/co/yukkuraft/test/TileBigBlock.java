package jp.co.yukkuraft.test;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

/**
 * 複数ブロック分の空間を占拠するブロック
 *
 * @author Insanophilia
 *
 */
public abstract class TileBigBlock extends TileEntity implements ITickable
{
    // 代表ブロックの場合にtrueを返します。
    public boolean isMaster;
    // 代表ブロックの座標
    public BlockPos masterPos;
    // NBTロード済フラグ
    public boolean isNBTLoaded = false;

    // 構成ブロックの更新処理
    @Override
    public void update()
    {
        if (!this.isNBTLoaded)
        {
            return; // NBT 読込前は何もしない
        }
        if (!this.isMaster)
        {
            return;
        }
        // 構造体としての更新処理を行う。
        this.updateStructure();
    }

    // 構成ブロックの初期化
    public void reset()
    {
        this.isMaster = false;
        this.masterPos = new BlockPos(0, 0, 0);
    }

    // 構造体の更新処理
    public abstract void updateStructure();

    // 構造体の削除処理
    public abstract void deleteStructure();

    // 代表ブロックの存在チェック処理
    public boolean existsMaster()
    {
        TileEntity tile = this.world.getTileEntity(masterPos);
        return (tile != null && (tile instanceof TileMultiBlockBase));
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

    // NBT書込処理
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound parentNBTTagCompound)
    {
        super.writeToNBT(parentNBTTagCompound);
        parentNBTTagCompound.setBoolean("isMaster", this.isMaster);
        parentNBTTagCompound.setInteger("masterPosX", this.masterPos.getX());
        parentNBTTagCompound.setInteger("masterPosY", this.masterPos.getY());
        parentNBTTagCompound.setInteger("masterPosZ", this.masterPos.getZ());
        if (isMaster)
        {
            writeToNBT_Master(parentNBTTagCompound);
        }
        return parentNBTTagCompound;
    }

    // NBT読込処理
    @Override
    public void readFromNBT(NBTTagCompound parentNBTTagCompound)
    {
        super.readFromNBT(parentNBTTagCompound);
        this.isMaster = parentNBTTagCompound.getBoolean("isMaster");
        int x = parentNBTTagCompound.getInteger("masterPosX");
        int y = parentNBTTagCompound.getInteger("masterPosY");
        int z = parentNBTTagCompound.getInteger("masterPosZ");
        this.masterPos = new BlockPos(x, y, z);
        if (isMaster)
        {
            readFromNBT_Master(parentNBTTagCompound);
        }
        this.isNBTLoaded = true;
    }

    // NBT書込処理（代表ブロック用）
    public abstract void writeToNBT_Master(NBTTagCompound tag);

    // NBT読込処理（代表ブロック用）
    public abstract void readFromNBT_Master(NBTTagCompound tag);
    // ここまでサーバとの通信処理
}
