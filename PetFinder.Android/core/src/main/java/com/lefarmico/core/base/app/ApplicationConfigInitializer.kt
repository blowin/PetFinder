package com.lefarmico.core.base.app

class ApplicationConfigInitializer(
    private vararg val configs: ApplicationConfig
) : ApplicationConfig {
    override fun init(application: BaseApplication) {
        configs.forEach { it.init(application) }
    }
}
