package org.willishz.playground.grammar.proxy;

/**
 * 汽车接口类
 */
public interface VehicleService {
    void engineStart();
    void engineStop();
    Integer throttle(Integer kph);
    void brake();
    void reverse();
}
