

public enum EnumDemo3 {

    FIRST {
        @Override
        public String getInfo() {
            return "FIRST";
        }
    },
    SEC {
        @Override
        public String getInfo() {
            return "SED";
        }
    };


    public abstract String getInfo();


    public static void main(String[] args) {
        System.out.println(EnumDemo3.FIRST.getInfo());
        System.out.println(EnumDemo3.SEC.getInfo());
    }


}