package com.example.asus.quartertou.component;
import com.example.asus.quartertou.module.HttpModule;
import com.example.asus.quartertou.ui.Creation.wjokes.WJokesActivity;
import com.example.asus.quartertou.ui.Favorites.FavoritesActivity;
import com.example.asus.quartertou.ui.UserInfo.UserInfoActivity;
import com.example.asus.quartertou.ui.login.LoginActivity;
import com.example.asus.quartertou.ui.recommend.FragmentRecommend;
import com.example.asus.quartertou.ui.recommend.JokeDetailActivity;
import com.example.asus.quartertou.ui.register.Register_an_accountActivity;
import com.example.asus.quartertou.ui.share.FragmentShare;
import com.example.asus.quartertou.ui.share.MyFragment;
import com.example.asus.quartertou.ui.share.MyFragment2;
import com.example.asus.quartertou.ui.video.FragmentVideo;
import com.example.asus.quartertou.ui.video.MyFragment3;
import com.example.asus.quartertou.ui.video.MyFragment4;
import com.example.asus.quartertou.ui.video.VideoDetailActivity;

import dagger.Component;

/**
 * Created by asus on 2018/6/4.
 */
@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(FragmentShare fragmentShare);
    void inject(FragmentRecommend fragmentRecommend);
    void inject(FragmentVideo fragmentVideo);
    void inject(MyFragment myFragment);
    void inject(MyFragment2 myFragment2);
    void inject(MyFragment3 myFragment3);
    void inject(MyFragment4 myFragment4);
    void inject(JokeDetailActivity jokeDetailActivity);
    void inject(VideoDetailActivity videoDetailActivity);
    void inject(WJokesActivity wJokesActivity);
    void inject(LoginActivity loginActivity);
    void inject(Register_an_accountActivity register_an_accountActivity);
    void inject(UserInfoActivity userInfoActivity);
    void inject(FavoritesActivity favoritesActivity);
}
