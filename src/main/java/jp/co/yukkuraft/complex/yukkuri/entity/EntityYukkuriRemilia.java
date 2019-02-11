package jp.co.yukkuraft.complex.yukkuri.entity;

import javax.annotation.Nullable;

import jp.co.yukkuraft.constant.YuLootTableList;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * このクラスは「ゆっくりレミリア」を定義します。
 *
 * @author Insanophilia
 *
 */
public class EntityYukkuriRemilia extends EntityYukkuri
{
    public EntityYukkuriRemilia(World worldIn)
    {
        super(worldIn);
    }

    // ドロップの設定
    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return YuLootTableList.YUKKURI_MARISA; //TODO
    }

    // 繁殖時に発生する子ゆっくりを設定
    @Override
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        return new EntityYukkuriRemilia(this.world);
    }
}
