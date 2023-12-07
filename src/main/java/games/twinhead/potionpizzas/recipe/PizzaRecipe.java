package games.twinhead.potionpizzas.recipe;

import games.twinhead.potionpizzas.PotionPizzas;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class PizzaRecipe extends SpecialCraftingRecipe {

    public PizzaRecipe(Identifier identifier, CraftingRecipeCategory category) {
        super(identifier, category);
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        if (inventory.getWidth() != 3 || inventory.getHeight() != 3) {
            return false;
        }

        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            switch (i) {
                case 1, 3, 5, 7 -> {
                    if (itemStack.isOf(Items.GOLD_INGOT)) continue;
                    return false;
                }
                case 0, 2, 6, 8 -> {
                    if (itemStack.isOf(Items.WHEAT)) continue;
                    return false;
                }
                case 4 -> {
                    if (itemStack.isOf(Items.POTION)) continue;
                    return false;
                }
                default -> {
                    if (itemStack.isOf(Items.AIR)) continue;
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        ItemStack itemStack = inventory.getStack(1 + inventory.getWidth());
        if (!itemStack.isOf(Items.POTION)) {
            return ItemStack.EMPTY;
        }
        ItemStack itemStack2 = new ItemStack(PotionPizzas.PIZZA, 1);

        PotionUtil.setPotion(itemStack2, PotionUtil.getPotion(itemStack));
        PotionUtil.setCustomPotionEffects(itemStack2, PotionUtil.getCustomPotionEffects(itemStack));
        return itemStack2;
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return PotionPizzas.PIZZA_RECIPE_SERIALIZER;
    }
}
