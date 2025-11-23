package com.mitarbeiterverwaltung.repository;

import com.mitarbeiterverwaltung.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository-Interface für den Datenzugriff auf die Employee-Entität.
 * Erweitert JpaRepository, um CRUD-Operationen bereitzustellen.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}