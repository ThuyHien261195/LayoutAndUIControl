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
    @BindView(R.id.edit_first_name)
    EditText editFirstName;

    @BindView(R.id.edit_last_name)
    EditText editLastName;

    @BindView(R.id.edit_email)
    EditText editEmail;

    @BindView(R.id.edit_phone)
    EditText editPhone;

    @BindString(R.string.error_empty_field)
    String errorEmptyField;

    @BindString(R.string.error_invalid_field)
    String errorInvalidField;

    @BindString(R.string.hint_first_name)
    String hintFirstName;

    @BindString(R.string.hint_last_name)
    String hintLastName;

    @BindString(R.string.hint_email)
    String hintEmail;

    @BindString(R.string.hint_phone_number)
    String hintPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step1);

        ButterKnife.bind(this);
    }

    @OnTextChanged(R.id.edit_email)
    public void onEmailChange(CharSequence cs, int start, int count, int after) {
        validateEmail();
    }

    private boolean validateEmail() {
        if (TextUtils.isEmpty(editEmail.getText())) {
            editEmail.setError(String.format(errorEmptyField, hintEmail));
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(editEmail.getText()).matches()) {
            editEmail.setError(String.format(errorInvalidField, hintEmail));
            return false;
        }
        return true;
    }

    @OnTextChanged(R.id.edit_phone)
    public void onPhoneChange(CharSequence cs, int start, int count, int after) {
        validatePhone();
    }

    @OnClick(R.id.button_next)
    public void onClickBtnNext() {
        if (checkValidInput()) {
            Intent intent = new Intent(getBaseContext(), SignUpStep2Activity.class);
            intent.putExtras(createInfoBundle());
            startActivity(intent);
        }
    }

    private boolean validatePhone() {
        if (TextUtils.isEmpty(editPhone.getText())) {
            editPhone.setError(String.format(errorEmptyField, hintPhoneNumber));
            return false;
        } else if (!Patterns.PHONE.matcher(editPhone.getText()).matches()) {
            editPhone.setError(String.format(errorInvalidField, hintPhoneNumber));
            return false;
        }
        return true;
    }

    private boolean checkValidInput() {
        boolean isValidFirstName = validateFirstName();
        boolean isValidLastName = validateLastName();
        boolean isValidEmail = validateEmail();
        boolean isValidPhone = validatePhone();
        return isValidFirstName && isValidLastName &&
                isValidEmail && isValidPhone;

    }

    private boolean validateFirstName() {
        if (TextUtils.isEmpty(editFirstName.getText())) {
            editFirstName.setError(String.format(errorEmptyField, hintFirstName));
            return false;
        }
        return true;
    }

    private boolean validateLastName() {
        if (TextUtils.isEmpty(editLastName.getText())) {
            editLastName.setError(String.format(errorEmptyField, hintLastName));
            return false;
        }
        return true;
    }

    private Bundle createInfoBundle() {
        Bundle bundleRegistrationInfo = new Bundle();
        bundleRegistrationInfo.putString(ConstantKeyBundle.BUNDLE_FIRST_NAME, editFirstName.getText().toString());
        bundleRegistrationInfo.putString(ConstantKeyBundle.BUNDLE_LAST_NAME, editLastName.getText().toString());
        bundleRegistrationInfo.putString(ConstantKeyBundle.BUNDLE_EMAIL, editEmail.getText().toString());
        bundleRegistrationInfo.putString(ConstantKeyBundle.BUNDLE_PHONE_NUMBER, editPhone.getText().toString());
        return bundleRegistrationInfo;
    }
}
