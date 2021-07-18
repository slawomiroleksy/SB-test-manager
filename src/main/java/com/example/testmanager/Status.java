package com.example.testmanager;

import javax.validation.constraints.NotEmpty;

@NotEmpty
public enum Status {
  UNDEFINED,
  FAILED,
  PASSED
}
