package jp.co.yukkuraft.entity;

import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.world.World;

/**
 * このクラスは「ゆっくり」の基底クラスを定義します。
 *
 * @author Insanophilia
 *
 */
public class EntityYukkuri extends EntityRabbit
{
    public EntityYukkuri(World worldIn)
    {
        super(worldIn);
    }

    // TODO ゆっくり Model の確認の為、暫定的に EntityRabbit をそのまま使う。
}
