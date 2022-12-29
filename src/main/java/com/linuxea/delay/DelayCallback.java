package com.linuxea.delay;

/**
 * delayed element callback
 *
 * @param <T>
 */
@FunctionalInterface
public interface DelayCallback<T extends DelayElement> {

  /**
   * execute callback
   *
   * @param t delayed element
   */
  void execute(T t);

}
