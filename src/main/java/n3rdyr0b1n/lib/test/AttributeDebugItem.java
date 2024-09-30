package n3rdyr0b1n.lib.test;

import n3rdyr0b1n.lib.items.InventoryAttributeItem;
import n3rdyr0b1n.lib.items.LeftMouseItem;
import n3rdyr0b1n.lib.test.nbt.Ability;
import n3rdyr0b1n.lib.test.nbt.DashAbility;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Objects;

public class AttributeDebugItem extends Item implements LeftMouseItem, FabricItem, InventoryAttributeItem {
    public AttributeDebugItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        itemStack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier("wsag", 1d, EntityAttributeModifier.Operation.ADDITION), EquipmentSlot.FEET);

        return TypedActionResult.success(itemStack);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity.age % 200 == 0) {
            if (stack.hasNbt() && stack.getNbt().contains("AttributeModifiers",  NbtElement.LIST_TYPE)) {
                NbtList nbtList = stack.getNbt().getList("AttributeModifiers", NbtElement.COMPOUND_TYPE);
                for (int i = 0; i < nbtList.size(); i++) {
                    NbtCompound compound = (NbtCompound) nbtList.get(i);
                    String comparison = compound.getString("Name");
                    if (Objects.equals(comparison, "wsag")) {
                        ((PlayerEntity) entity).getAttributes().removeModifiers(stack.getAttributeModifiers(EquipmentSlot.FEET));
                        nbtList.remove(i);
                        return;
                    }
                }
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

    @Override
    public void addTooltip(ItemStack stack, World world, ArrayList<Text> list, TooltipContext context, PlayerEntity player) {

    }
}
