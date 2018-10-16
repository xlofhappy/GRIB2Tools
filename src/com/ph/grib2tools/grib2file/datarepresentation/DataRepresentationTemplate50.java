package com.ph.grib2tools.grib2file.datarepresentation;

import com.ph.grib2tools.grib2file.GribSection;

import java.nio.ByteBuffer;

public class DataRepresentationTemplate50 extends DataRepresentationTemplate5x {

    private static final long serialVersionUID = 100L;

    private byte numberBits;
    private byte typeOfField;


    public DataRepresentationTemplate50(ByteBuffer byteBuffer) {

        super.setReferenceValueR(byteBuffer.getFloat());
        super.setDecimalScaleFactorD(GribSection.correctNegativeShort(byteBuffer.getShort()));
        super.setBinaryScaleFactorE(GribSection.correctNegativeShort(byteBuffer.getShort()));
        numberBits = byteBuffer.get();
        typeOfField = byteBuffer.get();
    }

    public byte getNumberBits() {
        return numberBits;
    }

    public void setNumberBits(byte numberBits) {
        this.numberBits = numberBits;
    }

    public byte getTypeOfField() {
        return typeOfField;
    }

    public void setTypeOfField(byte typeOfField) {
        this.typeOfField = typeOfField;
    }
}
