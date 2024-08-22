package org.hiedacamellia.whispergrove;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = WhisperGrove.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue DEBUG = BUILDER
            .comment("Set to true to enable debug info")
            .comment("设置为true以启用调试信息")
            .define("debug", true);

    public static final ModConfigSpec.DoubleValue DISEASE_MILD = BUILDER
            .comment("Get the ratio of yin and yang points of mild disease")
            .comment("获得轻度疾病的阴阳点数比例")
            .defineInRange("disease_mild", 1.3,1.0,Double.MAX_VALUE);

    public static final ModConfigSpec.DoubleValue DISEASE_MODERATE = BUILDER
            .comment("Get the ratio of yin and yang points of moderate disease")
            .comment("获得中度疾病的阴阳点数比例")
            .defineInRange("disease_moderate", 1.6,1.0,Double.MAX_VALUE);

    public static final ModConfigSpec.DoubleValue DISEASE_CRITICAL = BUILDER
            .comment("Get the ratio of yin and yang points of Critical disease")
            .comment("获得重度疾病的阴阳点数比例")
            .defineInRange("disease_Critical", 2.0,1.0,Double.MAX_VALUE);

    public static final ModConfigSpec.DoubleValue VISCERA_CONSTANT = BUILDER
            .comment("The multiply of changed points of viscera")
            .comment("脏腑变化点数的倍数")
            .defineInRange("viscera_constant", 1.0,0,Double.MAX_VALUE);

    public static final ModConfigSpec.BooleanValue ECLIPTIC_COMPACT = BUILDER
            .comment("Set to true to enable the compact of the solar term")
            .comment("设置为true以启用节气的联动")
            .define("ecliptic_compact", true);

    static final ModConfigSpec SPEC = BUILDER.build();

}