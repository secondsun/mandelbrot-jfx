public final class ColorEscapeMap {
    public static final int[] rgbColorEscapeMap = new int[101];

    static {
        rgbColorEscapeMap[0]=-16776962;
        rgbColorEscapeMap[1]=-16776969;
        rgbColorEscapeMap[2]=-16776976;
        rgbColorEscapeMap[3]=-16776984;
        rgbColorEscapeMap[4]=-16776992;
        rgbColorEscapeMap[5]=-16777000;
        rgbColorEscapeMap[6]=-16777007;
        rgbColorEscapeMap[7]=-16777015;
        rgbColorEscapeMap[8]=-16777023;
        rgbColorEscapeMap[9]=-16777031;
        rgbColorEscapeMap[10]=-16777038;
        rgbColorEscapeMap[11]=-16777046;
        rgbColorEscapeMap[12]=-16777054;
        rgbColorEscapeMap[13]=-16777061;
        rgbColorEscapeMap[14]=-16777069;
        rgbColorEscapeMap[15]=-16777077;
        rgbColorEscapeMap[16]=-16777085;
        rgbColorEscapeMap[17]=-16777092;
        rgbColorEscapeMap[18]=-16777100;
        rgbColorEscapeMap[19]=-16777108;
        rgbColorEscapeMap[20]=-16777116;
        rgbColorEscapeMap[21]=-16777123;
        rgbColorEscapeMap[22]=-16777131;
        rgbColorEscapeMap[23]=-16777139;
        rgbColorEscapeMap[24]=-16777146;
        rgbColorEscapeMap[25]=-16777154;
        rgbColorEscapeMap[26]=-16777162;
        rgbColorEscapeMap[27]=-16777170;
        rgbColorEscapeMap[28]=-16777177;
        rgbColorEscapeMap[29]=-16777185;
        rgbColorEscapeMap[30]=-16777193;
        rgbColorEscapeMap[31]=-16777201;
        rgbColorEscapeMap[32]=-16777208;
        rgbColorEscapeMap[33]=-16777216;
        rgbColorEscapeMap[34]=-16713984;
        rgbColorEscapeMap[35]=-16715776;
        rgbColorEscapeMap[36]=-16717824;
        rgbColorEscapeMap[37]=-16719872;
        rgbColorEscapeMap[38]=-16721920;
        rgbColorEscapeMap[39]=-16723712;
        rgbColorEscapeMap[40]=-16725760;
        rgbColorEscapeMap[41]=-16727808;
        rgbColorEscapeMap[42]=-16729856;
        rgbColorEscapeMap[43]=-16731648;
        rgbColorEscapeMap[44]=-16733696;
        rgbColorEscapeMap[45]=-16735744;
        rgbColorEscapeMap[46]=-16737536;
        rgbColorEscapeMap[47]=-16739584;
        rgbColorEscapeMap[48]=-16741632;
        rgbColorEscapeMap[49]=-16743680;
        rgbColorEscapeMap[50]=-16745472;
        rgbColorEscapeMap[51]=-16747520;
        rgbColorEscapeMap[52]=-16749568;
        rgbColorEscapeMap[53]=-16751616;
        rgbColorEscapeMap[54]=-16753408;
        rgbColorEscapeMap[55]=-16755456;
        rgbColorEscapeMap[56]=-16757504;
        rgbColorEscapeMap[57]=-16759296;
        rgbColorEscapeMap[58]=-16761344;
        rgbColorEscapeMap[59]=-16763392;
        rgbColorEscapeMap[60]=-16765440;
        rgbColorEscapeMap[61]=-16767232;
        rgbColorEscapeMap[62]=-16769280;
        rgbColorEscapeMap[63]=-16771328;
        rgbColorEscapeMap[64]=-16773376;
        rgbColorEscapeMap[65]=-16775168;
        rgbColorEscapeMap[66]=-16777216;
        rgbColorEscapeMap[67]=-131072;
        rgbColorEscapeMap[68]=-589824;
        rgbColorEscapeMap[69]=-1048576;
        rgbColorEscapeMap[70]=-1572864;
        rgbColorEscapeMap[71]=-2097152;
        rgbColorEscapeMap[72]=-2621440;
        rgbColorEscapeMap[73]=-3080192;
        rgbColorEscapeMap[74]=-3604480;
        rgbColorEscapeMap[75]=-4128768;
        rgbColorEscapeMap[76]=-4653056;
        rgbColorEscapeMap[77]=-5111808;
        rgbColorEscapeMap[78]=-5636096;
        rgbColorEscapeMap[79]=-6160384;
        rgbColorEscapeMap[80]=-6619136;
        rgbColorEscapeMap[81]=-7143424;
        rgbColorEscapeMap[82]=-7667712;
        rgbColorEscapeMap[83]=-8192000;
        rgbColorEscapeMap[84]=-8650752;
        rgbColorEscapeMap[85]=-9175040;
        rgbColorEscapeMap[86]=-9699328;
        rgbColorEscapeMap[87]=-10223616;
        rgbColorEscapeMap[88]=-10682368;
        rgbColorEscapeMap[89]=-11206656;
        rgbColorEscapeMap[90]=-11730944;
        rgbColorEscapeMap[91]=-12189696;
        rgbColorEscapeMap[92]=-12713984;
        rgbColorEscapeMap[93]=-13238272;
        rgbColorEscapeMap[94]=-13762560;
        rgbColorEscapeMap[95]=-14221312;
        rgbColorEscapeMap[96]=-14745600;
        rgbColorEscapeMap[97]=-15269888;
        rgbColorEscapeMap[98]=-15794176;
        rgbColorEscapeMap[99]=-16252928;
        rgbColorEscapeMap[100]=0;
    }


    /*
    * private void buildColorMap() {
        double coloStep = 1 / iterations;
        rgbColorEscapeMap = new int[iterations + 1];
        rgbColorEscapeMap[iterations] = Integer.MAX_VALUE;//white
        for (int i = 0; i < iterations; i++) {
            rgbColorEscapeMap[i] = 0;
            int firstThirdEnds = Math.floorDiv(iterations, 3);
            int secondThirdBegins = firstThirdEnds + 1;
            int thirdThirdBegins = 2 * firstThirdEnds + 1;

            int r = 0;
            int g = 0;
            int b = 0;

            if (i < secondThirdBegins) {
                b = (int) Math.round(255 - 255 * ((double) i / (double) firstThirdEnds));
                b = Math.max(0, b);
                b = Math.min(254, b);
            } else if (i < thirdThirdBegins) {
                g = (int) Math.round(255 - 255 * ((double) (i - firstThirdEnds) / (double) firstThirdEnds));
                g = Math.max(0, g);
                g = Math.min(254, g);
            } else {
                r = (int) Math.round(255 - 255 * ((double) (i - thirdThirdBegins) / (double) firstThirdEnds));
                r = Math.max(0, r);
                r = Math.min(254, r);
            }

            rgbColorEscapeMap[i] = new Color(Math.min(254, r), Math.min(254, g), Math.min(254, b)).getRGB();
            System.out.println(String.format("rgbColorEscapeMap[%d]=%d;",i,rgbColorEscapeMap[i] ));
        }
    }*/
}
