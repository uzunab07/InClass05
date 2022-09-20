package edu.uncc.inclass05;

//Joseph Mauney && Mohamed Ali Khaled

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.uncc.inclass05.databinding.ActivityMainBinding;
import edu.uncc.inclass05.fragments.AppCategoriesFragment;
import edu.uncc.inclass05.fragments.AppDetailsFragment;
import edu.uncc.inclass05.fragments.AppsListFragment;
import edu.uncc.inclass05.models.DataServices;

public class MainActivity extends AppCompatActivity implements AppCategoriesFragment.ACFListener, AppsListFragment.ALFListener {
    ActivityMainBinding binding;
    String cate;
    DataServices.App apps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, new AppCategoriesFragment(),"AppCategoriesFragment")
                .commit();
    }

    @Override
    public void setAppCate(String category) {
        cate = category;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView,AppsListFragment.newInstance(cate),"AppsListFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendToDetails(DataServices.App app) {
        apps = app;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, AppDetailsFragment.newInstance(apps), "AppTag")
                .addToBackStack(null)
                .commit();
    }
}