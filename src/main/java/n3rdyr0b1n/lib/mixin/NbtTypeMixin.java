package n3rdyr0b1n.lib.mixin;

import n3rdyr0b1n.lib.items.nbt.NbtObject;
import net.minecraft.nbt.NbtType;
import net.minecraft.nbt.NbtTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NbtTypes.class)
public class NbtTypeMixin {
    @Inject(method = "byId", at = @At("HEAD"), cancellable = true)
    private static void put(int key, CallbackInfoReturnable<NbtType<?>> cir) {
        if (key == NbtObject.NBTTAG) {
            cir.setReturnValue(NbtObject.TYPE);
        }
    }
}
