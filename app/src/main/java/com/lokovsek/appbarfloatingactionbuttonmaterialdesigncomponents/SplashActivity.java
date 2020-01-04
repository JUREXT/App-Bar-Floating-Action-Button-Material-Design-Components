package com.lokovsek.appbarfloatingactionbuttonmaterialdesigncomponents;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;


public class SplashActivity extends AppCompatActivity {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @BindView(R.id.first_line) View mFirstView;
    @BindView(R.id.second_line) View mSecondView;
    @BindView(R.id.third_line) View mThirdView;
    @BindView(R.id.fourth_line) View mFourthView;
    @BindView(R.id.fifth_line) View mFifthView;
    @BindView(R.id.sixth_line) View mSixthView;
    @BindView(R.id.title) TextView mTitle;
    @BindView(R.id.sub_title) TextView mSubTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        // Animation Calls
        Animation mFromTopAnimantion = AnimationUtils.loadAnimation(this, R.anim.from_top_animation_view);
        Animation mBottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation_view);
        Animation mMiddleFromLeftAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation_view);
        Animation mFromLeftAnimation = AnimationUtils.loadAnimation(this, R.anim.from_left_animation_view);
        Animation mFromBottomAnimation = AnimationUtils.loadAnimation(this, R.anim.from_bottom_animation_view);
        Animation mBackToTopAnimation = AnimationUtils.loadAnimation(this, R.anim.back_to_top_animation_view);
        Animation mToRightAnimation = AnimationUtils.loadAnimation(this, R.anim.to_right_animation_view);

        // Setting Animations to the elements of Splash Screen
        mFirstView.setAnimation(mFromTopAnimantion);
        mSecondView.setAnimation(mFromTopAnimantion);
        mThirdView.setAnimation(mFromTopAnimantion);
        mFourthView.setAnimation(mFromTopAnimantion);
        mFifthView.setAnimation(mFromTopAnimantion);
        mSixthView.setAnimation(mFromTopAnimantion);

        mTitle.setAnimation(mMiddleFromLeftAnimation);
        mSubTitle.setAnimation(mFromBottomAnimation);

        mCompositeDisposable.add(
                Single.timer(3, TimeUnit.SECONDS)
                        .subscribe(seconds -> {
                            Log.d("Splash", "Finishing splash!");
                            Intent intent = new Intent(this, MainActivity.class);
                            ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.activity_anim_frag_fade_in, R.anim.activity_anim_frag_fade_out);
                            startActivity(intent, options.toBundle());
                            finish();
                        }, throwable -> {
                            Log.d("Splash", "Error: " + throwable.getMessage());
                        }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }
}
