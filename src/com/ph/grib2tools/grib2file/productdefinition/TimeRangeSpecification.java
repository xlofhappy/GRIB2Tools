package com.ph.grib2tools.grib2file.productdefinition;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class TimeRangeSpecification implements Serializable {

    private static final long serialVersionUID = 100L;

    private byte statisticalProcess;
    private byte typeOfTimeIncrement;
    private byte unitTimeRange;
    private int  lengthTimeRange;
    private byte unitTimeIncrement;
    private int  lengthTimeIncrement;


    public TimeRangeSpecification(ByteBuffer byteBuffer) {
        statisticalProcess = byteBuffer.get();
        typeOfTimeIncrement = byteBuffer.get();
        unitTimeRange = byteBuffer.get();
        lengthTimeRange = byteBuffer.getInt();
        unitTimeIncrement = byteBuffer.get();
        lengthTimeIncrement = byteBuffer.getInt();
    }

    public byte getStatisticalProcess() {
        return statisticalProcess;
    }

    public void setStatisticalProcess(byte statisticalProcess) {
        this.statisticalProcess = statisticalProcess;
    }

    public byte getTypeOfTimeIncrement() {
        return typeOfTimeIncrement;
    }

    public void setTypeOfTimeIncrement(byte typeOfTimeIncrement) {
        this.typeOfTimeIncrement = typeOfTimeIncrement;
    }

    public byte getUnitTimeRange() {
        return unitTimeRange;
    }

    public void setUnitTimeRange(byte unitTimeRange) {
        this.unitTimeRange = unitTimeRange;
    }

    public int getLengthTimeRange() {
        return lengthTimeRange;
    }

    public void setLengthTimeRange(int lengthTimeRange) {
        this.lengthTimeRange = lengthTimeRange;
    }

    public byte getUnitTimeIncrement() {
        return unitTimeIncrement;
    }

    public void setUnitTimeIncrement(byte unitTimeIncrement) {
        this.unitTimeIncrement = unitTimeIncrement;
    }

    public int getLengthTimeIncrement() {
        return lengthTimeIncrement;
    }

    public void setLengthTimeIncrement(int lengthTimeIncrement) {
        this.lengthTimeIncrement = lengthTimeIncrement;
    }
}
