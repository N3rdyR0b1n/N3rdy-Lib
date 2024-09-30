package n3rdyr0b1n.lib.mixin;


import com.google.common.collect.Multimap;
import com.mojang.authlib.GameProfile;
import n3rdyr0b1n.lib.N3rdyLib;
import n3rdyr0b1n.lib.items.InventoryAttributeItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mixin(PlayerEntity.class)
public abstract class PlayerAttributeMixin {

    @Shadow @Final private PlayerInventory inventory;
    private NbtCompound persistantSaveData;
    private boolean[] nbtdirty;
    private Multimap<EntityAttribute, EntityAttributeModifier>[] oldmodifiers;


    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void injecttick(CallbackInfo cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (!player.getWorld().isClient() && player.age % 5 == 0) {
            PlayerInventory inventory = player.getInventory();
            for (int i = 0; i < inventory.size(); i++) {
                ItemStack stack = inventory.getStack(i);
                if (stack.isEmpty()) {
                    if (nbtdirty[i]) {
                        handleNbt(i, player);
                    }
                    continue;
                }
                if (stack.getItem() instanceof InventoryAttributeItem) {
                    Multimap<EntityAttribute, EntityAttributeModifier> mods = stack.getAttributeModifiers(EquipmentSlot.FEET);
                    player.getAttributes().addTemporaryModifiers(mods);
                    nbtdirty[i] = true;
                    oldmodifiers[i] = mods;
                }
                else if (nbtdirty[i]) {
                    handleNbt(i, player);
                }
            }
        }
    }
    private void handleNbt(int slot, PlayerEntity player) {
        Multimap<EntityAttribute, EntityAttributeModifier> mod = oldmodifiers[slot];
        if (mod != null) {
            player.getAttributes().removeModifiers(mod);
        }
        nbtdirty[slot] = false;
        oldmodifiers[slot] = null;
    }


    @Inject(at = @At("TAIL"), method = "<init>")
    public void init(World world, BlockPos pos, float yaw, GameProfile gameProfile, CallbackInfo info) {
        nbtdirty = new boolean[inventory.size()];
        oldmodifiers = new Multimap[inventory.size()];
    }


}
