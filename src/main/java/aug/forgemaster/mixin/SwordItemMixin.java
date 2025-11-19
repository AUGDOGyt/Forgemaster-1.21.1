package aug.forgemaster.mixin;

import aug.forgemaster.enchantment.ModEnchantmentEffects;
import aug.forgemaster.entity.GreekFireballEntity;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SwordItem.class)
public abstract class SwordItemMixin extends Item {
    public SwordItemMixin(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        MutableFloat strength = new MutableFloat(0);

        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : stack.getEnchantments().getEnchantmentEntries()) {
            entry.getKey().value().modifyValue(ModEnchantmentEffects.FIRE_ENGINE_STRENGTH, Random.create(), entry.getIntValue(), strength);
        }

        if (strength.floatValue() > 0 && !world.isClient) {
            Vec3d pos = user.getEyePos().add(user.getRotationVector());
            GreekFireballEntity fireball = new GreekFireballEntity(pos.x, pos.y, pos.z, user.getRotationVector(), world);
            world.spawnEntity(fireball);
            return TypedActionResult.success(stack);
        }

        return super.use(world, user, hand);
    }
}
