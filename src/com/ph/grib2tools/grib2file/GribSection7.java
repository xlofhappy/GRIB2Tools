package com.ph.grib2tools.grib2file;

import java.io.IOException;
import java.io.RandomAccessFile;

public class GribSection7 extends GribSection {

    /**
     * record the data's start position in the GRIB2 File
     */
    private long startPosition;
    /**
     * record the data's end position in the GRIB2 File
     */
    private long endPosition;

    @Override
    public void readData(RandomAccessFile grib2File) throws IOException {
        super.readData(grib2File);
        startPosition = grib2File.getFilePointer() - SECTION_HEADER_LENGTH;
        endPosition = grib2File.getFilePointer() + getSectionLength() - SECTION_HEADER_LENGTH;
    }


    private int bitPos = 0;
    private int bitBuf = 0;

    public int bits2UInt(RandomAccessFile grib2File, int numberOfBits) throws IOException {
        int bitsLeft = numberOfBits;
        int result   = 0;

        if ( bitPos == 0 ) {
            bitBuf = grib2File.read();
            bitPos = 8;
        }

        while ( true ) {
            int shift = bitsLeft - bitPos;
            if ( shift > 0 ) {
                result |= bitBuf << shift;
                bitsLeft -= bitPos;
                bitBuf = grib2File.read();
                bitPos = 8;
            } else {
                result |= bitBuf >> -shift;
                bitPos -= bitsLeft;
                bitBuf &= 0xff >> (8 - bitPos);
                return result;
            }
        }
    }

    public long getStartPosition() {
        return startPosition;
    }

    public long getEndPosition() {
        return endPosition;
    }
}
