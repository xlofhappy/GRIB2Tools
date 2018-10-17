package com.ph.grib2tools.grib2file;

import java.io.IOException;
import java.io.RandomAccessFile;

public class GribSection2 extends GribSection {

    private static final long serialVersionUID = 100L;

    public GribSection2(RandomAccessFile gribFile) throws IOException {
        super(gribFile);
    }

    @Override
    public void readData(RandomAccessFile gribFile) throws IOException {
        super.readData(gribFile);
        readSection();
    }

    private void readSection() {
    }
}
