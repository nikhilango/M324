# DevOps Prozesse

### 1. Was ist SDLC (Software Development Life Cycle), und wie gliedert sich dieser Prozess?
Der Software Development Life Cycle (SDLC) ist ein strukturierter Prozess zur Planung, Entwicklung, Bereitstellung und Wartung von Software.
Er sorgt für klare Abläufe, hohe Qualität und geringere Risiken.
Der SDLC besteht aus mehreren Phasen, die je nach Modell (z. B. Wasserfall, Agile, V-Modell) unterschiedlich organisiert sein können.

- Welche Schritte umfasst der SDLC, und wie tragen diese zur Entwicklung und Bereitstellung von Software bei?  
  1. Planung & Analyse: Anforderungen sammeln, Ziele definieren, Risiken bewerten. → Sorgt für klare Projektgrundlagen.
  2. Design: Architektur, Datenmodelle und technische Lösungen werden festgelegt. → Macht das System skalierbar und wartbar.
  3. Implementierung: Entwickler schreiben den Code. → Realisierung der Funktionen.
  4. Test: Fehler finden, Qualität sichern. → Stabilität und Zuverlässigkeit erhöhen.
  5. Deployment: Software in Betrieb nehmen. → Übergang zur echten Nutzung.
  6. Wartung: Fehler beheben, Updates liefern. → Langlebigkeit und Sicherheit des Systems.

- Wie wird der SDLC typischerweise in einem Projekt angewendet und gesteuert?  
  Der SDLC wird durch Projektmanagement-Methoden wie Scrum, Kanban, PRINCE2 oder Wasserfall gesteuert.
  Projektleitung überwacht Zeitplan, Budget und Risiko, während Entwickler und Tester die Phasen durchführen.
  Tools wie Jira, Azure DevOps oder GitHub unterstützen Planung, Dokumentation und Automatisierung.
  Regelmäßige Reviews stellen sicher, dass jede Phase korrekt abgeschlossen wird.

