package n3rdyr0b1n.lib.test;

import n3rdyr0b1n.lib.N3rdyLib;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item TEST = registerItems("test",
            new DebugItem(new FabricItemSettings().maxCount(1))
    );
    public static final Item ATTTEST = registerItems("att_test",
            new AttributeDebugItem(new FabricItemSettings().maxCount(1))
    );

    public static final Item LCT = registerItems("clicktest",
            new TestItem2(new FabricItemSettings().maxCount(1))
    );

    private static Item registerItems(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(N3rdyLib.MOD_ID,name), item);
    }



    public static void registerModItems() {
        N3rdyLib.LOGGER.info("Registering test items for " + N3rdyLib.MOD_ID);
    }

}
