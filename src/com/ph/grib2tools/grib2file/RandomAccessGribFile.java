package com.ph.grib2tools.grib2file;

import com.ph.grib2tools.grib2file.datarepresentation.DataRepresentationTemplate50;
import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate30;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

/**
 * A representation of a GRIB file containing all meta data and allowing a random
 * access to the data of the GRIB file. While the random access allows great flexibility
 * it requires that the complete data is held in memory
 **/
public class RandomAccessGribFile extends GribFile {

    private static final long serialVersionUID = 100L;

    private static final Logger log = Logger.getLogger(RandomAccessGribFile.class.getName());


    public RandomAccessGribFile(String typeId, String source) {
        super(typeId, source);
    }

    public void importFromStream(InputStream gribFile, int numSkip) throws IOException {
        if ( gribFile == null ) {
            return;
        }
        // By overwriting the section variables, the first numskip GRIB file data structures
        // within a stream or a file can be skipped
        for ( int t = 0; t < numSkip + 1; t++ ) {
            gridcnt = 0;
            //while (true) {
            while ( gridcnt < 1 ) {
                // Read all meta data but not the data itself in Section 7
                importMetadatFromStream(gribFile);
                // Read the data of Section 7 into memory
                GribSection nextSection = new GribSection(gribFile).initSection();
                if ( nextSection.getSectionNumber() == 7 ) {
                    section7[gridcnt] = (GribSection7) nextSection;
                    section7[gridcnt].setDataRepresentation(section5[gridcnt].getNumberDataPoints(), section5[gridcnt].getDataRepresentationTemplate());
                    section7[gridcnt].readData(gribFile);
                    gridcnt++;
                } else {
                    log.warning("Section " + nextSection.getSectionNumber() + " found while Section 7 expected. aborting.");
                    return;
                }
            }
            finalizeImport(gribFile);
        }
    }

    /**
     * Extracts the data belonging to the passed coordinate (lat, lon) in degrees and returns the
     * value represented by this data. If the passed coordinate is not a grid point of the
     * data grid, the data belonging to the grid point closest to the passed position
     * is considered.
     */
    public float getValueAtLocation(int gridIdx, double lat, double lon) {
        return getValueAt(gridIdx, GribFile.degToUnits(lat), GribFile.degToUnits(lon));
    }

