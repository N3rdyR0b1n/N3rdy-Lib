package n3rdyr0b1n.lib.test;

import n3rdyr0b1n.lib.N3rdyLib;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static ItemGroup TESTING;

    public static void registerItemGroup () {
        TESTING = Registry.register(Registries.ITEM_GROUP,
                new Identifier(N3rdyLib.MOD_ID, "n3rdylib_testing"), FabricItemGroup
                        .builder()
                        .displayName(Text.translatable("itemgroup.magic_mayhem_spell_drip"))
                        .icon(() -> new ItemStack(ModItems.TEST)).entries(((displayContext, entries) -> {
                            entries.add(ModItems.TEST);
                            entries.add(ModItems.ATTTEST);
                        })).build());


    }
}
