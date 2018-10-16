package com.ph.grib2tools.grib2file.productdefinition;

import java.nio.ByteBuffer;

public class ProductDefinitionTemplate48 extends ProductDefinitionTemplate4x {

    private static final long serialVersionUID = 100L;

    private ProductDefinitionTemplate40 productDefinitionTemplate40;

    private short                    yearEnd;
    private byte                     monthEnd;
    private byte                     dayEnd;
    private byte                     hourEnd;
    private byte                     minuteEnd;
    private byte                     secondEnd;
    private byte                     numberTimeRangeSpecifications;
    private int                      totalNumberDataValuesMissing;
    private TimeRangeSpecification[] timeRangeSpecification;


    public ProductDefinitionTemplate48(ByteBuffer byteBuffer) {
        productDefinitionTemplate40 = new ProductDefinitionTemplate40(byteBuffer);
        yearEnd = byteBuffer.getShort();
        monthEnd = byteBuffer.get();
        dayEnd = byteBuffer.get();
        hourEnd = byteBuffer.get();
        minuteEnd = byteBuffer.get();
        secondEnd = byteBuffer.get();
        numberTimeRangeSpecifications = byteBuffer.get();
        totalNumberDataValuesMissing = byteBuffer.getInt();
        timeRangeSpecification = new TimeRangeSpecification[numberTimeRangeSpecifications];
        for ( int i = 0; i < numberTimeRangeSpecifications; i++ ) {
            timeRangeSpecification[i] = new TimeRangeSpecification(byteBuffer);
        }
    }

    public ProductDefinitionTemplate40 getProductDefinitionTemplate40() {
        return productDefinitionTemplate40;
    }

    public void setProductDefinitionTemplate40(ProductDefinitionTemplate40 productDefinitionTemplate40) {
        this.productDefinitionTemplate40 = productDefinitionTemplate40;
    }

    public short getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(short yearEnd) {
        this.yearEnd = yearEnd;
    }

    public byte getMonthEnd() {
        return monthEnd;
    }

    public void setMonthEnd(byte monthEnd) {
        this.monthEnd = monthEnd;
    }

    public byte getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(byte dayEnd) {
        this.dayEnd = dayEnd;
    }

    public byte getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(byte hourEnd) {
        this.hourEnd = hourEnd;
    }

    public byte getMinuteEnd() {
        return minuteEnd;
    }

    public void setMinuteEnd(byte minuteEnd) {
        this.minuteEnd = minuteEnd;
    }

    public byte getSecondEnd() {
        return secondEnd;
    }

    public void setSecondEnd(byte secondEnd) {
        this.secondEnd = secondEnd;
    }

    public byte getNumberTimeRangeSpecifications() {
        return numberTimeRangeSpecifications;
    }

    public void setNumberTimeRangeSpecifications(byte numberTimeRangeSpecifications) {
        this.numberTimeRangeSpecifications = numberTimeRangeSpecifications;
    }

    public int getTotalNumberDataValuesMissing() {
        return totalNumberDataValuesMissing;
    }

    public void setTotalNumberDataValuesMissing(int totalNumberDataValuesMissing) {
        this.totalNumberDataValuesMissing = totalNumberDataValuesMissing;
    }

    public TimeRangeSpecification[] getTimeRangeSpecification() {
        return timeRangeSpecification;
    }

    public void setTimeRangeSpecification(TimeRangeSpecification[] timeRangeSpecification) {
        this.timeRangeSpecification = timeRangeSpecification;
    }
}
