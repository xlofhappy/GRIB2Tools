package com.ph.grib2tools.grib2file.griddefinition;

import java.nio.ByteBuffer;

public class GridDefinitionTemplate31 extends GridDefinitionTemplate30 {

    private float latOfSouthernPolOfProjection;
    private float lonOfSouthernPolOfProjection;
    private int   angelOfRotationOfProjection;


    public GridDefinitionTemplate31(ByteBuffer byteBuffer) {
        super(byteBuffer);
        // 73-76
        latOfSouthernPolOfProjection = byteBuffer.getInt();
        // 77-80
        lonOfSouthernPolOfProjection = byteBuffer.getInt();
        // 81-84
        angelOfRotationOfProjection = byteBuffer.getInt();
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
