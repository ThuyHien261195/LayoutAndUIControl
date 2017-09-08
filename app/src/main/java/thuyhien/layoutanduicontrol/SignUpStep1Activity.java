package thuyhien.layoutanduicontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class SignUpStep1Activity extends AppCompatActivity {
    @BindView(R.id.editFirstName)
    EditText editFirstName;

    @BindView(R.id.editLastName)
    EditText editLastName;

    @BindView(R.id.editEmail)
    EditText editEmail;

    @BindView(R.id.editPhone)
    EditText editPhone;

    @BindString(R.string.error_empty_field)
    String errorEmptyField;

    @BindString(R.string.hint_phone_number)
    String fieldNamePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step1);

        ButterKnife.bind(this);
    }

    @OnTextChanged(R.id.editEmail)
    public void onEmailChange(CharSequence cs, int start, int count, int after) {
        validateEmail();
    }

    private void validateEmail() {
        if (TextUtils.isEmpty(editEmail.getText())) {
            editEmail.setError(getResources().getString(R.string.error_empty_field,
                    getResources().getString(R.string.hint_email)));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(editEmail.getText()).matches()) {
            editEmail.setError(getResources().getString(R.string.error_invalid_field,
                    getResources().getString(R.string.hint_email)));
        }
    }

    @OnTextChanged(R.id.editPhone)
    public void onPhoneChange(CharSequence cs, int start, int count, int after) {
        validatePhone();
    }

    private void validatePhone() {
        if (TextUtils.isEmpty(editPhone.getText())) {
            editPhone.setError(String.format(errorEmptyField, fieldNamePhone));
        } else if (!Patterns.PHONE.matcher(editPhone.getText()).matches()) {
            editPhone.setError(getResources().getString(R.string.error_invalid_field,
                    getResources().getString(R.string.hint_phone_number)));
        }
    }

    @OnClick(R.id.btnNext)
    public void onClickBtnNext() {
        if (checkValidInput()) {
            Intent intent = new Intent(getBaseContext(), SignUpStep2Activity.class);
            startActivity(intent);
            this.finish();
        } else {
            if (TextUtils.isEmpty(editFirstName.getText())) {
                editFirstName.setError(getResources().getString(R.string.error_empty_field,
                        getResources().getString(R.string.hint_first_name)));
            }

            if (TextUtils.isEmpty(editLastName.getText())) {
                editLastName.setError(getResources().getString(R.string.error_empty_field,
                        getResources().getString(R.string.hint_last_name)));
            }

            validateEmail();
            validatePhone();
        }
    }

    public boolean checkValidInput() {
        return !TextUtils.isEmpty(editFirstName.getText()) &&
                !TextUtils.isEmpty(editLastName.getText()) &&
                !TextUtils.isEmpty(editEmail.getText()) &&
                !TextUtils.isEmpty(editPhone.getText()) &&
                Patterns.EMAIL_ADDRESS.matcher(editEmail.getText()).matches() &&
                Patterns.PHONE.matcher(editPhone.getText()).matches();
    }
}
