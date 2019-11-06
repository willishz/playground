package org.willishz.playground.service;

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
