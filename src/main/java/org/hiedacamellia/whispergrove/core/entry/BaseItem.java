package org.hiedacamellia.whispergrove.core.entry;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseItem extends Item {

    private final String regname;

    public BaseItem(Properties properties, String regname) {
        super(properties);
        this.regname = regname;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, TooltipContext ctx, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, ctx, list, flag);
        if (!Screen.hasShiftDown()) {
            list.add(Component.literal(
                    "§7§o" + Component.translatable("tooltip.whispergrove.press_shift").getString() + "§r"));
        } else {
            String[] description = Component.translatable("tooltip.whispergrove."+this.regname).getString().split("§n");
            for (String line : description) {
                list.add(Component.literal(line));
            }
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        super.finishUsingItem(itemstack, world, entity);
        return itemstack;
    }

}


