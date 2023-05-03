package main.support

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

class TestContainerLifeCycleManager: QuarkusTestResourceLifecycleManager{
    private val postgresDockerImage = DockerImageName.parse("postgres:latest")
    override fun start(): MutableMap<String, String> {
        val container = startTestPostgresContainer()

        return mutableMapOf(
            "quarkus.datasource.username" to container.username,
            "quarkus.datasource.password" to container.password,
            "quarkus.datasource.jdbc.url" to container.jdbcUrl,
            "quarkus.datasource.initScript" to container.withInitScript("V4__Testdata_init.sql").toString()
        )
    }
    private fun startTestPostgresContainer(): PostgreSQLContainer<out PostgreSQLContainer<*>> {
        val container = PostgreSQLContainer(postgresDockerImage)
            .withDatabaseName("testPostgres")
            .withUsername("testUsername")
            .withPassword("testPassword")
            .withInitScript("V4__TestData_Init.sql")
        container.start()
        return container
    }
    override fun stop() {
        startTestPostgresContainer().stop()
        startTestPostgresContainer().close()
    }

}