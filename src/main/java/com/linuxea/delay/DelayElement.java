package com.linuxea.delay;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class DelayElement {

  protected String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

  // very important for every element
  protected String id = UUID.randomUUID().toString();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }
}
