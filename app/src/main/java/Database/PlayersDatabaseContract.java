package Database;

public class PlayersDatabaseContract {

    private PlayersDatabaseContract() {
    }

    public static final class PlayerEntry {

        public static final String TABLE_NAME = "playerStat";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_PLAYER = "name";
        public static final String COLUMN_HEALTH = "health";
        public static final String COLUMN_EXPERIENCE = "experience";

        public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_PLAYER + " TEXT, " +
                COLUMN_HEALTH + " INTEGER, " +
                COLUMN_EXPERIENCE + " INTEGER )";
    }
}



