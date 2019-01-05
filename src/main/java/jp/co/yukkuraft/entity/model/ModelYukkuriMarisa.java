package jp.co.yukkuraft.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは「ゆっくり魔理沙」のモデルを定義します。
 *
 * @author Insanophilia
 *
 */
@SideOnly(Side.CLIENT)
public class ModelYukkuriMarisa extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer hair;
    public ModelRenderer face;
    public ModelRenderer hat1;
    public ModelRenderer hat2;
    public ModelRenderer hat3;
    public ModelRenderer hat4;

    public ModelYukkuriMarisa()
    {
        this.textureWidth = 128;
        this.textureHeight = 256;

        this.head = new ModelRenderer(this, 0, 67);
        this.head.setRotationPoint(-8.0F, 9.0F, -8.0F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 16, 15, 16, 0.0F);

        this.hair = new ModelRenderer(this, 0, 32);
        this.hair.setRotationPoint(-10.0F, 10.0F, -10.0F);
        this.hair.addBox(0.0F, 0.0F, 0.0F, 20, 13, 20, 0.0F);

        this.face = new ModelRenderer(this, 0, 0);
        this.face.setRotationPoint(-9.0F, 11.0F, -9.0F);
        this.face.addBox(0.0F, 0.0F, 0.0F, 18, 12, 18, 0.0F);

        this.hat1 = new ModelRenderer(this, 0, 101);
        this.hat1.setRotationPoint(-14.0F, 6.0F, -14.0F);
        this.hat1.addBox(0.0F, 0.0F, 1.0F, 28, 4, 28, 0.0F);
        this.setRotateAngle(hat1, -0.17453292519943295F, 0.0F, 0.0F);

        this.hat2 = new ModelRenderer(this, 0, 135);
        this.hat2.setRotationPoint(-11.0F, 1.0F, -11.0F);
        this.hat2.addBox(0.0F, 0.6F, 2.0F, 22, 6, 22, 0.0F);
        this.setRotateAngle(hat2, -0.17453292519943295F, 0.0F, 0.0F);

        this.hat3 = new ModelRenderer(this, 0, 165);
        this.hat3.setRotationPoint(-9.0F, -2.0F, -9.0F);
        this.hat3.addBox(0.0F, 0.0F, 2.5F, 18, 5, 18, 0.0F);
        this.setRotateAngle(hat3, -0.17453292519943295F, 0.0F, 0.0F);

        this.hat4 = new ModelRenderer(this, 0, 190);
        this.hat4.setRotationPoint(-5.0F, -4.0F, 0.0F);
        this.hat4.addBox(0.0F, 0.0F, -3.0F, 10, 4, 20, 0.0F);
        this.setRotateAngle(hat4, -0.17453292519943295F, 0.0F, 0.0F);
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
        this.hat1.render(f5);
        this.hat2.render(f5);
        this.hat3.render(f5);
        this.hat4.render(f5);

        GlStateManager.popMatrix();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
