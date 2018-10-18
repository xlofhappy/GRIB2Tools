package com.ph.grib2tools.grib2file.griddefinition;

import java.nio.ByteBuffer;

public class GridDefinitionTemplate33 extends GridDefinitionTemplate31 {

    private float latOfPoleOfStretching;
    private float lonOfPoleOfStretching;
    private float stretchingFactor;

    public GridDefinitionTemplate33(ByteBuffer byteBuffer) {
        super(byteBuffer);
        // 85-88
        latOfPoleOfStretching = byteBuffer.getInt();
        // 89-92
        lonOfPoleOfStretching = byteBuffer.getInt();
        // 93-96
        stretchingFactor = byteBuffer.getInt();
    }

    public float getLatOfPoleOfStretching() {
        return latOfPoleOfStretching;
    }

    public void setLatOfPoleOfStretching(float latOfPoleOfStretching) {
        this.latOfPoleOfStretching = latOfPoleOfStretching;
    }

    public float getLonOfPoleOfStretching() {
        return lonOfPoleOfStretching;
    }

    public void setLonOfPoleOfStretching(float lonOfPoleOfStretching) {
        this.lonOfPoleOfStretching = lonOfPoleOfStretching;
    }

    public float getStretchingFactor() {
        return stretchingFactor;
    }

    public void setStretchingFactor(float stretchingFactor) {
        this.stretchingFactor = stretchingFactor;
    }
}
