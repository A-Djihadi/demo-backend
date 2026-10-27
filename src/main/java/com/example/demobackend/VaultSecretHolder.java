package com.example.demobackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Detient le secret de demo charge depuis Vault (propriete {@code api-key}).
 * La valeur n'est jamais exposee : seul son etat de chargement est public.
 */
@Component
public class VaultSecretHolder {

    private final String apiKey;

    public VaultSecretHolder(@Value("${api-key:}") String apiKey) {
        this.apiKey = apiKey == null ? "" : apiKey;
    }

    public boolean isLoaded() {
        return !apiKey.isBlank();
    }

    @Override
    public String toString() {
        return "VaultSecretHolder{loaded=" + isLoaded() + "}";
    }
}