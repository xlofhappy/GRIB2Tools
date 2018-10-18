package com.ph.grib2tools.grib2file;

import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate30;
import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate31;
import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate33;
import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate3x;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class GribSection3 extends GribSection {

    private short                    sourceOfGridDefinition;
    private long                     numDataPoints;
    private byte                     numOfOctetsForOptionalList;
    private short                    interpretationOfList;
    private int                      gridDefinitionTemplateNumber;
    private GridDefinitionTemplate3x gridDefinitionTemplate;
    private byte[]                   optionalListOfPoints;

    @Override
    public void readData(RandomAccessFile gribFile) throws IOException {
        super.readData(gribFile);
        readSection();
    }

    private void readSection() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(getSectionData());
        // 6
        sourceOfGridDefinition = (short) Byte.toUnsignedInt(byteBuffer.get());
        // 7-10
        numDataPoints = Integer.toUnsignedLong(byteBuffer.getInt());
        // 11
        numOfOctetsForOptionalList = byteBuffer.get();
        // 12
        interpretationOfList = (short) Byte.toUnsignedInt(byteBuffer.get());
        // 13-14
        gridDefinitionTemplateNumber = Short.toUnsignedInt(byteBuffer.getShort());

        if ( sourceOfGridDefinition == 0 ) {
            if ( gridDefinitionTemplateNumber == 0 ) {
                gridDefinitionTemplate = new GridDefinitionTemplate30(byteBuffer);
            } else if ( gridDefinitionTemplateNumber == 1 || gridDefinitionTemplateNumber == 2) {
                gridDefinitionTemplate = new GridDefinitionTemplate31(byteBuffer);
            } else if ( gridDefinitionTemplateNumber == 3) {
                gridDefinitionTemplate = new GridDefinitionTemplate33(byteBuffer);
            } else {
                System.out.println("Grid Definition Template Number 3." + gridDefinitionTemplateNumber + " not implemented.");
            }
        }

        optionalListOfPoints = new byte[numOfOctetsForOptionalList];
        byteBuffer.get(optionalListOfPoints);
    }

    public short getSourceOfGridDefinition() {
        return sourceOfGridDefinition;
    }

    public void setSourceOfGridDefinition(short sourceOfGridDefinition) {
        this.sourceOfGridDefinition = sourceOfGridDefinition;
    }

    public long getNumDataPoints() {
        return numDataPoints;
    }

    public void setNumDataPoints(long numDataPoints) {
        this.numDataPoints = numDataPoints;
    }

    public byte getNumOfOctetsForOptionalList() {
        return numOfOctetsForOptionalList;
    }

    public void setNumOfOctetsForOptionalList(byte numOfOctetsForOptionalList) {
        this.numOfOctetsForOptionalList = numOfOctetsForOptionalList;
    }

    public short getInterpretationOfList() {
        return interpretationOfList;
    }

    public void setInterpretationOfList(short interpretationOfList) {
        this.interpretationOfList = interpretationOfList;
    }

    public int getGridDefinitionTemplateNumber() {
        return gridDefinitionTemplateNumber;
    }

    public void setGridDefinitionTemplateNumber(int gridDefinitionTemplateNumber) {
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
