package edu.uncc.inclass05.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import edu.uncc.inclass05.AppAdapater;
import edu.uncc.inclass05.R;
import edu.uncc.inclass05.databinding.FragmentAppsListBinding;
import edu.uncc.inclass05.models.DataServices;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppsListFragment extends Fragment {



    FragmentAppsListBinding binding;
    ArrayList<DataServices.App> Apps = new ArrayList<>();
    AppAdapater adapter;
    ALFListener alflistener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CATEGORY_KEY = "ITEM";


    // TODO: Rename and change types of parameters
    private String item;


    public AppsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param item Parameter 1.
     * @return A new instance of fragment AppsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppsListFragment newInstance(String item) {
        AppsListFragment fragment = new AppsListFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY_KEY, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = getArguments().getString(CATEGORY_KEY);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAppsListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(item!=null){
            Toast.makeText(getActivity(), "Here is the String Recieved:  "+item, Toast.LENGTH_SHORT).show();
            Apps = DataServices.getAppsByCategory(item);
            adapter = new AppAdapater(getActivity(), R.layout.app_layout, Apps);
            binding.listViewApp.setAdapter(adapter);
            binding.listViewApp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    DataServices.App app = Apps.get(i);
                    Log.d("demo", "onItemClick: "+app.toString());
                    alflistener.sendToDetails(app);
                }
            });
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof AppsListFragment.ALFListener){
            alflistener = (AppsListFragment.ALFListener) context;
        }else{
            throw  new RuntimeException(context.toString()+" Must Implement ALFListener");
        }
    }

    public interface ALFListener {
        void sendToDetails(DataServices.App app);
    }

}