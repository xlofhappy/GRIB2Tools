package com.ph.grib2tools.grib2file.datarepresentation;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DataRepresentationTemplate53 extends DataRepresentationTemplate52 {

    private static final long serialVersionUID = 100L;

    private int orderOfSpatialDifferencing;
    private int numberOfOctetsExtraDescriptors;


    public DataRepresentationTemplate53(RandomAccessFile grib2File) throws IOException {
        super(grib2File);
        orderOfSpatialDifferencing = grib2File.read();
        numberOfOctetsExtraDescriptors = grib2File.read();
    }

    public int getOrderOfSpatialDifferencing() {
        return orderOfSpatialDifferencing;
    }

    public int getNumberOfOctetsExtraDescriptors() {
        return numberOfOctetsExtraDescriptors;
    }
}
