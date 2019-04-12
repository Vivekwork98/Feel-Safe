package com.example.feelsafe;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    static final int PICK_CONTACT = 1;
    Button button;
    private ArrayList<String> names = new ArrayList<>(50);
    private ArrayList<String> numbers = new ArrayList<>(50);
    String cur_num;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myDb = new DatabaseHelper(this);

        button =  findViewById(R.id.btnAddContacts);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });
    }

    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        switch (reqCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                        String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        try {
                            if (hasPhone.equalsIgnoreCase("1")) {
                                Cursor phones = getContentResolver().query(
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                        null, null);
                                phones.moveToFirst();
                                String cNumber = phones.getString(phones.getColumnIndex("data1"));
                                System.out.println("number is:" + cNumber);
                                //txtphno.setText("Phone Number is: "+cNumber);
                                Log.d("abc", cNumber);
                                cur_num = cNumber;

                            }
                            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            //txtname.setText("Name is: "+name);
                            Log.d("abc", name);
//                            PhoneBook contact = null;
//                            contact.setName(name);
//                            contact.setNumber(cur_num);
                            //myDb.addContactDetails(contact);


                            myDb.insertData(name, cur_num);

                            numbers.clear();
                            names.clear();
                            Cursor res = myDb.getAllData();
                            while(res.moveToNext()) {
//                                numbers.clear();
//                                names.clear();
                                int index;

                                index = res.getColumnIndexOrThrow("NAME");
                                String cname = res.getString(index);

                                index = res.getColumnIndexOrThrow("NUMBER");
                                String cnumber = res.getString(index);

                                numbers.add(cname);
                                names.add(cnumber);
                            }

                            //Log.d("abcd", res.toString());
                            //adapter.onBindViewHolder(this);
                            //----------------------------------------------------------------------------------
                        }
                        catch (Exception st)
                        {
                            st.getMessage();
                            Log.d("abc", "catvh");
                        }
                    }
                }
                break;
        }
        initRecyclerView();
    }

    private void initRecyclerView(){
            Log.d("abc", "initRecyclerView: init recyclerview.");
            RecyclerView recyclerView = findViewById(R.id.rv);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, names, numbers);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

    }

    public void DeleteData() {
//        btnDelete.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
//                        if(deletedRows > 0)
//                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
    }
    public void UpdateData() {
//        btnviewUpdate.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
//                                editName.getText().toString(),
//                                editSurname.getText().toString(),editMarks.getText().toString());
//                        if(isUpdate == true)
//                            Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
    }
    public  void AddData() {
//        btnAddData.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean isInserted = myDb.insertData(editName.getText().toString(),
//                                editSurname.getText().toString(),
//                                editMarks.getText().toString() );
//                        if(isInserted == true)
//                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
    }

    public void viewAll() {
//        btnviewAll.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res = myDb.getAllData();
//                        if(res.getCount() == 0) {
//                            // show message
//                            showMessage("Error","Nothing found");
//                            return;
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while (res.moveToNext()) {
//                            buffer.append("Id :"+ res.getString(0)+"\n");
//                            buffer.append("Name :"+ res.getString(1)+"\n");
//                            buffer.append("Surname :"+ res.getString(2)+"\n");
//                            buffer.append("Marks :"+ res.getString(3)+"\n\n");
//                        }
//
//                        // Show all data
//                        showMessage("Data",buffer.toString());
//                    }
//                }
//        );
    }

}