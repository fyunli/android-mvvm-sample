package li.fyun.mvvm.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import li.fyun.mvvm.model.User;

/**
 * Created by fyunli on 15/12/28.
 */
public class UserModel implements ViewModel, Parcelable {

    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> portrait = new ObservableField<>();

    public UserModel(User user) {
        this.username.set(user.getUsername());
        this.portrait.set(user.getPortrait());
    }

    @Override
    public void destroy() {

    }

    // just a sample logic
    public static UserModel laodUser() {
        User user = User.getUser();
        return new UserModel(user);
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
        Log.e("Glide", "id: " + view.getId() + "; imageUrl:" + imageUrl);
        Glide.with(view.getContext())
                .load(imageUrl)
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean
                            isFirstResource) {
                        Log.e("onException", model);
                        e.printStackTrace();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Log.e("onResourceReady", model);
                        return false;
                    }
                })
                .into(view);
    }

}
