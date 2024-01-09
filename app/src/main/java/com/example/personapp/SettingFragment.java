package com.example.personapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SettingFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private TextView txtLogout,txtFeedback,txtEvaluate,txtShare;
    private RelativeLayout account;
    public SettingFragment() {
        // Required empty public constructor
    }
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        txtLogout = view.findViewById(R.id.txtLogout);
        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder backRequest = new AlertDialog.Builder(getActivity());
                backRequest.setTitle("Xác nhận đăng xuất");
                backRequest.setMessage("Bạn có chắc chắn muốn đăng xuất");
                backRequest.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast toast = Toast.makeText(getActivity(), "Đăng xuất thành công", Toast.LENGTH_SHORT);
                        toast.show();
                        // Toast sẽ hiển thị thông báo “Đăng nhập thành công” trong 2 giây.
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getActivity().finish();
                                Intent login = new Intent(getActivity(), LoginActivity.class);
                                startActivity(login);
                            }
                        }, 1000); // 2000 milliseconds = 2 seconds
                    }
                });

                backRequest.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                backRequest.create().show();
            }
        });
        account = view.findViewById(R.id.account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  account = new Intent(getActivity(),AccountActivity.class);
                startActivity(account);
            }
        });
        txtEvaluate = view.findViewById(R.id.txtEvaluate);
        txtEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent evaluate = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.dhcn.MyHAUI&pcampaignid=web_share"));
                startActivity(evaluate);
            }
        });
        txtFeedback = view.findViewById(R.id.txtFeedback);
        txtFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "vulq2k3@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
                    intent.putExtra(Intent.EXTRA_TEXT, "Hi");
                    startActivity(Intent.createChooser(intent, "Send mail..."));
                } catch(Exception e) {
                    Toast.makeText(getActivity(), "Sorry...You don't have any mail app", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        txtShare = view.findViewById(R.id.txtShare);
        txtShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                intent.putExtra(Intent.EXTRA_TEXT, "This is my text");
                startActivity(Intent.createChooser(intent, "Hãy chia sẻ đến với mọi người"));
            }
        });
        return view;
    }
}