package sd_dtu.hollerandroid;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InterestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InterestsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View v;
    ListView listView;
    TextView textView1,textView2;
    String[] interests;

    public InterestsFragment() {
        // Required empty public constructor
    }

    public static InterestsFragment newInstance(String param1, String param2) {
        InterestsFragment fragment = new InterestsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_interests, container, false);
        textView1 = (TextView) v.findViewById(R.id.inter);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/SourceSansPro-Regular.ttf");
        textView1.setTypeface(custom_font);
        textView2 = (TextView) v.findViewById(R.id.event_text);
        textView2.setTypeface(custom_font);
        listView = (ListView)v.findViewById(R.id.list_items);
        interests = getResources().getStringArray(R.array.interests);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_multiple_choice,interests);
        listView.setAdapter(arrayAdapter);
        Button button = (Button) v.findViewById(R.id.submit_button);
        button.setTypeface(custom_font);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerContact serverContact = new ServerContact();
                serverContact.execute();
            }
        });
        return v;
    }

    class ServerContact extends AsyncTask<Void,Void,Void> {

        ProgressDialog progressDialog;

        @Override
        protected Void doInBackground(Void... voids) {
            int i=0;
            synchronized (this) {

                while (i < 7) {
                    try {
                        wait(1000);
                        i++;
                    } catch (Exception e) {

                    }
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Loading your Preferences....");
            progressDialog.setMax(10);
            progressDialog.setProgress(0);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // super.onPostExecute(aVoid);
            progressDialog.hide();
            Intent intent = new Intent(getActivity(),NoticesAndProfile.class);
            startActivity(intent);
        }
    }

}
