package com.ph.grib2tools.grib2file.datarepresentation;

import java.io.Serializable;

public class DataRepresentationTemplate5x implements Serializable {

    private float referenceValueR;
    private int binaryScaleFactorE;
    private int decimalScaleFactorD;

    public float getReferenceValueR() {
        return referenceValueR;
    }

    public void setReferenceValueR(float referenceValueR) {
        this.referenceValueR = referenceValueR;
    }

    public int getBinaryScaleFactorE() {
        return binaryScaleFactorE;
    }

    public void setBinaryScaleFactorE(int binaryScaleFactorE) {
        this.binaryScaleFactorE = binaryScaleFactorE;
    }

    public int getDecimalScaleFactorD() {
        return decimalScaleFactorD;
    }

    public void setDecimalScaleFactorD(int decimalScaleFactorD) {
        this.decimalScaleFactorD = decimalScaleFactorD;
    }
}
