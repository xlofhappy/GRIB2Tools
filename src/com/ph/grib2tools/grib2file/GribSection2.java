package com.ph.grib2tools.grib2file;

import java.io.IOException;
import java.io.RandomAccessFile;

public class GribSection2 extends GribSection {

    private static final long serialVersionUID = 100L;

    @Override
    public void readData(RandomAccessFile gribFile) throws IOException {
        super.readData(gribFile);
        readSection(gribFile);
    }

    private void readSection(RandomAccessFile gribFile) {
    }
}
