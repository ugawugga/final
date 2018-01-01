package com.example.danny.afinal;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.danny.afinal.ChatDatabaseHelper.COST;
import static com.example.danny.afinal.ChatDatabaseHelper.DISTANCE;
import static com.example.danny.afinal.ChatDatabaseHelper.KEY_ID;
import static com.example.danny.afinal.ChatDatabaseHelper.KEY_MESSAGE;
import static com.example.danny.afinal.ChatDatabaseHelper.LITRES;
import static com.example.danny.afinal.ChatDatabaseHelper.TABLE_NAME;
import static com.example.danny.afinal.R.id.bleh;
import static com.example.danny.afinal.R.id.cost;
import static com.example.danny.afinal.R.id.fragLayout;
//import static com.example.danny.afinal.R.id.fragMessage;
import static com.example.danny.afinal.R.id.message_text;

public class ChatWindow extends AppCompatActivity {


    public static Cursor c;
    ListView getlistView;
    EditText getchatBox;
    Button getSend;
    String messaged;
    static SQLiteDatabase db;
    static ArrayList<String[]> messages = new ArrayList<String[]>();

    int index;
    String details;
    String iden;
    String gas;
    String cost;
    String distance;
    String date;
    FrameLayout getFrag;
    Fragment mFragment;
    Boolean isTablet;
    Button getDelete;
    static int deleteInt;
    static ChatAdapter messageAdapter;


    //TextView fDetails=(TextView) findViewById(R.id.fragMessage);

    public void changeFragmentTextView(String s) {

        //((TextView) mFragment.getView().findViewById(R.id.fragMessage)).setText(s);
    }


    @Override
    public void onBackPressed() {
        if(getFrag==null) {
            messages.clear();
            Intent intent = new Intent(ChatWindow.this, ChatWindow.class);

            startActivity(intent);
            //your method call

        }else {
            Intent intent = new Intent(ChatWindow.this, StartActivity.class);


            super.onBackPressed();
            startActivity(intent);


            finish();
        }

    }
    public static void doStuff(){
        System.out.println("booyah");
        messages.clear();
        //messages.remove(deleteInt);
        //db.execSQL("DELETE FROM "+TABLE_NAME+" where _ID=" +deleteInt);


        db.delete(TABLE_NAME,KEY_ID + "=" + (c.getInt(c.getColumnIndex(ChatDatabaseHelper.KEY_ID))), null);
        c = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        messageAdapter.notifyDataSetChanged();




    }
    public static void doStuff2(){
        System.out.println("booyah");
        messages.clear();
        //messages.remove(deleteInt);
        //db.execSQL("DELETE FROM "+TABLE_NAME+" where _ID=" +deleteInt);


        //db.delete(TABLE_NAME,KEY_ID + "=" + (c.getInt(c.getColumnIndex(ChatDatabaseHelper.KEY_ID))), null);
        c = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        messageAdapter.notifyDataSetChanged();




    }
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        getlistView = (ListView) findViewById(R.id.chatWindow);
        //getchatBox = (EditText) findViewById(R.id.chatBox);
        getFrag = (FrameLayout) findViewById(R.id.fragLayout);
        messageAdapter =new ChatAdapter( this );
        getlistView.setAdapter (messageAdapter);
        Toolbar myToolbar=(Toolbar)findViewById(R.id.toolbar2);
        myToolbar.setTitle("Auto Act.");
        setSupportActionBar(myToolbar);

        //final TextView fDetails=(TextView) findViewById(R.id.fragMessage);
        /**
         * lab5 stuff begins
         */
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        ChatDatabaseHelper data=new ChatDatabaseHelper(this);


        db=data.getWritableDatabase() ;

        //ContentValues content = new ContentValues();

        //content.put(KEY_MESSAGE ,"yunoquery");

        //db.insert(TABLE_NAME, null, content);
        //System.out.println(doesTableExist(db,TABLE_NAME));
        //System.out.println(DatabaseUtils.queryNumEntries(db, DATABASE_NAME));
        //System.out.println(DatabaseUtils.queryNumEntries(db, TABLE_NAME));

        getlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {

                //details = getlistView.getSelectedItem().toString();
                //iden=getlistView.getSelectedItemPosition();
                deleteInt=position;

                c.moveToPosition(position);
                System.out.println("boogah");
                details=c.getString(c.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE));
                iden= Integer.toString(c.getInt(c.getColumnIndex(ChatDatabaseHelper.KEY_ID)));
                date=c.getString(c.getColumnIndex(ChatDatabaseHelper.DATE));
                gas=Integer.toString(c.getInt(c.getColumnIndex(ChatDatabaseHelper.LITRES)));
                cost=c.getString(c.getColumnIndex(ChatDatabaseHelper.COST));
                distance=c.getString(c.getColumnIndex(ChatDatabaseHelper.DISTANCE));
                System.out.println(gas+"blargh");



                System.out.println("boogah2");
                System.out.println(details);

