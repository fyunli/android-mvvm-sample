package li.fyun.mvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import li.fyun.mvvm.R;
import li.fyun.mvvm.databinding.ActivityMainBinding;
import li.fyun.mvvm.viewmodel.UserModel;

public class MainActivity extends AppCompatActivity {

    private static final String USER_MODEL = "user_model";

    ActivityMainBinding binding;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        if(savedInstanceState == null) {
            userModel = new UserModel();
        }else{
            userModel = savedInstanceState.getParcelable(USER_MODEL);
        }
        binding.setUser(userModel);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(USER_MODEL, userModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userModel.destroy();
    }
}
