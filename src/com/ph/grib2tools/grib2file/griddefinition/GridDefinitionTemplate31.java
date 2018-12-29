package com.ph.grib2tools.grib2file.griddefinition;

import com.ph.grib2tools.grib2file.DataUtil;

import java.io.IOException;
import java.io.RandomAccessFile;

public class GridDefinitionTemplate31 extends GridDefinitionTemplate30 {

    private float latOfSouthernPolOfProjection;
    private float lonOfSouthernPolOfProjection;
    private int   angelOfRotationOfProjection;


    public GridDefinitionTemplate31(RandomAccessFile gribFile) throws IOException {
        super(gribFile);
        // 73-76
        latOfSouthernPolOfProjection = DataUtil.int4(gribFile);
        // 77-80
        lonOfSouthernPolOfProjection = DataUtil.int4(gribFile);
        // 81-84
        angelOfRotationOfProjection = DataUtil.int4(gribFile);
    }

    public float getLatOfSouthernPolOfProjection() {
        return latOfSouthernPolOfProjection;
    }

    public void setLatOfSouthernPolOfProjection(float latOfSouthernPolOfProjection) {
        this.latOfSouthernPolOfProjection = latOfSouthernPolOfProjection;
    }

    public float getLonOfSouthernPolOfProjection() {
        return lonOfSouthernPolOfProjection;
    }

    public void setLonOfSouthernPolOfProjection(float lonOfSouthernPolOfProjection) {
        this.lonOfSouthernPolOfProjection = lonOfSouthernPolOfProjection;
    }

    public int getAngelOfRotationOfProjection() {
        return angelOfRotationOfProjection;
    }

    public void setAngelOfRotationOfProjection(int angelOfRotationOfProjection) {
        this.angelOfRotationOfProjection = angelOfRotationOfProjection;
    }
}