#### Quellen
[GeeksForGeeks: Software Development Life Cycle](https://www.geeksforgeeks.org/software-development-life-cycle-sdlc/)
[What is SDLC Video](https://www.youtube.com/watch?v=Fi3_BjVzpqk&t=1s)

---

### 2. Was ist der DevOps Lifecycle, und wie ist er strukturiert?
- Wie ist der DevOps Lifecycle definiert, und welche Kernphasen beinhaltet er?  
- Welche Ziele verfolgt der DevOps Lifecycle im Vergleich zu traditionellen Entwicklungsansätzen?

Der **DevOps Lifecycle** stellt eine prozessuale und technologische Erweiterung der agilen Prinzipien dar. Es schliesst die Lücke zwischen 
Softwareentwicklung (**Dev**) und IT-Betrieb (**Ops**).

#### Definition und Kernphasen

Der DevOps Lifecycle wird typischerweise als eine **kontinuierliche Feedbackschleife** dargestellt. Diese Schleife verbindet die Entwicklung und Betrieb eng 
miteinander. Es fördert ständige Zusammenarbeit, Automatisierung und Iteration über den gesamten Lebenszyklus der Software.

Die **Kernphasen**:

* **1. Discover:** Zur Vorbereitung auf den nächsten Sprint müssen Teams Ideen finden, organisieren und priorisieren. Agile kann DevOps-Teams dabei als Orientierung dienen. (Beispiel: Workshops aufsetzen)
* **2. Plan:** Definition der Funktionen, Services und Ziele. Planung der Arbeitsabläufe, oft agil, um die Qualität und Geschwindigkeit zu verbessern. 
* **3. Build:** Schreiben und Speichern des Programmcodes in der Versionskontrolle (z.B. Git).
* **4. Test:** Tiefgreifendere automatisierte und manuelle Tests (Performance, Sicherheit) in separaten Umgebungen.
* **5. Deploy:** Automatisierte Bereitstellung der Artefakte in den Produktionsumgebungen. Dank CD können Teams regelmässig und automatisch Funktionen veröffentlichen. (z.B. Terraform)
* **6. Operate:** Der Betrieb der Anwendung in der Live-Umgebung. (z.B. Ansible)
* **7. Observe:** Probleme identifizieren und beheben. Team über Änderungen oder Ausfällen informieren, um Services weiter am Laufen zu halten. (z.B. Prometheus)
* **8. Continuous Feedback:** Durch kontinuierliches Feedback können Teams ihre Prozesse optimieren und Kundenrückmeldungen einbeziehen, um den nächsten Release zu verbessern.

![DevOps_LifeCycle](https://github.com/nikhilango/M324/blob/main/Images/T3_DevOpsLifeCycle.png)
Quelle: (DevOpsLifeCycle)[https://www.atlassian.com/de/devops]

#### Ziele im Vergleich zu traditionellen Entwicklungsansätzen

Der DevOps Lifecycle zielt darauf ab, die **Geschwindigkeit, Qualität, Effizienz und Sicherheit** der Softwarebereitstellung signifikant zu steigern.

| Zielbereich | DevOps Lifecycle | Traditionelle Ansätze (z.B. Wasserfall) |
| :--- | :--- | :--- |
| **Zusammenarbeit & Kultur** | **Kollaboration** zwischen Dev und Ops, gemeinsame Verantwortlichkeit. | **Silos** zwischen Entwicklung und Betrieb; getrennte Verantwortlichkeiten. |
| **Geschwindigkeit** | **Schnelle Iterationen** durch CI/CD und Automatisierung. Häufige, kleine Releases. | Starre Freigabeprozesse. Seltene, grosse Releases. |
| **Qualität & Risiko** | **Kontinuierliche Tests** und frühzeitiges Feedback. Kleinere, häufigere Updates. **Automatisierung** minimiert menschliche Fehler. | Tests erfolgen oft erst spät. Grosse Änderungen bergen höheres Risiko. |
| **Feedback** | **Kurze Feedbackschleifen** durch kontinuierliche Überwachung und schnelle Anpassung. | Lange Feedbackschleifen. Anpassungen sind aufwändig und teuer. |
| **Automatisierung** | **Hoher Automatisierungsgrad** in allen Phasen (Build, Test, Deployment). | Hoher Anteil manueller, fehleranfälliger Aufgaben. |

**Quellen:**
* Atlassian: (DevOpsLifeCycle)[https://www.atlassian.com/de/devops]
* digicomp: (Unterschied_DevOps&Klassisch)[https://digicomp.ch/blog/2023/12/17/devops-vs-klassische-entwicklung-versiegt-der-wasserfall]
* ChatGPT

---

## 3. Unterschiede zwischen SDLC und DevOps Lifecycle

Der wesentliche Unterschied zwischen dem klassischen Software Development Life Cycle (SDLC) und dem DevOps Lifecycle liegt nicht nur in den Werkzeugen, sondern in der **Geometrie der Zusammenarbeit**. Während der SDLC oft als Schritt-für-Schritt-Anleitung verstanden wird, bricht DevOps diese Silos auf und formt einen kontinuierlichen Kreislauf.

#### 1. Die visuelle Transformation

Um die beiden Ansätze zu verstehen, hilft ein Blick auf ihre schematische Darstellung.

##### SDLC: Die Treppe (oder Linie)
Im klassischen SDLC (insbesondere Wasserfall) erfolgt die Arbeit sequenziell. Jede Phase ist abgeschlossen, bevor die nächste beginnt. Es gleicht einer Treppe: Man steigt von der Planung bis zur Wartung hinab. Rücksprünge sind teuer und schwierig.

*Phasen:* Planung $\rightarrow$ Analyse $\rightarrow$ Design $\rightarrow$ Implementierung $\rightarrow$ Test $\rightarrow$ Deployment $\rightarrow$ Wartung

![SDLC Wasserfall Modell](https://upload.wikimedia.org/wikipedia/commons/e/e2/Waterfall_model.svg)
*(Quelle: Wikimedia Commons)*

##### DevOps: Die Unendlichkeitsschleife (Infinity Loop)
Der DevOps-Lifecycle wird als liegende Acht ($\infty$) dargestellt. Es gibt keinen Anfang und kein Ende. Der Betrieb (Operations) füttert die Planung (Development) direkt mit Daten für den nächsten Zyklus.

*Der Kreislauf:* Plan $\rightarrow$ Code $\rightarrow$ Build $\rightarrow$ Test $\rightarrow$ Release $\rightarrow$ Deploy $\rightarrow$ Operate $\rightarrow$ Monitor $\rightarrow$ *zurück zu Plan*

![DevOps Toolchain Loop](https://upload.wikimedia.org/wikipedia/commons/0/05/Devops-toolchain.svg)
*(Quelle: Wikimedia Commons)*

---

#### 2. Direkter Vergleich: Die Philosophie

Anstatt vieler kleiner Listenpunkte betrachten wir die Unterschiede im direkten Vergleich der Kernbereiche.

| Merkmal | Klassischer SDLC | DevOps Lifecycle |
| :--- | :--- | :--- |
| **Hauptziel** | Stabilität durch strikte Prozesskontrolle und Risikominimierung *vor* dem Release. | Geschwindigkeit und Zuverlässigkeit durch Automatisierung und schnelle Feedbackschleifen. |
| **Team-Kultur** | **Silos:** Entwicklung (Dev) und Betrieb (Ops) sind getrennt. "Throw over the wall"-Mentalität. | **Shared Ownership:** "You build it, you run it". Gemeinsame Verantwortung für Features und Stabilität. |
| **Arbeitsweise** | **Sequenziell:** Große Batches, lange Planungsphasen, seltene Releases. | **Iterativ & Kontinuierlich:** Kleine Änderungen fließen ständig durch CI/CD-Pipelines. |
| **Feedback** | **Spät:** Oft erst nach dem Deployment oder durch Endanwender-Tickets. | **Sofort:** Durch automatisiertes Testing und Monitoring in Echtzeit. |

---

#### 3. Auswirkungen auf den Arbeitsalltag

Wie verändert sich die tägliche Arbeit in den drei Hauptbereichen?

##### Entwicklung (Development)
Im **SDLC** ist die Entwicklung oft "planungsgetrieben". Entwickler arbeiten lange an umfangreichen Feature-Sets, die erst am Ende integriert werden. Dies führt oft zur "Integration Hell", wenn Code nach Monaten nicht zusammenpasst.

Im **DevOps-Ansatz** ändert sich dies zu *Continuous Integration*. Entwickler checken Code mehrmals täglich ein. Der Fokus liegt auf kleinen, handhabbaren Iterationen, die sofort getestet werden.

###### Bereitstellung (Deployment)
Die Bereitstellung ist im **SDLC** oft ein "Event" – ein geplantes Wochenende, an dem das System aktualisiert wird (oft mit Downtime verbunden).

**DevOps** macht das Deployment zur Routine ("Non-Event"). Durch *Continuous Delivery/Deployment* (CI/CD) werden Änderungen automatisiert in die Produktion gebracht. Ein Release ist kein Risiko mehr, sondern ein Standardprozess, der teilweise mehrfach täglich stattfindet.

##### Betrieb & Monitoring (Operations)
Während der Betrieb im **SDLC** oft nur reagiert, wenn Fehler auftreten (Ticketsystem), ist er bei **DevOps** proaktiv eingebunden.

> **Wichtig:** Monitoring ist nicht mehr nur Überwachung, sondern die Basis für neue Features. Logs und Performance-Daten fließen direkt zurück an die Entwickler, um den Code für den nächsten Zyklus ("Plan") zu optimieren.

#### Quellen

- https://www.geeksforgeeks.org/software-development-life-cycle-sdlc
- https://medium.com/@nalawade1000work/sdlc-vs-devops-25ff2dd0accf
- https://octopus.com/blog/devops-versus-sdlc
- https://www.youtube.com/watch?v=scEDHsr3APg
- https://www.youtube.com/watch?v=Fi3_BjVzpqk&pp=ygUOc2RsYyB2cyBkZXZvcHM%3D
- chat.openai.com
- google.gemini.com

### 4. Was ist ein MVP (Minimum Viable Product), und welche Bedeutung hat es im DevOps Lifecycle?
Ein Minimum Viable Product (MVP) ist ein strategisches Werkzeug der sogenannten ¨Lean-Startup-Methode". Seine formale Definition beschreibt es als die Version eines Produkts, die es einem Team erlaubt, mit dem geringsten Aufwand die maximale Menge an "validiertem Lernen" über Kunden zu sammeln. Statt dass man ein vollständiges Produkt  baut, zielt es darauf ab, nur die zentralen Funktionalitäten so minimal wie möglich zu gestalten, damit man es schon Früh genug von Early Adopters testen kann. Der Fokus liegt auf der schnellen Validierung von Ideen und dem Sammeln von Feedback, nicht auf sofortigem Umsatz.

![photo](https://github.com/nikhilango/M324/blob/main/Images/Minimum-Viable-Product-ig.webp)

Mit dem Devops-Lifecycle ist das MVP perfekt um die Geschwindigkeit des Dev Prozesses zu verbessern. Das MVP dient als kleinste zu testende Einheit (das "Was") und der DevOps liefert die Kultur und automatisierte CI/CD-Pipeline für extrem schnelle Bereitstellung (das "Wie"). Das MVP deckt auch alle "Three Ways" ab: Das MVP wird schnell erstellt ("First Way", Build), Nutzerfeedback wird durch Monitoring gesammelt ("Second Way", Measure), und die Erkentnisse fliessen direkt wieder in die nächste Iteration ein ("Third Way", Learn). Alles zusammen können grosse, riskante Releases, durch einen kontinuerlichen Prozess von Iteration und Feedback, ersetzt werden.

**Quellen:** [Wikipedia](https://en.wikipedia.org/wiki/Minimum_viable_product), [objectbay.com](https://www.objectbay.com/blog/was-ist-mvp), [atlassian.com](https://www.atlassian.com/agile/product-management/minimum-viable-product), [learnstartup.co](https://leanstartup.co/resources/articles/what-is-an-mvp/),   

## Bewertung

- Inhaltliche Vollständigkeit und Korrektheit
- Quellen: Mehrere Quellenangaben pro Thema. Quellen müssen angegeben werden
- Formatierung des Dokuments und Bildunterstützung
- Zusätzliche Ausführungen pro Thema (Vertiefung, Gegenüberstellung, Anwendungsbeispiele, etc)
