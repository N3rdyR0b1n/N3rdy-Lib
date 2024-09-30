package n3rdyr0b1n.lib.networking;

import n3rdyr0b1n.lib.N3rdyLib;
import n3rdyr0b1n.lib.networking.custom.C2S_Click_LMB;
import n3rdyr0b1n.lib.networking.custom.C2S_Hold_LMB;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class NetWorkChannels {


    public static final Identifier LEFT_CLICK = new Identifier(N3rdyLib.MOD_ID, "left_item_click");
    public static final Identifier LEFT_HOLD = new Identifier(N3rdyLib.MOD_ID, "left_item_hold");

    public static void registerChannelIds()
    {
        ServerPlayNetworking.registerGlobalReceiver(LEFT_CLICK, C2S_Click_LMB::recieve);
        ServerPlayNetworking.registerGlobalReceiver(LEFT_HOLD, C2S_Hold_LMB::recieve);
    }


}
