package com.ph.grib2tools.grib2file;

import java.io.*;
import java.nio.ByteBuffer;

public class GribSection0 implements Serializable {

    private static final long serialVersionUID = 100L;

    /**
     * Start identifier of a GRIB file "GRIB"
     */
    private final static byte[] GRIB_MAGIC_NUMBER = { 71, 82, 73, 66 };

    /**
     * Content and structure of a Section 0
     */
    private byte[] magicNumberBytes = new byte[4];
    private String name;
    private int    reserved;
    private int    discipline;
    private String disciplineName;
    private int    editionNumber;
    private long   totalLength;


    public GribSection0(RandomAccessFile gribFile) {
        try {
            // Read complete section
            // 1-4
            gribFile.read(magicNumberBytes);
            name = new String(magicNumberBytes);
            // 5-6
            reserved = DataUtil.int2(gribFile);
            // 7
            discipline = gribFile.read();
            disciplineName = chooseDisciplineName(discipline);
            // 8
            editionNumber = gribFile.readUnsignedByte();
            // 9-16
            totalLength = DataUtil.long8(gribFile);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private String chooseDisciplineName(int discipline) {
        String name;
        if ( discipline == 0 ) {
            name = "Meteorological Products";
        } else if ( discipline == 1 ) {
            name = "Hydrological Products";
        } else if ( discipline == 2 ) {
            name = "Land Surface Products";
        } else if ( discipline == 3 || discipline == 4 ) {
            name = "Space Products";
        } else if ( discipline >= 5 && discipline <= 9 ) {
            name = "Reserved";
        } else if ( discipline == 10 ) {
            name = "Oceanographic Products";
        } else if ( discipline >= 11 && discipline <= 191 ) {
            name = "Reserved";
        } else if ( discipline >= 192 && discipline <= 254 ) {
            name = "Reserved for Local Use";
        } else {
            name = "Missing";
        }
        return name;
    }

    private boolean isGribFile() {
        return (ByteBuffer.wrap(magicNumberBytes).compareTo(ByteBuffer.wrap(GRIB_MAGIC_NUMBER)) == 0);
    }

    public boolean isGrib2File() {
        if ( isGribFile() ) {
            return (editionNumber == 2);
        }
        return false;
    }

    public static byte[] getGRIBMAGICNUMBER() {
        return GRIB_MAGIC_NUMBER;
    }

    public byte[] getMagicNumberBytes() {
        return magicNumberBytes;
    }

    public void setMagicNumberBytes(byte[] magicNumberBytes) {
        this.magicNumberBytes = magicNumberBytes;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(short reserved) {
        this.reserved = reserved;
    }

    public int getDiscipline() {
        return discipline;
    }

    public void setDiscipline(short discipline) {
        this.discipline = discipline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    public long getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }
}
