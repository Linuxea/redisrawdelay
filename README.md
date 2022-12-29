# redis raw delay
redis 实现原始延时队列

## 延时元素

继承 <code>DelayElement</code>


## 延时队列

继承 <code>DefaultRedisDelayQueue</code>, <code>RedisDelayQueue</code>, <code>DelayQueue</code>


## 延时元素处理

继承 <code>DelayCallback</code>



# demo PrintDelayedQueue

```java
/**
 * one for print delayed element in queue
 */
public class PrintDelayedQueue extends DefaultRedisDelayQueue<TimeDelayedElement> {


  public PrintDelayedQueue(String queueName,
      Class<TimeDelayedElement> timeDelayedElementClass) {
    super(queueName, timeDelayedElementClass);
  }

  @Override
  public DelayCallback<TimeDelayedElement> callback() {
    return timeDelayedElement -> System.out.println(
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " print:"
            + timeDelayedElement);
  }
}
```


创建自己的延时业务类，继承自 <code>DefaultRedisDelayQueue<T></code>

实现 ```callback``` 触发的业务逻辑方法。 Here is just print simply.

编写 ```main``` 测试
```java
PrintDelayedQueue printDelayedQueue = new PrintDelayedQueue("printQueue", TimeDelayedElement.class);
printDelayedQueue.add(new TimeDelayedElement("hello redis delay queue"), TimeUnit.SECONDS, 5L);
printDelayedQueue.add(new TimeDelayedElement("hello redis delay queue again"), TimeUnit.SECONDS, 10L);

// 模拟过一段时间关闭
TimeUnit.SECONDS.sleep(20L);
printDelayedQueue.shutdown();
```




查看对应的 UML 关系

![延时队列 PrintDelayedQueue 示例完整 UML](PrintDelayedQueue.png "PrintDelayedQueue")