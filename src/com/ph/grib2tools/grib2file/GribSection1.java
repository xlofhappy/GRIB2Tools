package com.ph.grib2tools.grib2file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class GribSection1 extends GribSection {

    private int    generatingCenter;
    private String generatingCenterName;
    private int    generatingSubCenter;
    private String generatingSubCenterName;
    /**
     * GRIB master tables version number
     */
    private short  masterTablesVersion;
    private String masterTablesVersionDetail;
    private short  localTablesVersion;
    private String localTablesVersionDetail;
    private short  significanceOfReferenceTime;
    private String significanceOfReferenceTimeDetail;
    private int    year;
    private byte   month;
    private byte   day;
    private byte   hour;
    private byte   minute;
    private byte   second;
    private short  productionStatus;
    private String productionStatusName;
    private short  type;
    private String typeName;
    private byte[] reserved;

    @Override
    public void readData(RandomAccessFile gribFile) throws IOException {
        super.readData(gribFile);
        readSection();
    }

    private void readSection() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(getSectionData());
        // Parse section and extract data
        // 6-7
        generatingCenter = Short.toUnsignedInt(byteBuffer.getShort());
        generatingCenterName = chooseGeneratingCenterName(generatingCenter);
        // 8-9
        generatingSubCenter = Short.toUnsignedInt(byteBuffer.getShort());
        generatingSubCenterName = chooseGeneratingSubCenterName(generatingSubCenter);
        // 10
        masterTablesVersion = (short) Byte.toUnsignedInt(byteBuffer.get());
        masterTablesVersionDetail = chooseMasterTablesVersionDetail(masterTablesVersion);
        // 11
        localTablesVersion = (short) Byte.toUnsignedInt(byteBuffer.get());
        localTablesVersionDetail = chooseLocalTablesVersionDetail(localTablesVersion);
        // 12
        significanceOfReferenceTime = (short) Byte.toUnsignedInt(byteBuffer.get());
        significanceOfReferenceTimeDetail = chooseSignificanceOfReferenceTimeDetail(significanceOfReferenceTime);
        // 13-14
        year = Short.toUnsignedInt(byteBuffer.getShort());
        // 15
        month = byteBuffer.get();
        // 16
        day = byteBuffer.get();
        // 17
        hour = byteBuffer.get();
        // 18
        minute = byteBuffer.get();
        // 19
        second = byteBuffer.get();
        // 20
        productionStatus = (short) Byte.toUnsignedInt(byteBuffer.get());
        productionStatusName = chooseProductionStatusName(productionStatus);
        // 21
        type = (short) Byte.toUnsignedInt(byteBuffer.get());
        typeName = chooseTypeName(type);

        if ( getSectionLength() > 21 ) {
            reserved = new byte[getSectionLength() - 21];
            byteBuffer.get(reserved);
        }
    }

    private String chooseGeneratingCenterName(int generatingCenter) {
        String name = "Reserved";
        switch ( generatingCenter ) {
            case 1:
            case 2:
            case 3:
                name = "Melbourne (WMC)";
                break;
            case 4:
            case 5:
            case 6:
                name = "Moscow (WMC)";
                break;
            case 7:
                name = "US National Weather Service - NCEP (WMC)";
                break;
            case 8:
                name = "US National Weather Service - NWSTG (WMC)";
                break;
            case 9:
                name = "US National Weather Service - Other (WMC)";
                break;
            case 10:
            case 11:
                name = "Cairo (RSMC/RAFC)";
                break;
            case 12:
            case 13:
                name = "Dakar (RSMC/RAFC)";
                break;
            case 14:
            case 15:
                name = "Nairobi (RSMC/RAFC)";
                break;
            case 16:
                name = "Casablanca (RSMC)";
                break;
            case 17:
                name = "Tunis (RSMC)";
                break;
            case 18:
            case 19:
                name = "Tunis-Casablanca (RSMC)";
                break;
            case 20:
                name = "Las Palmas (RAFC)";
                break;
            case 21:
                name = "Algiers (RSMC)";
                break;
            case 22:
                name = "ACMAD";
                break;
            case 23:
                name = "Mozambique (NMC)";
                break;
            case 24:
                name = "Pretoria (RSMC)";
                break;
            case 25:
                name = "La Reunion (RSMC)";
                break;
            case 26:
            case 27:
                name = "Khabarovsk (RSMC)";
                break;
            case 28:
            case 29:
                name = "New Delhi (RSMC/RAFC)";
                break;
            case 30:
            case 31:
                name = "Novosibirsk (RSMC)";
                break;
            case 32:
                name = "Tashkent (RSMC)";
                break;
            case 33:
                name = "Jeddah (RSMC)";
                break;
            case 34:
            case 35:
                name = "Tokyo (RSMC), Japanese Meteorological Agency";
                break;
            case 36:
                name = "Bankok";
                break;
            case 37:
                name = "Ulan Bator";
                break;
            case 38:
            case 39:
                name = "Beijing (RSMC)";
                break;
            case 40:
                name = "Seoul";
                break;
            case 41:
            case 42:
                name = "Buenos Aires (RSMC/RAFC)";
                break;
            case 43:
            case 44:
                name = "Brasilia (RSMC/RAFC)";
                break;
            case 45:
                name = "Santiago";
                break;
            case 46:
                name = "Brazilian Space Agency - INPE";
                break;
            case 47:
                name = "Columbia (NMC)";
                break;
            case 48:
                name = "Ecuador (NMC)";
                break;
            case 49:
                name = "Peru (NMC)";
                break;
            case 50:
                name = "Venezuela (NMC)";
                break;
            case 51:
                name = "Miami (RSMC/RAFC)";
                break;
            case 52:
                name = "Miami (RSMC), National Hurricane Center";
                break;
            case 53:
            case 54:
                name = "Canadian Meteorological Service - Montreal (RSMC)";
                break;
            case 55:
                name = "San Francisco";
                break;
            case 56:
                name = "ARINC Center";
                break;
            case 57:
                name = "US Air Force - Air Force Global Weather Center";
                break;
            case 58:
                name = "Fleet Numerical Meteorology and Oceanography Center,Monterey,CA,USA";
                break;
            case 59:
                name = "The NOAA Forecast Systems Lab, Boulder, CO, USA";
                break;
            case 60:
                name = "National Center for Atmospheric Research (NCAR), Boulder, CO";
                break;
            case 61:
                name = "Service ARGOS - Landover, MD, USA";
                break;
            case 62:
                name = "US Naval Oceanographic Office";
                break;
            case 63:
                name = "nternational Research Institude for Climate and Society";
                break;
            case 64:
                name = "Honolulu";
                break;
            case 65:
            case 66:
                name = "Darwin (RSMC)";
                break;
            case 67:
                name = "Melbourne (RSMC)";
                break;
            case 68:
                break;
            case 69:
            case 70:
                name = "Wellington (RSMC/RAFC)";
                break;
            case 71:
                name = "Nadi (RSMC)";
                break;
            case 72:
                name = "Singapore";
                break;
            case 73:
                name = "Malaysia (NMC)";
                break;
            case 74:
            case 75:
                name = "U.K. Met Office - Exeter (RSMC)";
                break;
            case 76:
                name = "Moscow (RSMC/RAFC)";
                break;
            case 77:
                break;
            case 78:
            case 79:
                name = "Offenbach (RSMC)";
                break;
            case 80:
            case 81:
                name = "Rome (RSMC)";
                break;
            case 82:
            case 83:
                name = "Norrkoping";
                break;
            case 84:
            case 85:
                name = "French Weather Service - Toulouse";
                break;
            case 86:
                name = "Helsinki";
                break;
            case 87:
                name = "Belgrade";
                break;
            case 88:
                name = "Oslo";
                break;
            case 89:
                name = "Prague";
                break;
            case 90:
                name = "Episkopi";
                break;
            case 91:
                name = "Ankara";
                break;
            case 92:
                name = "Frankfurt/Main (RAFC)";
                break;
            case 93:
                name = "London (WAFC)";
                break;
            case 94:
                name = "Copenhagen";
                break;
            case 95:
                name = "Rota";
                break;
            case 96:
                name = "Athens";
                break;
            case 97:
                name = "European Space Agency (ESA)";
                break;
            case 98:
                name = "European Center for Medium-Range Weather Forecasts (RSMC)";
                break;
            case 99:
                name = "De Bilt, Netherlands";
                break;
            case 100:
                name = "Brazzaville";
                break;
            case 101:
                name = "Abidjan";
                break;
            case 102:
                name = "Libyan Arab Jamahiriya (NMC)";
                break;
            case 103:
                name = "Madagascar (NMC)";
                break;
            case 104:
                name = "Mauritius (NMC)";
                break;
            case 105:
                name = "Niger (NMC)";
                break;
            case 106:
                name = "Seychelles (NMC)";
                break;
            case 107:
                name = "Uganda (NMC)";
                break;
            case 108:
                name = "United Republic of Tanzania (NMC)";
                break;
            case 109:
                name = "Zimbabwe (NMC)";
                break;
            case 110:
                name = "Hong-Kong";
                break;
            case 111:
                name = "Afghanistan (NMC)";
                break;
            case 112:
                name = "Bahrain (NMC)";
                break;
            case 113:
                name = "Bangladesh (NMC)";
                break;
            case 114:
                name = "Bhutan (NMC)";
                break;
            case 115:
                name = "Cambodia (NMC)";
                break;
            case 116:
                name = "Democratic People's Republic of Korea (NMC)";
                break;
            case 117:
                name = "Islamic Republic of Iran (NMC)";
                break;
            case 118:
                name = "Iraq (NMC)";
                break;
            case 119:
                name = "Kazakhstan (NMC)";
                break;
            case 120:
                name = "Kuwait (NMC)";
                break;
            case 121:
                name = "Kyrgyz Republic (NMC)";
                break;
            case 122:
                name = "Lao People's Democratic Republic (NMC)";
                break;
            case 123:
                name = "Macao, China";
                break;
            case 124:
                name = "Maldives (NMC)";
                break;
            case 125:
                name = "Myanmar (NMC)";
                break;
            case 126:
                name = "Nepal (NMC)";
                break;
            case 127:
                name = "Oman (NMC) ";
                break;
            case 128:
                name = "Pakistan (NMC)";
                break;
            case 129:
                name = "Qatar (NMC)";
                break;
            case 130:
                name = "Yemen (NMC)";
                break;
            case 131:
                name = "Sri Lanka (NMC)";
                break;
            case 132:
                name = "Tajikistan (NMC)";
                break;
            case 133:
                name = "Turkmenistan (NMC)";
                break;
            case 134:
                name = "United Arab Emirates (NMC)";
                break;
            case 135:
                name = "Uzbekistan (NMC)";
                break;
            case 136:
                name = "Viet Nam (NMC)";
                break;
            case 137:
            case 138:
            case 139:
                break;
            case 140:
                name = "Bolivia (NMC)";
                break;
            case 141:
                name = "Guyana (NMC)";
                break;
            case 142:
                name = "Paraguay (NMC)";
                break;
            case 143:
                name = "Suriname (NMC)";
                break;
            case 144:
                name = "Uruguay (NMC)";
                break;
            case 145:
                name = "French Guyana";
                break;
            case 146:
                name = "Brazilian Navy Hydrographic Center";
                break;
            case 147:
                name = "National Commission on Space Activities - Argentina";
                break;
            case 148:
            case 149:
                break;
            case 150:
                name = "Antigua and Barbuda (NMC)";
                break;
            case 151:
                name = "Bahamas (NMC)";
                break;
            case 152:
                name = "Barbados (NMC)";
                break;
            case 153:
                name = "Belize (NMC)";
                break;
            case 154:
                name = "British Caribbean Territories Center";
                break;
            case 155:
                name = "San Jose";
                break;
            case 156:
                name = "Cuba (NMC)";
                break;
            case 157:
                name = "Dominica (NMC)";
                break;
            case 158:
                name = "Dominican Republic (NMC)";
                break;
            case 159:
                name = "El Salvador (NMC)";
                break;
            case 160:
                name = "US NOAA/NESDIS";
                break;
            case 161:
                name = "US NOAA Office of Oceanic and Atmospheric Research";
                break;
            case 162:
                name = "Guatemala (NMC)";
                break;
            case 163:
                name = "Haiti (NMC)";
                break;
            case 164:
                name = "Honduras (NMC)";
                break;
            case 165:
                name = "Jamaica (NMC)";
                break;
            case 166:
                name = "Mexico City";
                break;
            case 167:
                name = "Netherlands Antilles and Aruba (NMC)";
                break;
            case 168:
                name = "Nicaragua (NMC)";
                break;
            case 169:
                name = "Panama (NMC)";
                break;
            case 170:
                name = "Saint Lucia (NMC)";
                break;
            case 171:
                name = "Trinidad and Tobago (NMC)";
                break;
            case 172:
                name = "French Departments in RA IV";
                break;
            case 173:
                name = "US National Aeronautics and Space Administration (NASA)";
                break;
            case 174:
                name = "Integrated System Data Management/Marine \n Environmental Data Service (ISDM/MEDS) - Canada";
                break;
            case 175:
                break;
            case 176:
                name = "US Cooperative Institude for Meteorological Satellite Studies";
                break;
            case 177:
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 185:
            case 186:
            case 187:
            case 188:
            case 189:
                break;
            case 190:
                name = "Cook Islands (NMC)";
                break;
            case 191:
                name = "French Polynesia (NMC)";
                break;
            case 192:
                name = "Tonga (NMC)";
                break;
            case 193:
                name = "Vanuatu (NMC)";
                break;
            case 194:
                name = "Brunei (NMC)";
                break;
            case 195:
                name = "Indonesia (NMC)";
                break;
            case 196:
                name = "Kiribati (NMC)";
                break;
            case 197:
                name = "Federated States of Micronesia (NMC)";
                break;
            case 198:
                name = "New Caledonia (NMC)";
                break;
            case 199:
                name = "Niue";
                break;
            case 200:
                name = "Papua New Guinea (NMC)";
                break;
            case 201:
                name = "Philippines (NMC)";
                break;
            case 202:
                name = "Samoa (NMC)";
                break;
            case 203:
                name = "Solomon Islands (NMC)";
                break;
            case 204:
                name = "Narional Institude of Water and Atmospheric Research - New Zealand";
                break;
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
                break;
            case 210:
                name = "Frascati (ESA/ESRIN)";
                break;
            case 211:
                name = "Lanion";
                break;
            case 212:
                name = "Lisbon";
                break;
            case 213:
                name = "Reykjavik";
                break;
            case 214:
                name = "Madrid";
                break;
            case 215:
                name = "Zurich";
                break;
            case 216:
                name = "Service ARGOS - Toulouse";
                break;
            case 217:
                name = "Bratislava";
                break;
            case 218:
                name = "Budapest";
                break;
            case 219:
                name = "Ljubljana";
                break;
            case 220:
                name = "Warsaw";
                break;
            case 221:
                name = "Zagreb";
                break;
            case 222:
                name = "Albania (NMC)";
                break;
            case 223:
                name = "Armenia (NMC)";
                break;
            case 224:
                name = "Austria (NMC)";
                break;
            case 225:
                name = "Azerbaijan (NMC)";
                break;
            case 226:
                name = "Belarus (NMC)";
                break;
            case 227:
                name = "Belgium (NMC)";
                break;
            case 228:
                name = "Bosnia and Herzegovina (NMC)";
                break;
            case 229:
                name = "Bulgaria (NMC)";
                break;
            case 230:
                name = "Cyprus (NMC)";
                break;
            case 231:
                name = "Estonia (NMC)";
                break;
            case 232:
                name = "Georgia (NMC)";
                break;
            case 233:
                name = "Dublin";
                break;
            case 234:
                name = "Israel (NMC)";
                break;
            case 235:
                name = "Jordan (NMC)";
                break;
            case 236:
                name = "Latvia (NMC)";
                break;
            case 237:
                name = "Lebanon (NMC)";
                break;
            case 238:
                name = "Lithuania (NMC)";
                break;
            case 239:
                name = "Luxembourg";
                break;
            case 240:
                name = "Malta (NMC)";
                break;
            case 241:
                name = "Monaco";
                break;
            case 242:
                name = "Romania (NMC)";
                break;
            case 243:
                name = "Syrian Arab Republic (NMC)";
                break;
            case 244:
                name = "The former Yugoslav Republic of Macedonia (NMC)";
                break;
            case 245:
                name = "Ukraine (NMC)";
                break;
            case 246:
                name = "Republic of Moldova (NMC)";
                break;
            case 247:
                name = "Operational Programme for the Exchange of Weather RAdar Information (OPERA) - EUMETNET";
                break;
            case 248:
            case 249:
                break;
            case 250:
                name = "COnsortium for Small scale MOdelling (COSMO)";
                break;
            case 251:
            case 252:
            case 253:
                break;
            case 254:
                name = "EUMETSAT Operations Center";
                break;
            default:
                name = "Missing Value";
                break;
        }
        return name;
    }

    private String chooseGeneratingSubCenterName(int generatingSubCenter) {
        String name;
        switch ( generatingSubCenter ) {
            case 1:
                name = "NCEP Re-Analysis Project";
                break;
            case 2:
                name = "NCEP Ensemble Products";
                break;
            case 3:
                name = "NCEP Central Operations";
                break;
            case 4:
                name = "Environmental Modeling Center";
                break;
            case 5:
                name = "Hydrometeorological Prediction Center";
                break;
            case 6:
                name = "Marine Prediction Center";
                break;
            case 7:
                name = "Climate Prediction Center";
                break;
            case 8:
                name = "Aviation Weather Center";
                break;
            case 9:
                name = "Storm Prediction Center";
                break;
            case 10:
                name = "National Hurricane Prediction Center";
                break;
            case 11:
                name = "NWS Techniques Development Laboratory";
                break;
            case 12:
                name = "NESDIS Office of Research and Applications";
                break;
            case 13:
                name = "Federal Aviation Administration";
                break;
            case 14:
                name = "NWS Meteorological Development Laboratory";
                break;
            case 15:
                name = "North American Regional Reanalysis Project";
                break;
            case 16:
                name = "Space Weather Prediction Center";
                break;
            default:
                name = "Missing";
                break;
        }
        return name;
    }

    private String chooseMasterTablesVersionDetail(short masterTablesVersion) {
        String name;
        switch ( masterTablesVersion ) {
            case 0:
                name = "Experimental";
                break;
            case 1:
                name = "Version Implemented on 7 November 2001";
                break;
            case 2:
                name = "Version Implemented on 4 November 2003";
                break;
            case 3:
                name = "Version Implemented on 2 November 2005";
                break;
            case 4:
                name = "Version Implemented on 7 November 2007";
                break;
            case 5:
                name = "Version Implemented on 4 November 2009";
                break;
            case 6:
                name = "Version Implemented on 15 September 2010";
                break;
            case 7:
                name = "Version Implemented on 4 May 2011";
                break;
            case 8:
                name = "Version Implemented on 8 November 2011";
                break;
            case 9:
                name = "Version Implemented on 2 May 2012";
                break;
            case 10:
                name = "Version Implemented on 7 November 2012";
                break;
            case 11:
                name = "Version Implemented on 8 May 2013";
                break;
            case 12:
                name = "Version Implemented on 14 November 2013";
                break;
            case 13:
                name = "Version Implemented on 7 May 2014";
                break;
            case 14:
                name = "Version Implemented on 5 November 2014";
                break;
            case 15:
                name = "Version Implemented on 6 May 2015";
                break;
            case 16:
                name = "Version Implemented on 11 November 2015";
                break;
            case 17:
                name = "Version Implemented on 4 May 2016";
                break;
            case 18:
                name = "Version Implemented on 11 November 2015";
                break;
            case 19:
                name = "Version Implemented on 11 November 2015";
                break;
            case 20:
                name = "Version Implemented on 11 November 2015";
                break;
            case 21:
                name = "Version Implemented on 11 November 2015";
                break;
            case 22:
                name = "Version Implemented on 11 November 2015";
                break;
            case 255:
                name = "Master tables not used.  Local table entries and local templates may use the entire range of the table, not just those sections marked \"Reserved for local use\".";
                break;
            default:
                name = "Future Operational Version";
                break;
        }
        return name;
    }

    private String chooseLocalTablesVersionDetail(short localTablesVersion) {
        String name;
        switch ( localTablesVersion ) {
            case 0:
                name = "Local tables not used.  Only table entries and templates from the current master table are valid.";
                break;
            case 255:
                name = "Missing";
                break;
            default:
                name = "Number of local table version used.";
                break;
        }
        return name;
    }

    private String chooseSignificanceOfReferenceTimeDetail(short significanceOfReferenceTime) {
        String name;
        switch ( significanceOfReferenceTime ) {
            case 0:
                name = "Analysis";
                break;
            case 1:
                name = "Start of Forecast";
                break;
            case 2:
                name = "Verifying Time of Forecast";
                break;
            case 3:
                name = "Observation Time";
                break;
            case 255:
                name = "Missing";
                break;
            default:
                // 4-191 Reserved , 192-254 Reserved for Local Use
                name = "Reserved or Reserved for Local Use";
                break;
        }
        return name;
    }

    private String chooseProductionStatusName(short productionStatus) {
        String name;
        switch ( productionStatus ) {
            case 0:
                name = "Operational Products";
                break;
            case 1:
                name = "Operational Test Products";
                break;
            case 2:
                name = "Research Products";
                break;
            case 3:
                name = "Re-Analysis Products";
                break;
            case 4:
                name = "THORPEX Interactive Grand Global Ensemble (TIGGE)";
                break;
            case 5:
                name = "THORPEX Interactive Grand Global Ensemble (TIGGE) test";
                break;
            case 6:
                name = "S2S Operational Products";
                break;
            case 7:
                name = "S2S Test Products";
                break;
            case 8:
                name = "Uncertainties in ensembles of regional reanalysis project (UERRA)";
                break;
            case 9:
                name = "Uncertainties in ensembles of regional reanalysis project (UERRA) Test";
                break;
            case 255:
                name = "Missing";
                break;
            default:
                // 10-191 Reserved , 192-254 Reserved for Local Use
                name = "Reserved or Reserved for Local Use";
                break;
        }
        return name;
    }

    private String chooseTypeName(short type) {
        String name;
        switch ( type ) {
            case 0:
                name = "Analysis Products";
                break;
            case 1:
                name = "Forecast Products";
                break;
            case 2:
                name = "Analysis and Forecast Products";
                break;
            case 3:
                name = "Control Forecast Products";
                break;
            case 4:
                name = "Perturbed Forecast Products";
                break;
            case 5:
                name = "Control and Perturbed Forecast Products";
                break;
            case 6:
                name = "Processed Satellite Observations";
                break;
            case 7:
                name = "Processed Radar Observations";
                break;
            case 8:
                name = "Event Probability";
                break;
            case 192:
                name = "Experimental Products";
                break;
            case 255:
                name = "Missing";
                break;
            default:
                // 9-191 Reserved , 193-254 Reserved for Local Use
                name = "Reserved or Reserved for Local Use";
                break;
        }
        return name;
    }


    public int getGeneratingCenter() {
        return generatingCenter;
    }

    public void setGeneratingCenter(int generatingCenter) {
        this.generatingCenter = generatingCenter;
    }

    public String getGeneratingCenterName() {
        return generatingCenterName;
    }

    public void setGeneratingCenterName(String generatingCenterName) {
        this.generatingCenterName = generatingCenterName;
    }

    public int getGeneratingSubCenter() {
        return generatingSubCenter;
    }

    public void setGeneratingSubCenter(int generatingSubCenter) {
        this.generatingSubCenter = generatingSubCenter;
    }

    public String getGeneratingSubCenterName() {
        return generatingSubCenterName;
    }

    public void setGeneratingSubCenterName(String generatingSubCenterName) {
        this.generatingSubCenterName = generatingSubCenterName;
    }

    public short getMasterTablesVersion() {
        return masterTablesVersion;
    }

    public void setMasterTablesVersion(short masterTablesVersion) {
        this.masterTablesVersion = masterTablesVersion;
    }

    public String getMasterTablesVersionDetail() {
        return masterTablesVersionDetail;
    }

    public void setMasterTablesVersionDetail(String masterTablesVersionDetail) {
        this.masterTablesVersionDetail = masterTablesVersionDetail;
    }

    public short getLocalTablesVersion() {
        return localTablesVersion;
    }

    public void setLocalTablesVersion(short localTablesVersion) {
        this.localTablesVersion = localTablesVersion;
    }

    public String getLocalTablesVersionDetail() {
        return localTablesVersionDetail;
    }

    public void setLocalTablesVersionDetail(String localTablesVersionDetail) {
        this.localTablesVersionDetail = localTablesVersionDetail;
    }

    public short getSignificanceOfReferenceTime() {
        return significanceOfReferenceTime;
    }

    public void setSignificanceOfReferenceTime(short significanceOfReferenceTime) {
        this.significanceOfReferenceTime = significanceOfReferenceTime;
    }

    public String getSignificanceOfReferenceTimeDetail() {
        return significanceOfReferenceTimeDetail;
    }

    public void setSignificanceOfReferenceTimeDetail(String significanceOfReferenceTimeDetail) {
        this.significanceOfReferenceTimeDetail = significanceOfReferenceTimeDetail;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public byte getMonth() {
        return month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public byte getHour() {
        return hour;
    }

    public void setHour(byte hour) {
        this.hour = hour;
    }

    public byte getMinute() {
        return minute;
    }

    public void setMinute(byte minute) {
        this.minute = minute;
    }

    public byte getSecond() {
        return second;
    }

    public void setSecond(byte second) {
        this.second = second;
    }

    public short getProductionStatus() {
        return productionStatus;
    }

    public void setProductionStatus(short productionStatus) {
        this.productionStatus = productionStatus;
    }

    public String getProductionStatusName() {
        return productionStatusName;
    }

    public void setProductionStatusName(String productionStatusName) {
        this.productionStatusName = productionStatusName;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public byte[] getReserved() {
        return reserved;
    }

    public void setReserved(byte[] reserved) {
        this.reserved = reserved;
    }
}
