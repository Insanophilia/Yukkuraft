package jp.co.yukkuraft;

import jp.co.yukkuraft.entity.EntityYukkuri;
import jp.co.yukkuraft.entity.ModelYukkuriReimu;
import jp.co.yukkuraft.entity.RenderYukkuriReimu;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 * このクラスは Mod Entity の登録を行います。
 *
 * @author Insanophilia
 *
 */
public class YuEntities
{
    public static void registerEntities()
    {
        EntityRegistry.registerModEntity(new ResourceLocation("yukkuri_reimu"),
                EntityYukkuri.class, "yukkuri_reimu", 0, ModCore.instance, 50, 1, true, 16711680, 16777215);
    }

    public static void registerEntityRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityYukkuri.class, new IRenderFactory<EntityYukkuri>()
        {
            @Override
            public Render<? super EntityYukkuri> createRenderFor(RenderManager manager)
            {
                return new RenderYukkuriReimu(manager, new ModelYukkuriReimu(), 0.3f);
            }
        });
    }
}
