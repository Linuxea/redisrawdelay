package com.linuxea.delay;


import java.util.UUID;

public class DelayElement {

  // very important for every element
  protected String id = UUID.randomUUID().toString();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
