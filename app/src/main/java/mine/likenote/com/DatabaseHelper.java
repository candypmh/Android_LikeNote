package mine.likenote.com;

import static java.sql.DriverManager.println;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    //전역변수
    private Context context;
    private static final String DATABASE_NAME ="LikeNote.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME="Character";
    private static final String COLUMN_CHARINDEX = "charIndex";
    private static final String COLUMN_CHARNAME="charName";
    private static final String COLUMN_CHARSEX="charSex";
    private static final String COLUMN_CHARIMG="charImg";
    private static final String COLUMN_CHARAGE="charAge";
    private static final String COLUMN_CHARCM="charCm";
    private static final String COLUMN_CHARKG="charKg";
    private static final String COLUMN_CHARCONTENT="charContent";
    private static final String COLUMN_CHARTAG="charTag";
    private static final String COLUMN_CHARCREATEDATE="charCreateDate";
    private static final String COLUMN_CHARLIKECHK="charLikeChk";
    private static final String COLUMN_CHARSTAR="charStar";


    //생성자
    public DatabaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
/*
    public DatabaseHelper(@Nullable Fragment_Add context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
*/





    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        println("onCreate 호출");

        //테이블 없을 시 생성
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +
                " (" + COLUMN_CHARINDEX + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CHARNAME + " TEXT NOT NULL, "+
                COLUMN_CHARSEX + " TEXT, "+
                COLUMN_CHARIMG + " BLOB, "+
                COLUMN_CHARAGE + " TEXT, " +
                COLUMN_CHARCM + " TEXT, " +
                COLUMN_CHARKG + " TEXT, " +
                COLUMN_CHARCONTENT + " TEXT, " +
                COLUMN_CHARTAG + " TEXT, " +
                COLUMN_CHARCREATEDATE + " TEXT NOT NULL, " +
                COLUMN_CHARLIKECHK + " TEXT NOT NULL, " +
                COLUMN_CHARSTAR + " INTEGER)";
        sqLiteDatabase.execSQL(sqlCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        println("onUpgrade 호출");
        // 테이블 삭제 후 다시 생성
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void addCharacter(String charName, String charSex, String charImg, String charAge, String charCm, String charKg,
                      String charContent, String charTag, String charCreateDate, boolean charLikeChk, int charStar){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_CHARNAME, charName);
        contentValues.put(COLUMN_CHARSEX, charSex);
        contentValues.put(COLUMN_CHARIMG, charImg);
        contentValues.put(COLUMN_CHARAGE, charAge);
        contentValues.put(COLUMN_CHARCM, charCm);
        contentValues.put(COLUMN_CHARKG, charKg);
        contentValues.put(COLUMN_CHARCONTENT, charContent);
        contentValues.put(COLUMN_CHARTAG, charTag);
        contentValues.put(COLUMN_CHARCREATEDATE, charCreateDate);
        contentValues.put(COLUMN_CHARLIKECHK, charLikeChk);
        contentValues.put(COLUMN_CHARSTAR, charStar);

       long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
       
       if(result == -1){ //토스트 메세지 성공&실패
           Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
       }else{
           Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
       }
    }




    /*
    @Override
    public void onOpen(SQLiteDatabase sqLiteDatabase) {
        super.onOpen(sqLiteDatabase);
        println("onOpen 호출");
    }
*/

    //DB에서 모든 Character가져오기
    public List<CharacterDTO> getAllCharacters(){
        List<CharacterDTO> characterDTOList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase(); //읽어와서 db에 넣기
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do {
                @SuppressLint("Range") int charIndex = cursor.getInt(cursor.getColumnIndex(COLUMN_CHARINDEX));
                @SuppressLint("Range") String charName = cursor.getString(cursor.getColumnIndex(COLUMN_CHARNAME));
                @SuppressLint("Range") String charSex = cursor.getString(cursor.getColumnIndex(COLUMN_CHARSEX));
                @SuppressLint("Range") int charImg = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHARIMG)));
                @SuppressLint("Range") String charAge = cursor.getString(cursor.getColumnIndex(COLUMN_CHARAGE));
                @SuppressLint("Range") String charCm = cursor.getString(cursor.getColumnIndex(COLUMN_CHARCM));
                @SuppressLint("Range") String charKg = cursor.getString(cursor.getColumnIndex(COLUMN_CHARKG));
                @SuppressLint("Range") String charContent = cursor.getString(cursor.getColumnIndex(COLUMN_CHARCONTENT));
                @SuppressLint("Range") String charTag = cursor.getString(cursor.getColumnIndex(COLUMN_CHARTAG));
                @SuppressLint("Range") String charCreateDate = cursor.getString(cursor.getColumnIndex(COLUMN_CHARCREATEDATE));
                @SuppressLint("Range") String charLikeChk = cursor.getString(cursor.getColumnIndex(COLUMN_CHARLIKECHK));
                @SuppressLint("Range") String charStar = cursor.getString(cursor.getColumnIndex(COLUMN_CHARSTAR));

                CharacterDTO characterDTO = new CharacterDTO(charIndex, charName, charSex, charImg, charAge, charCm, charKg, charContent, charTag, charLikeChk, charCreateDate, charStar);



                characterDTOList.add(characterDTO);
            }while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

    return characterDTOList;
    }





}