    /**
     * Extracts the data belonging to the passed coordinate (lat, lon) in units and returns the
     * value represented by this data. If the passed coordinate is not a grid point of the
     * data grid, the data belonging to the grid point closest to the passed position
     * is considered.
     */
    public float getValueAt(int gridIdx, int lat, int lon) {
        GribSection5 sec5 = getSection5(gridIdx);
        GribSection7 sec7 = getSection7(gridIdx);
        float val = 0;
        if ( sec5.getDataRepresentationTemplateNumber() == 0 ) {
            DataRepresentationTemplate50 dataRepresentation = (DataRepresentationTemplate50) sec5.getDataRepresentationTemplate();
            int                          bytesperval        = dataRepresentation.getNumberBits() / 8;

            if ( section3.getGridDefinitionTemplateNumber() == 0 ) {

                GridDefinitionTemplate30 gridDefinition = (GridDefinitionTemplate30) section3.getGridDefinitionTemplate();

                int deltaj = 0;

                // Implementation of Scanning Modes
                if ( (gridDefinition.getScanningMode() & 0x01) == 0x01 ) {
                    log.warning("Scanning mode " + 0x01 + " not supported");
                }
                if ( (gridDefinition.getScanningMode() & 0x02) == 0x02 ) {
                    log.warning("Scanning mode " + 0x02 + " not supported");
                }
                if ( (gridDefinition.getScanningMode() & 0x04) == 0x04 ) {
                    log.warning("Scanning mode " + 0x04 + " not supported");
                }
                if ( (gridDefinition.getScanningMode() & 0x08) == 0x08 ) {
                    log.warning("Scanning mode " + 0x08 + " not supported");
                }
                if ( (gridDefinition.getScanningMode() & 0x10) == 0x10 ) {
                    log.warning("Scanning mode " + 0x10 + " not supported");
                }
                if ( (gridDefinition.getScanningMode() & 0x20) == 0x20 ) {
                    log.warning("Scanning mode " + 0x20 + " not supported");
                }
                if ( (gridDefinition.getScanningMode() & 0x40) == 0x40 ) {
                    deltaj = gridDefinition.getjDirectionIncrement() / 2;
                }
                if ( (gridDefinition.getScanningMode() & 0x80) == 0x80 ) {
                    log.warning("Scanning mode " + 0x80 + " not supported");
                }

                // Calculate j index of the matrix containing the data the contains the data of the
                // passed latitude lat
                int deltalat = lat - (gridDefinition.getFirstPointLat() + deltaj);
                int jidx     = Math.round((float) deltalat / (float) gridDefinition.getjDirectionIncrement());

                // Calculate i index of the matrix containing the data the contains the data of the
                // passed longitude lon
                int firstPointLon = gridDefinition.getFirstPointLon() + 0;
                if ( firstPointLon >= GribFile.degToUnits(180) ) { firstPointLon -= GribFile.degToUnits(360); }
                int deltalon = lon - firstPointLon;
                int iidx     = Math.round((float) deltalon / (float) gridDefinition.getiDirectionIncrement());

                // Extract data belonging to the referred location and calculate the value represented by the data
                byte  data[]      = sec7.getSectionData();
                short unsigneDraw = ByteBuffer.wrap(data).getShort((jidx * gridDefinition.getNumberPointsLon() + iidx) * bytesperval);
                val = sec5.calcValue(unsigneDraw);
            } else {
                log.warning("Grid Definition Template Number 3." + section3.getGridDefinitionTemplateNumber() + " not implemented.");
            }
        } else {
            log.warning("Data Representation Template Number 5." + sec5.getDataRepresentationTemplateNumber() + " not implemented.");
        }

        return val;
    }

    /**
     * Extracts the data belonging to the passed coordinate (lat, lon) in degrees and returns the
     * value represented by this data. If the passed coordinate is not a grid point of the
     * data grid, the data belonging to the neighbored grid points is interpolated and used
     * to calculate the value belonging to the passed position.
     */
    public float interpolateValueAtLocation(int gridIdx, double lat, double lon) {
        return interpolateValueAt(gridIdx, GribFile.degToUnits(lat), GribFile.degToUnits(lon));
    }

