package com.example.instagram;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

        private EditText edtprofilename, edtprofilebio, edtprofileprofession, edtprofilehobby,
                            edtprofilefovsport;
        private Button btnupdateinfo;
    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtprofilename = view.findViewById(R.id.edtprofilename);
        edtprofilebio = view.findViewById(R.id.edtbio);
        edtprofileprofession = view.findViewById(R.id.edtprofession);
        edtprofilehobby = view.findViewById(R.id.edthobbies);
        edtprofilefovsport = view.findViewById(R.id.edtfovsport);

        btnupdateinfo = view.findViewById(R.id.btnupdateinfo);

         final ParseUser parseuser = ParseUser.getCurrentUser();

    if(
    parseuser.get("profilename") != null &&
    parseuser.get("profilebio") != null &&
    parseuser.get("profileprofession") != null &&
    parseuser.get("profileshobby") != null &&
    parseuser.get("profilefovsport") != null
    )
    {
        edtprofilename.setText(parseuser.get("profilename").toString());
        edtprofilebio.setText(parseuser.get("profilebio").toString());
        edtprofileprofession.setText(parseuser.get("profileprofession").toString());
        edtprofilehobby.setText(parseuser.get("profileshobby").toString());
        edtprofilefovsport.setText(parseuser.get("profilefovsport").toString());
    }

      btnupdateinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Updating your credentials, wait for a while !");
                progressDialog.show();

                parseuser.put("profilename", edtprofilename.getText().toString());
                parseuser.put("profilebio", edtprofilebio.getText().toString());
                parseuser.put("profileprofession",edtprofileprofession.getText().toString());
                parseuser.put("profileshobby",edtprofilehobby.getText().toString());
                parseuser.put("profilefovsport", edtprofilefovsport.getText().toString());

                parseuser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(getContext() , "Info updated successfully",
                                    Toast.LENGTH_SHORT, FancyToast.INFO,
                                    true).show();

                        }else{
                            FancyToast.makeText(getContext() , e.getMessage() + " ",
                                    Toast.LENGTH_LONG, FancyToast.ERROR,
                                    true).show();

                        }
                            progressDialog.dismiss();
                    }
                });

            }
        });
        return view;

    }
}
