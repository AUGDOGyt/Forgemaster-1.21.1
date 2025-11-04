public class AttaccaSword extends Item {

    public TypedActionResult<ItemStack> inventoryTick(PlayerEntity user, Hand hand) {

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    public TypedActionResult<ItemStack> postHit(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 10);

        

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    public AttaccaSword(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> onCraft(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 10);

        LightningEntity lightningBolt new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightningBolt.setPosition(frontOfPlayer.toCenterPos());
        world.spawnEntity(lightningBolt);

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    public AttaccaSword(Settings settings) {
        super(settings);
    }
}