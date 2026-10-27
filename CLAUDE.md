# Conventions du projet demo-backend (lu par Claude Code ET par les agents DeepSeek)

Backend de démonstration de DevStation. Plan : `docs/superpowers/plans/2026-10-27-devstation.md`
du dépôt `A-Djihadi/devstation` (tâches B1 à B3).

## Stack
- Java 21, Spring Boot 4.1, Maven Wrapper, Spring Cloud Vault (désactivé sauf `VAULT_ENABLED=true`)
- Paquet racine : `com.example.demobackend` ; port HTTP 8081

## Commandes
- Tests : `sh mvnw -o -q test` (sandbox hors-ligne : aucune nouvelle dépendance Maven possible)

## Règles
- Tests JUnit 5 ; contrôleurs testés avec `@WebMvcTest` + `MockMvc`
- Messages et réponses de l'API en français
- Jamais de secret dans le code : le secret de démo vient de Vault ou d'une variable d'environnement
- Modifications minimales : ne pas reformater du code non concerné
- Un commit = une tâche ; message au format `agent: <titre de la tâche>`
