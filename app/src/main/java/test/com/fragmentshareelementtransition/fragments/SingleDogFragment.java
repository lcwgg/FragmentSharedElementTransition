package test.com.fragmentshareelementtransition.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import test.com.fragmentshareelementtransition.R;

/**
 * Created by laetitia on 5/11/15.
 */
public class SingleDogFragment extends Fragment {

    private static final String ARG_TRANSITION_NAME = "ARG_TRANSITION_NAME";
    private static final String ARG_IMAGE_REF_ID = "ARG_IMAGE_REF_ID";

    @InjectView(R.id.single_dog_imageview)
    ImageView mDogImage;

    public static SingleDogFragment getInstance(Context context, String transitionName, int imageRefId) {
        SingleDogFragment fragment = new SingleDogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TRANSITION_NAME, transitionName);
        bundle.putInt(ARG_IMAGE_REF_ID, imageRefId);
        fragment.setSharedElementEnterTransition(TransitionInflater.from(context).inflateTransition(R.transition.change_transform));
        fragment.setEnterTransition(TransitionInflater.from(context).inflateTransition(R.transition.slide_left));
        fragment.setExitTransition(TransitionInflater.from(context).inflateTransition(R.transition.slide_left));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_single_dog, container, false);
        ButterKnife.inject(this, v);
        mDogImage.setTransitionName(getArguments().getString(ARG_TRANSITION_NAME));
        mDogImage.setImageResource(getArguments().getInt(ARG_IMAGE_REF_ID));
        return v;
    }
}
