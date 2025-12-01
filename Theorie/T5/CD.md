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
## 7. Was sind Rollback Strategien?
## 8. Was ist Continuous Monitoring und wie wird es umgesetzt?
## 9. Wie werden Passwörter sicher gespeichert?
## 10. Welche Arten von Deployment gibt es? Geben Sie alle Ihre Ideen an. Sie müssen dann nicht alle umsetzen, aber finden Sie heraus was alles möglich ist (z.B. Container via Docker, Container via Docker swarm, Direkt auf Server code kompilieren, etc). geben Sie dabei jeweils auch an, welche Software/Umgebung dazu notwendig ist.
