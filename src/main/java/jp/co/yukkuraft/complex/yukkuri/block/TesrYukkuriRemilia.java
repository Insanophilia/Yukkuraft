package jp.co.yukkuraft.complex.yukkuri.block;

import jp.co.yukkuraft.complex.yukkuri.ModelYukkuriRemilia;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * このクラスは「ゆっくりレミリアブロック」のレンダラーを定義します。
 *
 * @author Insanophilia
 *
 */
public class TesrYukkuriRemilia extends TileEntitySpecialRenderer<TileYukkuriRemilia>
{
    // テクスチャ
    private static final ResourceLocation[] TEXTURES = {
            new ResourceLocation("yukkuraft:textures/entities/yukkuri_remilia_normal.png"),
            new ResourceLocation("yukkuraft:textures/entities/yukkuri_remilia_smile.png"),
            new ResourceLocation("yukkuraft:textures/entities/yukkuri_remilia_cry.png"),
            new ResourceLocation("yukkuraft:textures/entities/yukkuri_remilia_angry.png"),
            new ResourceLocation("yukkuraft:textures/entities/yukkuri_remilia_sleep.png")
    };
    // モデル
    public ModelYukkuriRemilia modelYukkuri = new ModelYukkuriRemilia();

    // 描画処理
    @Override
    public void render(TileYukkuriRemilia tileEntity, double relativeX, double relativeY, double relativeZ,
            float partialTicks, int blockDamageProgress, float alpha)
    {
        if (!(tileEntity instanceof TileYukkuriRemilia))
        {
            return;
        }
        // Entity のスケール
        float f5 = 0.0625F;
        // ブロックの中央までの差分
        float offsetX = 0.5f;
        float offsetY = 0.0f;
        float offsetZ = 0.5f;
        // テクスチャ設定
        this.bindTexture(TEXTURES[tileEntity.getFace()]);
        try
        {
            GlStateManager.pushMatrix();
            // ブロックの中心に移動
            GlStateManager.translate(relativeX + offsetX, relativeY + offsetY, relativeZ + offsetZ);
            // 何故かモデルが上下反転している為、反転する。
            GlStateManager.rotate((float) 180, 1, 0, 0);
            // 回転値を反映する。
            GlStateManager.rotate((float) ((tileEntity.getRotation() * 360) / 16.0F) + 180.0F, 0, 1, 0);
            if (tileEntity.isChild())
            {
                GlStateManager.scale(0.25F, 0.25F, 0.25F);
                GlStateManager.translate(0.0F, -24.0F * f5, 0.0F);
            } else
            {
                GlStateManager.scale(0.5F, 0.5F, 0.5F);
                GlStateManager.translate(0.0F, -24.0F * f5, 0.0F);
            }
            modelYukkuri.head.render(f5);
            modelYukkuri.hair.render(f5);
            modelYukkuri.face.render(f5);
            modelYukkuri.hat1.render(f5);
            modelYukkuri.hat2.render(f5);
            modelYukkuri.hat3.render(f5);
            modelYukkuri.wing.render(f5);

        } finally
        {
            GlStateManager.popMatrix();
        }
    }

    // バウンディングボックスによる描画の制限が無い場合 true
    @Override
    public boolean isGlobalRenderer(TileYukkuriRemilia tileYukkuriRemilia)
    {
        return false;
    }
}
