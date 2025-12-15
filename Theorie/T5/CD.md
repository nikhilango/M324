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
# 2. Was ist der Unterschied zwischen Continuous Deployment und Continuous Delivery?

Sowohl **Continuous Delivery (CDEL)** als auch **Continuous Deployment (CDEP)** sind logische Erweiterungen von Continuous Integration (CI) und zielen darauf ab, den Weg vom Code-Commit bis zur Produktionsumgebung zu automatisieren. Sie unterscheiden sich primär durch das **finale Gate** zur Live-Umgebung.

### Continuous Delivery (CDEL)

Bei CDEL ist der Code nach dem Durchlaufen der automatisierten Pipeline (Build, Test, Staging) **jederzeit bereit für die Veröffentlichung**. Er liegt jedoch hinter einem **manuellen Freigabe-Gate**.

* **Charakteristik:** Alle Phasen bis zur finalen Bereitstellung sind automatisiert. Die manuelle Freigabe, oft durch einen Product Owner oder das Operations-Team, dient dazu, den *Geschäftszeitpunkt* des Releases zu kontrollieren.
* **Vorteil:** Es ermöglicht Planbarkeit und Koordination mit Marketing-Aktivitäten oder Business-Anforderungen. Das Unternehmen behält die Kontrolle darüber, *wann* neue Features live geschaltet werden.
* **Automatisierungsgrad:** Hoch, aber nicht maximal. Die Entscheidung zur Produktivsetzung ist menschlich.

### Continuous Deployment (CDEP)

CDEP stellt die höchste Stufe der Automatisierung dar. Jede Code-Änderung, die alle automatisierten Tests und Qualitäts-Gates erfolgreich durchläuft, wird **ohne menschliches Zutun** direkt in die Produktion **bereitgestellt und freigeschaltet**.

* **Charakteristik:** Es gibt **keine manuelle Bremse**. Der Prozess ist durchgehend automatisiert. Der einzige Stopp-Mechanismus ist ein fehlgeschlagener automatisierter Test oder ein Alarm des Continuous Monitorings nach dem Rollout.
* **Vorteil:** Dies ermöglicht die **kürzeste Time-to-Market** und den schnellsten Feedback-Loop. Es eliminiert Release-Engpässe, die durch menschliche Entscheidungen entstehen.
* **Voraussetzung:** Extrem robustes Continuous Monitoring und nahezu **100%ige Abdeckung durch automatisierte Tests**.

| Gegenüberstellung | Continuous Delivery (CDEL) | Continuous Deployment (CDEP) |
| :--- | :--- | :--- |
| **Freigabe-Gate** | Manuell (Mensch entscheidet über Go-Live) | Automatisch (Software entscheidet über Go-Live) |
| **Risikomanagement** | Business-Kontrolle des Zeitpunkts | Technisches Vertrauen in Tests/Monitoring |



***
Quellen: 
* https://www.atlassian.com/continuous-delivery/principles/continuous-integration-vs-delivery-vs-deployment
* https://www.computerweekly.com/de/ratgeber/Zwischen-Continuous-Delivery-und-Continuous-Deployment-waehlen
* https://martinfowler.com/articles/continuousIntegration.html
***

---

## 3. Was sind die Vor- und Nachteile von Continuous Delivery und Continuous Deployment?

### Vorzüge und Wertbeitrag

Beide Ansätze teilen den grundlegenden Vorteil, dass sie das **Risiko pro Release massiv reduzieren**, da sie nur kleine Code-Batches freigeben.

**Continuous Delivery (CDEL)** bietet den entscheidenden Vorteil der **Planbarkeit und Business-Kontrolle**.

* Das Operations-Team wird von manuellem Bereitstellungsstress entlastet, während das Business den optimalen Zeitpunkt für die Veröffentlichung wählen kann, um beispielsweise Marketing-Aktionen oder regulatorische Fristen optimal zu erfüllen.

**Continuous Deployment (CDEP)** ist auf **maximale Geschwindigkeit** ausgelegt.

* Es ermöglicht die **kürzeste Time-to-Market**. Neue Features sind sofort für die Kunden verfügbar.
* Der Feedback-Loop ist der schnellste: Probleme in der Produktion werden unmittelbar nach dem Deployment sichtbar, was eine schnelle Fehlerbehebung oder einen sofortigen Rollback erlaubt.
* Es führt zu einer **Entlastung von Release-Druck** im Operations-Team, da der gesamte Prozess automatisiert ist.

### Herausforderungen und Nachteile

Die Nachteile sind eng mit der jeweiligen Automatisierungsstufe verbunden.

Bei **Continuous Delivery (CDEL)** liegt das Problem im manuellen Gate:

* **Verzögerung:** Die Notwendigkeit der **manuellen Freigabe** kann den Prozess verzögern und den Geschwindigkeitsvorteil der automatisierten Pipeline wieder reduzieren. Die manuelle Entscheidung kann zum Engpass werden.
* **Aufwand:** Es erfordert ständigen Aufwand für menschliche Entscheidungen, Koordination und Sign-offs.

**Continuous Deployment (CDEP)** stellt hohe technische Anforderungen:

* **Hohes Risiko bei Fehlern:** Aufgrund der vollständigen Automatisierung bedeutet ein Fehler in der Testsuite, dass die fehlerhafte Funktion **sofort live** geht. Dies erfordert ein **extrem hohes Vertrauen** in die automatisierte Testabdeckung (nahezu 100 %).
* **Hoher initialer Aufwand:** Die Einrichtung der robusten, ausfallsicheren Pipeline, des umfassenden Monitoring-Systems und der automatischen Rollback-Mechanismen ist initial sehr zeit- und ressourcenintensiv.

