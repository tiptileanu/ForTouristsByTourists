package mic.alliwanna.be.fortouristsbytourists;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PoiDetailsActivity extends AppCompatActivity {


    EditText poiName, poiAddress, poiOpeningHours, poiWebsite, poiBookingUrl, poiDescription;
    RatingBar poiRatingBar;
    Switch poiCanBook;
    Button btnCancel, btnSave;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi_details);

        poiName = findViewById(R.id.et_poi_name);
        poiAddress = findViewById(R.id.et_poi_address);
        poiOpeningHours = findViewById(R.id.et_opening_hours);
        poiWebsite = findViewById(R.id.et_website);
        poiBookingUrl = findViewById(R.id.et_booking_url);
        poiDescription = findViewById(R.id.et_poi_description);
        poiRatingBar = findViewById(R.id.rating_bar);
        poiCanBook = findViewById(R.id.sw_can_book);
        btnCancel = findViewById(R.id.btn_cancel);
        btnSave = findViewById(R.id.btn_save);


        Intent i=getIntent();
        final String downloadUrl=i.getStringExtra("URL");
        final String poiType=i.getStringExtra("TYPE");

        dbRef= FirebaseDatabase.getInstance().getReference(poiType);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                POI poi=new POI( poiName.getText().toString(),
                        poiDescription.getText().toString(),
                        poiAddress.getText().toString(),
                        poiOpeningHours.getText().toString(),
                        poiWebsite.getText().toString(),
                        downloadUrl,
                        poiBookingUrl.getText().toString(),
                        poiType, poiRatingBar.getRating(),
                        poiCanBook.isChecked());
                dbRef.child(poi.getPoiName()).setValue(poi);
            }
        });

    }
}
