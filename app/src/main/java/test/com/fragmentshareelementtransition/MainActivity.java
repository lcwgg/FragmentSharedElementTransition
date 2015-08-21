package test.com.fragmentshareelementtransition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import test.com.fragmentshareelementtransition.adapters.MainAdapter;
import test.com.fragmentshareelementtransition.intents.TargetIntent;

public class MainActivity extends Activity implements MainAdapter.OnItemCLickListener {

    @InjectView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // enable the shared element transition (only if theme different from Theme.Material)
//        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

//        getWindow().setEnterTransition(new Fade());
//        getWindow().setExitTransition(new Fade());

        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.chiot1);
        images.add(R.drawable.chiot2);
        images.add(R.drawable.chiot3);
        images.add(R.drawable.chiot4);
        images.add(R.drawable.chiot5);
        images.add(R.drawable.chiot6);
        images.add(R.drawable.chiot7);
        images.add(R.drawable.chiot8);

        final RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        final MainAdapter mainAdapter = new MainAdapter(images);
        mRecyclerView.setAdapter(mainAdapter);
        mainAdapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(View imageView, int imageRefId, String imageTransitionName) {
        final TargetIntent intent = new TargetIntent(this, imageRefId, imageTransitionName);
        final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, imageView, imageTransitionName);

        // Examples of transition
//        getWindow().setSharedElementEnterTransition(new AutoTransition());
//        getWindow().setSharedElementExitTransition(new AutoTransition());

        startActivity(intent, options.toBundle());

    }

}
