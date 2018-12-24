package jp.co.yukkuraft.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.world.World;

/**
 * このクラスは「ゆっくり霊夢」を定義します。
 *
 * @author Insanophilia
 *
 */
public class EntityYukkuriReimu extends EntityYukkuri
{
    public EntityYukkuriReimu(World worldIn)
    {
        super(worldIn);
    }

    @Override
    public EntityYukkuriReimu createChild(EntityAgeable ageable)
    {
        return new EntityYukkuriReimu(this.world);
    }
}
