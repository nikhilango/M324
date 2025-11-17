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
[IBM: What is SDLC?](https://www.ibm.com/topics/software-development-life-cycle)
[GeeksForGeeks: Software Development Life Cycle](https://www.geeksforgeeks.org/software-development-life-cycle-sdlc/)
[Microsoft Learn: Application Lifecycle Management Overview](https://learn.microsoft.com/en-us/devops/plan/what-is-alm)
[Atlassian: Software Development Lifecycle](https://www.atlassian.com/software-development/software-development-life-cycle)
[IBM Cloud Education: SDLC Phases](https://www.ibm.com/cloud/learn/sdlc)
[ISO/IEC 12207 – Software Lifecycle Processes](https://www.iso.org/standard/63712.html)
[PMI – Project Management Body of Knowledge (PMBOK)](https://www.pmi.org/pmbok-guide-standards/foundational/pmbok)
[Atlassian Jira Guide](https://www.atlassian.com/software/jira/guides)
[IEEE Software Lifecycle Standards Übersicht](https://standards.ieee.org/ieee/12207/5355/)

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

### Vergleich: SDLC vs. DevOps Lifecycle
### Ansatz & Denkweise

| Aspekt | SDLC | DevOps Lifecycle |
|--------|------|------------------|
| **Ziel** | Fertiges Produkt nach definiertem Prozess | Schnelle, stabile und kontinuierliche Releases |
| **Fokus** | Entwicklung | Entwicklung + Betrieb |
| **Teamstruktur** | Trennung zwischen Entwicklung & Betrieb | Gemeinsame Verantwortung (“You build it, you run it”) |
| **Arbeitsweise** | Linear/sequentiell oder iterativ | Kontinuierlicher Kreislauf |
| **Feedback** | Spät | Früh und dauerhaft |

## Prozesse & Phasen

**SDLC:** Planung → Analyse → Design → Implementierung → Test → Deployment → Wartung

**DevOps-Kreislauf:** Plan → Code → Build → Test → Release → Deploy → Operate → Monitor → zurück zu Plan


## Ziele

| SDLC | DevOps |
|------|--------|
| Stabilität & Qualität | Geschwindigkeit & Zuverlässigkeit |
| Strukturierter Ablauf | Automatisierung & Feedback |
| Minimierung von Risiken | Verkürzte Release-Zyklen |


## Auswirkungen auf Entwicklung, Bereitstellung und Betrieb

### 1. Entwicklung

**SDLC:**  Planungsgetrieben, Releases eher selten.

**DevOps:**  Kleine, schnelle Iterations (Continuous Integration).

### 2. Bereitstellung

**SDLC:**  Deployment oft erst am Ende.

**DevOps:**  Automatisiert über CI/CD-Pipelines; teilweise mehrere Deployments täglich.

### 3. Betrieb

**SDLC:**  
Betrieb separat, Feedback kommt spät.

**DevOps:**  
Monitoring und Logs fliessen direkt in Verbesserungen ein.


## Quellen

- https://www.geeksforgeeks.org/software-development-life-cycle-sdlc
- https://medium.com/@nalawade1000work/sdlc-vs-devops-25ff2dd0accf
- https://octopus.com/blog/devops-versus-sdlc
- https://www.youtube.com/watch?v=scEDHsr3APg
- https://www.youtube.com/watch?v=Fi3_BjVzpqk&pp=ygUOc2RsYyB2cyBkZXZvcHM%3D
- chat.openai.com

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
