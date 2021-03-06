package com.example.apptest2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.io.IOException;

public class ChatboxFragment extends Fragment implements TextWatcher {

    View v;
    urlConnect uc;
    String id;
    String response;
    String question;
    String question_orig;
    String response_id = "0";
    String place_id = "0";
    JSONObject put_id = new JSONObject();
    private EditText messageEdit_orig;
    private View sendBtn, pickImgBtn;
    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private String messageEdit_trans;
    Translate translate;

    private static final String URL_PRODUCTS = "http://140.117.71.149/chat_history/get_chathistory.php";

    //???mianactivity????????????
    private static final String uId = "0";
    public ChatboxFragment() {
        // Requires empty public constructor
    }
    public static Fragment newInstance(String uid){
        ChatboxFragment fragment = new ChatboxFragment();
        Bundle bundle = new Bundle();
        bundle.putString( uId, uid);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //??????READ_EXTERNAL_STORAGE??????
        // ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 10);
        //?????????????????????Fragment???requestPermissions??????

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 10);
            v = inflater.inflate(R.layout.fragment_chatbox, container, false);
            initializeView();
        }else {
            v = inflater.inflate(R.layout.fragment_chatbox, container, false);
            initializeView();
        }

        return v;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        String string = s.toString().trim();
        if (string.isEmpty()) {
            resetMessageEdit();
        } else {
            sendBtn.setVisibility(View.VISIBLE);
            pickImgBtn.setVisibility(View.INVISIBLE);
        }

    }

    private void resetMessageEdit() {
        messageEdit_orig.removeTextChangedListener(this);

        messageEdit_orig.setText("");
        sendBtn.setVisibility(View.INVISIBLE);
        pickImgBtn.setVisibility(View.VISIBLE);

        messageEdit_orig.addTextChangedListener(this);
    }

    private void initializeView() {
        messageEdit_orig = v.findViewById(R.id.messageEdit);
        sendBtn = v.findViewById(R.id.sendBtn);
        pickImgBtn = v.findViewById(R.id.pickImgBtn);
        recyclerView = v.findViewById(R.id.recyclerView);

        messageEdit_orig.addTextChangedListener(this);
        messageAdapter = new MessageAdapter(getLayoutInflater());
        recyclerView.setAdapter(messageAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        //get chat history
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                (Response.Listener<String>) response -> {
                    try {
                        //converting the string to json array object
                        JSONArray array = new JSONArray(response);

                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {

                            //getting product object from json array
                            JSONObject getchathistoryitem = array.getJSONObject(i);

                            //user`s chat history
                            getActivity().runOnUiThread(() -> {
                                try {
                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("message", getchathistoryitem.getString("umessage"));
                                    jsonObject.put("isSent", true);
                                    jsonObject.put("show",false);
                                    messageAdapter.addItem(jsonObject);
                                    messageAdapter.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });

                            //robot`s chat history
                            getActivity().runOnUiThread(() -> {
                                try {
                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("message", getchathistoryitem.getString("rmessage"));
                                    jsonObject.put("isSent", false);
                                    jsonObject.put("show",false);
                                    messageAdapter.addItem(jsonObject);
                                    messageAdapter.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                (Response.ErrorListener) error -> {
                }) {
            //post?????????sql
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("uId", getArguments().getString(uId));
                return params;
            }

        };
        Volley.newRequestQueue(getContext()).add(stringRequest);

        //????????????
        sendBtn.setOnClickListener(v -> {

            //translate
            if (checkInternetConnection()) {

                //If there is internet connection, get translate service and start translation:
                getTranslateService();
                translate();

            } else {

                //If not, display "no connection" warning:
                Toast.makeText(getContext().getApplicationContext(),"Wrong",Toast.LENGTH_SHORT).show();
            }

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("message", messageEdit_orig.getText().toString());
                jsonObject.put("isSent", true);
                jsonObject.put("show",false);
                messageAdapter.addItem(jsonObject);
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount()-1);
                messageAdapter.notifyDataSetChanged();

                //Toast.makeText(getContext().getApplicationContext(),messageEdit_trans,Toast.LENGTH_SHORT).show();

                //?????????pycharm
                question = messageEdit_trans;
                question_orig = messageEdit_orig.getText().toString();
                uc = new urlConnect("http://140.117.71.149:5000/?input=" + question);
                uc.start();
                while (uc.getLockStatus()) {
                }
                id = uc.getResult();
                response_id = id.substring(0,id.indexOf(" "));
                place_id = id.substring(id.indexOf(" "));
                put_id.put(String.valueOf(messageAdapter.getItemCount()),place_id);
                Thread thread = new Thread(mutiThread);
                thread.start();

                resetMessageEdit();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        //????????????
        messageAdapter.setOnItemClickListener((v, position) ->{
            Toast.makeText(getActivity(),"????????????????????????",Toast.LENGTH_SHORT).show();

            String[] field = new String[2];
            field[0] = "uId";
            field[1] = "pId";

            String[] data = new String[2];
            data[0] = getArguments().getString(uId);
            data[1] = put_id.getString(String.valueOf(position));

            PutData putData = new PutData("http://140.117.71.149/favorite/writein_favorite.php/?", "POST", field, data);
            putData.startPut();
            putData.onComplete();
        });
    }

    private Runnable mutiThread = new Runnable() {
        public void run() {
            try {
                URL url = new URL("http://140.117.71.149/getresponse.php/?" + id);
                // ???????????? HTTP ???????????????????????????????????????????????????
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // ?????? Google ???????????? HttpURLConnection ??????
                connection.setRequestMethod("POST");
                // ????????????????????? POST
                connection.setDoOutput(true); // ????????????
                connection.setDoInput(true); // ????????????
                connection.setUseCaches(false); // ???????????????
                connection.connect(); // ????????????

                int responseCode =
                        connection.getResponseCode();
                // ???????????????????????????
                if (responseCode ==
                        HttpURLConnection.HTTP_OK) {
                    // ?????? HTTP ??????????????? OK ???????????? Error
                    InputStream inputStream =
                            connection.getInputStream();
                    // ??????????????????
                    BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                    // ???????????????????????????
                    String box = ""; // ?????????????????????
                    String line = null; // ????????????????????????
                    while ((line = bufReader.readLine()) != null) {
                        box += line;
                        // ???????????????????????????????????????????????????
                    }
                    inputStream.close(); // ??????????????????
                    response = box; // ????????????????????????????????????
                }
                // ??????????????????????????????????????????
                // ????????????????????????????????????
                // ?????? Json ????????????????????????????????????

                //????????????
                String[] field = new String[3];
                field[0] = "uId";
                field[1] = "umessage";
                field[2] = "rmessage";

                String[] data = new String[3];
                data[0] = getArguments().getString(uId);
                data[1] = question_orig;
                data[2] = response;
                PutData putData = new PutData("http://140.117.71.149/chat_history/chat_history.php/?", "POST", field, data);
                putData.startPut();
                putData.onComplete();

            } catch (Exception e) {
                response = e.toString(); // ?????????????????????????????????
            }

            getActivity().runOnUiThread(() -> {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("message", response);
                    jsonObject.put("isSent", false);
                    if(response_id.equals("7")||response_id.equals("8")){
                        jsonObject.put("show",true);
                        //Toast.makeText(getContext().getApplicationContext(),response_id + " test1",Toast.LENGTH_SHORT).show();
                    }else{
                        jsonObject.put("show",false);
                        //Toast.makeText(getContext().getApplicationContext(),response_id + " test2",Toast.LENGTH_SHORT).show();
                    }
                    messageAdapter.addItem(jsonObject);
                    recyclerView.smoothScrollToPosition(messageAdapter.getItemCount()-1);
                    messageAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        }
    };

    //google translation api
    public void getTranslateService() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try (InputStream is = getResources().openRawResource(R.raw.googletranslate)) {

            //Get credentials:
            final GoogleCredentials myCredentials = GoogleCredentials.fromStream(is);

            //Set credentials and get translate service:
            TranslateOptions translateOptions = TranslateOptions.newBuilder().setCredentials(myCredentials).build();
            translate = translateOptions.getService();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }

    public void translate() {

        //Get input text to be translated:
        String originalText = messageEdit_orig.getText().toString();
        Translation translation = translate.translate(originalText, Translate.TranslateOption.targetLanguage("en"), Translate.TranslateOption.model("base"));
        messageEdit_trans = translation.getTranslatedText();
        messageEdit_trans = String.valueOf(messageEdit_trans);

    }

    public boolean checkInternetConnection() {

        //Check internet connection:
        ConnectivityManager connectivityManager = (ConnectivityManager)  getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        //Means that we are connected to a network (mobile or wi-fi)
        boolean connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        return connected;
    }
}
