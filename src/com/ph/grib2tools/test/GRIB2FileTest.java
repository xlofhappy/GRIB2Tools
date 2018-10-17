package com.ph.grib2tools.test;

import com.ph.grib2tools.grib2file.Grib2File;
import com.ph.grib2tools.grib2file.GribSection1;
import com.ph.grib2tools.grib2file.RandomAccessGribFile;
import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate30;
import com.ph.grib2tools.grib2file.productdefinition.ProductDefinitionTemplate40;

import java.io.RandomAccessFile;

public class GRIB2FileTest {

    public static void main(String[] args) {

        //        if ( args.length < 1 ) {
        //            System.out.println("Syntax: java GRIB2FileTest <filename> <structureid (optional)>");
        //            return;
        //        }

        // Name of the GRIB2 file
        //        String filename = args[0];
        String filename = "/Users/xiaole/gfs.t18z.pgrb2.1p00.f000.grb";

        // Defines how many GRIB file structures shall be skipped when reading the GRIB2 file. This
        // is useful since some organizations put several GRIB2 file structures in one file.
        int numskip;
        try {
            numskip = Integer.parseInt(args[1]);
        } catch ( Exception e ) {
            numskip = 0;
        }

        // Id of the grid within the GRIB2 file, since GRIB2 files can contain several grids (Sections 5-7)
        int gridid = 0;

        System.out.println("Reading file " + filename + ", file structure " + numskip + ":");
        try {

            // Open GRIB2 file and read it
            RandomAccessFile     grib2File   = new RandomAccessFile(filename, "r");
            RandomAccessGribFile gribFile    = new RandomAccessGribFile("testdata", filename);
            gribFile.importFromStream(grib2File, numskip);

            // Get identification information
            GribSection1 section1 = gribFile.getSection1();
            System.out.println("Date: " + String.format("%02d", section1.getDay()) + "." + String.format("%02d", section1.getMonth()) + "." + section1.getYear());
            System.out.println("Time: " + String.format("%02d", section1.getHour()) + ":" + String.format("%02d", section1.getMinute()) + "." + String.format("%02d", section1.getSecond()));
            System.out.println("Generating centre: " + section1.getGeneratingCenterName());
            // Get product information
            ProductDefinitionTemplate40 productDefinition = (ProductDefinitionTemplate40) gribFile.getProductDefinitionTemplate();
            System.out.println("Forecast time: " + productDefinition.getForecastTime());
            System.out.println("Parameter category: " + productDefinition.getParameterCategory());
            System.out.println("Parameter number: " + productDefinition.getParameterNumber());

            // Get grid information
            GridDefinitionTemplate30 gridDefinition = (GridDefinitionTemplate30) gribFile.getGridDefinitionTemplate();
            System.out.println("Covered area:");
            System.out.println("   from (latitude, longitude): " + Grib2File.unitsToDeg(gridDefinition.getFirstPointLat()) + ", " + Grib2File.unitsToDeg(gridDefinition.getFirstPointLon()));
            System.out.println("   to: (latitude, longitude): " + Grib2File.unitsToDeg(gridDefinition.getLastPointLat()) + ", " + Grib2File.unitsToDeg(gridDefinition.getLastPointLon()));


            for ( int i : gribFile.getSection7(0).getData().getVariablePart() ) {
                System.out.println(i + " \t " + gribFile.getSection7(0).getData().getVariablePart()[i]);
            }
            // Get grid data
            double latitude  = 1;
            double longitude = 1;
            System.out.println("Value at (" + latitude + ", " + longitude + "): " + gribFile.interpolateValueAtLocation(gridid, latitude, longitude));

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
