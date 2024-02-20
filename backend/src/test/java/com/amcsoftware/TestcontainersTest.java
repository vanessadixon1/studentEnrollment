package com.amcsoftware;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestcontainersTest extends AbstractTestContainer {

    @Test
    void canStartPostgresDB() {
        Assertions.assertThat(postgreSQLContainer.isCreated()).isTrue();
        Assertions.assertThat(postgreSQLContainer.isRunning()).isTrue();
    }



}
