
# REALITY CHECK 


● ¿Qué tareas subestimamos y por qué? (Falta de conocimiento, problemas de 
entorno, etc.). 

EL MVP se definió desde el principio como pequeño y enfocado, por lo que se priorizó las primeras cuatro historias de usuario para el motor de antifraude. No se subestimó el trabajo, sino que se agregaron tareas no esenciales para esta fase, especialmente en lo que se refiere a la base de datos. Lo aprendido es que en futuras iteraciones se priorizará lo indispensable para el MVP, dejando lo complementario para etapas posteriores.

● ¿El MVP construido es realmente valioso para el negocio a pesar de no haber 
terminado todo el backlog? 

Sí, el MVP que se construyó sí aporta valor al negocio, incluso sin haber terminado todo el backlog. Esto se debe a que las primeras cuatro historias de usuario ya cubren lo más importante: el motor de reglas para detectar fraude. Con lo que se desarrolló, ya es posible evaluar transacciones según el monto y la ubicación, identificar comportamientos inusuales e incluso asignarles un nivel de riesgo.

● ¿Cómo garantizó el QA la calidad del MVP entregado en un tiempo tan corto? 

El QA garantizó la calidad del MVP en corto tiempo mediante un claro análisis de riesgos en el PRD y plan de pruebas en el Test_Plan . El proyecto se enfocó en las HU críticas del negocio, HU1 a HU4, se creó una matriz de casos de pruebas de 26, cubriendo pruebas de cubrimiento, límite y negativas(nulos, tipos inválidos, valores extremos, datos faltantes).

Se planteó pruebas automatizadas en múltiples capas: SerenityBDD/Cucumber para validación funcional, Karate para contratos de API y k6 para rendimiento, lo que igualmnete ayuda a garantizar la calidad del MVP.

El desarrollador implementó TDD y pruebas unitarias, lo que también ayudó a validar el comportamiento de los componentes clave del sistema y  detectar errores rápidamente durante el desarrollo.
