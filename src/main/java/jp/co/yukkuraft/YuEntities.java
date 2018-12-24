package jp.co.yukkuraft;

import jp.co.yukkuraft.entity.EntityYukkuriMarisa;
import jp.co.yukkuraft.entity.EntityYukkuriReimu;
import jp.co.yukkuraft.entity.RenderYukkuriMarisa;
import jp.co.yukkuraft.entity.RenderYukkuriReimu;
import jp.co.yukkuraft.entity.model.ModelYukkuriMarisa;
import jp.co.yukkuraft.entity.model.ModelYukkuriReimu;
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
        int entityId = 1;

        EntityRegistry.registerModEntity(new ResourceLocation("yukkuri_reimu"),
                EntityYukkuriReimu.class, "yukkuri_reimu", entityId++, ModCore.instance, 50, 1, true, 16711680, 16777215);

        EntityRegistry.registerModEntity(new ResourceLocation("yukkuri_marisa"),
                EntityYukkuriMarisa.class, "yukkuri_marisa", entityId++, ModCore.instance, 50, 1, true, 0, 16777215);
    }

    public static void registerEntityRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityYukkuriReimu.class, new IRenderFactory<EntityYukkuriReimu>()
        {
            @Override
            public Render<? super EntityYukkuriReimu> createRenderFor(RenderManager manager)
            {
                return new RenderYukkuriReimu(manager, new ModelYukkuriReimu(), 0.3f);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityYukkuriMarisa.class, new IRenderFactory<EntityYukkuriMarisa>()
        {
            @Override
            public Render<? super EntityYukkuriMarisa> createRenderFor(RenderManager manager)
            {
                return new RenderYukkuriMarisa(manager, new ModelYukkuriMarisa(), 0.3f);
            }
        });
    }
}
