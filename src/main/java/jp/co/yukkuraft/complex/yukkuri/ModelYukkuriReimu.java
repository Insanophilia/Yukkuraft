package jp.co.yukkuraft.complex.yukkuri;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは「ゆっくり霊夢」のモデルを定義します。
 *
 * @author Insanophilia
 *
 */
@SideOnly(Side.CLIENT)
public class ModelYukkuriReimu extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer hair;
    public ModelRenderer face;
    public ModelRenderer option;

    public ModelYukkuriReimu()
    {
        this.textureWidth = 128;
        this.textureHeight = 128;

        this.head = new ModelRenderer(this, 0, 89);
        this.head.setRotationPoint(-8.0F, 9.0F, -8.0F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 16, 15, 16, 0.0F);

        this.hair = new ModelRenderer(this, 0, 22);
        this.hair.setRotationPoint(-10.0F, 10.0F, -10.0F);
        this.hair.addBox(0.0F, 0.0F, 0.0F, 20, 13, 20, 0.0F);

        this.face = new ModelRenderer(this, 0, 57);
        this.face.setRotationPoint(-9.0F, 11.0F, -9.0F);
        this.face.addBox(0.0F, 0.0F, 0.0F, 18, 12, 18, 0.0F);

        this.option = new ModelRenderer(this, 0, 0);
        this.option.setRotationPoint(-12.0F, 3.0F, 11.0F);
        this.option.addBox(0.0F, 0.0F, 0.0F, 24, 20, 0, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        GlStateManager.pushMatrix();
        if (this.isChild)
        {
            GlStateManager.scale(0.25F, 0.25F, 0.25F);
            GlStateManager.translate(0.0F, 24.0F * 3.0F * f5, 0.0F);

        } else
        {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * f5, 0.0F);
        }
        this.head.render(f5);
        this.hair.render(f5);
        this.face.render(f5);
        this.option.render(f5);

        GlStateManager.popMatrix();
    }

    public void render(float f5)
    {
        this.head.render(f5);
        this.hair.render(f5);
        this.face.render(f5);
        this.option.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
