package test.com.fragmentshareelementtransition;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.transition.TransitionInflater;

import butterknife.ButterKnife;
import test.com.fragmentshareelementtransition.fragments.DogHeaderFragment;
import test.com.fragmentshareelementtransition.fragments.DogListFragment;
import test.com.fragmentshareelementtransition.intents.TargetIntent;

/**
 * Created by laetitia on 4/30/15.
 */
public class TargetActivity extends Activity {

    private DogListFragment mDogListFragment;
    private DogHeaderFragment mDogHeaderFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // enable the shared element transition (only if theme different from Theme.Material)
//        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

//        getWindow().setEnterTransition(new Fade());
//        getWindow().setExitTransition(new Fade());

        TargetIntent intent = new TargetIntent(getIntent());

        setContentView(R.layout.activity_target);
        ButterKnife.inject(this);

        mDogListFragment = DogListFragment.getInstance(this);
        mDogHeaderFragment = DogHeaderFragment.getInstance(this, intent.getTransitionName(), intent.getImageId());

        getFragmentManager().beginTransaction()
                .add(R.id.header_container, mDogHeaderFragment, DogHeaderFragment.TAG)
                .commit();

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {

                getFragmentManager().beginTransaction()
                        .add(R.id.container, mDogListFragment, DogListFragment.TAG)
                        .commit();

            }
        }, 400);

    }

    @Override
    public void onBackPressed() {
        if (mDogListFragment.isVisible()) {
            // override doglistfragment exit to have a different transition
            mDogListFragment.setExitTransition(TransitionInflater.from(this).inflateTransition(R.transition.slide_bottom));
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new Fragment())
                    .commit();
        }
        super.onBackPressed();
    }

}
