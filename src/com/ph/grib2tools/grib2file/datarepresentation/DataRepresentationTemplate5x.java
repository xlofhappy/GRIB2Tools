package com.ph.grib2tools.grib2file.datarepresentation;

import java.io.Serializable;

public class DataRepresentationTemplate5x implements Serializable {

    private static final long serialVersionUID = 100L;

    private float referenceValueR;
    private short binaryScaleFactorE;
    private short decimalScaleFactorD;

    public float getReferenceValueR() {
        return referenceValueR;
    }

    public void setReferenceValueR(float referenceValueR) {
        this.referenceValueR = referenceValueR;
    }

    public short getBinaryScaleFactorE() {
        return binaryScaleFactorE;
    }

    public void setBinaryScaleFactorE(short binaryScaleFactorE) {
        this.binaryScaleFactorE = binaryScaleFactorE;
    }

    public short getDecimalScaleFactorD() {
        return decimalScaleFactorD;
    }

    public void setDecimalScaleFactorD(short decimalScaleFactorD) {
        this.decimalScaleFactorD = decimalScaleFactorD;
    }
}
