package com.csc475.athenaeum;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, author_input, pages_input, year_input, publisher_input, genre_input, rating_input, citation_output;
    Button update_button, citation_button, delete_button;

    String id, title, author, pages, year, publisher, genre, rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        pages_input = findViewById(R.id.pages_input2);
        year_input = findViewById(R.id.year_input2);
        publisher_input = findViewById(R.id.publisher_input2);
        genre_input = findViewById(R.id.genre_input2);
        rating_input = findViewById(R.id.rating_input2);
        citation_output = findViewById(R.id.citation_output2);
        update_button = findViewById(R.id.update_button);
        citation_button = findViewById(R.id.citation_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                author = author_input.getText().toString().trim();
                pages = pages_input.getText().toString().trim();
                year = year_input.getText().toString().trim();
                publisher = publisher_input.getText().toString().trim();
                genre = genre_input.getText().toString().trim();
                rating = rating_input.getText().toString().trim();
                myDB.updateData(id, title, author, pages, year, publisher, genre, rating);
            }
        });
        citation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = title_input.getText().toString().trim();
                author = author_input.getText().toString().trim();
                year = year_input.getText().toString().trim();
                publisher = publisher_input.getText().toString().trim();
                String first_name = author.replaceAll(" .*", "");
                String last_name = author.substring(author.lastIndexOf(" ")+1);
                char first_initial = first_name.charAt(0);
                switch (SettingsActivity.citation_style) {
                    case "APA":
                        Toast.makeText(
                                getApplicationContext(),
                                last_name+", "+first_initial+". ("+year+"). " + Html.fromHtml("<i>"+title+". </i>")+publisher+".",
                                Toast.LENGTH_SHORT).show();
                        citation_output.setText(last_name+", "+first_initial+". ("+year+"). " + Html.fromHtml("<i>"+title+". </i>")+publisher+".");
                        break;
                    case "Chicago/Turabian":
                        Toast.makeText(
                                getApplicationContext(),
                                last_name+", "+first_name+". "+Html.fromHtml("<i>"+title+". </i>")+"n.p.: "+publisher+", "+year+".",
                                Toast.LENGTH_SHORT).show();
                                citation_output.setText(last_name+", "+first_name+". "+Html.fromHtml("<i>"+title+". </i>")+"n.p.: "+publisher+", "+year+".");
                        break;
                    case "Harvard":
                        Toast.makeText(
                                getApplicationContext(),
                                last_name+", "+first_initial+". ("+year+"). " + Html.fromHtml("<i>"+title+". </i>")+publisher+", n.p.",
                                Toast.LENGTH_SHORT).show();
                        citation_output.setText(last_name+", "+first_initial+". ("+year+"). " + Html.fromHtml("<i>"+title+". </i>")+publisher+", n.p.");
                        break;
                    case "Vancounver":
                        Toast.makeText(
                                getApplicationContext(),
                                last_name+" "+first_initial+". "+title+". [place unknown]: "+publisher+"; "+year+".",
                                Toast.LENGTH_SHORT).show();
                        citation_output.setText(last_name+" "+first_initial+". "+title+". [place unknown]: "+publisher+"; "+year+".");
                        break;
                    case "OSCOLA":
                        Toast.makeText(
                                getApplicationContext(),
                                first_name+" "+last_name+". "+Html.fromHtml("<i>"+title+" </i>")+"("+publisher+" | "+year+").",
                                Toast.LENGTH_SHORT).show();
                        citation_output.setText(first_name+" "+last_name+". "+Html.fromHtml("<i>"+title+" </i>")+"("+publisher+" | "+year+").");
                        break;
                    case "IEEE":
                        Toast.makeText(
                                getApplicationContext(),
                                first_initial+" "+last_name+". "+title+". [place unknown]: "+publisher+", "+year+".",
                                Toast.LENGTH_SHORT).show();
                        citation_output.setText(first_initial+" "+last_name+". "+title+". [place unknown]: "+publisher+", "+year+".");
                        break;
                    case "AMA":
                        Toast.makeText(
                                getApplicationContext(),
                                last_name+" "+first_initial+". "+Html.fromHtml("<i>"+title+". </i>")+publisher+"; "+year+".",
                                Toast.LENGTH_SHORT).show();
                        citation_output.setText(last_name+" "+first_initial+". "+Html.fromHtml("<i>"+title+". </i>")+publisher+"; "+year+".");
                        break;
                    case "ACS":
                        Toast.makeText(
                                getApplicationContext(),
                                last_name+", "+first_initial+". "+Html.fromHtml("<i>"+title+"; </i>")+publisher+", "+year+".",
                                Toast.LENGTH_SHORT).show();
                        citation_output.setText(last_name+", "+first_initial+". "+Html.fromHtml("<i>"+title+"; </i>")+publisher+", "+year+".");
                        break;
                    case "NLM":
                        Toast.makeText(
                                getApplicationContext(),
                                last_name+" "+first_initial+". "+title+". [place unknown]: "+publisher+"; "+year+".",
                                Toast.LENGTH_SHORT).show();
                        citation_output.setText(last_name+" "+first_initial+". "+title+". [place unknown]: "+publisher+"; "+year+".");
                        break;
                    case "AAA/APSA":
                        Toast.makeText(
                                getApplicationContext(),
                                last_name+", "+first_name+". ("+year+"). " + Html.fromHtml("<i>"+title+". </i>")+"[place unknown]: "+publisher+".",
                                Toast.LENGTH_SHORT).show();
                        citation_output.setText(last_name+", "+first_name+". ("+year+"). " + Html.fromHtml("<i>"+title+". </i>")+"[place unknown]: "+publisher+".");
                        break;
                    default:
                        Toast.makeText(
                                getApplicationContext(),
                                last_name+", "+first_name+". "+Html.fromHtml("<i>"+title+". </i>")+publisher+", "+year+".",
                                Toast.LENGTH_SHORT).show();
                        citation_output.setText(last_name+", "+first_name+". "+Html.fromHtml("<i>"+title+". </i>")+publisher+", "+year+".");
                        break;
                }
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");
            year = getIntent().getStringExtra("year");
            publisher = getIntent().getStringExtra("publisher");
            genre = getIntent().getStringExtra("genre");
            rating = getIntent().getStringExtra("rating");

            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);
            year_input.setText(year);
            publisher_input.setText(publisher);
            genre_input.setText(genre);
            rating_input.setText(year);
            Log.d("stev", title+" "+author+" "+pages+" "+year+" "+publisher+" "+genre+" "+rating);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
