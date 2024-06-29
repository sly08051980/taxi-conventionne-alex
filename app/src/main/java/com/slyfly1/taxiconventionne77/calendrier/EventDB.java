package com.slyfly1.taxiconventionne77.calendrier;

import android.provider.BaseColumns;

public class EventDB {


    private EventDB() {}

    public static final class Event implements BaseColumns {
        public static final String TABLE_NAME = "EVENTS";
        public static final String COLUMN_ID = "ID";
        public static final String COLUMN_NAME = "NAME";
        public static final String COLUMN_START = "START_DATE";
        public static final String COLUMN_END = "END_DATE";
        public static final String COLUMN_SERI = "SERI_NO";
        public static final String COLUMN_SERI_TYPE = "SERI_TYPE";
        public static final String COLUMN_LOCATION = "LOCATION";
        public static final String COLUMN_LOCATION_LINK = "LOCATION_LINK";
        public static final String COLUMN_ARRIVER="ARRIVER";

        public static final String COLUMN_PRENOM="PRENOM";
        public static final String COLUMN_CPAM="CPAM";
        public static final String COLUMN_TELEPHONE="TELEPHONE";
        public static final String COLUMN_MONTANT="MONTANT";
        public static final String COLUMN_MODEPAYEMENT="MODEPAYEMENT";
        public static final String COLUMN_NOTE = "NOTE";

        public static final String COLUMN_CONFIRMERCPAM = "CONFIRMERCPAM";

        public static final String REMINDER_TABLE_NAME = "REMINDERS";
        public static final String REMINDER_COLUMN_ID = "ID";
        public static final String REMINDER_COLUMN_EID = "EVENT_ID";
        public static final String REMINDER_COLUMN_DATE = "DATE";
    }
}
