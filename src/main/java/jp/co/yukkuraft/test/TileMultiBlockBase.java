package jp.co.yukkuraft.test;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

/**
 * このクラスは MulitBlock 基底クラスの定義を行います。
 *
 * @author Insanophilia
 *
 */
public abstract class TileMultiBlockBase extends TileEntity implements ITickable
{
    // 構造が完成しているか
    public boolean isStructureCompleted;
    // 代表ブロックか
    public boolean isMaster;
    // 代表ブロックの座標
    public BlockPos masterPos;
    //
    public boolean isNBTLoaded = false;

    // 構成ブロックの更新処理
    @Override
    public void update()
    {
        if (!isNBTLoaded)
        {
            return; // NBT 読込前は何もしない
        }

        if (this.isStructureCompleted)
        {
            if (this.isMaster)
            {
                if (this.checkStructure())
                {
                    this.updateStructure();
                } else
                {
                    this.tearDownStructure();
                }
            } else
            {
                if (!existsMaster())
                {
                    this.reset();
                }
            }
        } else
        {
            if (this.checkStructure())
            {
                this.setUpStructure();
            }
        }
    }

    // 構成ブロックの初期化
    public void reset()
    {
        isStructureCompleted = false;
        isMaster = false;
        masterPos = new BlockPos(0, 0, 0);
    }

    // 構造体の更新処理
    public abstract void updateStructure();

    // 構造体のチェック処理
    public abstract boolean checkStructure();

    // 構造体の立ち上げ処理
    public abstract void setUpStructure();

    // 構造体の取り壊し処理
    public abstract void tearDownStructure();

    // マスターブロックの存在チェック処理
    public boolean existsMaster()
    {
        TileEntity tile = this.world.getTileEntity(masterPos);
        return (tile != null && (tile instanceof TileMultiBlockBase));
    }

    // マスターブロック用の NBT 書込処理
    public abstract void masterWriteToNBT(NBTTagCompound tag);

    // マスターブロック用の NBT 読込処理
    public abstract void masterReadFromNBT(NBTTagCompound tag);

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
        parentNBTTagCompound.setBoolean("isStructureCompleted", this.isStructureCompleted);
        parentNBTTagCompound.setBoolean("isMaster", this.isMaster);
        if (this.isStructureCompleted)
        {
            parentNBTTagCompound.setInteger("masterPosX", this.masterPos.getX());
            parentNBTTagCompound.setInteger("masterPosY", this.masterPos.getY());
            parentNBTTagCompound.setInteger("masterPosZ", this.masterPos.getZ());
        } else
        {
            parentNBTTagCompound.setInteger("masterPosX", 0);
            parentNBTTagCompound.setInteger("masterPosY", 0);
            parentNBTTagCompound.setInteger("masterPosZ", 0);
        }
        if (isStructureCompleted && isMaster)
        {
            masterWriteToNBT(parentNBTTagCompound);
        }
        return parentNBTTagCompound;
    }

    // NBT 読込処理
    @Override
    public void readFromNBT(NBTTagCompound parentNBTTagCompound)
    {
        super.readFromNBT(parentNBTTagCompound);
        this.isStructureCompleted = parentNBTTagCompound.getBoolean("isStructureCompleted");
        this.isMaster = parentNBTTagCompound.getBoolean("isMaster");
        int x = parentNBTTagCompound.getInteger("masterPosX");
        int y = parentNBTTagCompound.getInteger("masterPosY");
        int z = parentNBTTagCompound.getInteger("masterPosZ");
        this.masterPos = new BlockPos(x, y, z);
        if (isStructureCompleted && isMaster)
        {
            masterReadFromNBT(parentNBTTagCompound);
        }
        this.isNBTLoaded = true;
    }

}
