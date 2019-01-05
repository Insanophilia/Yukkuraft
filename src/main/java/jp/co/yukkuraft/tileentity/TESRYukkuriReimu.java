package jp.co.yukkuraft.tileentity;

import jp.co.yukkuraft.entity.model.ModelYukkuriReimu;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * このクラスは「ゆっくり霊夢ブロック」のレンダラーを定義します。
 *
 * @author Insanophilia
 *
 */
public class TESRYukkuriReimu extends TileEntitySpecialRenderer<TileEntityYukkuriReimu>
{
    // テクスチャ
    private static final ResourceLocation[] TEXTURES = {
            new ResourceLocation("yukkuraft:textures/entities/yukkuri_reimu_smile.png"),
            new ResourceLocation("yukkuraft:textures/entities/yukkuri_reimu_cry.png"),
            new ResourceLocation("yukkuraft:textures/entities/yukkuri_reimu_angry.png"),
            new ResourceLocation("yukkuraft:textures/entities/yukkuri_reimu_sleep.png")
    };
    // モデル
    public ModelYukkuriReimu modelYukkuriReimu = new ModelYukkuriReimu();

    // 描画処理
    @Override
    public void render(TileEntityYukkuriReimu tileEntity, double relativeX, double relativeY, double relativeZ,
            float partialTicks, int blockDamageProgress, float alpha)
    {
        if (!(tileEntity instanceof TileEntityYukkuriReimu))
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
            modelYukkuriReimu.face.render(f5);
            modelYukkuriReimu.option.render(f5);
            modelYukkuriReimu.hair.render(f5);
            modelYukkuriReimu.head.render(f5);
        } finally
        {
            GlStateManager.popMatrix();
        }
    }

    // バウンディングボックスによる描画の制限が無い場合 true
    @Override
    public boolean isGlobalRenderer(TileEntityYukkuriReimu tileEntityYukkuriReimu)
    {
        return false;
    }
}
