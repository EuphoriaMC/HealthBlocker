package me.wsman217.healthblocker.utils;

import javafx.beans.NamedArg;

import java.io.Serializable;
import java.util.Objects;

/**
This is basically just a rip off of {@link javafx.util.Pair}
 */
@SuppressWarnings("rawtypes")
public class Triplet<A, B, C> implements Serializable {

    private final A key;

    public A getKey() {
        return key;
    }

    private final B firstValue;

    public B getFirstValue() {
        return firstValue;
    }

    private final C secondValue;

    public C getSecondValue() {
        return secondValue;
    }

    public Triplet(@NamedArg("key") A key, @NamedArg("firstValue") B firstValue, @NamedArg("secondValue") C secondValue) {
        this.key = key;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @Override
    public String toString() {
        return key + "=" + "[" + firstValue + "," + secondValue + "]";
    }

    @Override
    public int hashCode() {
        return key.hashCode() * 13 + (firstValue == null ? 0 : firstValue.hashCode()) + (secondValue == null ? 0 : secondValue.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Triplet) {
            Triplet triplet = (Triplet) o;
            if (!Objects.equals(key, triplet.key)) return false;
            if (!Objects.equals(firstValue, triplet.firstValue)) return false;
            return Objects.equals(secondValue, triplet.secondValue);
        }
        return false;
    }
}
