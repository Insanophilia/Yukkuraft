package jp.co.yukkuraft.constant;

import net.minecraft.util.DamageSource;

/**
 * このクラスは DamageSource の定義を行います。
 *
 * @author Insanophilia
 *
 */
public class YuDamageSouce
{
    public static final DamageSource MINCER  = new DamageSource("yukkuraft.damage_source.mincer");
    public static final DamageSource HOURAKU = new DamageSource("yukkuraft.damage_source.houraku").setFireDamage();
    public static final DamageSource WET     = new DamageSource("yukkuraft.damage_source.wet");
}
