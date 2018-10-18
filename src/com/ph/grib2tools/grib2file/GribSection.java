package com.ph.grib2tools.grib2file;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Template of a GRIB2 Section, valid for Section types from 1 to 7. Not valid for Sections of type 0 and 8
 */
public class GribSection implements Serializable {

    private static final long serialVersionUID = 100L;

    protected static final int SECTION_HEADER_LENGTH = 5;

    /**
     * Length of the section
     */
    private int    sectionLength;
    /**
     * Section number
     */
    private byte   sectionNumber;
    /**
     * Data of the section
     */
    private byte[] sectionData;

    protected void readSectionHeader(RandomAccessFile grib2File) throws IOException {
        // All Sections of type 1 to 7 begin with a five byte long header. This header consists of a four byte
        // long length of the section, followed by a one byte section number (type)
        byte[] sectionHeader = new byte[SECTION_HEADER_LENGTH];
        grib2File.read(sectionHeader);
        ByteBuffer byteBuffer = ByteBuffer.wrap(sectionHeader);

        // Extract section length and section number (type) from the header
        sectionLength = byteBuffer.getInt();
        sectionNumber = byteBuffer.get();
    }

    public void readData(RandomAccessFile grib2File) throws IOException {
        readSectionHeader(grib2File);
        // Read complete section
        sectionData = new byte[sectionLength - SECTION_HEADER_LENGTH];
        grib2File.read(sectionData);
    }

    public void writeToStream(OutputStream gribFile) throws IOException {
        DataOutputStream dataOut = new DataOutputStream(gribFile);
        dataOut.writeInt(sectionLength);
        gribFile.write(sectionNumber);
        gribFile.write(sectionData);
    }

    /**
     * get the section number
     */
    public static short sectionNumber(RandomAccessFile grib2File) throws IOException {
        byte[] sectionHeader = new byte[SECTION_HEADER_LENGTH];
        grib2File.read(sectionHeader);
        ByteBuffer byteBuffer = ByteBuffer.wrap(sectionHeader);
        grib2File.seek(grib2File.getFilePointer() - SECTION_HEADER_LENGTH);
        return byteBuffer.get(SECTION_HEADER_LENGTH - 1);
    }

    /**
     * get the section number
     */
    public static short isEnd(RandomAccessFile grib2File) throws IOException {
        byte[] grib2End = new byte[4];
        grib2File.read(grib2End);
        ByteBuffer byteBuffer = ByteBuffer.wrap(grib2End);
        return byteBuffer.get(SECTION_HEADER_LENGTH - 1);
    }

    /**
     * Adjust a one byte value extracted from a GRIB2 file according to the GRIB specification to obtain
     * a correct unsigned byte
     */
    public static short adjustUnsignedByte(int unSignedByte) {
        return (short) ((unSignedByte & 0x7F) + (unSignedByte & 0x80));
    }

    /**
     * Adjust a two byte value extracted from a GRIB2 file according to the GRIB specification to obtain
     * a correct unsigned short
     **/
    public static int adjustUnsignedShort(int unsignedshort) {
        return (unsignedshort & 0x7FFF) + (unsignedshort & 0x8000);
    }

    /**
     * Adjust a four byte value extracted from a GRIB2 file according to the GRIB specification to obtain
     * a correct unsigned int
     */
    public static long adjustUnsignedInt(int unsignedint) {
        return (unsignedint & 0x7FFFFFFF) + (unsignedint & 0x80000000L);
    }

    /**
     * Convert a one byte value extracted from a GRIB2 file according to the GRIB specification to recover
     * the sign of a signed one
     */
    public static byte correctNegativeByte(byte unCorrectedValue) {
        byte correctedValue = (byte) (unCorrectedValue & 0x7f);
        if ( (unCorrectedValue & 0x80) == 0x80 ) { correctedValue = (byte) -correctedValue; }
        assert unCorrectedValue < 0x80 || unCorrectedValue != correctedValue;
        return correctedValue;
    }

    /**
     * Convert a two byte value extracted from a GRIB2 file according to the GRIB specification to recover
     * the sign of a signed short
     */
    public static short correctNegativeShort(short unCorrectedValue) {
        short correctedValue = (short) (unCorrectedValue & 0x7fff);
        if ( (unCorrectedValue & 0x8000) == 0x8000 ) { correctedValue = (short) -correctedValue; }
        return correctedValue;
    }

    /**
     * Convert a four byte value extracted from a GRIB2 file according to the GRIB specification to recover
     * the sign of a signed int
     */
    public static int correctNegativeInt(int unCorrectedValue) {
        int correctedValue = unCorrectedValue & 0x7fffffff;
        if ( (unCorrectedValue & 0x80000000L) == 0x80000000L ) {
            correctedValue = -correctedValue;
        }
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

    public static void main(String[] args) {
        int a = -2147483468;
        System.out.println(correctNegativeInt(a));
    }
}
