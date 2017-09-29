package com.itstep.javafall2016.dao.impl.jdbc;

/**
 * Created by strazhko on 20.07.2017.
 */
public class DBSchema {
    public static abstract class STUDENTS {
        public final static String ID = "id";
        public final static String ID_PASSPORT = "id_passport";
        public final static String DATE = "date";

        public static String name() {
            return "students";
        }
    }

    public static abstract class PASSPORTS {
        public final static String ID = "id";
        public final static String FIRST_NAME = "first_name";
        public final static String LAST_NAME = "last_name";

        public static String name() {
            return "passports";
        }
    }

}
