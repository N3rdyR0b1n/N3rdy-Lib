package n3rdyr0b1n.lib.test.nbt;

import n3rdyr0b1n.lib.items.nbt.NbtObject;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.swing.*;

public abstract class Ability extends NbtObject {
    public int duration;
    public Ability() {
        super("Swag");
        this.duration = 100;
    }
    public void Act(PlayerEntity player, ItemStack stack, World world) {
        if (!world.isClient()) {
            duration--;
        }
    }
    public void tick() {
        duration--;
    }
}
