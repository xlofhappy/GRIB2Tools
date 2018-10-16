package com.ph.grib2tools.grib2file.productdefinition;

import java.nio.ByteBuffer;

public class ProductDefinitionTemplate40 extends ProductDefinitionTemplate4x {

    private static final long serialVersionUID = 100L;

    private byte  parameterCategory;
    private byte  parameterNumber;
    private byte  typeOfGeneratingProcess;
    private byte  backgroundGeneratingProcessId;
    private byte  analysisOrForecastGeneratingProcessId;
    private short hoursObservationalDataCutOff;
    private byte  minutesObservationalDataCutOff;
    private byte  unitTimeRange;
    private int   forecastTime;
    private byte  typeFirstFixedSurface;
    private byte  scaleFactorFirstFixedSurface;
    private int   scaledValueFirstFixedSurface;
    private byte  typeSecondFixedSurface;
    private byte  scaleFactorSecondFixedSurface;
    private int   scaledValueSecondFixedSurface;


    public ProductDefinitionTemplate40(ByteBuffer byteBuffer) {

        parameterCategory = byteBuffer.get();
        parameterNumber = byteBuffer.get();
        typeOfGeneratingProcess = byteBuffer.get();
        backgroundGeneratingProcessId = byteBuffer.get();
        analysisOrForecastGeneratingProcessId = byteBuffer.get();
        hoursObservationalDataCutOff = byteBuffer.getShort();
        minutesObservationalDataCutOff = byteBuffer.get();
        unitTimeRange = byteBuffer.get();
        forecastTime = byteBuffer.getInt();
        typeFirstFixedSurface = byteBuffer.get();
        scaleFactorFirstFixedSurface = byteBuffer.get();
        scaledValueFirstFixedSurface = byteBuffer.getInt();
        typeSecondFixedSurface = byteBuffer.get();
        scaleFactorSecondFixedSurface = byteBuffer.get();
        scaledValueSecondFixedSurface = byteBuffer.getInt();
    }

    public byte getParameterCategory() {
        return parameterCategory;
    }

    public void setParameterCategory(byte parameterCategory) {
        this.parameterCategory = parameterCategory;
    }

    public byte getParameterNumber() {
        return parameterNumber;
    }

    public void setParameterNumber(byte parameterNumber) {
        this.parameterNumber = parameterNumber;
    }

    public byte getTypeOfGeneratingProcess() {
        return typeOfGeneratingProcess;
    }

    public void setTypeOfGeneratingProcess(byte typeOfGeneratingProcess) {
        this.typeOfGeneratingProcess = typeOfGeneratingProcess;
    }

    public byte getBackgroundGeneratingProcessId() {
        return backgroundGeneratingProcessId;
    }

    public void setBackgroundGeneratingProcessId(byte backgroundGeneratingProcessId) {
        this.backgroundGeneratingProcessId = backgroundGeneratingProcessId;
    }

    public byte getAnalysisOrForecastGeneratingProcessId() {
        return analysisOrForecastGeneratingProcessId;
    }

    public void setAnalysisOrForecastGeneratingProcessId(byte analysisOrForecastGeneratingProcessId) {
        this.analysisOrForecastGeneratingProcessId = analysisOrForecastGeneratingProcessId;
    }

    public short getHoursObservationalDataCutOff() {
        return hoursObservationalDataCutOff;
    }

    public void setHoursObservationalDataCutOff(short hoursObservationalDataCutOff) {
        this.hoursObservationalDataCutOff = hoursObservationalDataCutOff;
    }

    public byte getMinutesObservationalDataCutOff() {
        return minutesObservationalDataCutOff;
    }

    public void setMinutesObservationalDataCutOff(byte minutesObservationalDataCutOff) {
        this.minutesObservationalDataCutOff = minutesObservationalDataCutOff;
    }

    public byte getUnitTimeRange() {
        return unitTimeRange;
    }

    public void setUnitTimeRange(byte unitTimeRange) {
        this.unitTimeRange = unitTimeRange;
    }

    public int getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(int forecastTime) {
        this.forecastTime = forecastTime;
    }

    public byte getTypeFirstFixedSurface() {
        return typeFirstFixedSurface;
    }

    public void setTypeFirstFixedSurface(byte typeFirstFixedSurface) {
        this.typeFirstFixedSurface = typeFirstFixedSurface;
    }

    public byte getScaleFactorFirstFixedSurface() {
        return scaleFactorFirstFixedSurface;
    }

    public void setScaleFactorFirstFixedSurface(byte scaleFactorFirstFixedSurface) {
        this.scaleFactorFirstFixedSurface = scaleFactorFirstFixedSurface;
    }

    public int getScaledValueFirstFixedSurface() {
        return scaledValueFirstFixedSurface;
    }

    public void setScaledValueFirstFixedSurface(int scaledValueFirstFixedSurface) {
        this.scaledValueFirstFixedSurface = scaledValueFirstFixedSurface;
    }

    public byte getTypeSecondFixedSurface() {
        return typeSecondFixedSurface;
    }

    public void setTypeSecondFixedSurface(byte typeSecondFixedSurface) {
        this.typeSecondFixedSurface = typeSecondFixedSurface;
    }

    public byte getScaleFactorSecondFixedSurface() {
        return scaleFactorSecondFixedSurface;
    }

    public void setScaleFactorSecondFixedSurface(byte scaleFactorSecondFixedSurface) {
        this.scaleFactorSecondFixedSurface = scaleFactorSecondFixedSurface;
    }

    public int getScaledValueSecondFixedSurface() {
        return scaledValueSecondFixedSurface;
    }

    public void setScaledValueSecondFixedSurface(int scaledValueSecondFixedSurface) {
        this.scaledValueSecondFixedSurface = scaledValueSecondFixedSurface;
    }
}
