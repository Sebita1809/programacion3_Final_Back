#!/bin/bash

# Script de build para FoodStore - Java 17
# Este script compila el proyecto usando Maven Wrapper

echo "🏗️  Iniciando build del proyecto FoodStore..."
echo "================================================"

# Verificar que Java 17 está disponible
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)

if [ "$JAVA_VERSION" != "17" ]; then
    echo "⚠️  Advertencia: Se requiere Java 17, pero se detectó Java $JAVA_VERSION"
    echo "Por favor, asegúrate de tener Java 17 configurado en tu PATH"
    exit 1
fi

echo "✅ Java 17 detectado correctamente"
echo ""

# Asegurar que mvnw tiene permisos de ejecución
chmod +x mvnw 2>/dev/null || true

# Limpiar builds anteriores y compilar
echo "🧹 Limpiando builds anteriores..."
./mvnw clean

echo ""
echo "📦 Compilando proyecto..."
./mvnw package -DskipTests

# Verificar si el build fue exitoso
if [ $? -eq 0 ]; then
    echo ""
    echo "================================================"
    echo "✅ Build completado exitosamente!"
    echo "📦 JAR generado: target/foodStore-0.0.1-SNAPSHOT.jar"
    echo "================================================"
else
    echo ""
    echo "================================================"
    echo "❌ Error durante el build"
    echo "================================================"
    exit 1
fi

