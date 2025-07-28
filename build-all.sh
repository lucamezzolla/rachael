#!/bin/bash

set -e  # Ferma lo script al primo errore

echo "Arresto e rimozione dei container Docker attivi..."
docker compose down

echo "Pulizia delle immagini Docker inutilizzate..."
docker image prune -af

echo "Build Maven per tutti i progetti backend..."

for project in rachael-eureka-server rachael-config-server rachael-api-user rachael-api-wallet rachael-api-album
do
  echo "Building $project..."
  cd "$project"
  chmod +x mvnw  # Garantisce esecuzione di Maven wrapper
  ./mvnw clean package -DskipTests
  cd ..
done

echo "Build del progetto frontend (Vaadin) in modalità produzione..."
cd rachael-frontend
chmod +x mvnw
./mvnw clean package -Pproduction -DskipTests
cd ..

echo "Rebuild e avvio dei container Docker..."
docker compose build --no-cache
docker compose up -d

echo "Tutto avviato. Controlla i log con:"
echo "   docker compose logs -f"

