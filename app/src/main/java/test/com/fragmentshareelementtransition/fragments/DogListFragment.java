package test.com.fragmentshareelementtransition.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import test.com.fragmentshareelementtransition.R;
import test.com.fragmentshareelementtransition.adapters.DogAdapter;

/**
 * Created by laetitia on 4/30/15.
 */
public class DogListFragment extends Fragment implements DogAdapter.OnItemCLickListener {

    public static final String TAG = DogListFragment.class.getSimpleName();

    @InjectView(R.id.recyclerview_dog)
    RecyclerView mRecyclerView;

    private DogAdapter mDogAdapter;

    public static DogListFragment getInstance(Context context) {
        DogListFragment fragment = new DogListFragment();
        fragment.setEnterTransition(TransitionInflater.from(context).inflateTransition(R.transition.slide_bottom));
        fragment.setReenterTransition(TransitionInflater.from(context).inflateTransition(R.transition.slide_left));
        fragment.setExitTransition(TransitionInflater.from(context).inflateTransition(R.transition.slide_left));
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_dog_list, container, false);
        ButterKnife.inject(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final List<Integer> images = new ArrayList<>();
        images.add(R.drawable.dog1);
        images.add(R.drawable.dog2);
        images.add(R.drawable.dog3);

        final RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        mDogAdapter = new DogAdapter(images);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mDogAdapter);
        mDogAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position, int imageRefId, ImageView imageView) {
        SingleDogFragment fragment = SingleDogFragment.getInstance(getActivity(), mDogAdapter.getImageTransitionName(getActivity(), position), imageRefId);
        getFragmentManager().beginTransaction()
                .addSharedElement(imageView, mDogAdapter.getImageTransitionName(getActivity(), position))
                .replace(R.id.container, fragment, fragment.getTag())
                .addToBackStack(null)
                .commit();
    }
}
