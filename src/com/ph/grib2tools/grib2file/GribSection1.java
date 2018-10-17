package com.ph.grib2tools.grib2file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class GribSection1 extends GribSection {

    private static final long serialVersionUID = 100L;

    /**
     * Content and structure of a Section 1
     */
    private short generatingCentre;
    private short generatingSubCenter;
    private byte  masterTablesVersion;
    private byte  localTablesVersion;
    private byte  significanceOfReferenceTime;
    private short year;
    private byte  month;
    private byte  day;
    private byte  hour;
    private byte  minute;
    private byte  second;
    private byte  productionStatus;
    private byte  type;

    public GribSection1(RandomAccessFile gribFile) throws IOException {
        super(gribFile);
    }

    @Override
    public void readData(RandomAccessFile gribFile) throws IOException {
        super.readData(gribFile);
        readSection();
    }

    private void readSection() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(getSectionData());
        // Parse section and extract data
        generatingCentre = byteBuffer.getShort();
        generatingSubCenter = byteBuffer.getShort();
        masterTablesVersion = byteBuffer.get();
        localTablesVersion = byteBuffer.get();
        significanceOfReferenceTime = byteBuffer.get();
        year = byteBuffer.getShort();
        month = byteBuffer.get();
        day = byteBuffer.get();
        hour = byteBuffer.get();
        minute = byteBuffer.get();
        second = byteBuffer.get();
        productionStatus = byteBuffer.get();
        type = byteBuffer.get();
    }

    public short getGeneratingCentre() {
        return generatingCentre;
    }

    public void setGeneratingCentre(short generatingCentre) {
        this.generatingCentre = generatingCentre;
    }

    public short getGeneratingSubCenter() {
        return generatingSubCenter;
    }

    public void setGeneratingSubCenter(short generatingSubCenter) {
        this.generatingSubCenter = generatingSubCenter;
    }

    public byte getMasterTablesVersion() {
        return masterTablesVersion;
    }

    public void setMasterTablesVersion(byte masterTablesVersion) {
        this.masterTablesVersion = masterTablesVersion;
    }

    public byte getLocalTablesVersion() {
        return localTablesVersion;
    }

    public void setLocalTablesVersion(byte localTablesVersion) {
        this.localTablesVersion = localTablesVersion;
    }

    public byte getSignificanceOfReferenceTime() {
        return significanceOfReferenceTime;
    }

    public void setSignificanceOfReferenceTime(byte significanceOfReferenceTime) {
        this.significanceOfReferenceTime = significanceOfReferenceTime;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public byte getMonth() {
        return month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public byte getHour() {
        return hour;
    }

    public void setHour(byte hour) {
        this.hour = hour;
    }

    public byte getMinute() {
        return minute;
    }

    public void setMinute(byte minute) {
        this.minute = minute;
    }

    public byte getSecond() {
        return second;
    }

    public void setSecond(byte second) {
        this.second = second;
    }

    public byte getProductionStatus() {
        return productionStatus;
    }

    public void setProductionStatus(byte productionStatus) {
        this.productionStatus = productionStatus;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
}
