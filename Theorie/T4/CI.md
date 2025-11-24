# Continuous Integration

## Themen

### 1. Was ist Continuous Integration (CI) und wie wird es umgesetzt?
- Welche Bedeutung hat Continuous Integration im Softwareentwicklungsprozess?  
- Welche technischen Prozesse und Werkzeuge ermöglichen eine erfolgreiche Implementierung von CI?  
- Welche Rolle spielt CI in der Automatisierung und Zusammenarbeit in Teams?

Bei CI integriert man Code-Änderungen häufig in ein gemeinsames Repository. Jede Integration wird durch einen automatisierten Build oder Test verifiziert.
Dank CI kann man Integrationsfehler schnell erkennen und beheben.
Automatisierte Tests und Builds, Code-Analysen, Deployment-Pipelines oder Jenkins erlauben eine erfolgreiche Implementierung von CI.
CI kann die Transparenz im Team erhöhen und die merge conflicts reduzieren. Die Qualitätssicherung ist automatisiert.

### 2. Was sind die Vor- und Nachteile von CI?
- Welche Vorteile bringt die Einführung von CI für die Softwareentwicklung?  
- Welche Herausforderungen können bei der Implementierung und im Betrieb von CI auftreten?  
- Wie beeinflusst CI langfristig die Produktqualität und den Workflow in einem Team?

Vorteile:
- Früherkennung der Fehler
- Schnelle Entwicklungszyklen
- Bessere Qualität des Codes
- Erhöhte Automatisierung

Nachteile:
- initial ist der Einrichtungsaufwand grösser
- Die Kosten sind erhöht
- Testdisziplin (man muss häufig commiten)

Langfristige Nutzung von CI kann Produktqualität erhöhen, den Workflow effizienter machen, und stabilere Releases bringen

### 3. Was ist Continuous Testing, und wie wird es umgesetzt?
- Wie unterscheidet sich Continuous Testing von traditionellen Testmethoden?  
- Welche Rolle spielt Continuous Testing im Entwicklungszyklus?  
- Welche Arten von Tests werden dabei typischerweise automatisiert, und wie wird ihre Effektivität sichergestellt?  

### 4. Was ist eine Branching-Strategie, und welches sind die bekanntesten Ansätze?
- Nehmen Sie spziell den **trunk based Ansatz** in den Vergleich auf.
- Warum sind Branching-Strategien für die Versionskontrolle wichtig?  
- Wie beeinflussen unterschiedliche Strategien die Code-Organisation und den Arbeitsfluss in Teams?  
- Welche Branching-Strategien werden häufig verwendet, und worin unterscheiden sie sich?  

### 5. Wie kann man Commits und Branches mit User Stories verknüpfen?
- Warum ist es sinnvoll, Codeänderungen mit User Stories zu verknüpfen?  
    Die Verknüpfung macht die Entwicklung transparent: Rückverfolgung von Codeänderungen zu Anforderungen, einfachere Nachvollziehbarkeit bei Reviews und Releases sowie bessere Audit- und Fehlerursachenanalyse. Diese Nachvollziehbarkeit erleichtert auch automatische Release-Notes und Compliance-Anforderungen. 

- Welche Praktiken und Namenskonventionen können helfen, diese Verknüpfung effektiv umzusetzen?  
    Branch-Namen: z. B. feature/JRA-123-add-login oder bugfix/PROJ-45/fix-nullptr - das Projektschlüssel/Issue-ID zuerst, dann kurzer Text.
    Commit-Messages: immer Issue-Key referenzieren (JRA-123: Implement login flow) oder #123 bei GitHub, ggf. Smart-Commit-Keywords (JRA-123 #comment done) um Work-Item-Aktionen auszulösen.
    Pull-Request-Titel: Issue-Key + kurze Beschreibung; PR-Templates fordern Referenz auf die User Story.
    Diese Konventionen werden explizit in Docs/Guides empfohlen (z. B. Jira Smart Commits, GitHub Linking).

- Wie unterstützen Tools die Verbindung zwischen Aufgabenmanagement und Code-Repositories?  
    Tools verbinden Issues mit Commits und PRs automatisch:
    GitHub verlinkt Commits/PRs mit Issues per #issue oder Schlüsselwörtern.
    Jira zeigt im Development-Panel Commits, Branches und PRs, wenn Repos verbunden sind; Smart Commits erlauben auch Übergangskommandos aus Commit-Nachrichten.
    Integratoren (Unito, ZigiOps, Azure DevOps/GitHub Apps) bieten bidirektionale Synchronisation zwischen Issue-Trackern und Repos.

