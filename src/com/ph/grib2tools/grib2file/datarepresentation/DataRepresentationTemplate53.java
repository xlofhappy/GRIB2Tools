package com.ph.grib2tools.grib2file.datarepresentation;

import com.ph.grib2tools.grib2file.GribSection;

import java.nio.ByteBuffer;

public class DataRepresentationTemplate53 extends DataRepresentationTemplate52 {

    private static final long serialVersionUID = 100L;

    private byte  orderOfSpatialDifferencing;
    private short numberOfOctetsExtraDescriptors;


    public DataRepresentationTemplate53(ByteBuffer byteBuffer) {
        super(byteBuffer);
        assert byteBuffer.position() == 47 - 5;
        orderOfSpatialDifferencing = byteBuffer.get();
        numberOfOctetsExtraDescriptors = GribSection.adjustUnsignedByte(byteBuffer.get());
    }

    public byte getOrderOfSpatialDifferencing() {
        return orderOfSpatialDifferencing;
    }

    public void setOrderOfSpatialDifferencing(byte orderOfSpatialDifferencing) {
        this.orderOfSpatialDifferencing = orderOfSpatialDifferencing;
    }

    public short getNumberOfOctetsExtraDescriptors() {
        return numberOfOctetsExtraDescriptors;
    }

    public void setNumberOfOctetsExtraDescriptors(short numberOfOctetsExtraDescriptors) {
        this.numberOfOctetsExtraDescriptors = numberOfOctetsExtraDescriptors;
    }
}
