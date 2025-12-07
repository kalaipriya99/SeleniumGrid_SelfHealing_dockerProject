#!/bin/bash
set -e
echo "=============================="
echo "Starting Selenium Maven build"
echo "=============================="

# Wait for Healenium backend
echo "Waiting for Healenium backend..."
until curl -s http://hlm-backend:7878/actuator/health | grep -q 'UP'; do
  echo "Backend not ready yet..."
  sleep 3
done

# Safely delete target folder contents without failing
if [ -d "/app/target" ]; then
  echo "Cleaning /app/target manually..."
  rm -rf /app/target/* || true
fi

# Now run Maven (won’t fail on delete)
mvn test

echo "=============================="
echo "Build finished successfully"
echo "=============================="