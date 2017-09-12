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

    public void getInfoBundle() {
        bundleRegisInfo = getIntent().getExtras();
    }

    @OnClick(R.id.btn_send_mail)
    public void onBtnSendMailClick() {
        callEmailIntent();
    }

    private void callEmailIntent() {
        Intent intentSendMail = new Intent(Intent.ACTION_SENDTO);
        intentSendMail.setData(Uri.parse("mailto:"));
        if (bundleRegisInfo != null) {
            intentSendMail.putExtra(Intent.EXTRA_EMAIL, new String[]{bundleRegisInfo.getString("email")});
            intentSendMail.putExtra(Intent.EXTRA_SUBJECT,
                    getResources().getString(R.string.sign_up_subject_mail));
            intentSendMail.putExtra(Intent.EXTRA_TEXT, createMailContent());
        }
        String title = getResources().getString(R.string.intent_chooser_title);
        Intent intentChooser = Intent.createChooser(intentSendMail, title);

        if (intentSendMail.resolveActivity(getPackageManager()) != null) {
            startActivity(intentChooser);
        }
    }

    private String createMailContent() {
        String mailContent = bundleRegisInfo.getString("firstName") + "_" + bundleRegisInfo.getString("lastName") + "\n" +
                bundleRegisInfo.getString("phoneNumber") + "\n" + bundleRegisInfo.getString("salary") + " " +
                getResources().getString(R.string.dollars_title);
        return mailContent;
    }

    @OnClick(R.id.btn_restart)
    public void onBtnRestartClick() {
        restartMainActivity();
    }

    private void restartMainActivity() {
        Intent intent = new Intent(this, SignUpStep1Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
