package jp.co.yukkuraft.tileentity.engine;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * このクラスは「ゆっくりエンジンブロック」のレンダラーを定義します。
 *
 * @author Insanophilia
 *
 */
public class TesrYukkuriEngine extends TileEntitySpecialRenderer<TileYukkuriEngine>
{
    // テクスチャ
    private static final ResourceLocation[] TEXTURES = {
            new ResourceLocation("yukkuraft:textures/entities/yukkuri_engine.png"),
            new ResourceLocation("yukkuraft:textures/entities/yukkuri_reimu_cry.png")
    };

    // モデル
    public ModelYukkuriEngine model = new ModelYukkuriEngine();

    // 描画処理
    @Override
    public void render(TileYukkuriEngine tileEntity, double relativeX, double relativeY, double relativeZ,
            float partialTicks, int blockDamageProgress, float alpha)
    {
        if (!(tileEntity instanceof TileYukkuriEngine))
        {
            return;
        }
        // Entity のスケール
        float f5 = 0.0625F;
        // ベース部分の描画
        // テクスチャ設定
        this.bindTexture(TEXTURES[0]);
        try
        {
            GlStateManager.pushMatrix();
            // ブロックの中心に移動
            float offsetX = 0.5f;
            float offsetY = 0.0f;
            float offsetZ = 0.5f;
            GlStateManager.translate(relativeX + offsetX, relativeY + offsetY, relativeZ + offsetZ);
            // 何故かモデルが上下反転している為、反転する。
            GlStateManager.rotate((float) 180, 1, 0, 0);
            // 回転値を反映する。
            GlStateManager.rotate((float) ((tileEntity.rotation * 360) / 4.0F) + 180.0F, 0, 1, 0);
            model.base.render(f5);
            int x = 100;
            tileEntity.engineRotation = (++tileEntity.engineRotation) % (360 * x);
            model.shaft1.rotateAngleZ = tileEntity.engineRotation / x;
            model.shaft2.rotateAngleZ = tileEntity.engineRotation / x;
            model.shaft1.render(f5);
            model.shaft2.render(f5);
        } finally
        {
            GlStateManager.popMatrix();
        }
        // ゆっくり霊夢の描画
        this.bindTexture(TEXTURES[1]);
        try
        {
            GlStateManager.pushMatrix();
            // ゆっくりを動かす。
            tileEntity.yukkuriPosX = (tileEntity.yukkuriPosX + ((float) Math.random() * 2.0f)) % 360;
            tileEntity.yukkuriPosY = (tileEntity.yukkuriPosY + ((float) Math.random() * 15.0f)) % 360;
            // ブロックの中心に移動
            float offsetX = 0.5f;
            float offsetY = 1.7f;
            float offsetZ = 0.5f;
            double x = relativeX + offsetX + Math.sin(tileEntity.yukkuriPosX * Math.PI / 180 - Math.PI / 2) / 4d;
            double y = relativeY + offsetY + Math.sin(tileEntity.yukkuriPosY * Math.PI / 180 - Math.PI / 2) / 2d;
            GlStateManager.translate(x, y, relativeZ + offsetZ);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            // 何故かモデルが上下反転している為、反転する。
            GlStateManager.rotate((float) 180, 1, 0, 0);
            GlStateManager.rotate((float) -90, 0, 1, 0);
            // 回転値を反映する。
            GlStateManager.rotate((float) ((tileEntity.rotation * 360) / 4.0F) + 180.0F, 0, 1, 0);
            tileEntity.yukkuri.render(f5);
        } finally
        {
            GlStateManager.popMatrix();
        }
    }

    // バウンディングボックスによる描画の制限が無い場合 true
    @Override
    public boolean isGlobalRenderer(TileYukkuriEngine tileEntity)
    {
        return false;
    }
}
