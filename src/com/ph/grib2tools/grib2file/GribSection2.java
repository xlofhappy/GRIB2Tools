package com.ph.grib2tools.grib2file;

import java.io.IOException;
import java.io.InputStream;

public class GribSection2 extends GribSection {

    private static final long serialVersionUID = 100L;

    public GribSection2(InputStream gribFile) throws IOException {
        super(gribFile);
    }

    public GribSection2(GribSection gribSection) {
        super(gribSection.getSectionLength(), gribSection.getSectionNumber(), gribSection.getSectionData());
    }

    @Override
    public void readData(InputStream gribFile) throws IOException {
        super.readData(gribFile);
        readSection();
    }

    private void readSection() {
    }
}
