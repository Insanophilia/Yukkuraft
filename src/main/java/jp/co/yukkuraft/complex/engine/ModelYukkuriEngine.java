package jp.co.yukkuraft.complex.engine;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelYukkuriEngine extends ModelBase
{
    public ModelRenderer base;
    public ModelRenderer side_bar_1;
    public ModelRenderer shaft1;
    public ModelRenderer side_bar_2;
    public ModelRenderer shaft2;
    public ModelRenderer pane1;
    public ModelRenderer pane2;
    public ModelRenderer pane3;
    public ModelRenderer pane4;
    public ModelRenderer pane5;
    public ModelRenderer pane6;
    public ModelRenderer pane7;
    public ModelRenderer pane8;
    public ModelRenderer pane9;
    public ModelRenderer pane10;
    public ModelRenderer pane11;
    public ModelRenderer pane12;
    public ModelRenderer gear1;
    public ModelRenderer gear2;
    public ModelRenderer side1_1;
    public ModelRenderer side1_2;
    public ModelRenderer side2_1;
    public ModelRenderer side2_2;
    public ModelRenderer side3_1;
    public ModelRenderer side3_2;
    public ModelRenderer side4_1;
    public ModelRenderer side4_2;

    public ModelYukkuriEngine()
    {
        this.textureWidth = 288;
        this.textureHeight = 288;
        this.pane5 = new ModelRenderer(this, 0, 0);
        this.pane5.setRotationPoint(0.0F, 0.0F, 10.5F);
        this.pane5.addBox(-6.0F, -20.0F, 0.0F, 12, 1, 28, 0.0F);
        this.setRotateAngle(pane5, 0.0F, 0.0F, 2.0943951023931953F);
        this.side_bar_1 = new ModelRenderer(this, 0, 160);
        this.side_bar_1.setRotationPoint(21.0F, -47.0F, 4.0F);
        this.side_bar_1.addBox(0.0F, 0.0F, 0.0F, 4, 47, 1, 0.0F);
        this.side1_1 = new ModelRenderer(this, 0, 0);
        this.side1_1.setRotationPoint(0.0F, -0.0F, 12.0F);
        this.side1_1.addBox(-1.0F, -19.0F, 0.0F, 2, 38, 1, 0.0F);
        this.side1_2 = new ModelRenderer(this, 0, 0);
        this.side1_2.setRotationPoint(0.0F, 0.0F, 12.0F);
        this.side1_2.addBox(-1.0F, -19.0F, 0.0F, 2, 38, 1, 0.0F);
        this.setRotateAngle(side1_2, 0.0F, 0.0F, 1.5707963267948966F);
        this.pane12 = new ModelRenderer(this, 0, 0);
        this.pane12.setRotationPoint(0.0F, 0.0F, 9.5F);
        this.pane12.addBox(-6.0F, -20.0F, 0.0F, 12, 1, 28, 0.0F);
        this.setRotateAngle(pane12, 0.0F, 0.0F, -2.6179938779914944F);
        this.base = new ModelRenderer(this, 0, 160);
        this.base.setRotationPoint(-24.0F, -1.0F, -24.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 48, 1, 48, 0.0F);
        this.pane10 = new ModelRenderer(this, 0, 0);
        this.pane10.setRotationPoint(0.0F, 0.0F, 9.5F);
        this.pane10.addBox(-6.0F, -20.0F, 0.0F, 12, 1, 28, 0.0F);
        this.setRotateAngle(pane10, 0.0F, 0.0F, -1.5707963267948966F);
        this.side4_1 = new ModelRenderer(this, 0, 0);
        this.side4_1.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.side4_1.addBox(-1.0F, -20.0F, 0.0F, 2, 40, 1, 0.0F);
        this.setRotateAngle(side4_1, 0.0F, 0.0F, 0.7853981633974483F);
        this.pane9 = new ModelRenderer(this, 0, 0);
        this.pane9.setRotationPoint(0.0F, 0.0F, 10.5F);
        this.pane9.addBox(-6.0F, -20.0F, 0.0F, 12, 1, 28, 0.0F);
        this.setRotateAngle(pane9, 0.0F, 0.0F, -1.0471975511965976F);
        this.side2_1 = new ModelRenderer(this, 0, 0);
        this.side2_1.setRotationPoint(0.0F, 0.0F, 13.0F);
        this.side2_1.addBox(-1.0F, -20.0F, 0.0F, 2, 40, 1, 0.0F);
        this.setRotateAngle(side2_1, 0.0F, 0.0F, 0.7853981633974483F);
        this.gear1 = new ModelRenderer(this, 0, 160);
        this.gear1.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.gear1.addBox(-1.0F, -2.0F, 0.0F, 2, 4, 1, 0.0F);
        this.setRotateAngle(gear1, 0.0F, 0.0F, 1.5707963267948966F);
        this.side2_2 = new ModelRenderer(this, 0, 0);
        this.side2_2.setRotationPoint(0.0F, 0.0F, 13.0F);
        this.side2_2.addBox(-1.0F, -20.0F, 0.0F, 2, 40, 1, 0.0F);
        this.setRotateAngle(side2_2, 0.0F, 0.0F, 2.356194490192345F);
        this.side3_2 = new ModelRenderer(this, 0, 0);
        this.side3_2.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.side3_2.addBox(-1.0F, -19.0F, 0.0F, 2, 38, 1, 0.0F);
        this.setRotateAngle(side3_2, 0.0F, 0.0F, 1.5707963267948966F);
        this.side_bar_2 = new ModelRenderer(this, 0, 160);
        this.side_bar_2.setRotationPoint(21.0F, -47.0F, 43.0F);
        this.side_bar_2.addBox(0.0F, 0.0F, 0.0F, 4, 47, 1, 0.0F);
        this.pane1 = new ModelRenderer(this, 0, 0);
        this.pane1.setRotationPoint(0.0F, 0.0F, 10.5F);
        this.pane1.addBox(-6.0F, -20.0F, 0.0F, 12, 1, 28, 0.0F);
        this.pane3 = new ModelRenderer(this, 0, 0);
        this.pane3.setRotationPoint(0.0F, 0.0F, 10.5F);
        this.pane3.addBox(-6.0F, -20.0F, 0.0F, 12, 1, 28, 0.0F);
        this.setRotateAngle(pane3, 0.0F, 0.0F, 1.0471975511965976F);
        this.shaft1 = new ModelRenderer(this, 0, 0);
        this.shaft1.setRotationPoint(-1.0F, -25.0F, -24.0F);
        this.shaft1.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 16, 0.0F);
        this.gear2 = new ModelRenderer(this, 0, 160);
        this.gear2.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.gear2.addBox(-1.0F, -2.0F, 0.0F, 2, 4, 1, 0.0F);
        this.pane7 = new ModelRenderer(this, 0, 0);
        this.pane7.setRotationPoint(0.0F, 0.0F, 10.5F);
        this.pane7.addBox(-6.0F, -20.0F, 0.0F, 12, 1, 28, 0.0F);
        this.setRotateAngle(pane7, 0.0F, 0.0F, 3.141592653589793F);
        this.side4_2 = new ModelRenderer(this, 0, 0);
        this.side4_2.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.side4_2.addBox(-1.0F, -20.0F, 0.0F, 2, 40, 1, 0.0F);
        this.setRotateAngle(side4_2, 0.0F, 0.0F, 2.356194490192345F);
        this.pane2 = new ModelRenderer(this, 0, 0);
        this.pane2.setRotationPoint(0.0F, 0.0F, 9.5F);
        this.pane2.addBox(-6.0F, -20.0F, 0.0F, 12, 1, 28, 0.0F);
        this.setRotateAngle(pane2, 0.0F, 0.0F, 0.5235987755982988F);
        this.pane4 = new ModelRenderer(this, 0, 0);
        this.pane4.setRotationPoint(0.0F, 0.0F, 9.5F);
        this.pane4.addBox(-6.0F, -20.0F, 0.0F, 12, 1, 28, 0.0F);
        this.setRotateAngle(pane4, 0.0F, 0.0F, 1.5707963267948966F);
        this.shaft2 = new ModelRenderer(this, 0, 0);
        this.shaft2.setRotationPoint(-1.0F, -25.0F, 8.0F);
        this.shaft2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 16, 0.0F);
        this.pane8 = new ModelRenderer(this, 0, 0);
        this.pane8.setRotationPoint(0.0F, 0.0F, 9.5F);
        this.pane8.addBox(-6.0F, -20.0F, 0.0F, 12, 1, 28, 0.0F);
        this.setRotateAngle(pane8, 0.0F, 0.0F, -0.5235987755982988F);
        this.side3_1 = new ModelRenderer(this, 0, 0);
        this.side3_1.setRotationPoint(0.0F, -0.0F, 3.0F);
        this.side3_1.addBox(-1.0F, -19.0F, 0.0F, 2, 38, 1, 0.0F);
        this.pane6 = new ModelRenderer(this, 0, 0);
        this.pane6.setRotationPoint(0.0F, 0.0F, 9.5F);
        this.pane6.addBox(-6.0F, -20.0F, 0.0F, 12, 1, 28, 0.0F);
        this.setRotateAngle(pane6, 0.0F, 0.0F, 2.6179938779914944F);
        this.pane11 = new ModelRenderer(this, 0, 0);
        this.pane11.setRotationPoint(0.0F, 0.0F, 10.5F);
        this.pane11.addBox(-6.0F, -20.0F, 0.0F, 12, 1, 28, 0.0F);
        this.setRotateAngle(pane11, 0.0F, 0.0F, -2.0943951023931953F);
        this.shaft1.addChild(this.pane5);
        this.base.addChild(this.side_bar_1);
        this.shaft1.addChild(this.side1_1);
        this.shaft1.addChild(this.side1_2);
        this.shaft1.addChild(this.pane12);
        this.shaft1.addChild(this.pane10);
        this.shaft2.addChild(this.side4_1);
        this.shaft1.addChild(this.pane9);
        this.shaft1.addChild(this.side2_1);
        this.shaft1.addChild(this.gear1);
        this.shaft1.addChild(this.side2_2);
        this.shaft2.addChild(this.side3_2);
        this.base.addChild(this.side_bar_2);
        this.shaft1.addChild(this.pane1);
        this.shaft1.addChild(this.pane3);
        this.shaft1.addChild(this.gear2);
        this.shaft1.addChild(this.pane7);
        this.shaft2.addChild(this.side4_2);
        this.shaft1.addChild(this.pane2);
        this.shaft1.addChild(this.pane4);
        this.shaft1.addChild(this.pane8);
        this.shaft2.addChild(this.side3_1);
        this.shaft1.addChild(this.pane6);
        this.shaft1.addChild(this.pane11);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        //        this.shaft1.render(f5);
        //        this.side_bar_2.render(f5);
        //        this.shaft2.render(f5);
        this.base.render(f5);
        //        this.side_bar_1.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
