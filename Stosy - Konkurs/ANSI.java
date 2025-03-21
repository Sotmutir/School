public class ANSI {
    private final static int COLOR = 0;
    private final static int STYLE = 1;

    public static final ANSI BLACK = new ANSI("30");
    public static final ANSI DARK_BLUE = new ANSI("34");
    public static final ANSI DARK_GREEN = new ANSI("32");
    public static final ANSI DARK_AQUA = new ANSI("36");
    public static final ANSI DARK_RED = new ANSI("31");
    public static final ANSI DARK_PURPLE = new ANSI("35");
    public static final ANSI GOLD = new ANSI("33");
    public static final ANSI GRAY = new ANSI("37");
    public static final ANSI DARK_GRAY = new ANSI("90");
    public static final ANSI BLUE = new ANSI("94");
    public static final ANSI GREEN = new ANSI("92");
    public static final ANSI AQUA = new ANSI("96");
    public static final ANSI RED = new ANSI("91");
    public static final ANSI LIGHT_PURPLE = new ANSI("95");
    public static final ANSI YELLOW = new ANSI("93");
    public static final ANSI WHITE = new ANSI("97");


    public static final ANSI RESET = new ANSI("0", STYLE);
    public static final ANSI BOLD = new ANSI("1", STYLE);
    public static final ANSI STRIKETHROUGH = new ANSI("9", STYLE);
    public static final ANSI UNDERLINE = new ANSI("4", STYLE);
    public static final ANSI ITALIC = new ANSI("3", STYLE);


    private String ansi;
    private int type;

    private ANSI(String ansi, int type) {
        this.ansi = ansi;
        this.type = type;
    }
    private ANSI(String ansi) {
        this.ansi = ansi;
        this.type = COLOR;
    }



    public static String use(ANSI... styles) {
        String accumulator = "\u001B[";
        boolean isFirst = true;

        for(ANSI a : styles) {
            if(!isFirst) accumulator += ";";
            else isFirst = false;

            accumulator += a.ansi;
        }
        accumulator += "m";

        return accumulator;
    }



    public static void println(String x) {
        System.out.println(x + use(ANSI.RESET));
    }
    public static void print(String x) {
        System.out.print(x + use(ANSI.RESET));
    }
}