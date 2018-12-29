package com.ph.grib2tools.grib2file;

import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate30;
import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate31;
import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate33;
import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate3x;

import java.io.IOException;
import java.io.RandomAccessFile;

public class GribSection3 extends GribSection {

    private int                      sourceOfGridDefinition;
    private String                   sourceOfGridDefinitionName;
    private long                     numDataPoints;
    private int                      numOfOctetsForOptionalList;
    private int                      interpretationOfList;
    private String                   interpretationOfListName;
    private int                      gridDefinitionTemplateNumber;
    private String                   gridDefinitionTemplateNumberName;
    private GridDefinitionTemplate3x gridDefinitionTemplate;
    private byte[]                   optionalListOfPoints;

    @Override
    public void readData(RandomAccessFile gribFile) throws IOException {
        super.readData(gribFile);
        readSection(gribFile);
    }

    private void readSection(RandomAccessFile gribFile) throws IOException {
        // 6
        sourceOfGridDefinition = gribFile.read();
        sourceOfGridDefinitionName = chooseSourceOfGridDefinitionName(sourceOfGridDefinition);
        // 7-10
        numDataPoints = DataUtil.int4(gribFile);
        // 11
        numOfOctetsForOptionalList = gribFile.read();
        // 12
        interpretationOfList = gribFile.read();
        interpretationOfListName = chooseInterpretationOfListName(interpretationOfList);
        // 13-14
        gridDefinitionTemplateNumber = DataUtil.int2(gribFile);
        gridDefinitionTemplateNumberName = chooseGridDefinitionTemplateNumberName(gridDefinitionTemplateNumber);

        if ( sourceOfGridDefinition == 0 ) {
            if ( gridDefinitionTemplateNumber == 0 ) {
                gridDefinitionTemplate = new GridDefinitionTemplate30(gribFile);
            } else if ( gridDefinitionTemplateNumber == 1 || gridDefinitionTemplateNumber == 2 ) {
                gridDefinitionTemplate = new GridDefinitionTemplate31(gribFile);
            } else if ( gridDefinitionTemplateNumber == 3 ) {
                gridDefinitionTemplate = new GridDefinitionTemplate33(gribFile);
            } else {
                //TODO Other Template not implemented
                System.out.println("Grid Definition Template Number 3." + gridDefinitionTemplateNumber + " not implemented.");
            }
        } else {
            System.out.println("Grid Definition " + sourceOfGridDefinition + " not implemented.");
        }

        optionalListOfPoints = new byte[numOfOctetsForOptionalList];
        gribFile.read(optionalListOfPoints);
    }

    private String chooseGridDefinitionTemplateNumberName(int gridDefinitionTemplateNumber) {
        String name;
        if ( gridDefinitionTemplateNumber == 0 ) {
            name = "Latitude/Longitude, Also called Equidistant Cylindrical or Plate Caree";
        } else if ( gridDefinitionTemplateNumber == 1 ) {
            name = "Rotated Latitude/Longitude";
        } else if ( gridDefinitionTemplateNumber == 2 ) {
            name = "Stretched Latitude/Longitude";
        } else if ( gridDefinitionTemplateNumber == 3 ) {
            name = "Rotated and Stretched Latitude/Longitude";
        } else if ( gridDefinitionTemplateNumber == 4 ) {
            name = "Variable Resolution Latitude/longitude";
        } else if ( gridDefinitionTemplateNumber == 5 ) {
            name = "Variable Resolution Rotated Latitude/longitude";
        } else if ( gridDefinitionTemplateNumber >= 6 && gridDefinitionTemplateNumber <= 9 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 10 ) {
            name = "Mercator";
        } else if ( gridDefinitionTemplateNumber == 11 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 12 ) {
            name = "Transverse Mercator";
        } else if ( gridDefinitionTemplateNumber >= 13 && gridDefinitionTemplateNumber <= 19 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 20 ) {
            name = "Polar Stereographic Projection (Can be North or South)";
        } else if ( gridDefinitionTemplateNumber >= 21 && gridDefinitionTemplateNumber <= 29 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 30 ) {
            name = "Lambert Conformal (Can be Secant, Tangent, Conical, or Bipolar)";
        } else if ( gridDefinitionTemplateNumber == 31 ) {
            name = "Albers Equal Area";
        } else if ( gridDefinitionTemplateNumber >= 32 && gridDefinitionTemplateNumber <= 39 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 40 ) {
            name = "Gaussian Latitude/Longitude";
        } else if ( gridDefinitionTemplateNumber == 41 ) {
            name = "Rotated Gaussian Latitude/Longitude";
        } else if ( gridDefinitionTemplateNumber == 42 ) {
            name = "Stretched Gaussian Latitude/Longitude";
        } else if ( gridDefinitionTemplateNumber == 43 ) {
            name = "Rotated and Stretched Gaussian Latitude/Longitude";
        } else if ( gridDefinitionTemplateNumber >= 44 && gridDefinitionTemplateNumber <= 49 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 50 ) {
            name = "Spherical Harmonic Coefficients";
        } else if ( gridDefinitionTemplateNumber == 51 ) {
            name = "Rotated Spherical Harmonic Coefficients";
        } else if ( gridDefinitionTemplateNumber == 52 ) {
            name = "Stretched Spherical Harmonic Coefficients";
        } else if ( gridDefinitionTemplateNumber == 53 ) {
            name = "Rotated and Stretched Spherical Harmonic Coefficients";
        } else if ( gridDefinitionTemplateNumber >= 54 && gridDefinitionTemplateNumber <= 89 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 90 ) {
            name = "Space View Perspective or Orthographic";
        } else if ( gridDefinitionTemplateNumber >= 91 && gridDefinitionTemplateNumber <= 99 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 100 ) {
            name = "Triangular Grid Based on an Icosahedron";
        } else if ( gridDefinitionTemplateNumber == 101 ) {
            name = "General Unstructured Grid";
        } else if ( gridDefinitionTemplateNumber >= 102 && gridDefinitionTemplateNumber <= 109 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 110 ) {
            name = "Equatorial Azimuthal Equidistant Projection";
        } else if ( gridDefinitionTemplateNumber >= 111 && gridDefinitionTemplateNumber <= 119 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 120 ) {
            name = "Azimuth-Range Projection";
        } else if ( gridDefinitionTemplateNumber >= 121 && gridDefinitionTemplateNumber <= 139 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 140 ) {
            name = "Lambert Azimuthal Equal Area Projection";
        } else if ( gridDefinitionTemplateNumber >= 141 && gridDefinitionTemplateNumber <= 203 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 204 ) {
            name = "Curvilinear Orthogonal Grids";
        } else if ( gridDefinitionTemplateNumber >= 205 && gridDefinitionTemplateNumber <= 999 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 1000 ) {
            name = "Cross Section Grid with Points Equally Spaced on the Horizontal";
        } else if ( gridDefinitionTemplateNumber >= 1001 && gridDefinitionTemplateNumber <= 1099 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 1100 ) {
            name = "Hovmoller Diagram with Points Equally Spaced on the Horizontal";
        } else if ( gridDefinitionTemplateNumber >= 1101 && gridDefinitionTemplateNumber <= 1199 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 1200 ) {
            name = "Time Section Grid";
        } else if ( gridDefinitionTemplateNumber >= 1201 && gridDefinitionTemplateNumber <= 32767 ) {
            name = "Reserved";
        } else if ( gridDefinitionTemplateNumber == 32768 ) {
            name = "Rotated Latitude/Longitude (Arakawa Staggered E-Grid)";
        } else if ( gridDefinitionTemplateNumber == 32769 ) {
            name = "Rotated Latitude/Longitude (Arakawa Non-E Staggered Grid)";
        } else if ( gridDefinitionTemplateNumber >= 32770 && gridDefinitionTemplateNumber <= 65534 ) {
            name = "Reserved for Local Use";
        } else {
            name = "Missing";
        }
        return name;
    }

