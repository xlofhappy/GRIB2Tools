package com.ph.grib2tools.grib2file;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Template of a GRIB Section, valid for Section types from 1 to 7. Not valid for Sections of type 0 and 8
 */
public class GribSection implements Serializable {

    private static final long serialVersionUID = 100L;

    /**
     * Length of the section
     */
    private int sectionLength;
    /**
     * Section number
     */
    private byte sectionNumber;
    /**
     * Data of the section
     */
    private byte[] sectionData;


    public GribSection(int len, byte num, byte[] data) {
        sectionLength = len;
        sectionNumber = num;
        sectionData = data;
    }

    public GribSection(InputStream gribFile) throws IOException {


        // All Sections of type 1 to 7 begin with a five byte long header. This header consists of a four byte
        // long length of the section, followed by a one byte section number (type)
        byte[] sectionHeader = new byte[5];
        gribFile.read(sectionHeader);
        ByteBuffer byteBuffer = ByteBuffer.wrap(sectionHeader);

        // Extract section length and section number (type) from the header
        sectionLength = byteBuffer.getInt();
        sectionNumber = byteBuffer.get();
    }

    public GribSection initSection() {
        if ( sectionNumber == 1 ) {
            return new GribSection1(this);
        } else if ( sectionNumber == 2 ) {
            return new GribSection2(this);
        } else if ( sectionNumber == 3 ) {
            return new GribSection3(this);
        } else if ( sectionNumber == 4 ) {
            return new GribSection4(this);
        } else if ( sectionNumber == 5 ) {
            return new GribSection5(this);
        } else if ( sectionNumber == 6 ) {
            return new GribSection6(this);
        } else if ( sectionNumber == 7 ) {
            return new GribSection7(this);
        } else {
            System.out.println("Section Number " + sectionNumber + " not implemented");
        }
        return null;
    }

    public void readData(InputStream gribFile) throws IOException {
        // Read complete section
        sectionData = new byte[sectionLength - 5];
        gribFile.read(sectionData);
    }

    public void writeToStream(OutputStream gribFile) throws IOException {
        DataOutputStream dataOut = new DataOutputStream(gribFile);
        dataOut.writeInt(sectionLength);
        gribFile.write(sectionNumber);
        gribFile.write(sectionData);
    }

    /**
     * Adjust a one byte value extracted from a GRIB file according to the GRIB specification to obtain
     * a correct unsigned byte
     */
    public static short adjustUnsignedByte(int unsignedbyte) {
        return (short) ((unsignedbyte & 0x7F) + (unsignedbyte & 0x80));
    }

    /**
     * Adjust a two byte value extracted from a GRIB file according to the GRIB specification to obtain
     * a correct unsigned short
     **/
    public static int adjustUnsignedShort(int unsignedshort) {
        return (unsignedshort & 0x7FFF) + (unsignedshort & 0x8000);
    }

    /**
     * Adjust a four byte value extracted from a GRIB file according to the GRIB specification to obtain
     * a correct unsigned int
     */
    public static long adjustUnsignedInt(int unsignedint) {
        return (unsignedint & 0x7FFFFFFF) + (unsignedint & 0x80000000L);
    }

    /**
     * Convert a one byte value extracted from a GRIB file according to the GRIB specification to recover
     * the sign of a signed one
     */
    public static byte correctNegativeByte(byte unCorrectedValue) {
        byte correctedValue = (byte) (unCorrectedValue & 0x7f);
        if ( (unCorrectedValue & 0x80) == 0x80 ) { correctedValue = (byte) -correctedValue; }
        assert unCorrectedValue < 0x80 || unCorrectedValue != correctedValue;
        return correctedValue;
    }

    /**
     * Convert a two byte value extracted from a GRIB file according to the GRIB specification to recover
     * the sign of a signed short
     */
    public static short correctNegativeShort(short unCorrectedValue) {
        short correctedValue = (short) (unCorrectedValue & 0x7fff);
        if ( (unCorrectedValue & 0x8000) == 0x8000 ) { correctedValue = (short) -correctedValue; }
        return correctedValue;
    }

    /**
     * Convert a four byte value extracted from a GRIB file according to the GRIB specification to recover
     * the sign of a signed int
     */
    public static int correctNegativeInt(int unCorrectedValue) {
        int correctedValue = unCorrectedValue & 0x7fffffff;
        if ( (unCorrectedValue & 0x80000000L) == 0x80000000L ) { correctedValue = -correctedValue; }
        return correctedValue;
    }

    public int getSectionLength() {
        return sectionLength;
    }

    public void setSectionLength(int sectionLength) {
        this.sectionLength = sectionLength;
    }

    public byte getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(byte sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public byte[] getSectionData() {
        return sectionData;
    }

    public void setSectionData(byte[] sectionData) {
        this.sectionData = sectionData;
    }
}
