// 编译器帮助我们生成了一个Day类，改类继承自java.lang.Enum对象
// 编译器还帮我们生成了7个Day类型的实例对象分别对应枚举中定义的7个日期
// 这也充分说明了使用tnum定义的Day类型中的每种日期枚举常量也是Day实例对象；
// 编译器还为我们生成了两个静态方法，分别是values()和valueOf()
// 
// 使用关键字enum定义的枚举类型，在编译后，也将转化成一个实实在在的类；
//final class Day extends Enum {
//
//    public static Day[] values() {
//        return (Day[])$VALUES.clone();
//    }
//
//
//    // 私有构造函数
//    private Day(String s, int i) {
//        super(s, i);
//    }
//
//    // 所定义的7种类型
//    public static final Day MONDAY;
//    public static final Day TUESDAY;
//    public static final Day WEDNESDAY;
//    public static final Day THURSDAY;
//    public static final Day FRIDAY;
//    public static final Day SATURDAY;
//    public static final Day SUNDAY;
//    private static final Day $VALUES[];
//
//    static {
//        MONDAY = new Day("MONDAY", 0);
//        TUESDAY = new Day("TUESDAY", 1);
//        WEDNESDAY = new Day("WEDNESDAY", 2);
//        THURSDAY = new Day("THURSDAY", 3);
//        FRIDAY = new Day("FRIDAY", 4);
//        SATURDAY = new Day("SATURDAY", 5);
//        SUNDAY = new Day("SUNDAY", 6);
//
//        $VALUES = (new Day[]{
//           MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
//        });
//    }
//
//}
