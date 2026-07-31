package org.litkoit.dcw;

import org.litkoit.dcw.platform.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Общий вход в логику мода. Не реализует ModInitializer — это Fabric-специфичный
 * интерфейс, которого common не должен знать. Каждый version-модуль вызывает
 * init() из своей собственной точки входа (см. fabric-26-1-2/.../DirectorsCutWorkstationFabric).
 */
public final class DirectorsCutWorkstation {

	public static final String MOD_ID = "directorscutworkstation";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private DirectorsCutWorkstation() {
	}

	public static void init() {
		LOGGER.info("Director's Cut Workstation initialized! Platform: {}", Services.PLATFORM.getPlatformName());
	}
}
