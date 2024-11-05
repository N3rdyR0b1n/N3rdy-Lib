package n3rdyr0b1n.lib.test;

import n3rdyr0b1n.lib.items.LeftMouseItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TestItem2 extends Item implements LeftMouseItem {
    public TestItem2(Settings settings) {
        super(settings);
    }


    private void spawncow(World world, PlayerEntity player) {
        Entity entity = new CowEntity(EntityType.COW, world);
        entity.setPosition(player.getPos());
        world.spawnEntity(entity);
    }
    @Override
    public void onLmbClick(PlayerEntity player, ItemStack stack, World world) {
        spawncow(world,player);
    }

    @Override
    public void onLmbHold(PlayerEntity player, ItemStack stack, World world) {
        spawncow(world,player);
    }
}
