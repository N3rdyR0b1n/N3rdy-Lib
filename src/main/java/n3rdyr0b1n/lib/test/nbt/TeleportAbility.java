package n3rdyr0b1n.lib.test.nbt;

import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TeleportAbility extends Ability{
    @Override
    public void Act(PlayerEntity player, ItemStack stack, World world) {
        Vec3d pos = player.getPos().add(player.getRotationVec(0).multiply(2f));
        player.setPos(pos.x, pos.y, pos.z);
        super.Act(player,stack,world);
    }
}
