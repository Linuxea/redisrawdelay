package com.linuxea.delay;

import java.util.concurrent.TimeUnit;

/**
 * delay queue interface
 */
public interface DelayQueue<T extends DelayElement> {

  /**
   * add delayed T into delayed queue
   *
   * @param t             delayed element
   * @param delayTimeUnit delay time unit
   * @param delayValue    delay time value
   */
  void add(T t, TimeUnit delayTimeUnit, Long delayValue);


  /**
   * delay callback
   *
   * @return DelayCallback
   */
  DelayCallback<T> callback();


  /**
   * shutdown delay queue
   */
  void shutdown();


}
