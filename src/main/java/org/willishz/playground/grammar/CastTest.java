package org.willishz.playground.grammar;

import org.junit.Test;

/**
 * 强制转换
 */
public class CastTest {

    /**
     * 不报错,溢出截取
     */
    @Test
    public void intToByte() {
        System.out.println((byte) 128);
        System.out.println((byte) 129);
        System.out.println((byte) -129);
    }
}
