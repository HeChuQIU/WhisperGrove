package org.hiedacamellia.whispergrove.content.viscera;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import org.hiedacamellia.whispergrove.WhisperGrove;
import org.hiedacamellia.whispergrove.core.config.CommonConfig;
import org.hiedacamellia.whispergrove.core.codec.record.Lung;
import org.hiedacamellia.whispergrove.registers.WGAttachment;
import org.hiedacamellia.whispergrove.registers.WGEffect;

@EventBusSubscriber(modid = WhisperGrove.MODID)
public class LungEvent {

    public static void Check(Player player) {
        if (!player.hasData(WGAttachment.LUNG)) {
            return;
        }

        Lung lung = player.getData(WGAttachment.LUNG);
        double diff = lung.yang() / lung.yin();

        player.removeEffect(WGEffect.LUNG_HYPERACTIVITY);
        player.removeEffect(WGEffect.LUNG_DETERIORATED);

        if (diff >= CommonConfig.DISEASE_MODERATE.get()) {
            player.addEffect(new MobEffectInstance(WGEffect.LUNG_HYPERACTIVITY, 300 , 1));
            return;
        }
        if (diff >= CommonConfig.DISEASE_MILD.get()) {
            player.addEffect(new MobEffectInstance(WGEffect.LUNG_HYPERACTIVITY, 300 , 0));
            return;
        }
        if (diff <= 1/ CommonConfig.DISEASE_MODERATE.get()) {
            player.addEffect(new MobEffectInstance(WGEffect.LUNG_DETERIORATED, 300 , 1));
            return;
        }
        if (diff <= 1/ CommonConfig.DISEASE_MILD.get()) {
            player.addEffect(new MobEffectInstance(WGEffect.LUNG_DETERIORATED, 300 , 0));
            return;
        }
    }

    @SubscribeEvent
    public static void onPlayerHeal(LivingHealEvent event) {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Player player) {
            Lung lung = player.getData(WGAttachment.LUNG);
            event.setAmount((float) (event.getAmount() * (lung.yin() + lung.yang()) / 2000));
        }
    }

    public String getdesc(Player player) {
        if (!player.hasData(WGAttachment.LUNG)) {
            return "";
        }
        Lung lung = player.getData(WGAttachment.LUNG);
        double diff = lung.yang() - lung.yin();
        if (diff >= 60.0) {

            return Component.translatable("desc.whispergrove.lung.hyperactivity.level.2").getString();
        }
        if (diff >= 30.0) {

            return Component.translatable("desc.whispergrove.lung.hyperactivity.level.1").getString();
        }
        if (diff <= -60.0) {

            return Component.translatable("desc.whispergrove.lung.deteriorated.level.2").getString();
        }
        if (diff <= -30.0) {

            return Component.translatable("desc.whispergrove.lung.deteriorated.level.1").getString();
        }

        return Component.translatable("desc.whispergrove.lung.normal").getString();
    }
}
