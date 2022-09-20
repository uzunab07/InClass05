package edu.uncc.inclass05.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.uncc.inclass05.R;
import edu.uncc.inclass05.databinding.FragmentAppDetailsBinding;
import edu.uncc.inclass05.models.DataServices;
//Joseph Mauney && Mohamed Ali Khaled

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppDetailsFragment extends Fragment {
    FragmentAppDetailsBinding binding;
    ListView listView;
    TextView textViewAppName,textViewReleaseDate,textViewArtistName,textViewGenre;
    ArrayAdapter adapter;
    ListView listViewGenres;
    private static final String ARG_PARAM_APP = "mApp";

    private DataServices.App mAPP;

    public AppDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mAPP Parameter 1.
     * @return A new instance of fragment AppDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppDetailsFragment newInstance(DataServices.App mAPP) {
        AppDetailsFragment fragment = new AppDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_APP, mAPP);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAPP = (DataServices.App)getArguments().getSerializable(ARG_PARAM_APP);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAppDetailsBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mAPP != null) {

            ArrayList<String> genres = mAPP.genres;

            textViewArtistName = getActivity().findViewById(R.id.textViewArtistName);
            textViewAppName  = getActivity().findViewById(R.id.textViewAppName);
            textViewReleaseDate = getActivity().findViewById(R.id.textViewReleaseDate);

            textViewAppName.setText(mAPP.name);
            textViewArtistName.setText(mAPP.artistName);
            textViewReleaseDate.setText(mAPP.releaseDate);


            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, genres);
            binding.listViewGenres.setAdapter(adapter);

            Log.d("demo", "onViewCreated: " + mAPP);
        }
    }
}