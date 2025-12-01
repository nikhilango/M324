# Continuous Deployment

## 1. Was ist Continuous Deployment und wie wird es umgesetzt?
CD ist die Praxis, bei der jede Code-Änderung, die alle automatisierten Tests besteht, automatisch und ohne menschliches Zutun in die Produktion bereitgestellt wird. Es ist die höchste Stufe der Automatisierung in der CI/CD-Pipeline.

- Bedeutung: Ermöglicht den schnellsten Time-to-Market und beschleunigt das Kundenfeedback. Es eliminiert manuelle Release-Engpässe.

- Umsetzung:

  - Voraussetzung: Robuste Continuous Integration (CI) und nahezu 100%ige automatisierte Testabdeckung.

  - Prozess: Build, Test und Deployment in die Produktion sind vollständig automatisiert. Nur ein fehlgeschlagener Test stoppt den Rollout.

  - Tools: Jenkins, GitLab CI, AWS CodeDeploy, Feature Flags.
 
![CD Diagram](https://github.com/nikhilango/M324/blob/main/Images/CD_diagram.png)

Quelle: https://www.atlassian.com/de/continuous-delivery/software-testing/continuous-deployment, https://www.cologne-intelligence.de/glossar/continuous-deployment
## 2. Was ist der Unterschied zwischen Continuous Deployment und Continuous Delivery?
| Merkmal | Continuous Delivery (CD) | Continuous Deployment (CD) |
| :--- | :--- | :--- |
| **Status** | Code ist jederzeit bereit für die Veröffentlichung (manuelle Freigabe). | Code wird automatisch freigegeben und deployt. |
| **Freigabe** | Manuelle Entscheidung oder Knopfdruck ist erforderlich. | Vollautomatisch – keine menschliche Interaktion nötig. |
| **Automatisierung** | Hoch (bis zum Deployment-Gate). | Maximal (durchgehend bis zur Produktion). |

Quelle: https://www.atlassian.com/continuous-delivery/principles/continuous-integration-vs-delivery-vs-deployment, https://www.computerweekly.com/de/ratgeber/Zwischen-Continuous-Delivery-und-Continuous-Deployment-waehlen
## 3. Was sind die Vor- und Nachteile von Continuous Delivery und Continuous Deployment?
#### Vorteile 
- **Delivery:** Reduziertes Risiko pro Release; erlaubt dem Business, den optimalen Zeitpunkt für das Go-Live manuell zu wählen (Planbarkeit).
- **Deployment:** Kürzeste Time-to-Market; schnellster Feedback-Loop; Entlastung von Release-Druck.

#### Nachteile 
- **Delivery:** Die manuelle Freigabe kann den Prozess verzögern; erfordert den Aufwand für menschliche Entscheidungen.
- **Deployment:** Extrem hohe Anforderungen an die Testsuite (ein Fehler geht sofort live); hoher initialer Einrichtungsaufwand.

Quelle: https://www.abtasty.com/de/resources/guide-continuous-integration-delivery/, https://entwickler.de/continuous-delivery/continuous-deployment-fluch-oder-segen
## 4. Was sind die folgenden Deployment Strategien und wie werden sie umgesetzt?
#### Blue/Green Deployment (Blau/Grün) 
* **Definition:** Es werden zwei identische Produktionsumgebungen parallel betrieben.
* **Umsetzung:**
    1.  Die neue Version (Green) wird auf der inaktiven Umgebung bereitgestellt und vollständig getestet.
    2.  Der Load Balancer wird angewiesen, den gesamten Verkehr sofort (Zero-Downtime) von Blue auf Green umzuleiten.
    3.  Blue bleibt als sofortige Rollback-Option aktiv.
* **Resultat:** Zero-Downtime und sehr schnelles Rollback.

#### Canary Deployment (Kanarienvogel) 
* **Definition:** Die neue Version wird schrittweise nur für einen **kleinen Prozentsatz der Live-Benutzer** freigeschaltet.
* **Umsetzung:**
    1.  Die neue Version wird in Produktion deployt, aber der Load Balancer leitet anfangs nur z. B. **1–5 % des Datenverkehrs** dorthin.
    2.  Das **Monitoring** auf Fehler und Performance erfolgt in Echtzeit.
    3.  Bei Stabilität wird der Traffic-Anteil schrittweise erhöht (z. B. 20 %, 50 %, 100 %). Bei Problemen sofortiger Rollback.
* **Resultat:** **Minimales Risiko** (kleiner "Blast Radius"), Test unter realen Bedingungen.

Quelle: https://octopus.com/devops/software-deployments/blue-green-vs-canary-deployments/, https://www.pineparks.ch/deployment-strategien-fuer-apps/

## 5. Was ist A/B Testing?
A/B Testing ist eine Methode, bei der zwei Varianten (A und B) einer Komponente (z. B. Button-Farbe, Feature-Layout) gleichzeitig unterschiedlichen Benutzergruppen präsentiert werden, um zu messen, welche Variante eine vordefinierte Zielmetrik (z. B. Conversion-Rate, Klicks) statistisch signifikant besser erfüllt.

* **Vorgehensweise:** Benutzerverkehr zufällig aufteilen, Interaktionsdaten sammeln und anhand von Messwerten entscheiden, welche Version gewinnt.
* **Zweck:** Ermöglicht datengestützte Entscheidungen über Funktionen oder Design anstelle von Meinungen.
* **Verbindung zu CD:** Feature Flags und Deployment-Strategien (Canary) werden genutzt, um A/B-Tests in der Live-Umgebung durchzuführen.

![AB Diagram](https://github.com/nikhilango/M324/blob/main/Images/Ab-test.jpg)

Quelle: https://www.agile-academy.com/de/agiles-lexikon/a-b-testing/, https://de.wikipedia.org/wiki/A/B-Test

## 6. Was sind Feature Toggles?
Feature Toggles ermöglichen es, Features im Code zur Laufzeit an- oder auszuschalten, ohne neuen Code deployen zu müssen.
Das Grundprinzip ist die Entkopplung von Deployment und Release.

Anstatt darauf zu warten, dass ein Feature komplett fertiggestellt ist, bevor der Code in die Hauptlinie integriert wird, wird der Code kontinuierlich 
integriert, aber durch logische Schalter vor dem Endnutzer verborgen. Im Code wird eine bedingte Anweisung (z. b. ein if/else-Block) um das neue Feature 
gewickelt. Diese Bedingung prüft, ob das Feature für den aktuellen Nutzer aktiv sein soll.

Dies ermöglicht Teams, unfertige Funktionen sicher in die Produktionsumgebung zu bringen (Dark Launching), ohne den Betrieb zu stören. Zudem erlauben 
Toggles das gezielte Freischalten von Funktionen für bestimmte Nutzergruppen (Canary Releases) oder das schnelle Abschalten fehlerhafter Funktionen ohne 
erneutes Deployment (Kill Switch).

Quellen:

Martin Fowler: https://martinfowler.com/articles/feature-toggles.html 
Atlassian: https://www.atlassian.com/solutions/devops/integrations/feature-flags  

## 7. Was sind Rollback Strategien?
Ein Rollback ist der Prozess, ein System nach einem fehlerhaften Deployment so schnell wie möglich in einen vorherigen, stabilen Zustand 
zurückzuversetzen. Es ist im Grunde die Notbremse im Continuous Deployment. Die Strategie unterscheidet sich je nach verwendeter Deployment-Methode. In 
klassischen Umgebungen bedeutet ein Rollback oft ein "Redeploy": Die vorherige Version des Artefakts muss erneut durch die Pipeline geschleust und 
installiert werden, was zeitintensiv sein kann (Time-to-Recovery ist hoch).

In modernen Container- oder Cloud-Umgebungen setzt man eher auf das Umschalten von Routen. Da die alte Version oft noch parallel zur neuen Version auf einem 
anderen Server oder Container-Set läuft, besteht der Rollback lediglich darin, den Load Balancer wieder auf die alte Umgebung zeigen zu lassen. Jegliche 
Ausfallzeit wird auf Sekunden reduziert. Eine besondere Herausforderung stellen Datenbanken dar: Da Datenänderungen schwer rückgängig zu machen sind, gilt 
hier oft die Strategie "Roll Forward" (schnelles Fixen des Fehlers) statt "Roll Back", oder es muss strikt auf Abwärtskompatibilität geachtet werden.

Quellen

Google Cloud Architecture Center: https://cloud.google.com/architecture/devops/devops-tech-deployment-automation#roll_back_mechanisms 
Octopus Deploy: https://octopus.com/blog/rollback-strategies

## 8. Was ist Continuous Monitoring und wie wird es umgesetzt?
Continuous Monitoring erweitert die klassische Überwachung zu einer umfassenden "Observability" des gesamten Systemzustands in Echtzeit. Das Ziel ist es, 
nicht nur Ausfälle zu bemerken, sondern proaktiv Leistungsprobleme, Sicherheitslücken oder negatives Nutzerverhalten zu erkennen, oft bevor der Kunde es 
merkt. Es schliesst den Regelkreis von DevOps, indem Daten aus der Produktion direkt zurück in die Entwicklung fliessen, um zukünftige Versionen zu 
verbessern.

Die technische Umsetzung auf drei Säulen:

Metriken: Quantitative Daten wie CPU-Auslastung, die Trends aufzeigen.

Logs: Detaillierte Protokolle von Ereignissen, die bei der Fehlersuche helfen.

Tracing: Die Verfolgung einer einzelnen Anfrage über verschiedene Microservices hinweg, um Bottlenecks zu identifizieren. Tools wie Prometheus oder Grafana 
sammeln diese Daten zentral und alarmieren die Entwickler automatisch bei Anomalien.

Quellen

Dynatrace: https://www.dynatrace.com/de/news/blog/was-ist-continuous-monitoring/

Splunk: https://www.splunk.com/en_us/blog/learn/continuous-monitoring.html

## 9. Wie werden Passwörter sicher gespeichert?
In der Softwareentwicklung gilt der Grundsatz: Keine Geheimnisse im Quellcode. Passwörter, API-Schlüssel oder Zertifikate dürfen niemals im Klartext in 
Dateien wie config.js oder im Git-Repository gespeichert werden, da jeder mit Lesezugriff diese stehlen könnte. Stattdessen wird das Konzept des "Secrets 
Management" angewendet.

Dabei werden Geheimnisse entweder als environment variables erst beim Starten des Servers in den Arbeitsspeicher geladen oder in 
speziellen "Tresoren" wie HashiCorp Vault oder AWS Secrets Manager aufbewahrt. Die Anwendung authentifiziert sich beim Start gegenüber dem Tresor 
und erhält das Passwort nur temporär für die Laufzeit. Benutzerpasswörter in Datenbanken dürfen niemals im Klartext gespeichert 
werden, sondern müssen durch kryptografische Hash-Funktionen (wie bcrypt oder Argon2) und "Salting" (Zufallszeichen) unlesbar gemacht werden.

Quellen

OWASP: https://cheatsheetseries.owasp.org/cheatsheets/Secrets_Management_Cheat_Sheet.html

HashiCorp: https://www.hashicorp.com/products/vault/secrets-management

## 10. Welche Arten von Deployment gibt es? Geben Sie alle Ihre Ideen an. Sie müssen dann nicht alle umsetzen, aber finden Sie heraus was alles möglich ist (z.B. Container via Docker, Container via Docker swarm, Direkt auf Server code kompilieren, etc). geben Sie dabei jeweils auch an, welche Software/Umgebung dazu notwendig ist.)

Die Bereitstellung von Software hat sich von manuellen Eingriffen zu hochautomatisierten Abstraktionen entwickelt. Man kann die Deployment-Arten anhand des 
Grades der Kontrolle versus des Komforts unterscheiden.

Am unteren Ende steht das Bare Metal / VM Deployment. Hier wird Code direkt auf einem Betriebssystem installiert. Dies bietet maximale Kontrolle über die 
Hardware, erfordert aber hohen Wartungsaufwand (Patches, Konfiguration). 
Der nächste Evolutionsschritt ist das Container Deployment (z.B. via Docker & Kubernetes). Hier wird die Anwendung inklusive aller Abhängigkeiten in ein 
isoliertes Paket verpackt. Dies löst das Problem "Works on my machine", da der Container überall gleich läuft. 
Am oberen Ende der Abstraktion stehen Serverless und PaaS (Platform as a Service). Hierbei kümmert sich der Entwickler gar 
nicht mehr um Server oder Container, sondern lädt nur noch den Code oder einzelne Funktionen hoch. Die Cloud-Plattform übernimmt Skalierung und Betrieb 
automatisch.

Quellen

Red Hat: https://www.redhat.com/en/topics/cloud-native-apps/what-is-serverless

Docker: https://www.docker.com/resources/what-container/

AWS: https://aws.amazon.com/types-of-cloud-computing/dazu notwendig ist.
