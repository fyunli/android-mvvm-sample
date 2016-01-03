package li.fyun.mvvm.model;

import java.util.Random;

/**
 * Created by fyunli on 15/12/28.
 */
public class User {

    private static final User[] users = new User[]{
            new User("Andrea"), new User("Betty"), new User("Calvin"), new User("Daisy"), new User("Eason"),
            new User("Frank"), new User("Gary"), new User("Halen"), new User("Iris"), new User("Java")
    };

    private String username;
    private String portrait = "http://ww3.sinaimg.cn/large/620f1e7egw1ey8bjsbmr2j20b40b475e.jpg";

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    // just a sample logic
    public static User getUser() {
        int index = new Random().nextInt(users.length);
        return users[index];
    }

}
