package n3rdyr0b1n.lib.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.ArrayList;

public interface InventoryAttributeItem {

    void addTooltip(ItemStack stack, World world, ArrayList<Text> list, TooltipContext context, PlayerEntity player);
}
