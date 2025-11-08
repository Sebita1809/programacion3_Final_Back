#!/bin/bash

# Script de ejecución para FoodStore - Java 17
# Este script ejecuta la aplicación Spring Boot

echo "🚀 Iniciando aplicación FoodStore..."
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

# Verificar si existe el JAR compilado
JAR_FILE="target/foodStore-0.0.1-SNAPSHOT.jar"

if [ ! -f "$JAR_FILE" ]; then
    echo "⚠️  No se encontró el archivo JAR compilado"
    echo "Ejecutando build primero..."
    echo ""
    ./build.sh
    
    if [ $? -ne 0 ]; then
        echo "❌ Error durante el build. No se puede ejecutar la aplicación."
        exit 1
    fi
fi

echo "================================================"
echo "🎯 Ejecutando aplicación..."
echo "================================================"
echo ""

# Ejecutar la aplicación
java -jar "$JAR_FILE"

