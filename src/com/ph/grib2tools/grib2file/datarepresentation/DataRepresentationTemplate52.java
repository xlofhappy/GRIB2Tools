package com.ph.grib2tools.grib2file.datarepresentation;

import com.ph.grib2tools.grib2file.GribSection;

import java.nio.ByteBuffer;

public class DataRepresentationTemplate52 extends DataRepresentationTemplate50 {

    private static final long serialVersionUID = 100L;

    private byte  groupSplittingMethodUsed;
    private byte  missingValueManagementUsed;
    private long  primaryMissingValueSubstitute;
    private long  secondaryMissingValueSubstitute;
    private int   numberOfGroupsOfDataValues;
    private short referenceForGroupWidths;
    private short numberOfBitsUsedForTheGroupWidths;
    private int   referenceForGroupLengths;
    private short lengthIncrementForTheGroupLengths;
    private int   trueLengthOfLastGroup;
    private short numberOfBitsForScaledGroupLengths;

    public DataRepresentationTemplate52(ByteBuffer byteBuffer) {
        super(byteBuffer);
        assert byteBuffer.position() == 21 - 5;
        groupSplittingMethodUsed = byteBuffer.get();
        missingValueManagementUsed = byteBuffer.get();
        primaryMissingValueSubstitute = GribSection.adjustUnsignedInt(byteBuffer.getInt());
        secondaryMissingValueSubstitute = GribSection.adjustUnsignedInt(byteBuffer.getInt());
        // If negative this will go wrong during data extraction anyway
        numberOfGroupsOfDataValues = byteBuffer.getInt();
        referenceForGroupWidths = GribSection.adjustUnsignedByte(byteBuffer.get());
        numberOfBitsUsedForTheGroupWidths = GribSection.adjustUnsignedByte(byteBuffer.get());
        // If negative this will go wrong during data extraction anyway
        referenceForGroupLengths = byteBuffer.getInt();
        lengthIncrementForTheGroupLengths = GribSection.adjustUnsignedByte(byteBuffer.get());
        // If negative this will go wrong during data extraction anyway
        trueLengthOfLastGroup = byteBuffer.getInt();
        numberOfBitsForScaledGroupLengths = GribSection.adjustUnsignedByte(byteBuffer.get());
    }

    public byte getGroupSplittingMethodUsed() {
        return groupSplittingMethodUsed;
    }

    public byte getMissingValueManagementUsed() {
        return missingValueManagementUsed;
    }

    public long getPrimaryMissingValueSubstitute() {
        return primaryMissingValueSubstitute;
    }

    public long getSecondaryMissingValueSubstitute() {
        return secondaryMissingValueSubstitute;
    }

    public int getNumberOfGroupsOfDataValues() {
        return numberOfGroupsOfDataValues;
    }

    public short getReferenceForGroupWidths() {
        return referenceForGroupWidths;
    }

    public short getNumberOfBitsUsedForTheGroupWidths() {
        return numberOfBitsUsedForTheGroupWidths;
    }

    public int getReferenceForGroupLengths() {
        return referenceForGroupLengths;
    }

    public short getLengthIncrementForTheGroupLengths() {
        return lengthIncrementForTheGroupLengths;
    }

    public int getTrueLengthOfLastGroup() {
        return trueLengthOfLastGroup;
    }

    public short getNumberOfBitsForScaledGroupLengths() {
        return numberOfBitsForScaledGroupLengths;
    }
}
