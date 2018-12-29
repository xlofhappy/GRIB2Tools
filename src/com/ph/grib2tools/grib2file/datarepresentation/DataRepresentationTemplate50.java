package com.ph.grib2tools.grib2file.datarepresentation;

import com.ph.grib2tools.grib2file.DataUtil;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DataRepresentationTemplate50 extends DataRepresentationTemplate5x {

    private int    numberBits;
    private int    typeOfField;
    private String typeOfFieldName;


    public DataRepresentationTemplate50(RandomAccessFile grid2File) throws IOException {
        super.setReferenceValueR(DataUtil.float4(grid2File));
        super.setDecimalScaleFactorD(DataUtil.int2(grid2File));
        super.setBinaryScaleFactorE(DataUtil.int2(grid2File));
        numberBits = grid2File.read();
        typeOfField = grid2File.read();
        typeOfFieldName = chooseTypeOfFieldName(typeOfField);
    }

    public String chooseTypeOfFieldName(int typeOfField) {
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

    public int getNumberBits() {
        return numberBits;
    }

    public int getTypeOfField() {
        return typeOfField;
    }

    public String getTypeOfFieldName() {
        return typeOfFieldName;
    }
}
