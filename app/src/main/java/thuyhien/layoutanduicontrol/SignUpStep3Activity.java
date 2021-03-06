package thuyhien.layoutanduicontrol;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpStep3Activity extends AppCompatActivity {
    Bundle bundleRegisInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step3);

        ButterKnife.bind(this);
        getInfoBundle();
    }

    @OnClick(R.id.button_send_mail)
    public void onBtnSendMailClick() {
        callEmailIntent();
    }

    @OnClick(R.id.button_restart)
    public void onBtnRestartClick() {
        restartMainActivity();
    }

    private void callEmailIntent() {
        Intent intentSendMail = new Intent(Intent.ACTION_SENDTO);
        intentSendMail.setData(Uri.parse("mailto:"));
        if (bundleRegisInfo != null) {
            intentSendMail.putExtra(
                    Intent.EXTRA_EMAIL,
                    new String[]{bundleRegisInfo.getString(ConstantKeyBundle.BUNDLE_EMAIL)});
            intentSendMail.putExtra(Intent.EXTRA_SUBJECT,
                    getResources().getString(R.string.sign_up_mail_subject));
            intentSendMail.putExtra(Intent.EXTRA_TEXT, createMailContent());
        }
        String title = getResources().getString(R.string.title_intent_chooser);
        Intent intentChooser = Intent.createChooser(intentSendMail, title);

        if (intentSendMail.resolveActivity(getPackageManager()) != null) {
            startActivity(intentChooser);
        }
    }

    private String createMailContent() {
        String mailContent = bundleRegisInfo.getString(ConstantKeyBundle.BUNDLE_FIRST_NAME) + "_"
                + bundleRegisInfo.getString(ConstantKeyBundle.BUNDLE_LAST_NAME) + "\n"
                + bundleRegisInfo.getString(ConstantKeyBundle.BUNDLE_PHONE_NUMBER) + "\n"
                + bundleRegisInfo.getString(ConstantKeyBundle.BUNDLE_SALARY) + " "
                + getResources().getString(R.string.title_dollars);
        return mailContent;
    }

    private void getInfoBundle() {
        bundleRegisInfo = getIntent().getExtras();
    }

    private void restartMainActivity() {
        Intent intent = new Intent(this, SignUpStep1Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
