package org.otus.education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AnyObject {
    private String str;
    private int anInt;
    private long aLong;
    private double aDouble;
    private float aFloat;
    private byte aByte;
    private char aChar;
    private boolean aBoolean;
    private short aShort;
    private final char[] charArray = new char[]{'t', 'e', 's', 't', '1', '!'};
    private final int[] intArray = new int[]{9, 1, 1, 1, 1, 2};
    private final int[][][] multiIntArray = {{{0}, {3}}};
    private final double[] doubleArray = new double[]{0.1, 0.2, 0.4};
    private final long[] nullArray = null;

    private final List<Integer> integerList = List.of(1, 1, 2);
    private final List<List<Double>> doublesMultiList = List.of(List.of(9.1, 1.1, 1.2), List.of(0.1, 0.2, 0.3));
    private final List<int[][]> listOfMultiArray = new ArrayList<>();
    {
        listOfMultiArray.add(null);
        listOfMultiArray.add(new int[][]{{}, {}});
        listOfMultiArray.add(null);
    }

    private AnyObject() {
    }

    private AnyObject(String str, int anInt, long aLong, double aDouble, float aFloat, byte aByte, char aChar, boolean aBoolean, short aShort) {
        this.str = str;
        this.anInt = anInt;
        this.aLong = aLong;
        this.aDouble = aDouble;
        this.aFloat = aFloat;
        this.aByte = aByte;
        this.aChar = aChar;
        this.aBoolean = aBoolean;
        this.aShort = aShort;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getInt() {
        return anInt;
    }

    public void setInt(int anInt) {
        this.anInt = anInt;
    }

    public long getLong() {
        return aLong;
    }

    public void setLong(long aLong) {
        this.aLong = aLong;
    }

    public double getDouble() {
        return aDouble;
    }

    public void setDouble(double aDouble) {
        this.aDouble = aDouble;
    }

    public float getFloat() {
        return aFloat;
    }

    public void setFloat(float aFloat) {
        this.aFloat = aFloat;
    }

    public byte getByte() {
        return aByte;
    }

    public void setByte(byte aByte) {
        this.aByte = aByte;
    }

    public char getChar() {
        return aChar;
    }

    public void setChar(char aChar) {
        this.aChar = aChar;
    }

    public boolean isBoolean() {
        return aBoolean;
    }

    public void setBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public short getShort() {
        return aShort;
    }

    public void setShort(short aShort) {
        this.aShort = aShort;
    }

    @Override
    public String toString() {
        return "AnyObject{" +
                "str='" + str + '\'' +
                ", anInt=" + anInt +
                ", aLong=" + aLong +
                ", aDouble=" + aDouble +
                ", aFloat=" + aFloat +
                ", aByte=" + aByte +
                ", aChar=" + aChar +
                ", aBoolean=" + aBoolean +
                ", aShort=" + aShort +
                ", chars=" + Arrays.toString(charArray) +
                ", values=" + Arrays.toString(intArray) +
                ", multidim=" + Arrays.toString(multiIntArray) +
                ", doubles=" + Arrays.toString(doubleArray) +
                ", nullarray=" + Arrays.toString(nullArray) +
                ", integers=" + integerList +
                ", doublesMulti=" + doublesMultiList +
                ", listOfArray=" + listOfMultiArray +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnyObject anyObject = (AnyObject) o;
        return !(!(anInt == anyObject.anInt
                && aLong == anyObject.aLong
                && Double.compare(anyObject.aDouble, aDouble) == 0
                && Float.compare(anyObject.aFloat, aFloat) == 0
                && aByte == anyObject.aByte
                && aChar == anyObject.aChar
                && aBoolean == anyObject.aBoolean
                && aShort == anyObject.aShort
                && Objects.equals(str, anyObject.str)
                && Arrays.equals(charArray, anyObject.charArray)
                && Arrays.equals(intArray, anyObject.intArray)
                && Arrays.deepEquals(multiIntArray, anyObject.multiIntArray)
                && Arrays.equals(doubleArray, anyObject.doubleArray)
                && Arrays.equals(nullArray, anyObject.nullArray)
                && Objects.deepEquals(integerList, anyObject.integerList)
                && Objects.deepEquals(doublesMultiList, anyObject.doublesMultiList)
                && listOfMultiArray == anyObject.listOfMultiArray)
                && !(anyObject.listOfMultiArray != null && Arrays.deepEquals(listOfMultiArray.toArray(), anyObject.listOfMultiArray.toArray())));
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(str, anInt, aLong, aDouble, aFloat, aByte, aChar, aBoolean, aShort, integerList, doublesMultiList, listOfMultiArray);
        result = 31 * result + Arrays.hashCode(charArray);
        result = 31 * result + Arrays.hashCode(intArray);
        result = 31 * result + Arrays.hashCode(multiIntArray);
        result = 31 * result + Arrays.hashCode(doubleArray);
        result = 31 * result + Arrays.hashCode(nullArray);
        return result;
    }

    public static class Builder {
        private String str;
        private int anInt;
        private long aLong;
        private double aDouble;
        private float aFloat;
        private byte aByte;
        private char aChar;
        private boolean aBoolean;
        private short aShort;

        public Builder() {
        }

        private Builder(String str, int anInt, long aLong, double aDouble, float aFloat, byte aByte, char aChar, boolean aBoolean, short aShort) {
            this.str = str;
            this.anInt = anInt;
            this.aLong = aLong;
            this.aDouble = aDouble;
            this.aFloat = aFloat;
            this.aByte = aByte;
            this.aChar = aChar;
            this.aBoolean = aBoolean;
            this.aShort = aShort;
        }

        public Builder setStr(String str) {
            this.str = str;
            return this;
        }

        public Builder setInt(int anInt) {
            this.anInt = anInt;
            return this;
        }

        public Builder setLong(long aLong) {
            this.aLong = aLong;
            return this;
        }

        public Builder setDouble(double aDouble) {
            this.aDouble = aDouble;
            return this;
        }

        public Builder setFloat(float aFloat) {
            this.aFloat = aFloat;
            return this;
        }

        public Builder setByte(byte aByte) {
            this.aByte = aByte;
            return this;
        }

        public Builder setChar(char aChar) {
            this.aChar = aChar;
            return this;
        }

        public Builder setBoolean(boolean aBoolean) {
            this.aBoolean = aBoolean;
            return this;
        }

        public Builder setShort(short aShort) {
            this.aShort = aShort;
            return this;
        }

        public AnyObject build() {
            return new AnyObject(str, anInt, aLong, aDouble, aFloat, aByte, aChar, aBoolean, aShort);
        }
    }
}
