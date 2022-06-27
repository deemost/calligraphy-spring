package com.arabiccalligraphy.api;

import com.arabiccalligraphy.api.controller.ArtworkController;
import com.arabiccalligraphy.api.controller.HealthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ApiApplicationTests {

    @Autowired
    private HealthController healthController;

    @Autowired
    private ArtworkController artworkController;

    @Test
    /*
      This tests that both controllers are wired correctly and are started up when spring context starts
     */
    void contextLoads() {
        assertThat(this.healthController).isNotNull();
        assertThat(this.artworkController).isNotNull();
    }

}
