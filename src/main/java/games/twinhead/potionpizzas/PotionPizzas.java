package games.twinhead.potionpizzas;

import games.twinhead.potionpizzas.item.PizzaItem;
import games.twinhead.potionpizzas.recipe.PizzaRecipe;
import games.twinhead.potionpizzas.util.Util;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Arrays;

public class PotionPizzas implements ModInitializer {

    public static String MOD_ID = "potionpizzas";

    public static final Item PIZZA = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "pizza"), new PizzaItem(new Item.Settings().food(new FoodComponent.Builder().hunger(6).saturationModifier(0.6f).build()).maxDamage(4)));
    public static final RecipeSerializer<PizzaRecipe> PIZZA_RECIPE_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MOD_ID, "pizza"), new SpecialRecipeSerializer<PizzaRecipe>(PizzaRecipe::new));

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content ->
                Arrays.asList(Util.POTIONS_LIST).forEach(potion -> content.add(Util.getPizzaWithPotion(potion))));
    }








}