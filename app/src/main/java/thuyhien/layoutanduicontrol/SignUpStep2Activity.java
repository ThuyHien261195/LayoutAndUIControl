package thuyhien.layoutanduicontrol;

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
    @BindView(R.id.txtSalary)
    TextView txtSalary;

    @BindView(R.id.seekBarSalary)
    SeekBar seekBarSalary;

    @BindViews({R.id.ckbFootball, R.id.ckbTennis, R.id.ckbPingPong,
            R.id.ckbSwimming, R.id.ckbVolleyball, R.id.ckbBasketball})
    List<CheckBox> allChecksSport;

    @BindString(R.string.ckb_value_title)
    String ckbValueTitle;

    final int multiConst = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step2);

        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        // Set default value
        txtSalary.setText(String.format(ckbValueTitle, 0));

        seekBarSalary.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String result = String.format(ckbValueTitle, progress * 100);
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
        for (CheckBox checkItem :
             allChecksSport) {
            if(checkItem.isChecked()){
                this.finish();
                return;
            }
        }
        Toast.makeText(this, getResources().getString(R.string.warning_select_sport),
                Toast.LENGTH_SHORT).show();
    }
}
