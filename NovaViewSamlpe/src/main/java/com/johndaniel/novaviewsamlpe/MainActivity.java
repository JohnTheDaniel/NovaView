package com.johndaniel.novaviewsamlpe;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.os.Build;
import android.widget.Toast;
import com.johndaniel.novaviewlib.NovaView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }
        View fragmentView;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            fragmentView = rootView;
            NovaView novaView = (NovaView) rootView.findViewById(R.id.novaview);

            novaView.setDay(1);
            novaView.setMode(2);
            novaView.setSchoolClass("n2c");
            novaView.setSchoolId("52550");
            novaView.setWeekNo(7);

            novaView.loadSchoolUrl();

            Toast.makeText(getActivity(), "Width = " + novaView.getWidth() + " Height = " + novaView.getHeight(), Toast.LENGTH_LONG).show();
            return rootView;
        }

        @Override
        public void onStart() {
            super.onStart();
            final NovaView novaView = (NovaView) fragmentView.findViewById(R.id.novaview);
            ViewTreeObserver viewTree = novaView.getViewTreeObserver();
            if (viewTree != null) {
                viewTree.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    public boolean onPreDraw() {
                        int finalHeight = novaView.getMeasuredHeight();
                        int finalWidth = novaView.getMeasuredWidth();
                        novaView.viewHeight = finalHeight;
                        novaView.viewWidth = finalWidth;

                        return true;
                    }
                });
            }

            novaView.setDay(1);
            novaView.setMode(2);
            novaView.setSchoolClass("n2c");
            novaView.setSchoolId("52550");
            novaView.setWeekNo(7);

            novaView.loadSchoolUrl();
        }
    }

}
