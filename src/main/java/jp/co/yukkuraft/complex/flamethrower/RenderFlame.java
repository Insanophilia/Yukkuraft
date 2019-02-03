package jp.co.yukkuraft.complex.flamethrower;

import jp.co.yukkuraft.ModCore;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFlame extends RenderArrow<EntityFlame>
{
    public static final ResourceLocation texture = new ResourceLocation(
            ModCore.MOD_ID + ":textures/entities/entity_flame.png");

    ModelFlame model = new ModelFlame();

    public RenderFlame(RenderManager renderManager)
    {
        super(renderManager);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityFlame entity)
    {
        return texture;
    }

    public void doRender(EntityFlame entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.bindEntityTexture(entity);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.translate((float) x, (float) y, (float) z);

        model.render(entity, 1F, 1F, 1F, 1F, 1F, 0.1F);

        GlStateManager.disableRescaleNormal();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
}
