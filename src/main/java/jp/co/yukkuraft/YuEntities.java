package jp.co.yukkuraft;

import jp.co.yukkuraft.complex.flamethrower.EntityFlame;
import jp.co.yukkuraft.complex.flamethrower.RenderFlame;
import jp.co.yukkuraft.complex.yukkuri.ModelYukkuriMarisa;
import jp.co.yukkuraft.complex.yukkuri.ModelYukkuriReimu;
import jp.co.yukkuraft.complex.yukkuri.ModelYukkuriRemilia;
import jp.co.yukkuraft.complex.yukkuri.entity.EntityYukkuriMarisa;
import jp.co.yukkuraft.complex.yukkuri.entity.EntityYukkuriReimu;
import jp.co.yukkuraft.complex.yukkuri.entity.EntityYukkuriRemilia;
import jp.co.yukkuraft.complex.yukkuri.entity.RenderYukkuriMarisa;
import jp.co.yukkuraft.complex.yukkuri.entity.RenderYukkuriReimu;
import jp.co.yukkuraft.complex.yukkuri.entity.RenderYukkuriRemilia;
import jp.co.yukkuraft.constant.YuColor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
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
    // ゆっくりが自然スポーンするバイオーム
    public static final Biome[] YUKKURI_PLACE = {
            Biomes.PLAINS,
            Biomes.FOREST,
            Biomes.BIRCH_FOREST,
            Biomes.MUTATED_PLAINS,
            Biomes.MUTATED_FOREST,
            Biomes.MUTATED_BIRCH_FOREST
    };

    public static void registerEntities()
    {
        // ゆっくりの登録
        registerEntitie("yukkuri_marisa", EntityYukkuriMarisa.class, 50, 1, true, YuColor.MARISA_BLACK,
                YuColor.MARISA_WHITE);
        registerEntitie("yukkuri_reimu", EntityYukkuriReimu.class, 50, 1, true, YuColor.REIMU_RED,
                YuColor.REIMU_WHITE);
        registerEntitie("yukkuri_remilia", EntityYukkuriRemilia.class, 50, 1, true, YuColor.REMILIA_CRIMSON,
                YuColor.REMILIA_SCARLET);

        // ゆっくりの自然スポーン登録
        EntityRegistry.addSpawn(EntityYukkuriMarisa.class, 20, 5, 10, EnumCreatureType.CREATURE, YUKKURI_PLACE);
        EntityRegistry.addSpawn(EntityYukkuriReimu.class, 20, 5, 10, EnumCreatureType.CREATURE, YUKKURI_PLACE);
        EntityRegistry.addSpawn(EntityYukkuriRemilia.class, 20, 5, 10, EnumCreatureType.CREATURE, YUKKURI_PLACE);

        //
        registerEntitie("flame", EntityFlame.class, 50, 1, true);
    }

    // Entity ID
    private static int entityId = 1;

    // Entity 登録(スポーンエッグ指定)
    private static void registerEntitie(String name, Class<? extends Entity> clazz, int trackingRange,
            int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(name),
                clazz, name, entityId++, ModCore.instance, trackingRange, updateFrequency, sendsVelocityUpdates,
                eggPrimary, eggSecondary);
    }

    // Entity 登録(スポーンエッグ指定なし)
    private static void registerEntitie(String name, Class<? extends Entity> clazz, int trackingRange,
            int updateFrequency, boolean sendsVelocityUpdates)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(name),
                clazz, name, entityId++, ModCore.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    public static void registerEntityRenderers()
    {
        // ゆっくり魔理沙
        RenderingRegistry.registerEntityRenderingHandler(EntityYukkuriMarisa.class,
                new IRenderFactory<EntityYukkuriMarisa>()
                {
                    @Override
                    public Render<? super EntityYukkuriMarisa> createRenderFor(RenderManager manager)
                    {
                        return new RenderYukkuriMarisa(manager, new ModelYukkuriMarisa(), 0.3f);
                    }
                });

        // ゆっくり霊夢
        RenderingRegistry.registerEntityRenderingHandler(EntityYukkuriReimu.class,
                new IRenderFactory<EntityYukkuriReimu>()
                {
                    @Override
                    public Render<? super EntityYukkuriReimu> createRenderFor(RenderManager manager)
                    {
                        return new RenderYukkuriReimu(manager, new ModelYukkuriReimu(), 0.3f);
                    }
                });

        // ゆっくりレミリア
        RenderingRegistry.registerEntityRenderingHandler(EntityYukkuriRemilia.class,
                new IRenderFactory<EntityYukkuriRemilia>()
                {
                    @Override
                    public Render<? super EntityYukkuriRemilia> createRenderFor(RenderManager manager)
                    {
                        return new RenderYukkuriRemilia(manager, new ModelYukkuriRemilia(), 0.3f);
                    }
                });

        // 火炎
        RenderingRegistry.registerEntityRenderingHandler(EntityFlame.class,
                new IRenderFactory<EntityFlame>()
                {
                    @Override
                    public Render<? super EntityFlame> createRenderFor(RenderManager manager)
                    {
                        return new RenderFlame(manager);
                    }
                });
    }
}