    private String chooseSourceOfGridDefinitionName(int sourceOfGridDefinition) {
        String name;
        if ( sourceOfGridDefinition == 0 ) {
            name = "Latitude/Longitude, Also called Equidistant Cylindrical or Plate Caree";
        } else if ( sourceOfGridDefinition == 1 ) {
            name = "Predetermined Grid Definition - Defined by Originating Center";
        } else if ( sourceOfGridDefinition <= 191 && sourceOfGridDefinition >= 2 ) {
            name = "Reserved";
        } else if ( sourceOfGridDefinition <= 254 && sourceOfGridDefinition >= 192 ) {
            name = "Reserved for Local Use";
        } else {
            name = "A grid definition does not apply to this product.";
        }
        return name;
    }

    private String chooseInterpretationOfListName(int interpretationOfList) {
        String name;
        if ( interpretationOfList == 0 ) {
            name = "There is no appended list";
        } else if ( sourceOfGridDefinition == 1 ) {
            name = "Numbers define number of points corresponding to full coordinate circles (i.e. parallels).  Coordinate values on each circle are multiple of the circle mesh, and extreme coordinate values given in grid definition may not be reached in all rows.";
        } else if ( sourceOfGridDefinition == 2 ) {
            name = "Numbers define number of points corresponding to coordinate lines delimited by extreme coordinate values given in grid definition which are present in each row";
        } else if ( sourceOfGridDefinition == 3 ) {
            name = "Numbers define the actual latitudes for each row in the grid. The list of numbers are integer values of the valid latitudes in microdegrees (scale by 106) or in unit equal to the ratio of the basic angle and the subdivisions number for each row, in the same order as specified in the \"scanning mode flag\".";
        } else if ( sourceOfGridDefinition <= 254 && sourceOfGridDefinition >= 4 ) {
            name = "Reserved";
        } else {
            name = "Missing";
        }
        return name;
    }

    public int getSourceOfGridDefinition() {
        return sourceOfGridDefinition;
    }

    public long getNumDataPoints() {
        return numDataPoints;
    }

    public String getSourceOfGridDefinitionName() {
        return sourceOfGridDefinitionName;
    }

    public String getGridDefinitionTemplateNumberName() {
        return gridDefinitionTemplateNumberName;
    }

    public String getInterpretationOfListName() {
        return interpretationOfListName;
    }

    public int getNumOfOctetsForOptionalList() {
        return numOfOctetsForOptionalList;
    }

    public int getInterpretationOfList() {
        return interpretationOfList;
    }

    public int getGridDefinitionTemplateNumber() {
        return gridDefinitionTemplateNumber;
    }

    public GridDefinitionTemplate3x getGridDefinitionTemplate() {
        return gridDefinitionTemplate;
    }

    public byte[] getOptionalListOfPoints() {
        return optionalListOfPoints;
    }

}
