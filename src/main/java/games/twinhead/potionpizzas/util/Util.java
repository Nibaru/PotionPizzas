package games.twinhead.potionpizzas.util;

import games.twinhead.potionpizzas.PotionPizzas;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;

public class Util {

    public static final Potion[] POTIONS_LIST = {
            Potions.EMPTY,
            Potions.WATER,
            Potions.MUNDANE,
            Potions.THICK,
            Potions.AWKWARD,
            Potions.NIGHT_VISION,
            Potions.LONG_NIGHT_VISION,
            Potions.INVISIBILITY,
            Potions.LONG_INVISIBILITY,
            Potions.LEAPING,
            Potions.LONG_LEAPING,
            Potions.STRONG_LEAPING,
            Potions.FIRE_RESISTANCE,
            Potions.LONG_FIRE_RESISTANCE,
            Potions.SWIFTNESS,
            Potions.LONG_SWIFTNESS,
            Potions.STRONG_SWIFTNESS,
            Potions.SLOWNESS,
            Potions.LONG_SLOWNESS,
            Potions.STRONG_SLOWNESS,
            Potions.TURTLE_MASTER,
            Potions.LONG_TURTLE_MASTER,
            Potions.STRONG_TURTLE_MASTER,
            Potions.WATER_BREATHING,
            Potions.LONG_WATER_BREATHING,
            Potions.HEALING,
            Potions.STRONG_HEALING,
            Potions.HARMING,
            Potions.STRONG_HARMING,
            Potions.POISON,
            Potions.LONG_POISON,
            Potions.STRONG_POISON,
            Potions.REGENERATION,
            Potions.LONG_REGENERATION,
            Potions.STRONG_REGENERATION,
            Potions.STRENGTH,
            Potions.LONG_STRENGTH,
            Potions.STRONG_STRENGTH,
            Potions.WEAKNESS,
            Potions.LONG_WEAKNESS,
            Potions.LUCK,
            Potions.SLOW_FALLING,
            Potions.LONG_SLOW_FALLING
    };

    public static ItemStack getPizzaWithPotion(Potion... effects) {
        ItemStack stack = new ItemStack(PotionPizzas.PIZZA);
        for (Potion effect : effects) {
            PotionUtil.setPotion(stack, effect);
        }
        PotionUtil.setCustomPotionEffects(stack, PotionUtil.getCustomPotionEffects(stack));
        return stack;
    }
}
