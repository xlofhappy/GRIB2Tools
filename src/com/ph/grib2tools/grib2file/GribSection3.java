package com.ph.grib2tools.grib2file;

import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate30;
import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate3x;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class GribSection3 extends GribSection {

    private static final long serialVersionUID = 100L;

    /**
     * Content and structure of a Section 3
     */
    private byte                     sourceOfGridDefinition;
    private int                      numDataPoints;
    private byte                     numOfOctetsForOptionalList;
    private byte                     interpretationOfList;
    private short                    gridDefinitionTemplateNumber;
    private GridDefinitionTemplate3x gridDefinitionTemplate;
    private byte[]                   optionalListOfPoints;

    public GribSection3(RandomAccessFile gribFile) throws IOException {
        super(gribFile);
    }

    public GribSection3(GribSection gribSection) {
        super(gribSection.getSectionLength(), gribSection.getSectionNumber(), gribSection.getSectionData());
    }

    @Override
    public void readData(RandomAccessFile gribFile) throws IOException {
        super.readData(gribFile);
        readSection();
    }

    private void readSection() {

        ByteBuffer byteBuffer = ByteBuffer.wrap(getSectionData());

        // 6
        sourceOfGridDefinition = byteBuffer.get();
        // 7-10
        numDataPoints = byteBuffer.getInt();
        // 11
        numOfOctetsForOptionalList = byteBuffer.get();
        // 12
        interpretationOfList = byteBuffer.get();
        // 13-14
        gridDefinitionTemplateNumber = byteBuffer.getShort();

        if ( gridDefinitionTemplateNumber == 0 ) {
            gridDefinitionTemplate = new GridDefinitionTemplate30(byteBuffer);
        } else {
            System.out.println("Grid Definition Template Number 3." + gridDefinitionTemplateNumber + " not implemented.");
        }

        optionalListOfPoints = new byte[numOfOctetsForOptionalList];
        byteBuffer.get(optionalListOfPoints);
    }

    public byte getSourceOfGridDefinition() {
        return sourceOfGridDefinition;
    }

    public void setSourceOfGridDefinition(byte sourceOfGridDefinition) {
        this.sourceOfGridDefinition = sourceOfGridDefinition;
    }

    public int getNumDataPoints() {
        return numDataPoints;
    }

    public void setNumDataPoints(int numDataPoints) {
        this.numDataPoints = numDataPoints;
    }

    public byte getNumOfOctetsForOptionalList() {
        return numOfOctetsForOptionalList;
    }

    public void setNumOfOctetsForOptionalList(byte numOfOctetsForOptionalList) {
        this.numOfOctetsForOptionalList = numOfOctetsForOptionalList;
    }

    public byte getInterpretationOfList() {
        return interpretationOfList;
    }

    public void setInterpretationOfList(byte interpretationOfList) {
        this.interpretationOfList = interpretationOfList;
    }

    public short getGridDefinitionTemplateNumber() {
        return gridDefinitionTemplateNumber;
    }

    public void setGridDefinitionTemplateNumber(short gridDefinitionTemplateNumber) {
        this.gridDefinitionTemplateNumber = gridDefinitionTemplateNumber;
    }

    public GridDefinitionTemplate3x getGridDefinitionTemplate() {
        return gridDefinitionTemplate;
    }

    public void setGridDefinitionTemplate(GridDefinitionTemplate3x gridDefinitionTemplate) {
        this.gridDefinitionTemplate = gridDefinitionTemplate;
    }

    public byte[] getOptionalListOfPoints() {
        return optionalListOfPoints;
    }

    public void setOptionalListOfPoints(byte[] optionalListOfPoints) {
        this.optionalListOfPoints = optionalListOfPoints;
    }
}
