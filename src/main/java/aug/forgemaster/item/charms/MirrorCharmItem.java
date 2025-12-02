package aug.forgemaster.item.charms;

public class MirrorCharmItem {
    public AttaccaItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean slotted) {
        if (world.isClient && slotted) {
            if (activateCharm.wasPressed) {
                entity.addStatusEffect(new StatusEffectInstance(ModEffects.MIRROR, 300);
            }
        }
    }

}