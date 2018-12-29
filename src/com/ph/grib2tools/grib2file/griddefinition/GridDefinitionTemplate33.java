package com.ph.grib2tools.grib2file.griddefinition;

import com.ph.grib2tools.grib2file.DataUtil;

import java.io.IOException;
import java.io.RandomAccessFile;

public class GridDefinitionTemplate33 extends GridDefinitionTemplate31 {

    private float latOfPoleOfStretching;
    private float lonOfPoleOfStretching;
    private float stretchingFactor;

    public GridDefinitionTemplate33(RandomAccessFile gribFile) throws IOException {
        super(gribFile);
        // 85-88
        latOfPoleOfStretching = DataUtil.int4(gribFile);
        // 89-92
        lonOfPoleOfStretching = DataUtil.int4(gribFile);
        // 93-96
        stretchingFactor = DataUtil.int4(gribFile);
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
