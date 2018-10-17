package com.ph.grib2tools.grib2file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class GribSection6 extends GribSection {

    private static final long serialVersionUID = 100L;

    /**
     * Content and structure of a Section 6
     */
    private byte   bitMapIndicator;
    private byte[] bitMap;


    public GribSection6(RandomAccessFile grib2File) throws IOException {
        super(grib2File);
    }

    public GribSection6(GribSection gribSection) {
        super(gribSection.getSectionLength(), gribSection.getSectionNumber(), gribSection.getSectionData());
    }

    @Override
    public void readData(RandomAccessFile grib2File) throws IOException {
        super.readData(grib2File);
        readSection();
    }

    private void readSection() {

        ByteBuffer byteBuffer = ByteBuffer.wrap(getSectionData());

        // Parse section and extract data
        bitMapIndicator = byteBuffer.get();

        bitMap = new byte[getSectionLength() - 6];
        byteBuffer.get(bitMap);
    }

    public byte getBitMapIndicator() {
        return bitMapIndicator;
    }

    public void setBitMapIndicator(byte bitMapIndicator) {
        this.bitMapIndicator = bitMapIndicator;
    }

    public byte[] getBitMap() {
        return bitMap;
    }

    public void setBitMap(byte[] bitMap) {
        this.bitMap = bitMap;
    }
}
