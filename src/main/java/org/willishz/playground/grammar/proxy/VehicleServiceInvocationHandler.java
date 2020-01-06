package org.willishz.playground.grammar.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 汽车接口类
 */
@Slf4j
public class VehicleServiceInvocationHandler implements InvocationHandler {

    private VehicleService vehicleService;

    public VehicleServiceInvocationHandler(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    private VehicleService getProxy() {
        return (VehicleService) Proxy.newProxyInstance(vehicleService.getClass().getClassLoader(),
                vehicleService.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("before {} {}", method.getClass().getName(), method.getName());
        Object invoke = method.invoke(vehicleService, args);
        log.info("after {} {}", method.getClass().getName(), method.getName());
        return invoke;
    }

    public static void main(String[] args) {
        VehicleService service = new VehicleServiceImpl();
        VehicleServiceInvocationHandler vehicleServiceInvocationHandler = new VehicleServiceInvocationHandler(service);
        VehicleService proxy = vehicleServiceInvocationHandler.getProxy();
        assert proxy.throttle(40) == 40;
    }
}
