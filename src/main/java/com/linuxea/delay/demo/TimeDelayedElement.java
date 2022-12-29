package com.linuxea.delay.demo;

import com.linuxea.delay.DelayElement;

public class TimeDelayedElement extends DelayElement {

  private String content;

  public TimeDelayedElement(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  @Override
  public String toString() {
    return "TimeDelayedElement{" +
        "content='" + content + '\'' +
        ", id='" + id + '\'' +
        '}';
  }
}
