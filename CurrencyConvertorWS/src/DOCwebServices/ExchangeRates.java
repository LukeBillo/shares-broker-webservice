package DOCwebServices;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;

public class ExchangeRates {
    public String disclaimer;
    public String license;
    public double timestamp;
    public String base;
    Rates RatesObject;

    public String getDisclaimer() {
        return disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public Rates getRates() {
        return RatesObject;
    }

    public void setDisclaimer( String disclaimer ) {
        this.disclaimer = disclaimer;
    }

    public void setLicense( String license ) {
        this.license = license;
    }

    public void setTimestamp( double timestamp ) {
        this.timestamp = timestamp;
    }

    public void setBase( String base ) {
        this.base = base;
    }

    public void setRates( Rates ratesObject ) {
        this.RatesObject = ratesObject;
    }

    public double getRateBySymbol(String symbol) {
        double rate = 0.0;

        try {
            Field rateField = RatesObject.getClass().getDeclaredField(symbol);
            rateField.setAccessible(true);

            rate = rateField.getDouble(RatesObject);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return rate;
    }
}

class Rates {
    @JsonProperty("AED") public double AED;
    @JsonProperty("AFN") public double AFN;
    @JsonProperty("ALL") public double ALL;
    @JsonProperty("AMD") public double AMD;
    @JsonProperty("ANG") public double ANG;
    @JsonProperty("AOA") public double AOA;
    @JsonProperty("ARS") public double ARS;
    @JsonProperty("AUD") public double AUD;
    @JsonProperty("AWG") public double AWG;
    @JsonProperty("AZN") public double AZN;
    @JsonProperty("BAM") public double BAM;
    @JsonProperty("BBD") public double BBD;
    @JsonProperty("BDT") public double BDT;
    @JsonProperty("BGN") public double BGN;
    @JsonProperty("BHD") public double BHD;
    @JsonProperty("BIF") public double BIF;
    @JsonProperty("BMD") public double BMD;
    @JsonProperty("BND") public double BND;
    @JsonProperty("BOB") public double BOB;
    @JsonProperty("BRL") public double BRL;
    @JsonProperty("BSD") public double BSD;
    @JsonProperty("BTC") public double BTC;
    @JsonProperty("BTN") public double BTN;
    @JsonProperty("BWP") public double BWP;
    @JsonProperty("BYN") public double BYN;
    @JsonProperty("BZD") public double BZD;
    @JsonProperty("CAD") public double CAD;
    @JsonProperty("CDF") public double CDF;
    @JsonProperty("CHF") public double CHF;
    @JsonProperty("CLF") public double CLF;
    @JsonProperty("CLP") public double CLP;
    @JsonProperty("CNH") public double CNH;
    @JsonProperty("CNY") public double CNY;
    @JsonProperty("COP") public double COP;
    @JsonProperty("CRC") public double CRC;
    @JsonProperty("CUC") public double CUC;
    @JsonProperty("CUP") public double CUP;
    @JsonProperty("CVE") public double CVE;
    @JsonProperty("CZK") public double CZK;
    @JsonProperty("DJF") public double DJF;
    @JsonProperty("DKK") public double DKK;
    @JsonProperty("DOP") public double DOP;
    @JsonProperty("DZD") public double DZD;
    @JsonProperty("EGP") public double EGP;
    @JsonProperty("ERN") public double ERN;
    @JsonProperty("ETB") public double ETB;
    @JsonProperty("EUR") public double EUR;
    @JsonProperty("FJD") public double FJD;
    @JsonProperty("FKP") public double FKP;
    @JsonProperty("GBP") public double GBP;
    @JsonProperty("GEL") public double GEL;
    @JsonProperty("GGP") public double GGP;
    @JsonProperty("GHS") public double GHS;
    @JsonProperty("GIP") public double GIP;
    @JsonProperty("GMD") public double GMD;
    @JsonProperty("GNF") public double GNF;
    @JsonProperty("GTQ") public double GTQ;
    @JsonProperty("GYD") public double GYD;
    @JsonProperty("HKD") public double HKD;
    @JsonProperty("HNL") public double HNL;
    @JsonProperty("HRK") public double HRK;
    @JsonProperty("HTG") public double HTG;
    @JsonProperty("HUF") public double HUF;
    @JsonProperty("IDR") public double IDR;
    @JsonProperty("ILS") public double ILS;
    @JsonProperty("IMP") public double IMP;
    @JsonProperty("INR") public double INR;
    @JsonProperty("IQD") public double IQD;
    @JsonProperty("IRR") public double IRR;
    @JsonProperty("ISK") public double ISK;
    @JsonProperty("JEP")public double JEP;
    @JsonProperty("JMD")public double JMD;
    @JsonProperty("JOD")public double JOD;
    @JsonProperty("JPY")public double JPY;
    @JsonProperty("KES")public double KES;
    @JsonProperty("KGS")public double KGS;
    @JsonProperty("KHR")public double KHR;
    @JsonProperty("KMF")public double KMF;
    @JsonProperty("KPW")public double KPW;
    @JsonProperty("KRW")public double KRW;
    @JsonProperty("KWD")public double KWD;
    @JsonProperty("KYD")public double KYD;
    @JsonProperty("KZT")public double KZT;
    @JsonProperty("LAK")public double LAK;
    @JsonProperty("LBP")public double LBP;
    @JsonProperty("LKR")public double LKR;
    @JsonProperty("LRD")public double LRD;
    @JsonProperty("LSL")public double LSL;
    @JsonProperty("LYD")public double LYD;
    @JsonProperty("MAD")public double MAD;
    @JsonProperty("MDL")public double MDL;
    @JsonProperty("MGA")public double MGA;
    @JsonProperty("MKD")public double MKD;
    @JsonProperty("MMK")public double MMK;
    @JsonProperty("MNT")public double MNT;
    @JsonProperty("MOP")public double MOP;
    @JsonProperty("MRO")public double MRO;
    @JsonProperty("MRU")public double MRU;
    @JsonProperty("MUR")public double MUR;
    @JsonProperty("MVR")public double MVR;
    @JsonProperty("MWK")public double MWK;
    @JsonProperty("MXN")public double MXN;
    @JsonProperty("MYR")public double MYR;
    @JsonProperty("MZN")public double MZN;
    @JsonProperty("NAD")public double NAD;
    @JsonProperty("NGN")public double NGN;
    @JsonProperty("NIO")public double NIO;
    @JsonProperty("NOK")public double NOK;
    @JsonProperty("NPR")public double NPR;
    @JsonProperty("NZD")public double NZD;
    @JsonProperty("OMR")public double OMR;
    @JsonProperty("PAB")public double PAB;
    @JsonProperty("PEN")public double PEN;
    @JsonProperty("PGK")public double PGK;
    @JsonProperty("PHP")public double PHP;
    @JsonProperty("PKR")public double PKR;
    @JsonProperty("PLN")public double PLN;
    @JsonProperty("PYG")public double PYG;
    @JsonProperty("QAR")public double QAR;
    @JsonProperty("RON")public double RON;
    @JsonProperty("RSD")public double RSD;
    @JsonProperty("RUB")public double RUB;
    @JsonProperty("RWF")public double RWF;
    @JsonProperty("SAR")public double SAR;
    @JsonProperty("SBD")public double SBD;
    @JsonProperty("SCR")public double SCR;
    @JsonProperty("SDG")public double SDG;
    @JsonProperty("SEK")public double SEK;
    @JsonProperty("SGD")public double SGD;
    @JsonProperty("SHP")public double SHP;
    @JsonProperty("SLL")public double SLL;
    @JsonProperty("SOS")public double SOS;
    @JsonProperty("SRD")public double SRD;
    @JsonProperty("SSP")public double SSP;
    @JsonProperty("STD")public double STD;
    @JsonProperty("STN")public double STN;
    @JsonProperty("SVC")public double SVC;
    @JsonProperty("SYP")public double SYP;
    @JsonProperty("SZL")public double SZL;
    @JsonProperty("THB")public double THB;
    @JsonProperty("TJS")public double TJS;
    @JsonProperty("TMT")public double TMT;
    @JsonProperty("TND")public double TND;
    @JsonProperty("TOP")public double TOP;
    @JsonProperty("TRY")public double TRY;
    @JsonProperty("TTD")public double TTD;
    @JsonProperty("TWD")public double TWD;
    @JsonProperty("TZS")public double TZS;
    @JsonProperty("UAH")public double UAH;
    @JsonProperty("UGX")public double UGX;
    @JsonProperty("USD")public double USD;
    @JsonProperty("UYU")public double UYU;
    @JsonProperty("UZS")public double UZS;
    @JsonProperty("VEF")public double VEF;
    @JsonProperty("VES")public double VES;
    @JsonProperty("VND")public double VND;
    @JsonProperty("VUV")public double VUV;
    @JsonProperty("WST")public double WST;
    @JsonProperty("XAF")public double XAF;
    @JsonProperty("XAG")public double XAG;
    @JsonProperty("XAU")public double XAU;
    @JsonProperty("XCD")public double XCD;
    @JsonProperty("XDR")public double XDR;
    @JsonProperty("XOF")public double XOF;
    @JsonProperty("XPD")public double XPD;
    @JsonProperty("XPF")public double XPF;
    @JsonProperty("XPT")public double XPT;
    @JsonProperty("YER")public double YER;
    @JsonProperty("ZAR")public double ZAR;
    @JsonProperty("ZMW")public double ZMW;
    @JsonProperty("ZWL")public double ZWL;

    public double getAED() {
        return AED;
    }

    public double getAFN() {
        return AFN;
    }

    public double getALL() {
        return ALL;
    }

    public double getAMD() {
        return AMD;
    }

    public double getANG() {
        return ANG;
    }

    public double getAOA() {
        return AOA;
    }

    public double getARS() {
        return ARS;
    }

    public double getAUD() {
        return AUD;
    }

    public double getAWG() {
        return AWG;
    }

    public double getAZN() {
        return AZN;
    }

    public double getBAM() {
        return BAM;
    }

    public double getBBD() {
        return BBD;
    }

    public double getBDT() {
        return BDT;
    }

    public double getBGN() {
        return BGN;
    }

    public double getBHD() {
        return BHD;
    }

    public double getBIF() {
        return BIF;
    }

    public double getBMD() {
        return BMD;
    }

    public double getBND() {
        return BND;
    }

    public double getBOB() {
        return BOB;
    }

    public double getBRL() {
        return BRL;
    }

    public double getBSD() {
        return BSD;
    }

    public double getBTC() {
        return BTC;
    }

    public double getBTN() {
        return BTN;
    }

    public double getBWP() {
        return BWP;
    }

    public double getBYN() {
        return BYN;
    }

    public double getBZD() {
        return BZD;
    }

    public double getCAD() {
        return CAD;
    }

    public double getCDF() {
        return CDF;
    }

    public double getCHF() {
        return CHF;
    }

    public double getCLF() {
        return CLF;
    }

    public double getCLP() {
        return CLP;
    }

    public double getCNH() {
        return CNH;
    }

    public double getCNY() {
        return CNY;
    }

    public double getCOP() {
        return COP;
    }

    public double getCRC() {
        return CRC;
    }

    public double getCUC() {
        return CUC;
    }

    public double getCUP() {
        return CUP;
    }

    public double getCVE() {
        return CVE;
    }

    public double getCZK() {
        return CZK;
    }

    public double getDJF() {
        return DJF;
    }

    public double getDKK() {
        return DKK;
    }

    public double getDOP() {
        return DOP;
    }

    public double getDZD() {
        return DZD;
    }

    public double getEGP() {
        return EGP;
    }

    public double getERN() {
        return ERN;
    }

    public double getETB() {
        return ETB;
    }

    public double getEUR() {
        return EUR;
    }

    public double getFJD() {
        return FJD;
    }

    public double getFKP() {
        return FKP;
    }

    public double getGBP() {
        return GBP;
    }

    public double getGEL() {
        return GEL;
    }

    public double getGGP() {
        return GGP;
    }

    public double getGHS() {
        return GHS;
    }

    public double getGIP() {
        return GIP;
    }

    public double getGMD() {
        return GMD;
    }

    public double getGNF() {
        return GNF;
    }

    public double getGTQ() {
        return GTQ;
    }

    public double getGYD() {
        return GYD;
    }

    public double getHKD() {
        return HKD;
    }

    public double getHNL() {
        return HNL;
    }

    public double getHRK() {
        return HRK;
    }

    public double getHTG() {
        return HTG;
    }

    public double getHUF() {
        return HUF;
    }

    public double getIDR() {
        return IDR;
    }

    public double getILS() {
        return ILS;
    }

    public double getIMP() {
        return IMP;
    }

    public double getINR() {
        return INR;
    }

    public double getIQD() {
        return IQD;
    }

    public double getIRR() {
        return IRR;
    }

    public double getISK() {
        return ISK;
    }

    public double getJEP() {
        return JEP;
    }

    public double getJMD() {
        return JMD;
    }

    public double getJOD() {
        return JOD;
    }

    public double getJPY() {
        return JPY;
    }

    public double getKES() {
        return KES;
    }

    public double getKGS() {
        return KGS;
    }

    public double getKHR() {
        return KHR;
    }

    public double getKMF() {
        return KMF;
    }

    public double getKPW() {
        return KPW;
    }

    public double getKRW() {
        return KRW;
    }

    public double getKWD() {
        return KWD;
    }

    public double getKYD() {
        return KYD;
    }

    public double getKZT() {
        return KZT;
    }

    public double getLAK() {
        return LAK;
    }

    public double getLBP() {
        return LBP;
    }

    public double getLKR() {
        return LKR;
    }

    public double getLRD() {
        return LRD;
    }

    public double getLSL() {
        return LSL;
    }

    public double getLYD() {
        return LYD;
    }

    public double getMAD() {
        return MAD;
    }

    public double getMDL() {
        return MDL;
    }

    public double getMGA() {
        return MGA;
    }

    public double getMKD() {
        return MKD;
    }

    public double getMMK() {
        return MMK;
    }

    public double getMNT() {
        return MNT;
    }

    public double getMOP() {
        return MOP;
    }

    public double getMRO() {
        return MRO;
    }

    public double getMRU() {
        return MRU;
    }

    public double getMUR() {
        return MUR;
    }

    public double getMVR() {
        return MVR;
    }

    public double getMWK() {
        return MWK;
    }

    public double getMXN() {
        return MXN;
    }

    public double getMYR() {
        return MYR;
    }

    public double getMZN() {
        return MZN;
    }

    public double getNAD() {
        return NAD;
    }

    public double getNGN() {
        return NGN;
    }

    public double getNIO() {
        return NIO;
    }

    public double getNOK() {
        return NOK;
    }

    public double getNPR() {
        return NPR;
    }

    public double getNZD() {
        return NZD;
    }

    public double getOMR() {
        return OMR;
    }

    public double getPAB() {
        return PAB;
    }

    public double getPEN() {
        return PEN;
    }

    public double getPGK() {
        return PGK;
    }

    public double getPHP() {
        return PHP;
    }

    public double getPKR() {
        return PKR;
    }

    public double getPLN() {
        return PLN;
    }

    public double getPYG() {
        return PYG;
    }

    public double getQAR() {
        return QAR;
    }

    public double getRON() {
        return RON;
    }

    public double getRSD() {
        return RSD;
    }

    public double getRUB() {
        return RUB;
    }

    public double getRWF() {
        return RWF;
    }

    public double getSAR() {
        return SAR;
    }

    public double getSBD() {
        return SBD;
    }

    public double getSCR() {
        return SCR;
    }

    public double getSDG() {
        return SDG;
    }

    public double getSEK() {
        return SEK;
    }

    public double getSGD() {
        return SGD;
    }

    public double getSHP() {
        return SHP;
    }

    public double getSLL() {
        return SLL;
    }

    public double getSOS() {
        return SOS;
    }

    public double getSRD() {
        return SRD;
    }

    public double getSSP() {
        return SSP;
    }

    public double getSTD() {
        return STD;
    }

    public double getSTN() {
        return STN;
    }

    public double getSVC() {
        return SVC;
    }

    public double getSYP() {
        return SYP;
    }

    public double getSZL() {
        return SZL;
    }

    public double getTHB() {
        return THB;
    }

    public double getTJS() {
        return TJS;
    }

    public double getTMT() {
        return TMT;
    }

    public double getTND() {
        return TND;
    }

    public double getTOP() {
        return TOP;
    }

    public double getTRY() {
        return TRY;
    }

    public double getTTD() {
        return TTD;
    }

    public double getTWD() {
        return TWD;
    }

    public double getTZS() {
        return TZS;
    }

    public double getUAH() {
        return UAH;
    }

    public double getUGX() {
        return UGX;
    }

    public double getUSD() {
        return USD;
    }

    public double getUYU() {
        return UYU;
    }

    public double getUZS() {
        return UZS;
    }

    public double getVEF() {
        return VEF;
    }

    public double getVES() {
        return VES;
    }

    public double getVND() {
        return VND;
    }

    public double getVUV() {
        return VUV;
    }

    public double getWST() {
        return WST;
    }

    public double getXAF() {
        return XAF;
    }

    public double getXAG() {
        return XAG;
    }

    public double getXAU() {
        return XAU;
    }

    public double getXCD() {
        return XCD;
    }

    public double getXDR() {
        return XDR;
    }

    public double getXOF() {
        return XOF;
    }

    public double getXPD() {
        return XPD;
    }

    public double getXPF() {
        return XPF;
    }

    public double getXPT() {
        return XPT;
    }

    public double getYER() {
        return YER;
    }

    public double getZAR() {
        return ZAR;
    }

    public double getZMW() {
        return ZMW;
    }

    public double getZWL() {
        return ZWL;
    }

    // Setter Methods

    public void setAED( double AED ) {
        this.AED = AED;
    }

    public void setAFN( double AFN ) {
        this.AFN = AFN;
    }

    public void setALL( double ALL ) {
        this.ALL = ALL;
    }

    public void setAMD( double AMD ) {
        this.AMD = AMD;
    }

    public void setANG( double ANG ) {
        this.ANG = ANG;
    }

    public void setAOA( double AOA ) {
        this.AOA = AOA;
    }

    public void setARS( double ARS ) {
        this.ARS = ARS;
    }

    public void setAUD( double AUD ) {
        this.AUD = AUD;
    }

    public void setAWG( double AWG ) {
        this.AWG = AWG;
    }

    public void setAZN( double AZN ) {
        this.AZN = AZN;
    }

    public void setBAM( double BAM ) {
        this.BAM = BAM;
    }

    public void setBBD( double BBD ) {
        this.BBD = BBD;
    }

    public void setBDT( double BDT ) {
        this.BDT = BDT;
    }

    public void setBGN( double BGN ) {
        this.BGN = BGN;
    }

    public void setBHD( double BHD ) {
        this.BHD = BHD;
    }

    public void setBIF( double BIF ) {
        this.BIF = BIF;
    }

    public void setBMD( double BMD ) {
        this.BMD = BMD;
    }

    public void setBND( double BND ) {
        this.BND = BND;
    }

    public void setBOB( double BOB ) {
        this.BOB = BOB;
    }

    public void setBRL( double BRL ) {
        this.BRL = BRL;
    }

    public void setBSD( double BSD ) {
        this.BSD = BSD;
    }

    public void setBTC( double BTC ) {
        this.BTC = BTC;
    }

    public void setBTN( double BTN ) {
        this.BTN = BTN;
    }

    public void setBWP( double BWP ) {
        this.BWP = BWP;
    }

    public void setBYN( double BYN ) {
        this.BYN = BYN;
    }

    public void setBZD( double BZD ) {
        this.BZD = BZD;
    }

    public void setCAD( double CAD ) {
        this.CAD = CAD;
    }

    public void setCDF( double CDF ) {
        this.CDF = CDF;
    }

    public void setCHF( double CHF ) {
        this.CHF = CHF;
    }

    public void setCLF( double CLF ) {
        this.CLF = CLF;
    }

    public void setCLP( double CLP ) {
        this.CLP = CLP;
    }

    public void setCNH( double CNH ) {
        this.CNH = CNH;
    }

    public void setCNY( double CNY ) {
        this.CNY = CNY;
    }

    public void setCOP( double COP ) {
        this.COP = COP;
    }

    public void setCRC( double CRC ) {
        this.CRC = CRC;
    }

    public void setCUC( double CUC ) {
        this.CUC = CUC;
    }

    public void setCUP( double CUP ) {
        this.CUP = CUP;
    }

    public void setCVE( double CVE ) {
        this.CVE = CVE;
    }

    public void setCZK( double CZK ) {
        this.CZK = CZK;
    }

    public void setDJF( double DJF ) {
        this.DJF = DJF;
    }

    public void setDKK( double DKK ) {
        this.DKK = DKK;
    }

    public void setDOP( double DOP ) {
        this.DOP = DOP;
    }

    public void setDZD( double DZD ) {
        this.DZD = DZD;
    }

    public void setEGP( double EGP ) {
        this.EGP = EGP;
    }

    public void setERN( double ERN ) {
        this.ERN = ERN;
    }

    public void setETB( double ETB ) {
        this.ETB = ETB;
    }

    public void setEUR( double EUR ) {
        this.EUR = EUR;
    }

    public void setFJD( double FJD ) {
        this.FJD = FJD;
    }

    public void setFKP( double FKP ) {
        this.FKP = FKP;
    }

    public void setGBP( double GBP ) {
        this.GBP = GBP;
    }

    public void setGEL( double GEL ) {
        this.GEL = GEL;
    }

    public void setGGP( double GGP ) {
        this.GGP = GGP;
    }

    public void setGHS( double GHS ) {
        this.GHS = GHS;
    }

    public void setGIP( double GIP ) {
        this.GIP = GIP;
    }

    public void setGMD( double GMD ) {
        this.GMD = GMD;
    }

    public void setGNF( double GNF ) {
        this.GNF = GNF;
    }

    public void setGTQ( double GTQ ) {
        this.GTQ = GTQ;
    }

    public void setGYD( double GYD ) {
        this.GYD = GYD;
    }

    public void setHKD( double HKD ) {
        this.HKD = HKD;
    }

    public void setHNL( double HNL ) {
        this.HNL = HNL;
    }

    public void setHRK( double HRK ) {
        this.HRK = HRK;
    }

    public void setHTG( double HTG ) {
        this.HTG = HTG;
    }

    public void setHUF( double HUF ) {
        this.HUF = HUF;
    }

    public void setIDR( double IDR ) {
        this.IDR = IDR;
    }

    public void setILS( double ILS ) {
        this.ILS = ILS;
    }

    public void setIMP( double IMP ) {
        this.IMP = IMP;
    }

    public void setINR( double INR ) {
        this.INR = INR;
    }

    public void setIQD( double IQD ) {
        this.IQD = IQD;
    }

    public void setIRR( double IRR ) {
        this.IRR = IRR;
    }

    public void setISK( double ISK ) {
        this.ISK = ISK;
    }

    public void setJEP( double JEP ) {
        this.JEP = JEP;
    }

    public void setJMD( double JMD ) {
        this.JMD = JMD;
    }

    public void setJOD( double JOD ) {
        this.JOD = JOD;
    }

    public void setJPY( double JPY ) {
        this.JPY = JPY;
    }

    public void setKES( double KES ) {
        this.KES = KES;
    }

    public void setKGS( double KGS ) {
        this.KGS = KGS;
    }

    public void setKHR( double KHR ) {
        this.KHR = KHR;
    }

    public void setKMF( double KMF ) {
        this.KMF = KMF;
    }

    public void setKPW( double KPW ) {
        this.KPW = KPW;
    }

    public void setKRW( double KRW ) {
        this.KRW = KRW;
    }

    public void setKWD( double KWD ) {
        this.KWD = KWD;
    }

    public void setKYD( double KYD ) {
        this.KYD = KYD;
    }

    public void setKZT( double KZT ) {
        this.KZT = KZT;
    }

    public void setLAK( double LAK ) {
        this.LAK = LAK;
    }

    public void setLBP( double LBP ) {
        this.LBP = LBP;
    }

    public void setLKR( double LKR ) {
        this.LKR = LKR;
    }

    public void setLRD( double LRD ) {
        this.LRD = LRD;
    }

    public void setLSL( double LSL ) {
        this.LSL = LSL;
    }

    public void setLYD( double LYD ) {
        this.LYD = LYD;
    }

    public void setMAD( double MAD ) {
        this.MAD = MAD;
    }

    public void setMDL( double MDL ) {
        this.MDL = MDL;
    }

    public void setMGA( double MGA ) {
        this.MGA = MGA;
    }

    public void setMKD( double MKD ) {
        this.MKD = MKD;
    }

    public void setMMK( double MMK ) {
        this.MMK = MMK;
    }

    public void setMNT( double MNT ) {
        this.MNT = MNT;
    }

    public void setMOP( double MOP ) {
        this.MOP = MOP;
    }

    public void setMRO( double MRO ) {
        this.MRO = MRO;
    }

    public void setMRU( double MRU ) {
        this.MRU = MRU;
    }

    public void setMUR( double MUR ) {
        this.MUR = MUR;
    }

    public void setMVR( double MVR ) {
        this.MVR = MVR;
    }

    public void setMWK( double MWK ) {
        this.MWK = MWK;
    }

    public void setMXN( double MXN ) {
        this.MXN = MXN;
    }

    public void setMYR( double MYR ) {
        this.MYR = MYR;
    }

    public void setMZN( double MZN ) {
        this.MZN = MZN;
    }

    public void setNAD( double NAD ) {
        this.NAD = NAD;
    }

    public void setNGN( double NGN ) {
        this.NGN = NGN;
    }

    public void setNIO( double NIO ) {
        this.NIO = NIO;
    }

    public void setNOK( double NOK ) {
        this.NOK = NOK;
    }

    public void setNPR( double NPR ) {
        this.NPR = NPR;
    }

    public void setNZD( double NZD ) {
        this.NZD = NZD;
    }

    public void setOMR( double OMR ) {
        this.OMR = OMR;
    }

    public void setPAB( double PAB ) {
        this.PAB = PAB;
    }

    public void setPEN( double PEN ) {
        this.PEN = PEN;
    }

    public void setPGK( double PGK ) {
        this.PGK = PGK;
    }

    public void setPHP( double PHP ) {
        this.PHP = PHP;
    }

    public void setPKR( double PKR ) {
        this.PKR = PKR;
    }

    public void setPLN( double PLN ) {
        this.PLN = PLN;
    }

    public void setPYG( double PYG ) {
        this.PYG = PYG;
    }

    public void setQAR( double QAR ) {
        this.QAR = QAR;
    }

    public void setRON( double RON ) {
        this.RON = RON;
    }

    public void setRSD( double RSD ) {
        this.RSD = RSD;
    }

    public void setRUB( double RUB ) {
        this.RUB = RUB;
    }

    public void setRWF( double RWF ) {
        this.RWF = RWF;
    }

    public void setSAR( double SAR ) {
        this.SAR = SAR;
    }

    public void setSBD( double SBD ) {
        this.SBD = SBD;
    }

    public void setSCR( double SCR ) {
        this.SCR = SCR;
    }

    public void setSDG( double SDG ) {
        this.SDG = SDG;
    }

    public void setSEK( double SEK ) {
        this.SEK = SEK;
    }

    public void setSGD( double SGD ) {
        this.SGD = SGD;
    }

    public void setSHP( double SHP ) {
        this.SHP = SHP;
    }

    public void setSLL( double SLL ) {
        this.SLL = SLL;
    }

    public void setSOS( double SOS ) {
        this.SOS = SOS;
    }

    public void setSRD( double SRD ) {
        this.SRD = SRD;
    }

    public void setSSP( double SSP ) {
        this.SSP = SSP;
    }

    public void setSTD( double STD ) {
        this.STD = STD;
    }

    public void setSTN( double STN ) {
        this.STN = STN;
    }

    public void setSVC( double SVC ) {
        this.SVC = SVC;
    }

    public void setSYP( double SYP ) {
        this.SYP = SYP;
    }

    public void setSZL( double SZL ) {
        this.SZL = SZL;
    }

    public void setTHB( double THB ) {
        this.THB = THB;
    }

    public void setTJS( double TJS ) {
        this.TJS = TJS;
    }

    public void setTMT( double TMT ) {
        this.TMT = TMT;
    }

    public void setTND( double TND ) {
        this.TND = TND;
    }

    public void setTOP( double TOP ) {
        this.TOP = TOP;
    }

    public void setTRY( double TRY ) {
        this.TRY = TRY;
    }

    public void setTTD( double TTD ) {
        this.TTD = TTD;
    }

    public void setTWD( double TWD ) {
        this.TWD = TWD;
    }

    public void setTZS( double TZS ) {
        this.TZS = TZS;
    }

    public void setUAH( double UAH ) {
        this.UAH = UAH;
    }

    public void setUGX( double UGX ) {
        this.UGX = UGX;
    }

    public void setUSD( double USD ) {
        this.USD = USD;
    }

    public void setUYU( double UYU ) {
        this.UYU = UYU;
    }

    public void setUZS( double UZS ) {
        this.UZS = UZS;
    }

    public void setVEF( double VEF ) {
        this.VEF = VEF;
    }

    public void setVES( double VES ) {
        this.VES = VES;
    }

    public void setVND( double VND ) {
        this.VND = VND;
    }

    public void setVUV( double VUV ) {
        this.VUV = VUV;
    }

    public void setWST( double WST ) {
        this.WST = WST;
    }

    public void setXAF( double XAF ) {
        this.XAF = XAF;
    }

    public void setXAG( double XAG ) {
        this.XAG = XAG;
    }

    public void setXAU( double XAU ) {
        this.XAU = XAU;
    }

    public void setXCD( double XCD ) {
        this.XCD = XCD;
    }

    public void setXDR( double XDR ) {
        this.XDR = XDR;
    }

    public void setXOF( double XOF ) {
        this.XOF = XOF;
    }

    public void setXPD( double XPD ) {
        this.XPD = XPD;
    }

    public void setXPF( double XPF ) {
        this.XPF = XPF;
    }

    public void setXPT( double XPT ) {
        this.XPT = XPT;
    }

    public void setYER( double YER ) {
        this.YER = YER;
    }

    public void setZAR( double ZAR ) {
        this.ZAR = ZAR;
    }

    public void setZMW( double ZMW ) {
        this.ZMW = ZMW;
    }

    public void setZWL( double ZWL ) {
        this.ZWL = ZWL;
    }
}