# Ticketverwaltung Microservice

Dieser Microservice ist für die Verwaltung von Tickets zuständig (User Story 3).
Er läuft auf Port 8081 und kommuniziert mit dem Mitarbeiterverwaltung-Service (Port 8080) zur Validierung von Mitarbeitern.

## Technologien
- Java 17
- Spring Boot 3.4.0
- Spring Data JPA
- H2 Database
- Lombok
- Validation

## Starten
1. Stellen Sie sicher, dass der Mitarbeiterverwaltung-Service auf Port 8080 läuft.
2. Führen Sie diesen Service aus:
   ```bash
   ./mvnw spring-boot:run
   ```

## Endpoints

### POST /api/tickets
Erstellt ein neues Ticket.

**Body:**
```json
{
  "title": "Server down",
  "description": "Critical error on prod",
  "status": "OPEN",
  "employeeId": "uuid-des-mitarbeiters"
}
```

**Validierung:**
- `employeeId` muss im Mitarbeiterverwaltung-Service existieren.
- Datumsangaben (`reviewDate`, `doneDate`) müssen je nach `status` gesetzt oder leer sein.

## Tests
Führen Sie die Unit Tests aus mit:
```bash
./mvnw test
```
Es sind Unit Tests für Controller und Service vorhanden, die Happy Paths und Sad Paths abdecken.
