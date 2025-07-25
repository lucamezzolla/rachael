#!/bin/bash

set -e  # esci al primo errore

docker compose down -v

echo "Build Maven per tutti i progetti..."

for project in rachael-eureka-server rachael-config-server rachael-api-user
do
  echo "Building $project..."
  cd "$project"
  chmod +x mvnw  # nel caso manchino i permessi
  ./mvnw clean package -DskipTests
  cd ..
done

echo "Avvio dei container Docker..."
docker compose up --build -d

echo "Tutto avviato. Controlla i log con: docker-compose logs -f"