    /**
     * Extracts the data belonging to the passed coordinate (lat, lon) in units and returns the
     * value represented by this data. If the passed coordinate is not a grid point of the
     * data grid, the data belonging to the neighbored grid points is interpolated and used
     * to calculate the value belonging to the passed position.
     */
    public float interpolateValueAt(int gridIdx, int lat, int lon) {
        GribSection5 sec5 = getSection5(gridIdx);
        GribSection7 sec7 = getSection7(gridIdx);
        float val = 0;
        if ( sec5.getDataRepresentationTemplateNumber() == 0 ) {
            DataRepresentationTemplate50 dataRepresentation = (DataRepresentationTemplate50) sec5.getDataRepresentationTemplate();
            int                          bytesperval        = dataRepresentation.getNumberBits() / 8;
            if ( section3.getGridDefinitionTemplateNumber() == 0 ) {
                GridDefinitionTemplate30 gridDefinition = (GridDefinitionTemplate30) section3.getGridDefinitionTemplate();
                int deltaj = 0;
                // Implementation of Scanning Modes
                if ( (gridDefinition.getScanningMode() & 0x01) == 0x01 ) {
                    log.warning("Scanning mode " + 0x01 + " not supported");
                }
                if ( (gridDefinition.getScanningMode() & 0x02) == 0x02 ) {
                    log.warning("Scanning mode " + 0x02 + " not supported");
                }
                if ( (gridDefinition.getScanningMode() & 0x04) == 0x04 ) {
                    log.warning("Scanning mode " + 0x04 + " not supported");
                }
                if ( (gridDefinition.getScanningMode() & 0x08) == 0x08 ) {
                    log.warning("Scanning mode " + 0x08 + " not supported");
                }
                if ( (gridDefinition.getScanningMode() & 0x10) == 0x10 ) {
                    log.warning("Scanning mode " + 0x10 + " not supported");
                }
                if ( (gridDefinition.getScanningMode() & 0x20) == 0x20 ) {
                    log.warning("Scanning mode " + 0x20 + " not supported");
                }
                if ( (gridDefinition.getScanningMode() & 0x40) == 0x40 ) {
                    deltaj = gridDefinition.getjDirectionIncrement() / 2;
                }
                if ( (gridDefinition.getScanningMode() & 0x80) == 0x80 ) {
                    log.warning("Scanning mode " + 0x80 + " not supported");
                }

                deltaj = 0;
                // Find j indices of the grid points of the matrix containing the data that surround the
                // passed latitude lat
                int deltalat = lat - (gridDefinition.getFirstPointLat() + deltaj);
                //int jidx1 = Math.round((float)deltalat / (float)gridDefinition.jDirectionIncrement);
                int jidx1 = (int) Math.floor((float) deltalat / (float) gridDefinition.getjDirectionIncrement());

                // Correct indices when end of the array dimension is reached
                if ( jidx1 >= gridDefinition.getNumberPointsLat() - 1 ) { jidx1--; }
                int jidx2 = jidx1 + 1;

                // Find i indices of the grid points of the matrix containing the data that surround the
                // passed longitude lon
                int firstPointLon = gridDefinition.getFirstPointLon() + 0;
                if ( firstPointLon >= GribFile.degToUnits(180) ) { firstPointLon -= GribFile.degToUnits(360); }
                int deltalon = lon - firstPointLon;
                //int iidx1 = Math.round((float)deltalon / (float)gridDefinition.iDirectionIncrement);
                int iidx1 = (int) Math.floor((float) deltalon / (float) gridDefinition.getiDirectionIncrement());

                // Correct indices when end of the array dimension is reached
                if ( iidx1 >= gridDefinition.getNumberPointsLon() - 1 ) { iidx1--; }
                int iidx2 = iidx1 + 1;


                byte data[] = sec7.getSectionData();

                // Extract data of the four grid points surrounding the passed coordinate and calculate the
                // values represented by the data
                float val11 = sec5.calcValue(ByteBuffer.wrap(data).getShort((jidx1 * gridDefinition.getNumberPointsLon() + iidx1) * bytesperval));
                float val12 = sec5.calcValue(ByteBuffer.wrap(data).getShort((jidx1 * gridDefinition.getNumberPointsLon() + iidx2) * bytesperval));

                float val21 = sec5.calcValue(ByteBuffer.wrap(data).getShort((jidx2 * gridDefinition.getNumberPointsLon() + iidx1) * bytesperval));
                float val22 = sec5.calcValue(ByteBuffer.wrap(data).getShort((jidx2 * gridDefinition.getNumberPointsLon() + iidx2) * bytesperval));


                // Find latitudes of the grid points of the matrix containing the data that surround the
                // passed latitude lat
                int lat1 = (gridDefinition.getFirstPointLat() + deltaj) + jidx1 * gridDefinition.getjDirectionIncrement();
                int lat2 = lat1 + gridDefinition.getjDirectionIncrement();

                // Find longitudes of the grid points of the matrix containing the data that surround the
                // passed longitude lon
                int lon1 = firstPointLon + iidx1 * gridDefinition.getiDirectionIncrement();
                int lon2 = lon1 + gridDefinition.getiDirectionIncrement();

                // Interpolate value belonging to the passed location
                float val1x = val11 + (val12 - val11) * (lon - lon1) / (lon2 - lon1);
                float val2x = val21 + (val22 - val21) * (lon - lon1) / (lon2 - lon1);
                val = val1x + (val2x - val1x) * (lat - lat1) / (lat2 - lat1);
            } else {
                log.warning("Grid Definition Template Number 3." + section3.getGridDefinitionTemplateNumber() + " not implemented.");
            }
        } else {
            log.warning("Data Representation Template Number 5." + sec5.getDataRepresentationTemplateNumber() + " not implemented.");
        }

        return val;
    }
}
