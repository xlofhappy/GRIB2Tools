package com.ph.grib2tools.grib2file.productdefinition;

import java.nio.ByteBuffer;

public class ProductDefinitionTemplate40 extends ProductDefinitionTemplate4x {

    private short  parameterCategory;
    private String parameterCategoryName;
    private short  parameterNumber;
    private String parameterNumberName;
    private short  typeOfGeneratingProcess;
    private byte   backgroundGeneratingProcessId;
    private byte   analysisOrForecastGeneratingProcessId;
    private short  hoursObservationalDataCutOff;
    private byte   minutesObservationalDataCutOff;
    private short  unitTimeRange;
    private int    forecastTime;
    private short  typeFirstFixedSurface;
    private byte   scaleFactorFirstFixedSurface;
    private int    scaledValueFirstFixedSurface;
    private short  typeSecondFixedSurface;
    private byte   scaleFactorSecondFixedSurface;
    private int    scaledValueSecondFixedSurface;


    public ProductDefinitionTemplate40(ByteBuffer byteBuffer) {
        // 10
        parameterCategory = (short) Byte.toUnsignedInt(byteBuffer.get());
        parameterCategoryName = chooseParameterCategoryName(parameterCategory);
        // 11
        parameterNumber = byteBuffer.get();
        // 12
        typeOfGeneratingProcess = (short) Byte.toUnsignedInt(byteBuffer.get());
        // 13
        backgroundGeneratingProcessId = byteBuffer.get();
        // 14
        analysisOrForecastGeneratingProcessId = byteBuffer.get();
        // 15-16
        hoursObservationalDataCutOff = byteBuffer.getShort();
        // 17
        minutesObservationalDataCutOff = byteBuffer.get();
        // 18
        unitTimeRange = (short) Byte.toUnsignedInt(byteBuffer.get());
        // 19-22
        forecastTime = byteBuffer.getInt();
        // 23
        typeFirstFixedSurface = (short) Byte.toUnsignedInt(byteBuffer.get());
        // 24
        scaleFactorFirstFixedSurface = byteBuffer.get();
        // 25-28
        scaledValueFirstFixedSurface = byteBuffer.getInt();
        // 29
        typeSecondFixedSurface = (short) Byte.toUnsignedInt(byteBuffer.get());
        // 30
        scaleFactorSecondFixedSurface = byteBuffer.get();
        // 31-34
        scaledValueSecondFixedSurface = byteBuffer.getInt();
    }

    /**
     * TODO should be different based Product discipline
     *
     * @param parameterCategory
     */
    private String chooseParameterCategoryName(short parameterCategory) {
        String name;
        switch ( parameterCategory ) {
            case 0:
                name = "Temperature";
                break;
            case 1:
                name = "Moisture";
                break;
            case 2:
                name = "Momentum";
                break;
            case 3:
                name = "Mass";
                break;
            case 4:
                name = "Short-wave radiation";
                break;
            case 5:
                name = "Long-wave radiation";
                break;
            case 6:
                name = "Cloud";
                break;
            case 7:
                name = "Thermodynamic Stability indicies";
                break;
            case 8:
                name = "Kinematic Stability indicies";
                break;
            case 9:
                name = "Temperature Probabilities*";
                break;
            case 10:
                name = "Moisture Probabilities*";
                break;
            case 11:
                name = "Momentum Probabilities*";
                break;
            case 12:
                name = "Mass Probabilities*";
                break;
            case 13:
                name = "Aerosols";
                break;
            case 14:
                name = "Trace gases (e.g. Ozone, CO2)";
                break;
            case 15:
                name = "Radar";
                break;
            case 16:
                name = "Forecast Radar Imagery";
                break;
            case 17:
                name = "Electrodynamics";
                break;
            case 18:
                name = "Nuclear/radiology";
                break;
            case 19:
                name = "Physical atmospheric properties";
                break;
            case 20:
                name = "Atmospheric chemical Constituents";
                break;
            case 190:
                name = "CCITT IA5 string";
                break;
            case 191:
                name = "Miscellaneous ";
                break;
            case 192:
                name = "Covariance";
                break;
            case 255:
                name = "Missing";
                break;
            default:
                name = "Reserved for Local Use";
                break;
        }
        return name;
    }

    public short getParameterCategory() {
        return parameterCategory;
    }

    public void setParameterCategory(short parameterCategory) {
        this.parameterCategory = parameterCategory;
    }

    public String getParameterCategoryName() {
        return parameterCategoryName;
    }

    public void setParameterCategoryName(String parameterCategoryName) {
        this.parameterCategoryName = parameterCategoryName;
    }

    public short getParameterNumber() {
        return parameterNumber;
    }

    public void setParameterNumber(short parameterNumber) {
        this.parameterNumber = parameterNumber;
    }

    public String getParameterNumberName() {
        return parameterNumberName;
    }

    public void setParameterNumberName(String parameterNumberName) {
        this.parameterNumberName = parameterNumberName;
    }

    public short getTypeOfGeneratingProcess() {
        return typeOfGeneratingProcess;
    }

    public void setTypeOfGeneratingProcess(short typeOfGeneratingProcess) {
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

    public short getUnitTimeRange() {
        return unitTimeRange;
    }

    public void setUnitTimeRange(short unitTimeRange) {
        this.unitTimeRange = unitTimeRange;
    }

    public int getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(int forecastTime) {
        this.forecastTime = forecastTime;
    }

    public short getTypeFirstFixedSurface() {
        return typeFirstFixedSurface;
    }

    public void setTypeFirstFixedSurface(short typeFirstFixedSurface) {
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

    public short getTypeSecondFixedSurface() {
        return typeSecondFixedSurface;
    }

    public void setTypeSecondFixedSurface(short typeSecondFixedSurface) {
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
