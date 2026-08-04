package org.litkoit.dcw.fabric.platform;

import net.fabricmc.loader.api.FabricLoader;
import org.litkoit.dcw.platform.services.PlatformHelper;

/**
 * Регистрируется через
 * META-INF/services/org.litkoit.dcw.platform.services.PlatformHelper
 */
public class FabricPlatformHelper implements PlatformHelper {

	@Override
	public String getPlatformName() {
		return "Fabric";
	}

	@Override
	public boolean isDevelopmentEnvironment() {
		return FabricLoader.getInstance().isDevelopmentEnvironment();
	}

	@Override
	public boolean isModLoaded(String modId) {
		return FabricLoader.getInstance().isModLoaded(modId);
	}
}
