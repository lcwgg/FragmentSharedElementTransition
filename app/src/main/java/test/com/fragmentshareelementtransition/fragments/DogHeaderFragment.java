package test.com.fragmentshareelementtransition.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import test.com.fragmentshareelementtransition.R;
import test.com.fragmentshareelementtransition.adapters.DogAdapter;

/**
 * Created by laetitia on 4/30/15.
 */
public class DogHeaderFragment extends Fragment {

    public static final String TAG = DogHeaderFragment.class.getSimpleName();

    private static final String ARG_TRANSITION_NAME = "ARG_TRANSITION_NAME";
    private static final String ARG_IMAGE_REF_ID = "ARG_IMAGE_REF_ID";

    @InjectView(R.id.view_imageview)
    ImageView mImageView;

    public static DogHeaderFragment getInstance(Context context, String transitionName, int imageRefId) {
        DogHeaderFragment fragment = new DogHeaderFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TRANSITION_NAME, transitionName);
        bundle.putInt(ARG_IMAGE_REF_ID, imageRefId);
        fragment.setSharedElementEnterTransition(TransitionInflater.from(context).inflateTransition(R.transition.change_transform));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_dog_header, container, false);
        ButterKnife.inject(this, v);

        final String transitionName = getArguments().getString(ARG_TRANSITION_NAME);
        final int imageRefId = getArguments().getInt(ARG_IMAGE_REF_ID);
        mImageView.setTransitionName(transitionName);
        mImageView.setImageResource(imageRefId);

        return v;
    }
}
