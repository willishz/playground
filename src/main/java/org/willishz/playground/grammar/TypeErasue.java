package org.willishz.playground.grammar;

public interface TypeErasue<A> {

    public int compareTo(A that);

}

final class NumericValue implements Comparable<NumericValue> {

    private byte value;

    public NumericValue(byte value) {

        this.value = value;

    }

    public byte getValue() {

        return value;

    }

    @Override
    public int compareTo(NumericValue that) {

        return this.value - that.value;

    }

}