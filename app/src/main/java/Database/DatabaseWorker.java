package Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import Player.Player;

public class DatabaseWorker {

    private  SQLiteDatabase database;
    PlayersDatabaseContract.PlayerEntry entry = new PlayersDatabaseContract.PlayerEntry();



    public DatabaseWorker(SQLiteDatabase database) {
        this.database = database;
    }

    public long createNewPlayer(Integer id, String name, int health, int experience) {

        ContentValues contentValues = new ContentValues();

        if (id != null)
            contentValues.put(entry.COLUMN_ID, id);

        contentValues.put(entry.COLUMN_PLAYER, name);
        contentValues.put(entry.COLUMN_HEALTH, health);
        contentValues.put(entry.COLUMN_EXPERIENCE, experience);

        long newPlayerID = database.insert(entry.TABLE_NAME, null, contentValues);

        return newPlayerID;

    }

    public List<Player> SelectPlayer() {

        String[] columns = new String[]{
                entry.COLUMN_ID,
                entry.COLUMN_PLAYER,
                entry.COLUMN_HEALTH,
                entry.COLUMN_EXPERIENCE};

        Cursor cursor = database.query(entry.TABLE_NAME, columns, null, null, null, null, null);

        List<Player> players = new ArrayList<>();

        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(entry.COLUMN_ID);
            int id = cursor.getInt(idIndex);

            int playerIndex = cursor.getColumnIndex(entry.COLUMN_PLAYER);
            String name = cursor.getString(playerIndex);


            int experienceIndex = cursor.getColumnIndex(entry.COLUMN_EXPERIENCE);
            int experience = cursor.getInt(experienceIndex);


            Player player = new Player(id, name,  experience);
            players.add(player);
        }

        return players;
    }


    public  List<Player> checkPlayer(String[] newPlayer) {

        String[] columns = new String[]{
                entry.COLUMN_ID,
                entry.COLUMN_PLAYER,
                entry.COLUMN_HEALTH,
                entry.COLUMN_EXPERIENCE};

        Cursor cursor = database.query(entry.TABLE_NAME, columns,  entry.COLUMN_PLAYER + "=?", newPlayer, null, null, null);

        List<Player> players = new ArrayList<>();

        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(entry.COLUMN_ID);
            int id = cursor.getInt(idIndex);

            int playerIndex = cursor.getColumnIndex(entry.COLUMN_PLAYER);
            String name = cursor.getString(playerIndex);


            int experienceIndex = cursor.getColumnIndex(entry.COLUMN_EXPERIENCE);
            int experience = cursor.getInt(experienceIndex);


            Player player = new Player(id, name, experience);
            players.add(player);

        }
        return players;
    }


    public void update(int health, int experience, String[] id) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(entry.COLUMN_HEALTH, health);
        contentValues.put(entry.COLUMN_EXPERIENCE, experience);

        database.update(entry.TABLE_NAME, contentValues, entry.COLUMN_ID + "=?",id );
    }

    public void delete(int playerId) {

        String[] id = {String.valueOf(playerId)};

        database.delete(entry.TABLE_NAME, entry.COLUMN_ID + "=?",id);
    }
}


