package com.ph.grib2tools.grib2file;

import java.io.IOException;
import java.io.RandomAccessFile;

public class GribSection6 extends GribSection {

    private static final long serialVersionUID = 100L;

    /**
     * Content and structure of a Section 6
     */
    private int    bitMapIndicator;
    private byte[] bitMap;


    @Override
    public void readData(RandomAccessFile grib2File) throws IOException {
        super.readData(grib2File);
        readSection(grib2File);
    }

    private void readSection(RandomAccessFile grib2File) throws IOException {
        // 6
        bitMapIndicator = grib2File.read();
        bitMap = new byte[getSectionLength() - 6];
        grib2File.read(bitMap);
    }

    public int getBitMapIndicator() {
        return bitMapIndicator;
    }

    public byte[] getBitMap() {
        return bitMap;
    }
}
