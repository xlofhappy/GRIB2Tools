package com.ph.grib2tools.grib2file.griddefinition;

import com.ph.grib2tools.grib2file.GribSection;

import java.nio.ByteBuffer;

public class GridDefinitionTemplate30 extends GridDefinitionTemplate3x {

    private static final long serialVersionUID = 100L;

    private byte shapeOfEarth;
    private byte scaleFactorRadiusSphericalEarth;
    private int  scaledValueRadiusSphericalEarth;
    private byte scaleFactorMajorAxisOblateSpheroidEarth;
    private int  scaledValueMajorAxisOblateSpheroidEarth;
    private byte scaleFactorMinorAxisOblateSpheroidEarth;
    private int  scaledValueMinorAxisOblateSpheroidEarth;
    private int  numberPointsLon;
    private int  numberPointsLat;
    private int  basicAngle;
    private int  subdivisionsBasicAngle;
    private int  firstPointLat;
    private int  firstPointLon;
    private byte resolutionAndComponentFlags;
    private int  lastPointLat;
    private int  lastPointLon;
    private int  iDirectionIncrement;
    private int  jDirectionIncrement;
    private byte scanningMode;


    public GridDefinitionTemplate30(ByteBuffer byteBuffer) {

        shapeOfEarth = byteBuffer.get();
        scaleFactorRadiusSphericalEarth = byteBuffer.get();
        scaledValueRadiusSphericalEarth = byteBuffer.getInt();
        scaleFactorMajorAxisOblateSpheroidEarth = byteBuffer.get();
        scaledValueMajorAxisOblateSpheroidEarth = byteBuffer.getInt();
        scaleFactorMinorAxisOblateSpheroidEarth = byteBuffer.get();
        scaledValueMinorAxisOblateSpheroidEarth = byteBuffer.getInt();
        numberPointsLon = byteBuffer.getInt();
        numberPointsLat = byteBuffer.getInt();
        basicAngle = byteBuffer.getInt();
        subdivisionsBasicAngle = byteBuffer.getInt();
        firstPointLat = GribSection.correctNegativeInt(byteBuffer.getInt());
        firstPointLon = GribSection.correctNegativeInt(byteBuffer.getInt());
        resolutionAndComponentFlags = byteBuffer.get();
        lastPointLat = GribSection.correctNegativeInt(byteBuffer.getInt());
        lastPointLon = GribSection.correctNegativeInt(byteBuffer.getInt());
        iDirectionIncrement = GribSection.correctNegativeInt(byteBuffer.getInt());
        jDirectionIncrement = GribSection.correctNegativeInt(byteBuffer.getInt());
        scanningMode = byteBuffer.get();
    }

    /**
     * Compares this GridDefinitionTemplate30 with the passed GridDefinitionTemplate30 other
     *
     * @param other {@link GridDefinitionTemplate30}
     */
    public boolean equals(GridDefinitionTemplate30 other) {
        if ( other == null ) { return false; }
        return (this.firstPointLat == other.firstPointLat) && (this.firstPointLon == other.firstPointLon) && (this.lastPointLat == other.lastPointLat) && (this.lastPointLon == other.lastPointLon) && (this.iDirectionIncrement == other.iDirectionIncrement) && (this.jDirectionIncrement == other.jDirectionIncrement) && (this.numberPointsLat == other.numberPointsLat) && (this.numberPointsLon == other.numberPointsLon);
    }

    public byte getShapeOfEarth() {
        return shapeOfEarth;
    }

    public void setShapeOfEarth(byte shapeOfEarth) {
        this.shapeOfEarth = shapeOfEarth;
    }

    public byte getScaleFactorRadiusSphericalEarth() {
        return scaleFactorRadiusSphericalEarth;
    }

    public void setScaleFactorRadiusSphericalEarth(byte scaleFactorRadiusSphericalEarth) {
        this.scaleFactorRadiusSphericalEarth = scaleFactorRadiusSphericalEarth;
    }

    public int getScaledValueRadiusSphericalEarth() {
        return scaledValueRadiusSphericalEarth;
    }

