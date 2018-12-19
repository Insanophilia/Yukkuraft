package jp.co.yukkuraft.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
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
    //    public ModelRenderer head;
    //    public ModelRenderer option;
    //    public ModelRenderer hair;
    //    public ModelRenderer face;

    public ModelRenderer hair;
    public ModelRenderer face;
    public ModelRenderer top;
    public ModelRenderer shape4;
    public ModelRenderer shape5;
    public ModelRenderer shape6;
    public ModelRenderer shape7;

    public ModelYukkuriMarisa()
    {

        // 縦軸の高さを補正する。
        float offsetY = 120.0F + 5F;

        this.textureWidth = 128;
        this.textureHeight = 256;
        this.shape5 = new ModelRenderer(this, 0, 135);
        this.shape5.setRotationPoint(-11.0F, 1.0F, -11.0F);
        this.shape5.addBox(0.0F, 0.6F, 2.0F, 22, 6, 22, 0.0F);
        this.setRotateAngle(shape5, -0.17453292519943295F, 0.0F, 0.0F);
        this.top = new ModelRenderer(this, 0, 67);
        this.top.setRotationPoint(-8.0F, 9.0F, -8.0F);
        this.top.addBox(0.0F, 0.0F, 0.0F, 16, 15, 16, 0.0F);
        this.face = new ModelRenderer(this, 0, 0);
        this.face.setRotationPoint(-9.0F, 11.0F, -9.0F);
        this.face.addBox(0.0F, 0.0F, 0.0F, 18, 12, 18, 0.0F);
        this.shape7 = new ModelRenderer(this, 0, 190);
        this.shape7.setRotationPoint(-5.0F, -4.0F, 0.0F);
        this.shape7.addBox(0.0F, 0.0F, -3.0F, 10, 4, 20, 0.0F);
        this.setRotateAngle(shape7, -0.17453292519943295F, 0.0F, 0.0F);
        this.hair = new ModelRenderer(this, 0, 32);
        this.hair.setRotationPoint(-10.0F, 10.0F, -10.0F);
        this.hair.addBox(0.0F, 0.0F, 0.0F, 20, 13, 20, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 165);
        this.shape6.setRotationPoint(-9.0F, -2.0F, -9.0F);
        this.shape6.addBox(0.0F, 0.0F, 2.5F, 18, 5, 18, 0.0F);
        this.setRotateAngle(shape6, -0.17453292519943295F, 0.0F, 0.0F);
        this.shape4 = new ModelRenderer(this, 0, 101);
        this.shape4.setRotationPoint(-14.0F, 6.0F, -14.0F);
        this.shape4.addBox(0.0F, 0.0F, 1.0F, 28, 4, 28, 0.0F);
        this.setRotateAngle(shape4, -0.17453292519943295F, 0.0F, 0.0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.shape5.render(f5);
        this.top.render(f5);
        this.face.render(f5);
        this.shape7.render(f5);
        this.hair.render(f5);
        this.shape6.render(f5);
        this.shape4.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
