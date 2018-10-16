package com.ph.grib2tools.grib2file.datarepresentation;

import com.ph.grib2tools.grib2file.GribSection;

import java.nio.ByteBuffer;

public class DataSection73 extends DataSection72 {

    public int[] fstValuesOfOriginal;

    public DataSection73(int numberDataPoints, DataRepresentationTemplate53 representation, ByteBuffer byteBuffer) {
        // We cant read "parent" data here
        super();
        fstValuesOfOriginal = new int[representation.getOrderOfSpatialDifferencing() + 1];
        for ( int i = 0; i < fstValuesOfOriginal.length; ++i ) {
            switch ( representation.getNumberOfOctetsExtraDescriptors() ) {
                case 1:
                    fstValuesOfOriginal[i] = GribSection.correctNegativeByte(byteBuffer.get());
                    break;
                case 2:
                    fstValuesOfOriginal[i] = GribSection.correctNegativeShort(byteBuffer.getShort());
                    break;
                case 4:
                    fstValuesOfOriginal[i] = GribSection.correctNegativeInt(byteBuffer.getInt());
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Unsupported nb of octets : %d", representation.getNumberOfOctetsExtraDescriptors()));
            }
        }
        readData72(numberDataPoints, representation, byteBuffer);
        assert !byteBuffer.hasRemaining();

        // Compute real values
        // ****************************************
        // WARNING : That's the part i am not sure : we lose the first 2 variablePart why ?
        // ****************************************
        for ( int i = 0; i < fstValuesOfOriginal.length - 1; ++i ) {
            setVariablePart(i, fstValuesOfOriginal[i]);
        }
        int on = fstValuesOfOriginal[fstValuesOfOriginal.length - 1];
        for ( int i = fstValuesOfOriginal.length - 1; i < getVariablePart().length; ++i ) {
            setVariablePart(i, getVariablePart(i) + on + 2 * getVariablePart(i - 1) - getVariablePart(i - 2));
        }
    }
}
