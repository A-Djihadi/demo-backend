#!/bin/sh
set -e

# Valeur fictive de démonstration : ne jamais y mettre de vrai secret.
vault kv put secret/demo-backend api-key=demo-not-a-secret