# 🛡️ Motor de Reglas Antifraude

![Status](https://img.shields.io/badge/Status-Active-green)
![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Tests](https://img.shields.io/badge/Tests-Passing-success)
![License](https://img.shields.io/badge/License-MIT-blue)

---

## 📋 Descripción 

Repositorio dedicado a la validación automatizada de las HU1-HU4 del sistema antifraude 
mediante Serenity BDD, Cucumber, Karate y k6, cubriendo pruebas funcionales, de API y de performance.

## 📁 Estructura del Proyecto

```
fraud-detection/
├── Docs/                          # Documentación del proyecto
│   ├── PRD.md                    # Visión y éxito del producto
│   ├── USER_STORIES.md           # Historias de usuario (HU1-HU10)
│   ├── TEST_CASES.md             # Casos de prueba detallados
│   ├── TEST_PLAN.md              # Plan de pruebas (Karate, Serenity, k6)
│   ├── SUBTASKS.md               # Tareas de desarrollo y QA
│   └── REALITY_CHECK.md          # Validaciones de realidad
│
├── fraud-service/                # Backend microservicio
│   ├── backend/transaction-service/  # API REST Spring Boot 3.x
│   │   ├── src/main/java/com/fraud/
│   │   │   ├── domain/           # Lógica de negocio
│   │   │   ├── application/      # Casos de uso
│   │   │   └── infrastructure/   # Controladores, DB, Config
│   │   └── pom.xml
│   ├── frontend/                 # UI React + Vite + Tailwind
│   ├── docker-compose.yml        # Orquestación local
│   └── README.md
│
├── karate-fraud/                 # Pruebas API (Karate)
│   ├── src/test/java/co/sofka/fraud/
│   │   └── FraudApiTest.java     # Test runner Karate
│   ├── src/test/resources/
│   │   ├── karate-config.js      # Configuración (baseUrl, envs)
│   │   └── karate/
│   │       ├── hu1_negative.feature   # TC-005–007 (Validaciones)
│   │       ├── hu2_negative.feature   # TC-013–015, TC-018 (Datos faltantes)
│   │       └── hu4_risk_levels.feature # TC-023–026 (Niveles riesgo)
│   ├── target/karate-reports/   # HTML reports (generados)
│   └── pom.xml
│
├── serenity-fraud/              # Pruebas BDD (Serenity + Cucumber)
│   ├── src/test/java/         # Step definitions y runners
│   ├── src/test/resources/    # Feature files (Gherkin)
│   └── pom.xml
│
└── .agent/skills/              # Custom skills para modernization
```

---

## 🛠️ Tech Stack

| Componente | Tecnología | Versión |
|-----------|-----------|---------|
| **Backend** | Spring Boot | 3.2.x |
| **Java** | OpenJDK | 17 LTS |
| **Database** | PostgreSQL | 16 |
| **Frontend** | React + Vite | 5.x |
| **API Tests** | Karate | 1.4.1 |
| **BDD Tests** | SerenityBDD + Cucumber | 4.x |
| **Performance Tests** | k6 | Latest |
| **Containerization** | Docker + Docker Compose | Latest |
| **Build** | Maven | 3.9.x |

---

## 📊 Documentación Importante

| Documento | Propósito |
|-----------|-----------|
| [PRD.md](Docs/PRD.md) | Visión del producto, éxito y alcance |
| [USER_STORIES.md](Docs/USER_STORIES.md) | Historias de usuario (HU1-HU10) |
| [TEST_CASES.md](Docs/TEST_CASES.md) | Casos de prueba con criterios de aceptación |
| [TEST_PLAN.md](Docs/TEST_PLAN.md) | Estrategia de pruebas: Karate, Serenity, k6 |
| [SUBTASKS.md](Docs/SUBTASKS.md) | Tareas técnicas de desarrollo y QA |
| [REALITY_CHECK.md](Docs/REALITY_CHECK.md) | Validaciones de requisitos |

---

## 🚀 Quick Start

### Requisitos Previos
- Java 17 JDK
- Maven 3.9.x
- Docker y Docker Compose
- Node.js 18+ (para frontend)
- PostgreSQL 16 (o usar Docker)

### Instalación Local

```bash
# 1. Clonar repositorio
git clone https://github.com/majoymajo/fraud-QA.git
cd fraud-QA

# 2. Cambiar a rama Karate (para pruebas API)
git checkout Karate

# 3. Levantar stack (Postgres + Backend + Frontend)
cd fraud-service
docker compose up -d --build postgres fraud-service

# 4. Verificar salud del backend
curl http://localhost:8080/api/v1/fraud/health
# Response: {"status":"UP"}
```

---

## 🧪 Ejecutar Pruebas

### **Pruebas API (Karate)** - TC-005–007, TC-013–015, TC-018, TC-023–026

```bash
# Ejecutar todas las pruebas Karate
mvn clean test -f karate-fraud/pom.xml

# Ejecutar solo un feature específico
mvn clean test -f karate-fraud/pom.xml -Dkarate.options="classpath:karate/hu1_negative.feature"

# Con output detallado
mvn clean test -f karate-fraud/pom.xml -X
```

**Abre el reporte:**
```powershell
# Windows PowerShell
ii .\karate-fraud\target\karate-reports\karate-summary.html

# Linux/Mac
open ./karate-fraud/target/karate-reports/karate-summary.html
```

**Resultados Actuales:**
- ✅ **11 tests ejecutados**
- ✅ **0 fallos**
- ✅ **Cobertura:** HU1 (casos negativos), HU2 (datos faltantes), HU4 (niveles de riesgo)

---

### **Pruebas Funcionales (Serenity + Cucumber)** - HU1–HU4

```bash
# Ejecutar todas las pruebas Serenity
mvn clean verify -f serenity-fraud/pom.xml

# Ejecutar con tag específico (ej: @critico)
mvn clean verify -f serenity-fraud/pom.xml -Dcucumber.filter.tags="@critico"

# Reportes detallados
mvn clean verify -f serenity-fraud/pom.xml -Dserenity.report.dir=target/site/serenity
```

**Abre el reporte Serenity:**
```powershell
ii .\serenity-fraud\target\site\serenity\index.html
```

**Resultados Actuales:**
- ✅ **15 casos ejecutados**
- ✅ **14 pasados**
- ❌ **1 fallo**
- ✅ **0 errores**
- ✅ **0 pendientes**
- ✅ **0 ignorados**

---

### **Pruebas de Performance (k6)** - TC-PERF

```bash
# Ejecutar prueba de carga
k6 run k6-fraud/fraud_load_test.js

# Con configuración custom (ej: 100 VUs, 1 minuto)
k6 run -u 100 -d 1m k6-fraud/fraud_load_test.js

# Output en JSON para análisis
k6 run -o json=results.json k6-fraud/fraud_load_test.js
```

---

## 📈 Arquitectura & Flujo

```
┌─────────────────────────────────────────────────────────────────┐
│                     Frontend (React + Vite)                     │
│                    :3000 | Tailwind CSS                         │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│                 API Gateway (Spring Boot)                       │
│                    :8080 | JAX-RS/REST                          │
│  POST /api/v1/fraud/evaluate  (HU1, HU2, HU3, HU4)             │
│  GET  /api/v1/fraud/evaluations  (HU6)                         │
└────────────┬────────────────────────────┬──────────────────────┘
             │                            │
             ▼                            ▼
    ┌─────────────────┐        ┌──────────────────┐
    │  Domain Rules   │        │  Persistence     │
    │ (AmountRule,    │        │  Adapter         │
    │ LocationRule)   │        │ (PostgreSQL)     │
    └─────────────────┘        └──────────────────┘
             │
             ▼
    ┌─────────────────────────────────────────┐
    │  Fraud Evaluation Service (Use Cases)   │
    │  - EvaluateTransaction                  │
    │  - GetFraudHistory                      │
    └─────────────────────────────────────────┘
             │
             ▼
    ┌─────────────────────────────────────────┐
    │  Risk Classification                    │
    │  LOW | MEDIUM | HIGH                    │
    └─────────────────────────────────────────┘
```

---

## 📋 Criterios de Evaluación de Historias de Usuario

Se utilizó la escala **Fibonacci (3, 5, 8)** para estimar complejidad:
- **Complejidad técnica**
- **Tamaño del trabajo**
- **Incertidumbre**


---


| HU   | Descripción                      | Story Points | Justificación |
|------|----------------------------------|-------------|--------------|
| HU1  | Evaluar monto                   | 3           | Lógica simple de validación y comparación contra umbral. Baja complejidad y poca incertidumbre. |
| HU2  | Evaluar ubicación               | 5           | Requiere comparar con historial del usuario y definir qué es “inusual”. Mayor lógica e incertidumbre. |
| HU3  | Marcar sospechosa               | 3           | Orquesta resultados de otras HUs. No agrega lógica compleja nueva. |
| HU4  | Clasificar el riesgo               | 5           | Combina múltiples condiciones (monto y/ o ubicación) e introduce reglas de negocio adicionales. |
| HU5  | Generar alerta                  | 5           | Incluye lógica de duplicidad y emisión de eventos. Depende de otras HUs. |
| HU6  | Registrar transacciones         | 5           | Implementación de persistencia, endpoint, filtros y paginación.  |
| HU7  | Revisión manual                 | 5           | Manejo de estados y reglas de negocio adicionales. Persistencia y consultas. |
| HU8  | Configurar monto                | 3           | CRUD simple con validaciones básicas e integración con HU1. Baja complejidad. |
| HU9  | Ubicaciones habituales          | 8           | Manejo de historial por usuario e integración directa con HU2. Alta complejidad y crecimiento potencial. |
| HU10 | Configurar niveles de riesgo    | 5           | Configuración dinámica de reglas. Requiere validación e integración con clasificación. |

---

## 🔄 Ramas de Desarrollo

| Rama | Propósito | Estado |
|------|-----------|--------|
| `main` | Producción estable | Sincronizada |
| `Serenity-+-Cucumber` | Pruebas funcionales BDD | En desarrollo |
| **`Karate`** | **Pruebas API (Actual)** | **✅ Activa** |

---

## 📊 Estado de Pruebas

### Karate API Tests (Step 3 - Completado)
| Suite | Feature | Escenarios | Estado | Tiempo |
|-------|---------|-----------|--------|--------|
| HU1 | hu1_negative.feature | 3 | ✅ Pasado | 1.73s |
| HU2 | hu2_negative.feature | 4 | ✅ Pasado | 1.33s |
| HU4 | hu4_risk_levels.feature | 4 | ✅ Pasado | 0.38s |
| **Total** | **3 Features** | **11 Casos** | **✅ 0 Fallos** | **3.44s** |

### Serenity BDD Tests (Step 4 - Completado)
- TC-001 a TC-004: HU1 (Validaciones)
- TC-008 a TC-012: HU2 y HU3 (Ubicación y Evaluación)
- TC-016 a TC-022: HU4 (Riesgo y Alertas)

**Ejecutar pruebas Serenity:**
```bash
mvn clean verify -f serenity-fraud/pom.xml
```

**Abre el reporte Serenity:**
```powershell
ii .\serenity-fraud\target\site\serenity\index.html
```

**Resultados Actuales:**
| Métrica | Valor |
|---------|-------|
| Casos de Prueba | 15 |
| Pasados | ✅ 14 |
| Fallos | ❌ 1 |
| Errores | 0 |
| Pendientes | 0 |
| Ignorados | 0 |
| **Tasa de Éxito** | **93.3%** |

### k6 Performance Tests (Step 5 - Próximo)
- TC-PERF: Carga combinada HU1 + HU3
- Métricas: p95 < 500ms, error rate < 5%

**Estado:** 🔘 Sin ejecutar

---

## 📊 Resumen General de Ejecución

| Etapa | Pruebas | Casos | Pasados | Fallos | Estado | Cobertura |
|-------|---------|-------|---------|--------|--------|-----------|
| **Step 3** | Karate API | 11 | ✅ 11 | 0 | ✅ Completado | HU1, HU2, HU4 |
| **Step 4** | Serenity BDD | 15 | ✅ 14 | ❌ 1 | ✅ Completado (93.3%) | HU1-HU4 |
| **Step 5** | k6 Performance | 1 | - | - | 🔘 Pendiente | HU1+HU3 |
| **TOTAL** | **27 TCs** | **27** | **✅ 25** | **❌ 1** | **96.3% Éxito** | **HU1-HU4** |

---

## 🤝 Contribuir

### Workflow de Desarrollo

1. **Crear rama desde `Karate`:**
   ```bash
   git checkout -b feature/nueva-prueba Karate
   ```

2. **Implementar pruebas siguiendo:**
   - [TEST_CASES.md](Docs/TEST_CASES.md) para specs
   - [TEST_PLAN.md](Docs/TEST_PLAN.md) para framework asignado
   - [Karate Setup Guide](.agent/skills/test-implementation/references/karate-setup.md)

3. **Ejecutar pruebas localmente:**
   ```bash
   mvn clean test -f karate-fraud/pom.xml
   ```

4. **Commit y Push:**
   ```bash
   git add .
   git commit -m "✨ feat: agregar pruebas para TC-XXX"
   git push origin feature/nueva-prueba
   ```

5. **Crear Pull Request** → `Karate`

---

## 📚 Recursos & Enlaces

- **[Documentación del Proyecto](Docs/)**
- **[Test Plan Detallado](Docs/TEST_PLAN.md)**
- **[API Endpoints](fraud-service/backend/transaction-service/README.md)**
- **[Karate Labs Docs](https://karatelabs.io)**
- **[SerenityBDD Guide](https://serenity-bdd.info)**
- **[k6 Performance Guide](https://k6.io/docs)**

---

## 🐛 Reportar Bugs

Found a bug? [Open an issue](https://github.com/majoymajo/fraud-QA/issues) con:
- Descripción clara del problema
- Pasos para reproducir
- Comportamiento esperado vs actual
- Logs/screenshots si aplica

---

## ✅ Checklist de Release

- [ ] Todas las pruebas Karate pasan
- [ ] Todas las pruebas Serenity pasan
- [ ] Pruebas de performance ✅ 
- [ ] Documentación actualizada
- [ ] CHANGELOG.md completado
- [ ] PR review aprobado
- [ ] Merge a `main`
- [ ] Tag de release creado

---

## 📄 Licencia

Este proyecto está bajo licencia **MIT**. Ver [LICENSE](LICENSE) para detalles.
