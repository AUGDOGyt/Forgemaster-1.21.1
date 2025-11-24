package aug.forgemaster;

import aug.forgemaster.block.ModBlocks;
import aug.forgemaster.config.ForgemasterConfig;
import aug.forgemaster.config.conditions.CraterResourceCondition;
import aug.forgemaster.effect.ModEffects;
import aug.forgemaster.enchantment.ModEnchantmentEffects;
import aug.forgemaster.entity.ModEntities;
import aug.forgemaster.item.ModItemComponentTypes;
import aug.forgemaster.item.ModItemGroups;
import aug.forgemaster.item.ModItems;
import aug.forgemaster.particle.ModParticles;
import aug.forgemaster.util.ModTags;
import aug.forgemaster.world.gen.ModFeatures;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.block.BlockState;
import net.minecraft.block.SideShapeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Forgemaster implements ModInitializer {
    public static final String MOD_ID = "forgemaster";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ForgemasterConfig CONFIG = ConfigApiJava.registerAndLoadConfig(ForgemasterConfig::new);

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    public static boolean canPlaceFireAt(BlockState state, World world, BlockPos pos) {
        if (world.isOutOfHeightLimit(pos)) {
            return true;
        }

        return (!state.isSolidBlock(world, pos) && (state.getHardness(world, pos) == 0 || state.isReplaceable())) && world.getBlockState(pos.down()).isSideSolid(world, pos.down(), Direction.UP, SideShapeType.FULL);
    }

    @Override
    public void onInitialize() {
        ModItems.register();
        ModBlocks.register();
        ModItemGroups.register();
        ModEffects.register();
        ModEnchantmentEffects.register();
        ModItemComponentTypes.register();
        ModEntities.register();
        ModParticles.register();
        ModFeatures.register();
        CraterResourceCondition.register();

        EnchantmentEvents.ALLOW_ENCHANTING.register((enchantment, target, context) -> {
            if (target.isOf(ModItems.ATTACCA) && enchantment.isIn(ModTags.Enchantments.TEMPERATURE_BASED)) {
                return TriState.FALSE;
            }
            return TriState.DEFAULT;
        });
    }
}