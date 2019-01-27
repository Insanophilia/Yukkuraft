package jp.co.yukkuraft.test;

import jp.co.yukkuraft.block.base.YuBlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * このクラスは「MODで追加されるマルチブロックの基底クラス」です。
 *
 * @author Insanophilia
 *
 */
public abstract class YuMultiBlock extends YuBlockContainer
{
    public YuMultiBlock(String name, Material material, SoundType soundType)
    {
        super(name, material, soundType);
    }
}
