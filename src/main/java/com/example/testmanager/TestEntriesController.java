package com.example.testmanager;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests")
public class TestEntriesController {

  private final TestEntryRepository testEntryRepository;

  public TestEntriesController(TestEntryRepository testEntryRepository) {
    this.testEntryRepository = testEntryRepository;
  }

  @GetMapping
  public List<TestEntry> getTests() {
    return testEntryRepository.findAll();
  }

  @GetMapping("/{id}")
  public TestEntry getTest(@PathVariable Long id) {
    return testEntryRepository.findById(id).orElseThrow(RuntimeException::new);
  }

  @PostMapping
  public ResponseEntity createTest(@Valid @RequestBody TestEntry test) throws URISyntaxException {
    TestEntry savedTest = testEntryRepository.save(test);
    return ResponseEntity.created(new URI("/tests/" + savedTest.getId())).body(savedTest);
  }

  @PutMapping("/{id}")
  public ResponseEntity updateTest(@PathVariable Long id, @Valid @RequestBody TestEntry test) {
    TestEntry currentTest = testEntryRepository.findById(id).orElseThrow(RuntimeException::new);
    currentTest.setName(test.getName());
    currentTest.setStatus(test.getStatus());
    currentTest = testEntryRepository.save(currentTest);

    return ResponseEntity.ok(currentTest);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteTest(@PathVariable Long id) {
    testEntryRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }
}