    public void setScaledValueRadiusSphericalEarth(int scaledValueRadiusSphericalEarth) {
        this.scaledValueRadiusSphericalEarth = scaledValueRadiusSphericalEarth;
    }

    public byte getScaleFactorMajorAxisOblateSpheroidEarth() {
        return scaleFactorMajorAxisOblateSpheroidEarth;
    }

    public void setScaleFactorMajorAxisOblateSpheroidEarth(byte scaleFactorMajorAxisOblateSpheroidEarth) {
        this.scaleFactorMajorAxisOblateSpheroidEarth = scaleFactorMajorAxisOblateSpheroidEarth;
    }

    public int getScaledValueMajorAxisOblateSpheroidEarth() {
        return scaledValueMajorAxisOblateSpheroidEarth;
    }

    public void setScaledValueMajorAxisOblateSpheroidEarth(int scaledValueMajorAxisOblateSpheroidEarth) {
        this.scaledValueMajorAxisOblateSpheroidEarth = scaledValueMajorAxisOblateSpheroidEarth;
    }

    public byte getScaleFactorMinorAxisOblateSpheroidEarth() {
        return scaleFactorMinorAxisOblateSpheroidEarth;
    }

    public void setScaleFactorMinorAxisOblateSpheroidEarth(byte scaleFactorMinorAxisOblateSpheroidEarth) {
        this.scaleFactorMinorAxisOblateSpheroidEarth = scaleFactorMinorAxisOblateSpheroidEarth;
    }

    public int getScaledValueMinorAxisOblateSpheroidEarth() {
        return scaledValueMinorAxisOblateSpheroidEarth;
    }

    public void setScaledValueMinorAxisOblateSpheroidEarth(int scaledValueMinorAxisOblateSpheroidEarth) {
        this.scaledValueMinorAxisOblateSpheroidEarth = scaledValueMinorAxisOblateSpheroidEarth;
    }

    public int getNumberPointsLon() {
        return numberPointsLon;
    }

    public void setNumberPointsLon(int numberPointsLon) {
        this.numberPointsLon = numberPointsLon;
    }

    public int getNumberPointsLat() {
        return numberPointsLat;
    }

    public void setNumberPointsLat(int numberPointsLat) {
        this.numberPointsLat = numberPointsLat;
    }

    public int getBasicAngle() {
        return basicAngle;
    }

    public void setBasicAngle(int basicAngle) {
        this.basicAngle = basicAngle;
    }

    public int getSubdivisionsBasicAngle() {
        return subdivisionsBasicAngle;
    }

    public void setSubdivisionsBasicAngle(int subdivisionsBasicAngle) {
        this.subdivisionsBasicAngle = subdivisionsBasicAngle;
    }

    public int getFirstPointLat() {
        return firstPointLat;
    }

    public void setFirstPointLat(int firstPointLat) {
        this.firstPointLat = firstPointLat;
    }

    public int getFirstPointLon() {
        return firstPointLon;
    }

    public void setFirstPointLon(int firstPointLon) {
        this.firstPointLon = firstPointLon;
    }

    public byte getResolutionAndComponentFlags() {
        return resolutionAndComponentFlags;
    }

    public void setResolutionAndComponentFlags(byte resolutionAndComponentFlags) {
        this.resolutionAndComponentFlags = resolutionAndComponentFlags;
    }

    public int getLastPointLat() {
        return lastPointLat;
    }

    public void setLastPointLat(int lastPointLat) {
        this.lastPointLat = lastPointLat;
    }

    public int getLastPointLon() {
        return lastPointLon;
    }

    public void setLastPointLon(int lastPointLon) {
        this.lastPointLon = lastPointLon;
    }

    public int getiDirectionIncrement() {
        return iDirectionIncrement;
    }

    public void setiDirectionIncrement(int iDirectionIncrement) {
        this.iDirectionIncrement = iDirectionIncrement;
    }

    public int getjDirectionIncrement() {
        return jDirectionIncrement;
    }

    public void setjDirectionIncrement(int jDirectionIncrement) {
        this.jDirectionIncrement = jDirectionIncrement;
    }

    public byte getScanningMode() {
        return scanningMode;
    }

    public void setScanningMode(byte scanningMode) {
        this.scanningMode = scanningMode;
    }
}
