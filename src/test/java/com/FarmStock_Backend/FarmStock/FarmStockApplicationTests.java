package com.FarmStock_Backend.FarmStock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = FarmStockApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class FarmStockApplicationTests {

    @Test
    void contextLoads() {
        // Test vacío para validar que arranca el contexto mínimo
    }
}
