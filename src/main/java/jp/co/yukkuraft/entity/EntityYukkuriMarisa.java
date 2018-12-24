package jp.co.yukkuraft.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.world.World;

/**
 * このクラスは「ゆっくり魔理沙」を定義します。
 *
 * @author Insanophilia
 *
 */
public class EntityYukkuriMarisa extends EntityYukkuri
{
    public EntityYukkuriMarisa(World worldIn)
    {
        super(worldIn);
    }

    @Override
    public EntityYukkuriMarisa createChild(EntityAgeable ageable)
    {
        return new EntityYukkuriMarisa(this.world);
    }
}
