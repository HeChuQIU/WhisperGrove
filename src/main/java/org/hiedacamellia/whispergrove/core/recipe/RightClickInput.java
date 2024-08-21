package org.hiedacamellia.whispergrove.core.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.block.state.BlockState;

public record RightClickInput(BlockState state, ItemStack stack) implements RecipeInput {

    @Override
    public ItemStack getItem(int slot) {
        return this.stack();
    }

    @Override
    public int size() {
        return 1;
    }

}