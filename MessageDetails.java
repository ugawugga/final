package com.example.danny.afinal;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import static com.example.danny.afinal.ChatWindow.db;
import static com.example.danny.afinal.R.id.fragLayout;
//import static com.example.danny.afinal.R.id.fragMessage;

public class MessageDetails extends Activity {
    FrameLayout frag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        frag=(FrameLayout) findViewById(R.id.fragLayout);
    }
    public static class MessageFragment extends Fragment {
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";
        private static final String ARG_PARAM3 = "param3";
        private static final String ARG_PARAM4 = "param4";
        TextView fragMess;
        TextView fragI;

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;
        private String mParam3;
        private String mParam4;


        public static MessageFragment newInstance(String param1, String param2, String param3, String param4) {
            MessageFragment fragment = new MessageFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            args.putString(ARG_PARAM3, param3);
            args.putString(ARG_PARAM4, param4);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
                mParam3 = getArguments().getString(ARG_PARAM3);
                mParam4 = getArguments().getString(ARG_PARAM4);

            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_blank, container, false);

        }
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState){
            EditText date = (EditText) getActivity().findViewById(R.id.dater2);
            EditText litres = (EditText) getActivity().findViewById(R.id.litres3);
            EditText cost = (EditText) getActivity().findViewById(R.id.cost3);
            EditText kilo = (EditText) getActivity().findViewById(R.id.kilo3);
            date.setText(mParam1);
            litres.setText(mParam2);
            cost.setText(mParam3);
            kilo.setText(mParam4);
            // Displaying the user details on the screen
            //fragMess.setText(mParam1);
            //fragI = (TextView) getActivity().findViewById(R.id.fragID);
            //fragI.setText(mParam2);
            System.out.println(mParam2);
            Button getDelete = (Button) getActivity().findViewById(R.id.delete);
            getDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    System.out.println("ugawugga");
                    ChatWindow.doStuff();

                    Intent intent = new Intent(getActivity(), ChatWindow.class);
                    startActivity(intent);


                }



            });
            Button getUpdate = (Button) getActivity().findViewById(R.id.update);
            getUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText date = (EditText) getActivity().findViewById(R.id.dater2);
                    EditText litres = (EditText) getActivity().findViewById(R.id.litres3);
                    EditText cost = (EditText) getActivity().findViewById(R.id.cost3);
                    EditText kilo = (EditText) getActivity().findViewById(R.id.kilo3);

                    System.out.println("ugawugga2");
                    mParam1=date.getText().toString();
                    mParam2=litres.getText().toString();
                    mParam3=cost.getText().toString();
                    mParam4=kilo.getText().toString();

                    ContentValues cv = new ContentValues();
                    //c.put(ChatDatabaseHelper.KEY_ID, ChatWindow.deleteInt);
                    cv.put(ChatDatabaseHelper.KEY_MESSAGE, "blarg");
                    cv.put(ChatDatabaseHelper.DATE, mParam1);
                    cv.put(ChatDatabaseHelper.LITRES, mParam2);
                    cv.put(ChatDatabaseHelper.COST, mParam3);
                    cv.put(ChatDatabaseHelper.DISTANCE, mParam4);


// this will insert if record is new, update otherwise
                    //db.insertWithOnConflict(ChatDatabaseHelper.TABLE_NAME, null, c, SQLiteDatabase.CONFLICT_IGNORE);
                    db.update(ChatDatabaseHelper.TABLE_NAME, cv, ChatDatabaseHelper.KEY_ID+"=" + ((ChatWindow.c.getInt(ChatWindow.c.getColumnIndex(ChatDatabaseHelper.KEY_ID)))), null);
                    ChatWindow.doStuff2();

                    Intent intent = new Intent(getActivity(), ChatWindow.class);
                    startActivity(intent);


                }



            });

        }
    }
}
