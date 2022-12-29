package com.linuxea.delay.demo;

import com.linuxea.delay.DefaultRedisDelayQueue;
import com.linuxea.delay.DelayCallback;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * one for print delayed element in queue
 */
public class PrintDelayedQueue extends DefaultRedisDelayQueue<TimeDelayedElement> {


  public PrintDelayedQueue(String queueName,
      Class<TimeDelayedElement> timeDelayedElementClass) {
    super(queueName, timeDelayedElementClass);
  }

  public static void main(String[] args) throws InterruptedException {
    PrintDelayedQueue printDelayedQueue = new PrintDelayedQueue("printQueue",
        TimeDelayedElement.class);
    printDelayedQueue.add(new TimeDelayedElement("hello redis delay queue"), TimeUnit.SECONDS, 5L);
    printDelayedQueue.add(new TimeDelayedElement("hello redis delay queue again"), TimeUnit.SECONDS,
        10L);

    TimeUnit.SECONDS.sleep(20L);
    printDelayedQueue.shutdown();
  }

  @Override
  public DelayCallback<TimeDelayedElement> callback() {
    return timeDelayedElement -> System.out.println(
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " print:"
            + timeDelayedElement);
  }
}
