package thuyhien.layoutanduicontrol;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpStep3Activity extends AppCompatActivity {
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step3);

        ButterKnife.bind(this);
        getInfoBundle();
    }

    public void getInfoBundle() {
        bundle = getIntent().getExtras();
    }

    @OnClick(R.id.btn_send_mail)
    public void onBtnSendMailClick() {
        callEmailIntent();
    }

    private void callEmailIntent() {
        Intent intentSendMail = new Intent(Intent.ACTION_SEND);
        intentSendMail.setData(Uri.parse("mailto:"));
        intentSendMail.setType("message/rfc822");
        if (bundle != null) {
            intentSendMail.putExtra(Intent.EXTRA_EMAIL, new String[]{bundle.getString("email")});
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
        String content = bundle.getString("firstName") + "_" + bundle.getString("lastName") + "\n" +
                bundle.getString("phoneNumber") + "\n" + bundle.getString("salary") + " " +
                getResources().getString(R.string.dollars_title);
        return content;
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
