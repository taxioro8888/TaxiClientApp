package com.taxi.oro.cliente.fragment;



import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.taxi.oro.R;
import com.taxi.oro.cliente.activity.MainActivity;
import com.taxi.oro.cliente.adapter.LanguageAdapter;
import com.taxi.oro.cliente.model.LanguagePojo;
import com.taxi.oro.cliente.model.M;
import com.taxi.oro.cliente.onclick.ClickListener;
import com.taxi.oro.cliente.onclick.RecyclerTouchListener;
import com.taxi.oro.cliente.settings.ConnectionDetector;
import com.taxi.oro.cliente.settings.LocaleManager;

import java.util.ArrayList;
import java.util.List;

public class FragmentLanguage extends Fragment{

    ViewPager pager;
    TabLayout tabs;
    View view;
    public static Context context;
    public static ConnectionDetector connectionDetector;
    String TAG="FragmentHome";
    ArrayList<String> tabNames = new ArrayList<String>();
    int currpos=0;

    private RecyclerView recycler_view_language;
    private List<LanguagePojo> albumList_language;
    private LanguageAdapter adapter_language;
    private TextView apply;
    String lang = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null)
            currpos = getArguments().getInt("tab_pos",0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_language, container, false);

        context=getActivity();
        if(M.getCountry(context).equals("All"))
            MainActivity.setTitle(getResources().getString(R.string.item_language));
        else
            MainActivity.setTitle(getResources().getString(R.string.item_language)+" - "+M.getCountry(context));
        connectionDetector=new ConnectionDetector(context);

        albumList_language = new ArrayList<>();
        adapter_language = new LanguageAdapter(context, albumList_language, getActivity());

        apply = (TextView) view.findViewById(R.id.apply);
        recycler_view_language = (RecyclerView) view.findViewById(R.id.recycler_view_langue);
        @SuppressLint("WrongConstant") LinearLayoutManager horizontalLayoutManagerGarde = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recycler_view_language.setLayoutManager(horizontalLayoutManagerGarde);
        recycler_view_language.setItemAnimator(new DefaultItemAnimator());
        recycler_view_language.setAdapter(adapter_language);
        recycler_view_language.addOnItemTouchListener(new RecyclerTouchListener(context, recycler_view_language, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                LanguagePojo languagePojo = albumList_language.get(position);
                lang = languagePojo.getName();
            }

            @Override
            public void onLongClick(View view, int position) {
                LanguagePojo languagePojo = albumList_language.get(position);
                lang = languagePojo.getName();
            }
        }));

        setLanguage();
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!lang.equals("")){
                    MainActivity.selectedLanguage(lang);
                }else{
                    Toast.makeText(context, getResources().getString(R.string.please_select_a_language), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void setLanguage(){
        albumList_language.add(new LanguagePojo(1,"English", getResources().getDrawable(R.drawable.ic_uk), "no"));
        albumList_language.add(new LanguagePojo(2,"Fran??ais", getResources().getDrawable(R.drawable.ic_france), "no"));
        albumList_language.add(new LanguagePojo(3,"??????", getResources().getDrawable(R.drawable.ic_china), "no"));
        albumList_language.add(new LanguagePojo(4,"?????????", getResources().getDrawable(R.drawable.ic_japan), "no"));
        albumList_language.add(new LanguagePojo(5,"??????", getResources().getDrawable(R.drawable.ic_saudi_arabia), "no"));
        albumList_language.add(new LanguagePojo(6,"??????????????????", getResources().getDrawable(R.drawable.ic_india), "no"));
        albumList_language.add(new LanguagePojo(7,"Espa??ol", getResources().getDrawable(R.drawable.ic_spain), "no"));
        albumList_language.add(new LanguagePojo(8,"????????????????????????", getResources().getDrawable(R.drawable.ic_bangladesh), "no"));
        albumList_language.add(new LanguagePojo(9,"Deutschland", getResources().getDrawable(R.drawable.ic_germany), "no"));
        albumList_language.add(new LanguagePojo(10,"????????????", getResources().getDrawable(R.drawable.ic_north_korea), "no"));
        albumList_language.add(new LanguagePojo(11,"Portugal", getResources().getDrawable(R.drawable.ic_portugal), "no"));
        albumList_language.add(new LanguagePojo(12,"????????????", getResources().getDrawable(R.drawable.ic_russia), "no"));

        String local = ""+LocaleManager.getLocale(context.getResources())+"";
        if((local.equals("en"))){
            lang = "English";
            albumList_language.get(0).setStatus("yes");
        }else if(local.equals("fr")){
            lang = "Fran??ais";
            albumList_language.get(1).setStatus("yes");
        }else if(local.equals("bo")){
            lang = "??????";
            albumList_language.get(2).setStatus("yes");
        }else if(local.equals("ja")){
            lang = "?????????";
            albumList_language.get(3).setStatus("yes");
        }else if(local.equals("ar")){
            lang = "??????";
            albumList_language.get(4).setStatus("yes");
        }else if(local.equals("hi")){
            lang = "??????????????????";
            albumList_language.get(5).setStatus("yes");
        }else if(local.equals("es")) {
            lang = "Espa??ol";
            albumList_language.get(6).setStatus("yes");
        }else if(local.equals("bn")) {
            lang = "????????????????????????";
            albumList_language.get(7).setStatus("yes");
        }else if(local.equals("de")) {
            lang = "Deutschland";
            albumList_language.get(8).setStatus("yes");
        }else if(local.equals("ko")) {
            lang = "????????????";
            albumList_language.get(9).setStatus("yes");
        }else if(local.equals("pt")) {
            lang = "Portugal";
            albumList_language.get(10).setStatus("yes");
        }else if(local.equals("ru")) {
            lang = "????????????";
            albumList_language.get(11).setStatus("yes");
        }
        adapter_language.notifyDataSetChanged();
    }
}
