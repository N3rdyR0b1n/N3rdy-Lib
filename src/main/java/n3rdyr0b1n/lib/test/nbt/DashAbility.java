package n3rdyr0b1n.lib.test.nbt;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DashAbility extends Ability{
    @Override
    public void Act(PlayerEntity player, ItemStack stack, World world) {
        player.addVelocity(player.getRotationVec(0).multiply(2f));
        super.Act(player,stack,world);
    }
}
