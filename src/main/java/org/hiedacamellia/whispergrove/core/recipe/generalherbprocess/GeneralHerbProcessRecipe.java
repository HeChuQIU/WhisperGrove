package org.hiedacamellia.whispergrove.core.recipe.generalherbprocess;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.hiedacamellia.whispergrove.registers.WGRicipe;
import org.hiedacamellia.whispergrove.registers.WGRicipeSerializer;

import java.util.List;

public class GeneralHerbProcessRecipe implements Recipe<GeneralHerbProcessInput> {
    // An in-code representation of our recipe data. This can be basically anything you want.
    // Common things to have here is a processing time integer of some kind, or an experience reward.
    // Note that we now use an ingredient instead of an item stack for the input.
    private final BlockState inputState;
    private final List<Ingredient> inputItems;
    private final int processtime;
    private final ItemStack result;

    // Add a constructor that sets all properties.
    public GeneralHerbProcessRecipe(BlockState inputState, List<Ingredient> inputItems, int processtime, ItemStack result) {
        this.inputState = inputState;
        this.inputItems = inputItems;
        this.processtime = processtime;
        this.result = result;
    }

    // A list of our ingredients. Does not need to be overridden if you have no ingredients
    // (the default implementation returns an empty list here). It makes sense to cache larger lists in a field.
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.addAll(this.inputItems);
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return WGRicipeSerializer.RIGHT_CLICK_BLOCK.get();
    }

    @Override
    public RecipeType<?> getType() {
        return WGRicipe.RIGHT_CLICK_BLOCK.get();
    }

    // Grid-based recipes should return whether their recipe can fit in the given dimensions.
    // We don't have a grid, so we just return if any item can be placed in there.
    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 1;
    }

    // Check whether the given input matches this recipe. The first parameter matches the generic.
    // We check our blockstate and our item stack, and only return true if both match.
    @Override
    public boolean matches(GeneralHerbProcessInput input, Level level) {
        for (Ingredient ingredient : this.inputItems) {
            if (!ingredient.test(input.stack())) {
                return false;
            }
        }
        return this.inputState == input.state();
    }

    // Return an UNMODIFIABLE version of your result here. The result of this method is mainly intended
    // for the recipe book, and commonly used by JEI and other recipe viewers as well.
    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
    }

    // Return the result of the recipe here, based on the given input. The first parameter matches the generic.
    // IMPORTANT: Always call .copy() if you use an existing result! If you don't, things can and will break,
    // as the result exists once per recipe, but the assembled stack is created each time the recipe is crafted.
    @Override
    public ItemStack assemble(GeneralHerbProcessInput input, HolderLookup.Provider registries) {
        return this.result.copy();
    }


    // This example outlines the most important methods. There is a number of other methods to override.
    // Check the class definition of Recipe to view them all.


    public List<Ingredient> getInputItems() {
        return inputItems;
    }

    public BlockState getInputState() {
        return inputState;
    }

    public ItemStack getResult() {
        return result;
    }

    public int getProcesstime() {
        return processtime;
    }


}