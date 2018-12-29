package com.ph.grib2tools.grib2file.productdefinition;

import com.ph.grib2tools.grib2file.DataUtil;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ProductDefinitionTemplate40 extends ProductDefinitionTemplate4x {

    private int    parameterCategory;
    private String parameterCategoryName;
    private int    parameterNumber;
    private String parameterNumberName;
    private int    typeOfGeneratingProcess;
    private int    backgroundGeneratingProcessId;
    private int    analysisOrForecastGeneratingProcessId;
    private int    hoursObservationalDataCutOff;
    private int    minutesObservationalDataCutOff;
    private int    unitTimeRange;
    private int    forecastTime;
    private int    typeFirstFixedSurface;
    private int    scaleFactorFirstFixedSurface;
    private int    scaledValueFirstFixedSurface;
    private int    typeSecondFixedSurface;
    private int    scaleFactorSecondFixedSurface;
    private int    scaledValueSecondFixedSurface;


    public ProductDefinitionTemplate40(RandomAccessFile gribFile) throws IOException {
        // 10
        parameterCategory = gribFile.read();
        parameterCategoryName = chooseParameterCategoryName(parameterCategory);
        // 11
        parameterNumber = gribFile.read();
        // 12
        typeOfGeneratingProcess = gribFile.read();
        // 13
        backgroundGeneratingProcessId = gribFile.read();
        // 14
        analysisOrForecastGeneratingProcessId = gribFile.read();
        // 15-16
        hoursObservationalDataCutOff = DataUtil.int2(gribFile);
        // 17
        minutesObservationalDataCutOff = gribFile.read();
        // 18
        unitTimeRange = gribFile.read();
        // 19-22
        forecastTime = DataUtil.int4(gribFile);
        // 23
        typeFirstFixedSurface = gribFile.read();
        // 24
        scaleFactorFirstFixedSurface = gribFile.read();
        // 25-28
        scaledValueFirstFixedSurface = DataUtil.int4(gribFile);
        // 29
        typeSecondFixedSurface = gribFile.read();
        // 30
        scaleFactorSecondFixedSurface = gribFile.read();
        // 31-34
        scaledValueSecondFixedSurface = DataUtil.int4(gribFile);
    }

    /**
     * TODO should be different based Product discipline
     *
     * @param parameterCategory
     */
    private String chooseParameterCategoryName(int parameterCategory) {
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

    public int getParameterCategory() {
        return parameterCategory;
    }

    public String getParameterCategoryName() {
        return parameterCategoryName;
    }

    public int getParameterNumber() {
        return parameterNumber;
    }

    public String getParameterNumberName() {
        return parameterNumberName;
    }

    public int getTypeOfGeneratingProcess() {
        return typeOfGeneratingProcess;
    }

    public int getBackgroundGeneratingProcessId() {
        return backgroundGeneratingProcessId;
    }

    public int getAnalysisOrForecastGeneratingProcessId() {
        return analysisOrForecastGeneratingProcessId;
    }

    public int getHoursObservationalDataCutOff() {
        return hoursObservationalDataCutOff;
    }

    public int getMinutesObservationalDataCutOff() {
        return minutesObservationalDataCutOff;
    }

    public int getUnitTimeRange() {
        return unitTimeRange;
    }

    public int getForecastTime() {
        return forecastTime;
    }

    public int getTypeFirstFixedSurface() {
        return typeFirstFixedSurface;
    }

    public int getScaleFactorFirstFixedSurface() {
        return scaleFactorFirstFixedSurface;
    }

    public int getScaledValueFirstFixedSurface() {
        return scaledValueFirstFixedSurface;
    }

    public int getTypeSecondFixedSurface() {
        return typeSecondFixedSurface;
    }

    public int getScaleFactorSecondFixedSurface() {
        return scaleFactorSecondFixedSurface;
    }

    public int getScaledValueSecondFixedSurface() {
        return scaledValueSecondFixedSurface;
    }
}
