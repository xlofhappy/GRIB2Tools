package com.ph.grib2tools.grib2file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class GribSection6 extends GribSection {

    private static final long serialVersionUID = 100L;

    /**
     * Content and structure of a Section 6
     */
    private short  bitMapIndicator;
    private byte[] bitMap;


    @Override
    public void readData(RandomAccessFile grib2File) throws IOException {
        super.readData(grib2File);
        readSection();
    }

    private void readSection() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(getSectionData());
        // 6
        bitMapIndicator = (short) Byte.toUnsignedInt(byteBuffer.get());
        bitMap = new byte[getSectionLength() - 6];
        byteBuffer.get(bitMap);
    }

    public short getBitMapIndicator() {
        return bitMapIndicator;
    }

    public void setBitMapIndicator(short bitMapIndicator) {
        this.bitMapIndicator = bitMapIndicator;
    }

    public byte[] getBitMap() {
        return bitMap;
    }

    public void setBitMap(byte[] bitMap) {
        this.bitMap = bitMap;
    }
}
