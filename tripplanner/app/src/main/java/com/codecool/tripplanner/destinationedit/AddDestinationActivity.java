package com.codecool.tripplanner.destinationedit;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.codecool.tripplanner.R;

public class AddDestinationActivity extends AppCompatActivity implements EditDestinationContract.View {

    private EditDestinationContract.Presenter presenter;
    @BindView(R.id.save_button)
    Button saveButton;
    @BindView(R.id.dest_name)
    EditText destName;
    @BindView(R.id.image_url)
    EditText imageUrl;
    @BindView(R.id.latitude)
    EditText latitude;
    @BindView(R.id.longitude)
    EditText longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_destination);

        ButterKnife.bind(this);

        presenter = new EditDestinationPresenter(getApplication(), this);

        AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.dest_name, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.image_url, Patterns.WEB_URL, R.string.invalid_site);
        awesomeValidation.addValidation(this, R.id.latitude, "^(\\+|-)?((\\d((\\.)|\\.\\d{1,6})?)|(0*?[0-8]\\d((\\.)|\\.\\d{1,6})?)|(0*?90((\\.)|\\.0{1,6})?))$", R.string.invalid_lat);
        awesomeValidation.addValidation(this, R.id.longitude,"^(\\+|-)?((\\d((\\.)|\\.\\d{1,6})?)|(0*?\\d\\d((\\.)|\\.\\d{1,6})?)|(0*?1[0-7]\\d((\\.)|\\.\\d{1,6})?)|(0*?180((\\.)|\\.0{1,6})?))$" , R.string.invalid_long );

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()){
                    presenter.addNewDestination(destName.getText().toString(), imageUrl.getText().toString(), latitude.getText().toString(), longitude.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), R.string.form_fail, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void setPresenter(EditDestinationContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showSaveError() {
        Toast.makeText(getApplicationContext(), R.string.failed_save, Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    public void showSaveSuccess() {
        Toast.makeText(getApplicationContext(), R.string.form_success, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}