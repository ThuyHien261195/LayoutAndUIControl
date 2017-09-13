package thuyhien.layoutanduicontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpStep2Activity extends AppCompatActivity {

    @BindView(R.id.txt_salary)
    TextView txtSalary;

    @BindView(R.id.text_end_dollar)
    TextView txtEndDollar;

    @BindView(R.id.seek_bar_salary)
    CustomSeekBar seekBarSalary;

    @BindViews({R.id.chk_football, R.id.chk_tennis, R.id.chk_ping_pong,
            R.id.chk_swimming, R.id.chk_volleyball, R.id.chk_basketball})
    List<CheckBox> allChecksSport;

    @BindString(R.string.title_seek_bar_value)
    String ckbValueTitle;

    Bundle bundleRegistrationInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step2);

        ButterKnife.bind(this);

        getInfoBundle();
        initViews();
    }

    @OnClick(R.id.button_done)
    public void onClickBtnDone() {
        boolean isValidInput = checkValidInput();
        if (isValidInput) {
            Intent intent = new Intent(this, SignUpStep3Activity.class);
            createInfoBundle();
            intent.putExtras(bundleRegistrationInfo);
            startActivity(intent);
        } else {
            Toast.makeText(this, getResources().getString(R.string.warning_select_sport),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void getInfoBundle() {
        bundleRegistrationInfo = getIntent().getExtras();
    }

    private void initViews() {
        // Set default value
        txtSalary.setText(String.format(ckbValueTitle, 0));
        setEndDollarTitle();

        seekBarSalary.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String result = String.format(ckbValueTitle, progress);
                txtSalary.setText(result);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setEndDollarTitle() {
        txtEndDollar.setText(getResources().getString(
                R.string.title_seek_bar_end_value,
                seekBarSalary.getRealMax()));
    }

    private boolean checkValidInput() {
        for (CheckBox checkItem :
                allChecksSport) {
            if (checkItem.isChecked()) {
                return true;
            }
        }
        return false;
    }

    private void createInfoBundle() {
        if (bundleRegistrationInfo == null) {
            bundleRegistrationInfo = new Bundle();
        }
        bundleRegistrationInfo.putString("salary", String.valueOf(seekBarSalary.getProgress()));
    }
}
