package org.litkoit.dcw.fabric;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.litkoit.dcw.DirectorsCutWorkstation;

public class DirectorsCutWorkstationFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		DirectorsCutWorkstation.init();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(DirectorsCutWorkstation.MOD_ID, path);
	}
}
