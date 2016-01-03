package li.fyun.mvvm.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import li.fyun.mvvm.model.User;

/**
 * Created by fyunli on 15/12/28.
 */
public class UserModel implements ViewModel, Parcelable {

    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> portrait = new ObservableField<>();

    public UserModel() {
        User user = User.getUser();
        this.username.set(user.getUsername());
        this.portrait.set(user.getPortrait());
    }

    @Override
    public void destroy() {

    }

    // just a sample logic
    public void reload() {
        User user = User.getUser();
        this.username.set(user.getUsername());
        this.portrait.set(user.getPortrait());
    }

    // use android parceable generator plugin to gen the code
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.username);
        dest.writeSerializable(this.portrait);
    }

    protected UserModel(Parcel in) {
        this.username = (ObservableField<String>) in.readSerializable();
        this.portrait = (ObservableField<String>) in.readSerializable();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .dontAnimate()
                .into(view);
    }

}
