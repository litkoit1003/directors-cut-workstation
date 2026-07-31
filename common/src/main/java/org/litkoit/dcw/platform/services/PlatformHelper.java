package org.litkoit.dcw.platform.services;

/**
 * Всё, что зависит от конкретной версии Minecraft или загрузчика (Fabric/NeoForge/...),
 * описывается здесь как контракт. Класс из common ничего не знает о Fabric API —
 * он знает только этот интерфейс.
 *
 * Каждый version-модуль (fabric-26-1-2, fabric-26-2-0, ...) кладёт свою реализацию
 * и регистрирует её через META-INF/services (см. Services.java).
 */
public interface PlatformHelper {

	/**
	 * @return название платформы, например "Fabric"
	 */
	String getPlatformName();

	/**
	 * @return true, если запущено окружение разработки (dev-среда в IDE)
	 */
	boolean isDevelopmentEnvironment();

	/**
	 * @param modId идентификатор мода
	 * @return true, если мод с таким id загружен (полезно для optional-интеграций)
	 */
	boolean isModLoaded(String modId);
}
