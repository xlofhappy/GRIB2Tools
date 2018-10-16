package com.ph.grib2tools.grib2file;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
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
    private short  reserved;
    private byte   discipline;
    private byte   number;
    private long   totalLength;


    public GribSection0(InputStream gribfile) {

        try {

            // Read complete section
            byte[] section0 = new byte[16];
            gribfile.read(section0);
            ByteBuffer byteBuffer = ByteBuffer.wrap(section0);

            // Parse section and extract data
            byteBuffer.get(magicNumberBytes);
            reserved = byteBuffer.getShort();
            discipline = byteBuffer.get();
            number = byteBuffer.get();
            totalLength = byteBuffer.getLong();

        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void writeToStream(OutputStream gribFile) {
        DataOutputStream dataOut = new DataOutputStream(gribFile);
        try {
            gribFile.write(magicNumberBytes);
            dataOut.writeShort(reserved);
            gribFile.write(discipline);
            gribFile.write(number);
            dataOut.writeLong(totalLength);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public boolean verifyMagicNumber() {
        return (ByteBuffer.wrap(magicNumberBytes).compareTo(ByteBuffer.wrap(GRIB_MAGIC_NUMBER)) == 0);
    }

    public boolean verifyGribVersion() {
        return (number == 2);
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

    public short getReserved() {
        return reserved;
    }

    public void setReserved(short reserved) {
        this.reserved = reserved;
    }

    public byte getDiscipline() {
        return discipline;
    }

    public void setDiscipline(byte discipline) {
        this.discipline = discipline;
    }

    public byte getNumber() {
        return number;
    }

    public void setNumber(byte number) {
        this.number = number;
    }

    public long getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }

}
