package com.example.demobackend;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

class VaultSecretHolderTest {

    @Nested
    @SpringBootTest(classes = VaultSecretHolder.class, properties = "api-key=demo-not-a-secret")
    class AvecSecret {

        @Autowired
        private VaultSecretHolder holder;

        @Test
        void indiqueLeSecretCharge() {
            assertThat(holder.isLoaded()).isTrue();
        }

        @Test
        void neDivulguePasLaValeurDansToString() {
            assertThat(holder.toString()).doesNotContain("demo-not-a-secret");
        }
    }

    @Nested
    class SansSecret {

        @Test
        void indiqueAucunSecretCharge() {
            assertThat(new VaultSecretHolder("").isLoaded()).isFalse();
        }

        @Test
        void neDivulguePasLaValeurDansToString() {
            assertThat(new VaultSecretHolder("demo-not-a-secret").toString())
                    .doesNotContain("demo-not-a-secret");
        }
    }
}