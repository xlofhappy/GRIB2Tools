package com.ph.grib2tools.grib2file.datarepresentation;

public class DataSection7x {

    /**
     * The sum x1+x2 so that real value = (ref + (x1+x2)*2^n)/10^n.
     * See "Guide to the WMO Table Driven Code Form Used for the Representation and Exchange of Regularly
     * Spaced Data In Binary Form"
     */
    private int[] variablePart;

    public int[] getVariablePart() {
        return variablePart;
    }

    public int getVariablePart(int idx) {
        return variablePart[idx];
    }

    public void setVariablePart(int[] variablePart) {
        this.variablePart = variablePart;
    }

    public void setVariablePart(int idx, int value) {
        this.variablePart[idx] = value;
    }
}
