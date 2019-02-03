package jp.co.yukkuraft.complex.yukkuri.entity;

import jp.co.yukkuraft.ModCore;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは「ゆっくり魔理沙」のレンダラーを定義します。
 *
 * @author Insanophilia
 *
 */
@SideOnly(Side.CLIENT)
public class RenderYukkuriMarisa extends RenderLiving<EntityLiving>
{
    public static final ResourceLocation texture = new ResourceLocation(ModCore.MOD_ID + ":textures/entities/yukkuri_marisa_smile.png");

    public RenderYukkuriMarisa(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn)
    {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLiving entity)
    {
        return texture;
    }
}
