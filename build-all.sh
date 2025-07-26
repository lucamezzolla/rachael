#!/bin/bash

set -e  # Esci al primo errore

docker compose down

echo "Build Maven per tutti i progetti..."

for project in rachael-eureka-server rachael-config-server rachael-api-user rachael-api-wallet rachael-api-album
do
  echo "Building $project..."
  cd "$project"
  chmod +x mvnw  # se mancano i permessi
  ./mvnw clean package -DskipTests
  cd ..
done

echo "Build del progetto frontend (Vaadin) in modalità produzione..."
cd rachael-frontend
chmod +x mvnw
./mvnw clean package -Pproduction -DskipTests
cd ..

echo "Avvio dei container Docker..."
docker compose up --build -d

echo "Tutto avviato. Controlla i log con: docker compose logs -f"

