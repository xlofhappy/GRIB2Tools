package com.ph.grib2tools.test;

import com.ph.grib2tools.grib2file.*;
import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate30;
import com.ph.grib2tools.grib2file.productdefinition.ProductDefinitionTemplate40;
import com.ph.grib2tools.grib2file.productdefinition.ProductDefinitionTemplate4x;

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

        // Id of the grid within the GRIB2 file, since GRIB2 files can contain several grids (Sections 5-7)
        int gridId = 0;

        System.out.println("Reading file " + filename);
        try {

            // Open GRIB2 file and read it
            RandomAccessFile grib2FileRandomAccess = new RandomAccessFile(filename, "r");
            Grib2File        grib2File              = Grid2FileReader.read(grib2FileRandomAccess);

            System.out.println(grib2File.getSection0().getDisciplineName());
            for ( GribSection4 gribSection4 : grib2File.getSection4() ) {
                ProductDefinitionTemplate4x productDefinitionTemplate = gribSection4.getProductDefinitionTemplate();
                if ( productDefinitionTemplate instanceof ProductDefinitionTemplate40 ) {
                    System.out.println("\t" + ((ProductDefinitionTemplate40) productDefinitionTemplate).getParameterCategoryName());
                } else {
                    System.out.println("\t1");
                }
            }

            //             Get identification information
            GribSection1 section1 = grib2File.getSection1();
            System.out.println("Date: " + String.format("%02d", section1.getDay()) + "." + String.format("%02d", section1.getMonth()) + "." + section1.getYear());
            System.out.println("Time: " + String.format("%02d", section1.getHour()) + ":" + String.format("%02d", section1.getMinute()) + "." + String.format("%02d", section1.getSecond()));
            System.out.println("Generating centre: " + section1.getGeneratingCenterName());
            // Get product information
            for ( GribSection4 gribSection4 : grib2File.getSection4() ) {
                ProductDefinitionTemplate40 productDefinition = (ProductDefinitionTemplate40) gribSection4.getProductDefinitionTemplate();
                System.out.println("Forecast time: " + productDefinition.getForecastTime());
                System.out.println("Parameter category: " + productDefinition.getParameterCategory());
                System.out.println("Parameter number: " + productDefinition.getParameterNumber());
            }

            // Get grid information
            for ( GribSection3 gribSection3 : grib2File.getSection3() ) {
                GridDefinitionTemplate30 gridDefinition = (GridDefinitionTemplate30) gribSection3.getGridDefinitionTemplate();
                System.out.println("Covered area:");
//                System.out.println("   from (latitude, longitude): " + Grib2File.unitsToDeg(gridDefinition.getFirstPointLat()) + ", " + Grib2File.unitsToDeg(gridDefinition.getFirstPointLon()));
//                System.out.println("   to: (latitude, longitude): " + Grib2File.unitsToDeg(gridDefinition.getLastPointLat()) + ", " + Grib2File.unitsToDeg(gridDefinition.getLastPointLon()));
                System.out.println("   from (latitude, longitude): " + gridDefinition.getFirstPointLat() + ", " + gridDefinition.getFirstPointLon());
                System.out.println("   to: (latitude, longitude): " + gridDefinition.getLastPointLat() + ", " + gridDefinition.getLastPointLon());
            }


//            for ( GribSection7 gribSection7 : grib2File.getSection7() ) {
//                for ( int i : gribSection7.getData().getVariablePart() ) {
//                    System.out.println(i + " \t " + gribFile.getSection7(0).getData().getVariablePart()[i]);
//                }
//            }
            //             Get grid data
            double latitude  = 1;
            double longitude = 1;
//            System.out.println("Value at (" + latitude + ", " + longitude + "): " + grib2File.interpolateValueAtLocation(gridid, latitude, longitude));

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
