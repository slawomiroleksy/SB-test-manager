package com.example.testmanager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntryRepository extends JpaRepository<TestEntry, Long> {
}