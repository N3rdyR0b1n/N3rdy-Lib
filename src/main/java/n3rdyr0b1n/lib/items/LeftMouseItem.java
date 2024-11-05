package n3rdyr0b1n.lib.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public interface LeftMouseItem {

    public default void onLmbClick(PlayerEntity player, ItemStack stack, World world) {

    }
    public default void onLmbHold(PlayerEntity player, ItemStack stack, World world) {
    }


    public default void onLmbClickClient(PlayerEntity player, ItemStack stack) {
        onLmbClick(player, stack, player.getWorld());
    }
    public default void onLmbClickServer(PlayerEntity player, ItemStack stack, ServerWorld world) {
        onLmbClick(player, stack, world);
    }

    public default void onLmbHoldClient(PlayerEntity player, ItemStack stack) {
        onLmbHold(player, stack, player.getWorld());
    }

    public default void onLmbHoldServer(PlayerEntity player, ItemStack stack, ServerWorld world) {
        onLmbHold(player, stack, world);
    }


}