***
Quellen: 
* https://www.abtasty.com/de/resources/guide-continuous-integration-delivery/
* https://entwickler.de/continuous-delivery/continuous-deployment-fluch-oder-segen
* https://aws.amazon.com/types-of-cloud-computing/
***

---

## 4. Was sind die folgenden Deployment Strategien und wie werden sie umgesetzt?

Deployment-Strategien definieren, wie eine neue Softwareversion in die Produktionsumgebung überführt wird, um die Verfügbarkeit zu maximieren und das Risiko für die Endbenutzer zu minimieren.

### 💙 Blue/Green Deployment (Blau/Grün)

**Definition und Ziel:** Beim Blue/Green Deployment werden **zwei identische Produktionsumgebungen** (*Blue* und *Green*) parallel betrieben. Nur eine davon ist zu einem Zeitpunkt aktiv und erhält den gesamten Live-Verkehr. Das primäre Ziel ist ein **sofortiger Switch-over** zur neuen Version.

**Umsetzung:**
1.  Die derzeit aktive Umgebung ist z. B. **Blue**. Die neue Anwendungsversion (**Green**) wird vollständig auf der inaktiven Umgebung installiert und intern getestet.
2.  Sobald die neue Version in Green als stabil eingestuft wird, wird der **Load Balancer** angewiesen, den gesamten Live-Verkehr sofort von Blue auf Green umzuleiten. Dieser Wechsel erfolgt beinahe augenblicklich (**Zero-Downtime**).
3.  Die Blue-Umgebung bleibt für kurze Zeit als **sofortige Rollback-Option** aktiv. Bei kritischen Problemen wird der Load Balancer einfach wieder auf Blue zurückgeschaltet.

**Vorteile:** Zero-Downtime und sehr schnelles Rollback.
**Nachteile:** Benötigt die doppelten Ressourcen (zwei komplette Umgebungen).



### 🐤 Canary Deployment (Kanarienvogel)

**Definition und Ziel:** Ähnlich dem Einsatz eines Kanarienvogels im Bergbau zur Risikoprüfung, wird die neue Version schrittweise nur für einen **kleinen, isolierten Prozentsatz der Live-Benutzer** freigeschaltet. Das Ziel ist das **Testen unter realen Bedingungen** mit minimalem Risiko ("Blast Radius").

**Umsetzung:**
1.  Die neue Version wird in Produktion deployt, aber das **Routing** (oft durch den Load Balancer oder Service Mesh) leitet anfangs nur einen geringen Anteil des Datenverkehrs (z. B. 1–5 %) dorthin.
2.  Es erfolgt ein intensives **Echtzeit-Monitoring** der Metriken (Fehlerraten, Performance, Nutzerverhalten) der Canary-Gruppe.
3.  Ist die neue Version stabil, wird der Traffic-Anteil in Phasen schrittweise erhöht (z. B. 20 %, dann 50 %, dann 100 %).
4.  Bei kritischen Fehlern wird der Traffic sofort von der Canary-Version ab- und auf die stabile alte Version zurückgeleitet.

**Vorteile:** Minimales Risiko und frühzeitige Fehlererkennung unter Produktionslast.
**Nachteile:** Langsamerer Rollout-Prozess; komplexeres Traffic-Management nötig.



### Rolling Deployment (Rolling Update)

**Definition und Ziel:** Bei dieser Methode werden Instanzen der neuen Version **schrittweise und inkrementell** gegen die Instanzen der alten Version ausgetauscht. Ziel ist es, die Service-Verfügbarkeit während des Updates aufrechtzuerhalten, ohne doppelte Infrastruktur zu benötigen.

**Umsetzung:**
1.  Die Pipeline ersetzt eine kleine Anzahl alter Server/Container durch neue. Die übrigen alten Instanzen bedienen weiterhin den Verkehr.
2.  Nach erfolgreicher Validierung der neuen Instanzen wird der Vorgang fortgesetzt, bis alle alten Instanzen ersetzt sind.
3.  Während des gesamten Prozesses sind immer sowohl alte als auch neue Versionen gleichzeitig aktiv, was eine strenge **Abwärtskompatibilität** zwischen Versionen erfordert.

**Vorteile:** Effizienter in Bezug auf die Ressourcennutzung (keine doppelten Umgebungen wie bei Blue/Green).
**Nachteile:** Rollbacks sind zeitaufwendiger und der Status der Anwendung ist während des Rollouts inkonsistent.

***
Quellen: 
* https://octopus.com/devops/software-deployments/blue-green-vs-canary-deployments/
* https://www.pineparks.ch/deployment-strategien-fuer-apps/
* https://www.redhat.com/en/topics/cloud-native-apps/what-is-rolling-update
***

---
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

![Feature Toggle](https://github.com/nikhilango/M324/blob/main/Images/FeatureToggles.png)

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

![Rollback](https://github.com/nikhilango/M324/blob/main/Images/Rollback.png)

Quellen

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

![CM](https://github.com/nikhilango/M324/blob/main/Images/ContinuousMonitoring.png)

Quellen

Splunk: https://www.splunk.com/en_us/blog/learn/continuous-monitoring.html

ChatGPT

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

AWS: https://learn.microsoft.com/en-us/azure/key-vault/general/basic-concepts

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

![Deplyoment](https://github.com/nikhilango/M324/blob/main/Images/Deployment.png)

Quellen

Red Hat: https://www.redhat.com/en/topics/cloud-native-apps/what-is-serverless

Docker: https://www.docker.com/resources/what-container/

AWS: https://aws.amazon.com/types-of-cloud-computing/
