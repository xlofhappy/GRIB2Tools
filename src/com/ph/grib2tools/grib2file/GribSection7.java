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
        super.readSectionHeader(grib2File);
        startPosition = grib2File.getFilePointer() - SECTION_HEADER_LENGTH;
        endPosition = grib2File.getFilePointer() + getSectionLength() - SECTION_HEADER_LENGTH;
        grib2File.seek(endPosition);
    }

    public long getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(long startPosition) {
        this.startPosition = startPosition;
    }

    public long getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(long endPosition) {
        this.endPosition = endPosition;
    }
}
