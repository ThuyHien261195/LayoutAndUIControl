package thuyhien.layoutanduicontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpStep2Activity extends AppCompatActivity {
    @BindView(R.id.txtSalary)
    TextView txtSalary;

    @BindView(R.id.seekBarSalary)
    SeekBar seekBarSalary;

    @BindView(R.id.ckbFootball)
    CheckBox ckbFootball;

    @BindView(R.id.ckbTennis)
    CheckBox ckbTennis;

    @BindView(R.id.ckbPingPong)
    CheckBox ckbPingPong;

    @BindView(R.id.ckbSwimming)
    CheckBox ckbSwimming;

    @BindView(R.id.ckbVolleyball)
    CheckBox ckbVolleyball;

    @BindView(R.id.ckbBasketball)
    CheckBox ckbBasketball;

    @BindViews({R.id.ckbBasketball, R.id.ckbFootball, R.id.ckbTennis})
    List<CheckBox> allCkbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step2);

        ButterKnife.bind(this);

        initViews();


    }

    private void initViews() {
        // Set default value
        txtSalary.setText(getResources().getString(R.string.ckb_value_title, 0));

        seekBarSalary.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String result = getResources().getString(R.string.ckb_value_title, progress);
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

    @OnClick(R.id.btnDone)
    public void onClickBtnDone() {
        if (!ckbFootball.isChecked() && !ckbTennis.isChecked() && !ckbPingPong.isChecked() &&
                !ckbSwimming.isChecked() && !ckbVolleyball.isChecked() && !ckbBasketball.isChecked()) {
            Toast.makeText(this, getResources().getString(R.string.warning_select_sport),
                    Toast.LENGTH_SHORT).show();
        } else {
            this.finish();
        }
    }
}