**Quellen:**  
[GitHub: Linking pull requests to issues](https://docs.github.com/en/issues/tracking-your-work-with-issues/linking-a-pull-request-to-an-issue)  

### 6. Welche Merge-Strategien gibt es, und wann werden sie verwendet?
- Welche Ansätze gibt es, um Änderungen aus einem Branch in einen anderen zu integrieren?  
    Merge Commit (git merge --no-ff): erzeugt einen Merge-Commit, der die Branch-Zusammenführung sichtbar macht.
    Fast-Forward Merge (ohne Merge-Commit): wenn die Ziel-Branch keinen neuen Commit hat, wird der Pointer weiterbewegt - keine Merge-Commit-Historie.
    Squash Merge (--squash oder GitHub “Squash and merge”): fasst alle Commits eines Branches zu einem einzigen Commit zusammen.
    Rebase (lokal oder vor dem Merge): integriert Änderungen neu entlang der Ziel-Branch-Historie, um eine lineare Historie zu erzeugen.

- Wie beeinflussen unterschiedliche Merge-Strategien die Historie und die Nachvollziehbarkeit von Änderungen?  
    Merge Commit erhält komplettes Branch-History-Kontext (gut für Nachvollziehbarkeit großer Feature-Arbeiten).
    Fast-Forward ist sauber, aber verliert explizite Zusammenführungsmarkierung.
    Squash produziert saubere, kompakte Haupt-Branch-History, dafür gehen einzelne Zwischenschritte verloren (weniger granular nachvollziehbar).
    Rebase erzeugt lineare History (leichter zu lesen), verändert aber Commit-IDs.

- Unter welchen Umständen wird welche Strategie bevorzugt?  
    Preserve history / Traceability needed → Merge Commit / --no-ff. (große Features, Compliance)
    Saubere Main-Branch-History bevorzugt → Squash oder Rebase + Fast-Forward (bei kleinen, häufigen PRs).
    Private/topic branches vor Push → Rebase lokal möglich; öffentliche Branches meiden Rebase, weil IDs geändert werden.
    Viele Teams kombinieren Regeln: z. B. feature/* → Squash, release/* → Merge Commit.

**Quellen:**  
[GitLab Docs: Merge Methods](https://docs.gitlab.com/ee/user/project/merge_requests/methods/)  
[StackOverflow: Differences between merge strategies](https://stackoverflow.com/questions/457927/git-fast-forward-merge)

### 7. Was ist Semantic Versioning, und wie wird es eingesetzt?
- Wie hilft Semantic Versioning bei der Verwaltung von Software-Versionen?  
    Semantic Versioning (SemVer) ist eine standardisierte Notation (MAJOR.MINOR.PATCH), die kommuniziert, ob eine neue Version inkompatible API-Änderungen, neue abwärtskompatible Features oder nur Bugfixes enthält. Das vereinfacht Abhängigkeitsmanagement, Release-Strategien und Erwartungen der Nutzer/Integratoren.

- Welche Konventionen werden bei Semantic Versioning angewendet?  
    MAJOR erhöht bei inkompatiblen API-Änderungen.
    MINOR bei abwärtskompatiblen Funktions-Erweiterungen.
    PATCH bei abwärtskompatiblen Bugfixes.
    Zusätzlich gibt es Prä-Release- und Build-Meta-Anhänge (z. B. 1.2.3-alpha.1). Die offizielle Spezifikation beschreibt Regeln zur Definition der öffentlichen API.

- Warum ist Semantic Versioning wichtig für die Kompatibilität und Kommunikation von Änderungen?  
    SemVer ermöglicht automatisches Dependency-Resolution (Package Manager können Versionen sinnvoll auflösen), klare Upgrade-Erwartungen und bessere Kommunikation zwischen Entwicklern, Betrieb und Kunden. Bei APIs sollte man jedoch die Kosten von Multi-Version-Support bedenken — manchmal ist Full-SemVer für öffentliche APIs eine Herausforderung.

**Quellen:**  
[GitHub Docs: Versioning Software](https://docs.github.com/en/repositories/releasing-projects-on-github/about-releases)  

### 8. Welchen Unterschied haben Mono- und Multirepo-Ansätze im Kontext von Microservices?
- Wie unterscheiden sich Mono- und Multirepo-Ansätze in der Organisation von Code?  
    Monorepo: alle Dienste/Bibliotheken in einem einzigen Repository.
    Multirepo: jeder Service (oder Paket) hat ein eigenes Repository.
    Die Struktur beeinflusst CI, Zugriffsrechte, Tooling und Dependency-Management.

- Welche Vor- und Nachteile haben beide Ansätze speziell für die Entwicklung und Wartung von Microservices?  
    Monorepo – Vorteile: einfachere Refactorings über Services hinweg, konsistente Toolchain, zentrales Sichtbarmachen von Code.
    Nachteile: CI-Skalierung (lange Builds), grössere Repo-Grösse, mögliche Zugriffs-/Ownership-Komplexität.

    Multirepo – Vorteile: klare Ownership, kleine Repos, unabhängige CI/CD für jeden Service.
    Nachteile: Koordination über Repos hinweg schwieriger, cross-repo Änderungen aufwändiger, mehr Verwaltungsaufwand.

- Wie beeinflussen die Ansätze die Skalierbarkeit, Zusammenarbeit und Abhängigkeiten zwischen Teams?  
    Skalierbarkeit CI/CD: Multirepo skaliert leichter pro Service; Monorepo benötigt ausgefeilte selective CI (z. B. nur betroffene Teile bauen).
    Zusammenarbeit: Monorepo erleichtert teamübergreifende Änderungen; Multirepo zwingt zu stabilen APIs und besseren Versionierungsstrategien.
    Abhängigkeiten: Monorepo ermöglicht gleichzeitige Änderungen an abhängigen Komponenten; Multirepo erfordert Versioned Releases und synchronisierte Upgrades.
    Viele Organisationen wählen Hybrid-Ansätze (z. B. mehrere Monorepos nach Domänen) je nach Teamgröße und Tooling.

**Quellen:**  
[Atlassian: Microservices and Repository Strategies](https://www.atlassian.com/microservices)  

### 9. Was ist ein Artifact-Repository, und welche Aufgaben erfüllt es?
- Welche Rolle spielt ein Artifact-Repository in der Softwareentwicklung und im Bereitstellungsprozess?  
- Welche Arten von Artefakten werden typischerweise in einem Repository verwaltet?  
- Warum ist ein Artifact-Repository wichtig für CI/CD-Pipelines?



## Bewertung

- Inhaltliche Vollständigkeit und Korrektheit
- Quellen: Mehrere Quellenangaben pro Thema. Quellen müssen angegeben werden
- Formatierung des Dokuments und Bildunterstützung
- Zusätzliche Ausführungen pro Thema (Vertiefung, Gegenüberstellung, Anwendungsbeispiele, etc)
