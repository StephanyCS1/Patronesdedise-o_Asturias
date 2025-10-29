# 🍽️ Sistema de Gestión de Pedidos para Restaurante

![Java](https://img.shields.io/badge/Java-17+-orange.svg)
![License](https://img.shields.io/badge/License-MIT-blue.svg)
![Patterns](https://img.shields.io/badge/Patterns-5-green.svg)

Sistema completo de gestión de pedidos para restaurantes que implementa **5 patrones de diseño** de forma práctica y escalable.

---

## 📋 Tabla de Contenidos

- [Descripción del Proyecto](#-descripción-del-proyecto)
- [Problema que Resuelve](#-problema-que-resuelve)
- [Patrones de Diseño Implementados](#-patrones-de-diseño-implementados)
- [Arquitectura del Sistema](#-arquitectura-del-sistema)
- [Requisitos](#-requisitos)
- [Instalación](#-instalación)
- [Ejecución](#-ejecución)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Autor](#-autor)

---

## 🎯 Descripción del Proyecto

Este proyecto implementa un **sistema de gestión de pedidos** para restaurantes que permite:

✅ Crear pedidos de forma flexible y escalable  
✅ Notificar en tiempo real a cocina, meseros y clientes  
✅ Gestionar diferentes tipos de menús (Regular, Vegetariano, Premium)  
✅ Procesar pagos con múltiples métodos (Efectivo, Tarjeta, Digital)  
✅ Mantener una configuración centralizada del restaurante

---

## 🔧 Problema que Resuelve

### Contexto Empresarial

Los restaurantes modernos enfrentan varios desafíos:

1. **Pedidos complejos**: Los clientes pueden personalizar sus pedidos con múltiples items y modificaciones
2. **Comunicación ineficiente**: Falta de sincronización entre cocina, meseros y clientes
3. **Menús cambiantes**: Necesidad de agregar nuevos tipos de menú sin modificar código existente
4. **Métodos de pago diversos**: Integración de efectivo, tarjetas y billeteras digitales
5. **Configuración inconsistente**: Diferentes valores de impuestos y propinas según ubicación

### Solución

Este sistema resuelve estos problemas mediante:

- **Construcción flexible** de pedidos con el patrón Builder
- **Notificaciones en tiempo real** con el patrón Observer
- **Creación dinámica** de menús con Factory Method
- **Procesamiento intercambiable** de pagos con Strategy
- **Configuración única** y centralizada con Singleton

---

## 🏗️ Patrones de Diseño Implementados

### 1. 🏭 **Factory Method Pattern**

**Propósito**: Crear diferentes tipos de productos sin especificar sus clases concretas.

**Implementación**:
- `MenuFactory` (Clase abstracta)
- `RegularMenuFactory` (Menú tradicional)
- `VegetarianMenuFactory` (Menú vegetariano)
- `PremiumMenuFactory` (Menú gourmet)

**Beneficio**: Fácil agregar nuevos tipos de menú (ej: vegano, infantil) sin modificar código existente.

```java
MenuFactory factory = new VegetarianMenuFactory();
Product ensalada = factory.createProduct("Ensalada Bowl");
```

---

### 2. 🔨 **Builder Pattern**

**Propósito**: Construir objetos complejos paso a paso.

**Implementación**:
- `OrderBuilder` permite crear pedidos con múltiples configuraciones

**Beneficio**: Facilita la creación de pedidos complejos con validaciones.

```java
Order order = new OrderBuilder()
    .setTable("Mesa 5")
    .setWaitress(maria)
    .addItem(product1, 2)
    .addItem(product2, 1)
    .addKitchenNotification()
    .build();
```

---

### 3. 🔔 **Observer Pattern**

**Propósito**: Notificar automáticamente a múltiples objetos sobre cambios de estado.

**Implementación**:
- `OrderObserver` (Interfaz)
- `KitchenObserver` (Notifica a cocina)
- `WaiterObserver` (Notifica a meseros)
- `CustomerNotificationObserver` (Notifica a clientes)

**Beneficio**: Desacoplamiento entre el pedido y los sistemas de notificación.

```java
order.setStatus(OrderStatus.READY);
// Automáticamente notifica a cocina, mesero y cliente
```

---

### 4. 🎯 **Strategy Pattern**

**Propósito**: Definir una familia de algoritmos intercambiables.

**Implementación**:
- `PaymentStrategy` (Interfaz)
- `CashPaymentStrategy` (Pago en efectivo)
- `CardPaymentStrategy` (Pago con tarjeta)
- `DigitalPaymentStrategy` (Pago digital: Nequi, Daviplata)

**Beneficio**: Agregar nuevos métodos de pago sin modificar el código existente.

```java
PaymentProcessor processor = new PaymentProcessor();
processor.setPaymentStrategy(new CardPaymentStrategy("4532...", "Juan"));
processor.executePayment(order);
```

---

### 5. 🏢 **Singleton Pattern**

**Propósito**: Garantizar una única instancia de una clase.

**Implementación**:
- `RestaurantConfig` (Configuración global)

**Beneficio**: Configuración centralizada accesible desde cualquier parte del sistema.

```java
RestaurantConfig config = RestaurantConfig.getInstance();
BigDecimal tax = config.calculateTax(subtotal);
```

---

## 🏛️ Arquitectura del Sistema

```
┌─────────────────────────────────────────────────┐
│           RESTAURANT ORDER SYSTEM               │
└─────────────────────────────────────────────────┘
                      │
        ┌─────────────┼─────────────┐
        │             │             │
   ┌────▼───┐   ┌────▼───┐   ┌────▼───┐
   │ Domain │   │Patterns│   │  Main  │
   └────┬───┘   └────┬───┘   └────────┘
        │            │
        │     ┌──────┴──────┬────────────┬──────────┐
        │     │             │            │          │
    ┌───▼─────▼──┐   ┌─────▼─────┐  ┌──▼───┐  ┌───▼────┐
    │  Builder   │   │  Factory  │  │Observ│  │Strategy│
    │  Pattern   │   │  Method   │  │-er   │  │ Pattern│
    └────────────┘   └───────────┘  └──────┘  └────────┘
```

---

## 📦 Requisitos

- **Java**: 17 o superior
- **Maven**: 3.8+
- **Lombok**: Plugin configurado en el IDE
- **JUnit 5**: Para tests (incluido en pom.xml)

---

## 🚀 Instalación

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/restaurant-order-system.git
cd restaurant-order-system
```

### 2. Compilar con Maven

```bash
mvn clean compile
```

### 3. Instalar Dependencias

```bash
mvn install
```

---

## ▶️ Ejecución

### Ejecutar la aplicación

Compilar el proyecto:

```bash
mvn compile
java -cp target/classes org.restaurante.pedidos.SistemaPedidosRestauranteApplication

O con Java directamente:

java -cp target/classes org.restaurante.pedidos.SistemaPedidosRestauranteApplication
```


### Salida Esperada

```
╔═══════════════════════════════════════════════╗
║  SISTEMA DE GESTIÓN DE PEDIDOS - RESTAURANTE ║
╚═══════════════════════════════════════════════╝

1️⃣  SINGLETON PATTERN - Configuración del Restaurante
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
=== Configuración del Restaurante ===
Nombre: El Buen Sabor
Dirección: Calle Principal #123
IVA: 19.0%
...
```

---

## 📁 Estructura del Proyecto

```
restaurant-order-system/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/restaurante/pedidos/
│   │           ├── Domain/
│   │           │   ├── Category.java
│   │           │   ├── Product.java
│   │           │   ├── Items.java
│   │           │   ├── Order.java
│   │           │   ├── OrderStatus.java
│   │           │   ├── PaymentMethod.java
│   │           │   └── Waitress.java
│   │           │
│   │           ├── Patterns/
│   │           │   ├── Builder/
│   │           │   │   └── OrderBuilder.java
│   │           │   │
│   │           │   ├── Factory/
│   │           │   │   ├── MenuFactory.java
│   │           │   │   ├── RegularMenuFactory.java
│   │           │   │   ├── VegetarianMenuFactory.java
│   │           │   │   └── PremiumMenuFactory.java
│   │           │   │
│   │           │   ├── Observer/
│   │           │   │   ├── OrderObserver.java
│   │           │   │   ├── KitchenObserver.java
│   │           │   │   ├── WaiterObserver.java
│   │           │   │   └── CustomerNotificationObserver.java
│   │           │   │
│   │           │   ├── Singleton/
│   │           │   │   └── RestaurantConfig.java
│   │           │   │
│   │           │   └── Strategy/
│   │           │       ├── PaymentStrategy.java
│   │           │       ├── PaymentProcessor.java
│   │           │       ├── CashPaymentStrategy.java
│   │           │       ├── CardPaymentStrategy.java
│   │           │       └── DigitalPaymentStrategy.java
│   │           │
│   │           └── Main.java
│
├── pom.xml
├── README.md
└── .gitignore
```

---

## 👤 Autor

**Stephany Castro Salas**  
🔗 LinkedIn: [Stephany Castro Salas](https://www.linkedin.com/in/stephany-castro-salas/)  
💻 GitHub: [StephanyCS1](https://github.com/StephanyCS1)

---


## 📚 Referencias

- [Refactoring Guru - Design Patterns](https://refactoring.guru/design-patterns)
- [Gang of Four - Design Patterns](https://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
- [Effective Java - Joshua Bloch](https://www.amazon.com/Effective-Java-Joshua-Bloch/dp/0134685997)

---
