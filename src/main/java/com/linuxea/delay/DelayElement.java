package com.linuxea.delay;


import java.util.UUID;

public class DelayElement {

  protected String id = UUID.randomUUID().toString();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
