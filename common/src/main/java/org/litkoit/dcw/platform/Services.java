package org.litkoit.dcw.platform;

import java.util.ServiceLoader;
import org.litkoit.dcw.DirectorsCutWorkstation;
import org.litkoit.dcw.platform.services.PlatformHelper;

/**
 * Точка доступа к платформенным сервисам из общего (common) кода.
 *
 * ServiceLoader находит реализацию PlatformHelper, зарегистрированную
 * в version-модуле файлом:
 *   src/main/resources/META-INF/services/org.litkoit.dcw.platform.services.PlatformHelper
 *
 * Использование из common-кода:
 *   Services.PLATFORM.getPlatformName();
 */
public final class Services {

	public static final PlatformHelper PLATFORM = load(PlatformHelper.class);

	private Services() {
	}

	private static <T> T load(Class<T> clazz) {
		T loadedService = ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new IllegalStateException(
				"No implementation found " + clazz.getName()
						+ " check the META-INF/services file in your version module."));
		DirectorsCutWorkstation.LOGGER.info("Platform implementation {} for {} loaded",
				loadedService, clazz.getSimpleName());
		return loadedService;
	}
}
