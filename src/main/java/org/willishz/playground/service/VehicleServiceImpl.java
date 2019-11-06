package org.willishz.playground.service;

/**
 * 汽车类
 */
public class VehicleServiceImpl implements VehicleService {

    private Integer kph = 0;

    @Override
    public void engineStart() {

    }

    @Override
    public void engineStop() {

    }

    @Override
    public Integer throttle(Integer kph) {
        this.kph += kph;
        return this.kph;
    }

    @Override
    public void brake() {

    }

    @Override
    public void reverse() {

    }
}
