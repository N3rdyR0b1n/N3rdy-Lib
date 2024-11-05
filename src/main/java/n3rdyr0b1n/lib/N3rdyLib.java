package n3rdyr0b1n.lib;

import n3rdyr0b1n.lib.items.nbt.ErrorNbtObject;
import n3rdyr0b1n.lib.networking.NetWorkChannels;
import n3rdyr0b1n.lib.test.ModItemGroup;
import n3rdyr0b1n.lib.test.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class N3rdyLib implements ModInitializer {
	public static final String MOD_ID = "n3rdylib";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ErrorNbtObject.Serialised = new ErrorNbtObject().Serialise();
		ModItems.registerModItems();
		ModItemGroup.registerItemGroup();
		NetWorkChannels.registerChannelIds();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}
}