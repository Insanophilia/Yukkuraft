package jp.co.yukkuraft.complex.yukkuri;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * このクラスは「ゆっくりレミリア」のモデルを定義します。
 *
 * @author Insanophilia
 *
 */
@SideOnly(Side.CLIENT)
public class ModelYukkuriRemilia extends ModelBase
{
    public ModelRenderer wing;
    public ModelRenderer hair;
    public ModelRenderer face;
    public ModelRenderer head;
    public ModelRenderer hat1;
    public ModelRenderer hat2;
    public ModelRenderer hat3;
    public ModelRenderer wingR;
    public ModelRenderer wingL;

    public ModelYukkuriRemilia()
    {
        this.textureWidth = 256;
        this.textureHeight = 256;

        this.hat1 = new ModelRenderer(this, 0, 97);
        this.hat1.setRotationPoint(-11.0F, 9.0F, -11.0F);
        this.hat1.addBox(0.0F, 0.0F, 0.0F, 22, 2, 22, 0.0F);
        this.wingR = new ModelRenderer(this, 0, 189);
        this.wingR.setRotationPoint(10.0F, 12.0F, -1.0F);
        this.wingR.addBox(0.0F, 0.0F, 0.0F, 0, 1, 1, 0.0F);
        this.head = new ModelRenderer(this, 0, 65);
        this.head.setRotationPoint(-8.0F, 9.0F, -8.0F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 16, 15, 16, 0.0F);
        this.wing = new ModelRenderer(this, 0, 169);
        this.wing.setRotationPoint(-18.0F, 4.0F, 11.0F);
        this.wing.addBox(0.0F, 0.0F, 0.0F, 36, 20, 0, 0.0F);
        this.hair = new ModelRenderer(this, 0, 31);
        this.hair.setRotationPoint(-10.0F, 10.0F, -10.0F);
        this.hair.addBox(0.0F, 0.0F, 0.0F, 20, 13, 20, 0.0F);
        this.hat3 = new ModelRenderer(this, 0, 148);
        this.hat3.setRotationPoint(-9.0F, 3.0F, -9.0F);
        this.hat3.addBox(0.0F, 0.0F, 0.0F, 18, 2, 18, 0.0F);
        this.hat2 = new ModelRenderer(this, 0, 122);
        this.hat2.setRotationPoint(-10.0F, 4.0F, -10.0F);
        this.hat2.addBox(0.0F, 0.0F, 0.0F, 20, 5, 20, 0.0F);
        this.wingL = new ModelRenderer(this, 0, 189);
        this.wingL.setRotationPoint(26.0F, 12.0F, -1.0F);
        this.wingL.addBox(0.0F, 0.0F, 0.0F, 0, 1, 1, 0.0F);
        this.face = new ModelRenderer(this, 0, 0);
        this.face.setRotationPoint(-9.0F, 11.0F, -9.0F);
        this.face.addBox(0.0F, 0.0F, 0.0F, 18, 12, 18, 0.0F);
        this.wing.addChild(this.wingR);
        this.wing.addChild(this.wingL);
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
        this.hair.render(f5);
        this.wing.render(f5);
        this.face.render(f5);
        this.hat2.render(f5);
        this.hat3.render(f5);
        this.hat1.render(f5);
        this.head.render(f5);

        GlStateManager.popMatrix();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
