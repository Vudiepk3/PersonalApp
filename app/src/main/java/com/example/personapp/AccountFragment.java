package com.example.personapp;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AccountFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ImageView imgDel,imgEdit;
    private TextView txtID, txtUsername,txtName ,txtPhone ,txtBirtday,
                    txtAddress,txtSex,txtEmail,txtFunction;
    String luuThongTin="account";
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public AccountFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        Calendar calendar =Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        imgEdit = view.findViewById(R.id.imgEdit);
        txtUsername = view.findViewById(R.id.txtUsername);
        txtID = view.findViewById(R.id.txtID);
        txtPhone = view.findViewById(R.id.txtPhone);
        txtAddress = view.findViewById(R.id.txtAddress);
        txtSex = view.findViewById(R.id.txtSex);
        txtBirtday = view.findViewById(R.id.txtBirthday);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtFunction = view.findViewById(R.id.txtFunction);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_information);
                Window window = dialog.getWindow();
                if(window==null){
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams windowacc = window.getAttributes();
                windowacc.gravity = Gravity.NO_GRAVITY ;
                window.setAttributes(windowacc);
                Button btnCancel = dialog.findViewById(R.id.btnCancel);
                Button btnAdd = dialog.findViewById(R.id.btnAdd_KH);
                btnAdd.setText("CẬP NHẬT");
                EditText ed_name = dialog.findViewById(R.id.edName);
                EditText ed_phone = dialog.findViewById(R.id.edPhone);
                EditText ed_address = dialog.findViewById(R.id.edAddress);
                EditText ed_sex = dialog.findViewById(R.id.edSex);
                EditText ed_email = dialog.findViewById(R.id.edEmail);
                EditText ed_function = dialog.findViewById(R.id.edFunction);
                EditText ed_birthday = dialog.findViewById(R.id.edBirthday);
                TextView txt = dialog.findViewById(R.id.txtTitel);

                ImageView img = dialog.findViewById(R.id.imgdate);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog dialog1 = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                int myear = year ;
                                int mmonth = month ;
                                int mdayOfMonth = dayOfMonth ;
                                GregorianCalendar c = new GregorianCalendar(myear,mmonth,mdayOfMonth);
                                ed_birthday.setText(sdf.format(c.getTime()));
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
                        dialog1.show();
                    }
                });
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ed_name.getText().length()==0||
                                ed_phone.getText().length()==0||
                                ed_address.getText().length()==0||
                                ed_sex.getText().length()==0||
                                ed_email.getText().length()==0||
                                ed_function.getText().length()==0||
                                ed_birthday.getText().length()==0){
                            Toast.makeText(getActivity(),"Không được để trống", Toast.LENGTH_SHORT).show();
                        }else if(!(isValidFormat("dd/MM/yyyy",ed_birthday.getText().toString()))){
                            Toast.makeText(getActivity(),"Không đúng định dạng ngày",Toast.LENGTH_SHORT).show();
                        } else {
                            txtUsername.setText(ed_name.getText().toString());
                            txtName.setText(ed_name.getText().toString());
                            txtPhone.setText(ed_phone.getText().toString());
                            txtSex.setText(ed_sex.getText().toString());
                            txtAddress.setText(ed_address.getText().toString());
                            txtEmail.setText(ed_email.getText().toString());
                            txtFunction.setText(ed_function.getText().toString());
                            txtBirtday.setText(ed_birthday.getText().toString());
                            Toast.makeText(getActivity(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
        return view;
    }
    public boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = (Date) sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }

}