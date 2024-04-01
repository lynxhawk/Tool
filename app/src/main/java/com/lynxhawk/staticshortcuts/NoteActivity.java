package com.lynxhawk.staticshortcuts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    EditText note_view;
    Button store;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        note_view = (EditText)findViewById(R.id.note_view);
        store = (Button)findViewById(R.id.store);

        String note = preferences.getString("note","");
        note_view.setText(note);

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = note_view.getText().toString();
                    editor=preferences.edit();
                    editor.putString("note",note);
                editor.apply();
                Intent intent = new Intent(NoteActivity.this, MainActivity.class);
                startActivity(intent);
                NoteActivity.this.finish();
            }
        });

    }
}
