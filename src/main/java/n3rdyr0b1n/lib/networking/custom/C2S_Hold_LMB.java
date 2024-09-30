package n3rdyr0b1n.lib.networking.custom;

import n3rdyr0b1n.lib.items.LeftMouseItem;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class C2S_Hold_LMB {
    public static void recieve(MinecraftServer server, ServerPlayerEntity player,
                               ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        ItemStack usestack = player.getStackInHand(player.getActiveHand());
        if (usestack.getItem() instanceof LeftMouseItem) {
            player.setPitch(buf.readFloat());
            player.setYaw(buf.readFloat());
            ((LeftMouseItem) usestack.getItem()).onLmbHoldServer(player, usestack, player.getServerWorld());
        }
    }
}
