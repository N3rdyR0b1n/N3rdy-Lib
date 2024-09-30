package n3rdyr0b1n.lib.test;

import n3rdyr0b1n.lib.items.LeftMouseItem;
import n3rdyr0b1n.lib.test.nbt.Ability;
import n3rdyr0b1n.lib.test.nbt.DashAbility;
import n3rdyr0b1n.lib.test.nbt.TeleportAbility;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DebugItem extends Item implements LeftMouseItem, FabricItem {
    public DebugItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (user.isInPose(EntityPose.CROUCHING)) {
            itemStack.getOrCreateNbt().put("Ability", new DashAbility());
        }
        else {
            executeAbility(user, itemStack, world);
        }
        return TypedActionResult.success(itemStack);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.getOrCreateNbt().get("Ability") instanceof Ability ability && !world.isClient()) {
            if (ability.duration < 200) {
                ability.duration--;
                stack.getNbt().putInt(":3", stack.getNbt().getInt(":3")+1);
            }
            if (ability.duration < 0) {
                stack.getNbt().remove("Ability");
            }
        }
    }
    @Override
    public void onLmbClickClient(PlayerEntity player, ItemStack stack) {
        executeAbility(player, stack, player.getWorld());
    }

    @Override
    public void onLmbClickServer(PlayerEntity player, ItemStack stack, ServerWorld world) {
        executeAbility(player, stack, player.getWorld());
    }

    @Override
    public void onLmbHoldClient(PlayerEntity player, ItemStack stack) {

    }

    @Override
    public void onLmbHoldServer(PlayerEntity player, ItemStack stack, ServerWorld world) {

    }

    public void executeAbility(PlayerEntity player, ItemStack stack, World world) {
        if (stack.getOrCreateNbt().get("Ability") instanceof Ability ability) {
            ability.Act(player, stack, world);

        }
    }
    public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }
}