                if(getFrag!=null){
                    mFragment = new MessageDetails.MessageFragment().newInstance(date,gas,cost,distance);
                    FragmentTransaction ft = getFragmentManager().beginTransaction()
                            .replace(R.id.fragLayout, 	mFragment).addToBackStack(null);


                         // add    Fragment
                    ft.commit();

                   // changeFragmentTextView(details);
                    //System.out.println(fragMessage);
                    //fDetails.setText(details);

                } else {
                    View v = findViewById(R.id.bleh);
                    v.setVisibility(View.GONE);
                    mFragment = new MessageDetails.MessageFragment().newInstance(date,gas,cost,distance);
                    FragmentTransaction ft = getFragmentManager().beginTransaction()
                            .replace(R.id.blargh, 	mFragment).addToBackStack(null);
                    ft.commit();


                }


            }
        });







        //Cursor c=db.rawQuery("select * from "+TABLE_NAME, new String[] { KEY_MESSAGE });
        //Cursor c = db.rawQuery("SELECT "+KEY_MESSAGE +" from tbl WHERE "+KEY_MESSAGE+" = ?", new String[] {String.valueOf(KEY_MESSAGE)});
         c = db.rawQuery("SELECT * FROM " + data.TABLE_NAME,null);
        //Cursor c = db.query(false, TABLE_NAME, new String[] {KEY_MESSAGE }, KEY_MESSAGE+ " LIKE ?", null, null, null, null,null);
        //db.query (TABLE_NAME, KEY_MESSAGE, null , null, null, null, null);

        //Cursor c = db.rawQuery("SELECT "+KEY_MESSAGE+" from "+TABLE_NAME +"WHERE "+KEY_MESSAGE+" =? ", new String[] {KEY_MESSAGE});
        System.out.println(c.getCount());
        //c.moveToFirst();
        //while (!c.isAfterLast())
        //    c.moveToNext();

        //int colIndex = c.getColumnIndex(KEY_MESSAGE);

        //    for (int i = 0; i < c.getCount(); i++) {
                //System.out.println(DatabaseUtils.queryNumEntries(db, data.DATABASE_NAME));
        //        messages.add(c.getString(colIndex));
        //        c.moveToNext();
        //    }

        if (c!=null) {
            c.moveToFirst();
            //while (!c.isAfterLast()) {
            for (int i = 0; i < c.getCount(); i++) {
                String[] cars=new String[4];
                cars[0]=(c.getString(c.getColumnIndex(ChatDatabaseHelper.DATE)));
                cars[1]=(c.getString(c.getColumnIndex(ChatDatabaseHelper.LITRES)));
                cars[2]=(c.getString(c.getColumnIndex(ChatDatabaseHelper.COST)));
                cars[3]=(c.getString(c.getColumnIndex(ChatDatabaseHelper.DISTANCE)));
                //colIndex++;
                messages.add(cars);
                Log.i("ChatWindow", "SQL MESSAGE:" + c.getString(c.getColumnIndex(ChatDatabaseHelper.DATE))+"boooy");

                if (i<c.getCount())
                    c.moveToNext();
            }
        }
            messageAdapter.notifyDataSetChanged();
            //getchatBox.setText("");
                Log.i("ChatWindow", "Cursor’s  column count =" + c.getColumnCount());

            Log.i("ChatWindow", "Cursor’s  column count =" + c.getColumnCount());
            //System.out.println(c.getCount());
            //c.close();


















        getSend = (Button) findViewById(R.id.send);
        final TextView gas=(TextView) findViewById(R.id.litres2);
        //TextView date=(TextView) findViewById(R.id.date2);
        final TextView kilometers= (TextView) findViewById(R.id.kilo2);
        final TextView price=(TextView) findViewById(R.id.cost2);
        getSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] tempCars=new String[4];
                tempCars[1]=gas.getText().toString();
                tempCars[2]=price.getText().toString();
                tempCars[3]=kilometers.getText().toString();


                //System.out.println(messaged);
                //System.out.println(messages);
                ContentValues content=new ContentValues();

                messages.add(tempCars);
                content.put(LITRES,tempCars[1]);
                content.put(COST,tempCars[2]);
                content.put(DISTANCE,tempCars[3]);
                db.insert(TABLE_NAME,null,content);



                //messageAdapter.clear();
                //messageAdapter.addAll(messages);

                c = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
                messageAdapter.notifyDataSetChanged();
                gas.setText("");
                price.setText("");
                kilometers.setText("");
                Intent intent = new Intent(ChatWindow.this, ChatWindow.class);
                messages.clear();
                startActivity(intent);


                //SharedPreferences pref = getSharedPreferences("pref", LoginActivity.MODE_PRIVATE);
                //SharedPreferences.Editor editor = pref.edit();
                //editor.putString("pref", textView.getText().toString());
                //editor.commit();




            }
        });





    }        @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("LoginActivity", "onDestroy");
        c.close();
        db.close();
        messages.clear();
    }
    public boolean doesTableExist(SQLiteDatabase db, String name) {
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + TABLE_NAME + "'", null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                //cursor.close();
                return true;
            }
            //cursor.close();
        }
        return false;
    }
    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx) {
            super(ctx, 0);}
        public int getCount() {
            return messages.size();
        }
        public long getItemId(int position){


            return deleteInt+1;
        }


        public String getDate(int position) {
            return messages.get(position)[0];
        }
        public String getLitres(int position) {
            return messages.get(position)[1];
        }
        public String getCost(int position) {
            return messages.get(position)[2];
        }
        public String getKilo(int position) {
            return messages.get(position)[3];
        }


        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;

                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            TextView message = (TextView)result.findViewById(R.id.message_text);
            TextView date= (TextView)result.findViewById(R.id.dater);
            TextView litres= (TextView)result.findViewById(R.id.litres);
            TextView cost= (TextView)result.findViewById(R.id.cost);
            TextView kilo= (TextView)result.findViewById(R.id.kilo);
            date.setText(   getDate(position)  );
            litres.setText(   getLitres(position)  );
            cost.setText(   getCost(position)  );
            kilo.setText(   getKilo(position)  );// get the string at position
            return result;





        }
        // @param db, readable database from SQLiteOpenHelper







    }










}
