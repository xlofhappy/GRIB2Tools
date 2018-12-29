package com.ph.grib2tools.grib2file.datarepresentation;

import com.ph.grib2tools.grib2file.DataUtil;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DataRepresentationTemplate52 extends DataRepresentationTemplate50 {

    private static final long serialVersionUID = 100L;

    private int  groupSplittingMethodUsed;
    private int  missingValueManagementUsed;
    private long primaryMissingValueSubstitute;
    private long secondaryMissingValueSubstitute;
    private int  numberOfGroupsOfDataValues;
    private int  referenceForGroupWidths;
    private int  numberOfBitsUsedForTheGroupWidths;
    private int  referenceForGroupLengths;
    private int  lengthIncrementForTheGroupLengths;
    private int  trueLengthOfLastGroup;
    private int  numberOfBitsForScaledGroupLengths;

    public DataRepresentationTemplate52(RandomAccessFile grib2File) throws IOException {
        super(grib2File);
        groupSplittingMethodUsed = grib2File.read();
        missingValueManagementUsed = grib2File.read();
        primaryMissingValueSubstitute = DataUtil.int4(grib2File);
        secondaryMissingValueSubstitute = DataUtil.int4(grib2File);
        // If negative this will go wrong during data extraction anyway
        numberOfGroupsOfDataValues = DataUtil.int4(grib2File);
        referenceForGroupWidths = grib2File.read();
        numberOfBitsUsedForTheGroupWidths = grib2File.read();
        // If negative this will go wrong during data extraction anyway
        referenceForGroupLengths = DataUtil.int4(grib2File);
        lengthIncrementForTheGroupLengths = grib2File.read();
        // If negative this will go wrong during data extraction anyway
        trueLengthOfLastGroup = DataUtil.int4(grib2File);
        numberOfBitsForScaledGroupLengths = grib2File.read();
    }

    public int getGroupSplittingMethodUsed() {
        return groupSplittingMethodUsed;
    }

    public int getMissingValueManagementUsed() {
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

    public int getReferenceForGroupWidths() {
        return referenceForGroupWidths;
    }

    public int getNumberOfBitsUsedForTheGroupWidths() {
        return numberOfBitsUsedForTheGroupWidths;
    }

    public int getReferenceForGroupLengths() {
        return referenceForGroupLengths;
    }

    public int getLengthIncrementForTheGroupLengths() {
        return lengthIncrementForTheGroupLengths;
    }

    public int getTrueLengthOfLastGroup() {
        return trueLengthOfLastGroup;
    }

    public int getNumberOfBitsForScaledGroupLengths() {
        return numberOfBitsForScaledGroupLengths;
    }
}
