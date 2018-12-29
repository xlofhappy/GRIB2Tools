package com.ph.grib2tools.grib2file;

import java.io.*;
import java.nio.ByteBuffer;

public class GribSection8 implements Serializable {

    private static final long serialVersionUID = 100L;

    /**
     * End identifier of a GRIB2 file "7777"
     */
    private final byte[] GRIB_END_IDENTIFIER = { 55, 55, 55, 55 };

    /**
     * Content and structure of a Section 8
     */
    private byte[] endIdentifierBytes = new byte[4];


    public GribSection8(RandomAccessFile grib2File) {
        try {
            grib2File.read(endIdentifierBytes);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public boolean isEnd() {
        return (ByteBuffer.wrap(endIdentifierBytes).compareTo(ByteBuffer.wrap(GRIB_END_IDENTIFIER)) == 0);
    }
}
