package games.twinhead.potionpizzas.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class PizzaItem extends Item {

    public PizzaItem(Settings settings) {
        super(settings);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        itemStack.setDamage(itemStack.getDamage() + 1);

        PotionUtil.getPotionEffects(itemStack).forEach(effect -> {
            float duration = effect.getDuration() * 0.25f;

            if(user.hasStatusEffect(effect.getEffectType()))
                duration += Objects.requireNonNull(user.getStatusEffect(effect.getEffectType())).getDuration();

            user.addStatusEffect(new StatusEffectInstance(effect.getEffectType(), (int) duration, effect.getAmplifier(), effect.isAmbient(), effect.shouldShowParticles(), effect.shouldShowIcon()));
        });

        if (itemStack.getDamage() >= itemStack.getMaxDamage()){
            user.eatFood(world, itemStack);
            return ItemStack.EMPTY;
        }

        return user.eatFood(world, itemStack);
    }

    @Override
    public ItemStack getDefaultStack() {
        return PotionUtil.setPotion(super.getDefaultStack(), Potions.POISON);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        PotionUtil.buildTooltip(stack, tooltip, 0.25f);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return PotionUtil.getPotion(stack).finishTranslationKey(this.getTranslationKey() + ".effect.");
    }

}
