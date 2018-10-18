package com.ph.grib2tools.grib2file.datarepresentation;

import com.ph.grib2tools.grib2file.GribSection;

import java.nio.ByteBuffer;

public class DataRepresentationTemplate50 extends DataRepresentationTemplate5x {

    private byte   numberBits;
    private short  typeOfField;
    private String typeOfFieldName;


    public DataRepresentationTemplate50(ByteBuffer byteBuffer) {

        super.setReferenceValueR(byteBuffer.getFloat());
        super.setDecimalScaleFactorD(GribSection.correctNegativeShort(byteBuffer.getShort()));
        super.setBinaryScaleFactorE(GribSection.correctNegativeShort(byteBuffer.getShort()));
        numberBits = byteBuffer.get();
        typeOfField = (short) Byte.toUnsignedInt(byteBuffer.get());
        typeOfFieldName = chooseTypeOfFieldName(typeOfField);
    }

    public String chooseTypeOfFieldName(short typeOfField) {
        String name;
        switch ( typeOfField ) {
            case 0:
                name = "Floating Point";
                break;
            case 1:
                name = "Integer";
                break;
            case 255:
                name = "Missing";
                break;
            default:
                // 2-191: Reserved , 192-254: Reserved for Local Use
                name = "Reserved or Reserved for Local Use";
                break;
        }
        return name;
    }

    public byte getNumberBits() {
        return numberBits;
    }

    public void setNumberBits(byte numberBits) {
        this.numberBits = numberBits;
    }

    public short getTypeOfField() {
        return typeOfField;
    }

    public void setTypeOfField(short typeOfField) {
        this.typeOfField = typeOfField;
    }

    public String getTypeOfFieldName() {
        return typeOfFieldName;
    }

    public void setTypeOfFieldName(String typeOfFieldName) {
        this.typeOfFieldName = typeOfFieldName;
    }
}
