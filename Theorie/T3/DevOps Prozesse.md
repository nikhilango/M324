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

### 2. Was ist der DevOps Lifecycle, und wie ist er strukturiert?
- Wie ist der DevOps Lifecycle definiert, und welche Kernphasen beinhaltet er?  
- Welche Ziele verfolgt der DevOps Lifecycle im Vergleich zu traditionellen Entwicklungsansätzen?

Der **DevOps Lifecycle** stellt eine prozessuale und technologische Erweiterung der agilen Prinzipien dar. Es schliesst die Lücke zwischen 
Softwareentwicklung (**Dev**) und IT-Betrieb (**Ops**).

#### Definition und Kernphasen

Der DevOps Lifecycle wird typischerweise als eine **kontinuierliche Feedbackschleife** dargestellt. Diese Schleife verbindet die Entwicklung und Betrieb eng 
miteinander. Es fördert ständige Zusammenarbeit, Automatisierung und Iteration über den gesamten Lebenszyklus der Software.

Die **Kernphasen**:

* **1. Discover:** Zur Vorbereitung auf den nächsten Sprint müssen Teams Ideen finden, organisieren und priorisieren. Agile kann DevOps-Teams dabei als Orientierung dienen
* **2. Plan:** Definition der Funktionen, Services und Ziele. Planung der Arbeitsabläufe, oft agil, um die Qualität und Geschwindigkeit zu verbessern.
* **3. Build:** Schreiben und Speichern des Programmcodes in der Versionskontrolle (z.B. Git).
* **4. Test:** Tiefgreifendere automatisierte und manuelle Tests (Performance, Sicherheit) in separaten Umgebungen.
* **5. Deploy:** Automatisierte Bereitstellung der Artefakte in den Produktionsumgebungen. Dank CD können Teams regelmässig und automatisch Funktionen veröffentlichen 
* **6. Operate:** Der Betrieb der Anwendung in der Live-Umgebung.
* **7. Observe:** Probleme identifizieren und beheben. Team über Änderungen oder Ausfällen informieren, um Services weiter am Laufen zu halten.
* **8. Continuous Feedback:** Durch kontinuierliches Feedback können Teams ihre Prozesse optimieren und Kundenrückmeldungen einbeziehen, um den nächsten Release zu verbessern.

![DevOps_LifeCycle]()

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

### 3. Welche Unterschiede gibt es zwischen dem SDLC und dem DevOps Lifecycle?
- Wie unterscheiden sich die Ansätze, Prozesse und Ziele dieser beiden Modelle?  
- Welche Auswirkungen haben diese Unterschiede auf die Entwicklung, Bereitstellung und den Betrieb von Software?  

### 4. Was ist ein MVP (Minimum Viable Product), und welche Bedeutung hat es im DevOps Lifecycle?
- Wie wird ein MVP definiert, und welche Kernmerkmale zeichnen es aus?  
- Welche Rolle spielt das MVP in den verschiedenen Phasen des DevOps Lifecycles, insbesondere im Hinblick auf schnelles Feedback und iterative Verbesserung?  

## Bewertung

- Inhaltliche Vollständigkeit und Korrektheit
- Quellen: Mehrere Quellenangaben pro Thema. Quellen müssen angegeben werden
- Formatierung des Dokuments und Bildunterstützung
- Zusätzliche Ausführungen pro Thema (Vertiefung, Gegenüberstellung, Anwendungsbeispiele, etc)
