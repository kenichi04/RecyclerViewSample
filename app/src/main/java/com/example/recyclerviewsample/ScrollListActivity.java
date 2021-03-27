package com.example.recyclerviewsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrollListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbarLayout);
        toolbarLayout.setTitle(getString(R.string.toolbar_title));
        toolbarLayout.setExpandedTitleColor(Color.WHITE);
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY);

        RecyclerView lvMenu = findViewById(R.id.lvMenu);
        // LinearLayoutManagerオブジェクト生成
        LinearLayoutManager layout = new LinearLayoutManager(ScrollListActivity.this);
        // RecyclerViewにレイアウトマネージャーとしてLinearLayoutManagerを設定
        lvMenu.setLayoutManager(layout);
        // 定食メニューリスト生成
        List<Map<String, Object>> menuList = createTeishokuList();
        // アダプタオブジェクト生成
        RecyclerListAdapter adaper = new RecyclerListAdapter(menuList);
        // RecyclerViewにアダプタオブジェクト設定
        lvMenu.setAdapter(adaper);

        // 区切り線用のオブジェクト生成、第2引数ではLinearLayoutManagerの表示方向と合わせている
        DividerItemDecoration decorator = new DividerItemDecoration(ScrollListActivity.this, layout.getOrientation());
        lvMenu.addItemDecoration(decorator);

    }

    //menulist作成メソッド
    //priceを数値にするため、Mapのバリューは Object型。
    private List<Map<String, Object>> createTeishokuList() {
        //Listオブジェクト生成
        List<Map<String, Object>> menuList = new ArrayList<>();

        //Listに格納するデータを作成、追加していく
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "唐揚げ定食");
        menu.put("price", 800);
        menu.put("desc", "若鶏の唐揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        //ここから上の繰り返し。menu変数を再利用。
        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", 850);
        menu.put("desc", "手ごねハンバーグにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        //ここから繰り返し。
        menu = new HashMap<>();
        menu.put("name", "生姜焼き定食");
        menu.put("price", 850);
        menu.put("desc", "特製ソースの生姜焼きにサラダ、ご飯とお味噌汁がつきます");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ステーキ定食");
        menu.put("price", 1000);
        menu.put("desc", "国産牛のステーキにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ステーキ定食");
        menu.put("price", 1000);
        menu.put("desc", "国産牛のステーキにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ステーキ定食");
        menu.put("price", 1000);
        menu.put("desc", "国産牛のステーキにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ステーキ定食");
        menu.put("price", 1000);
        menu.put("desc", "国産牛のステーキにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        return menuList;
    }

    private class RecyclerListViewHolder extends RecyclerView.ViewHolder {

        /* リスト1行分中でメニュー名を表示する */
        public TextView _tvMenuName;
        /* リスト1行分中でメニュー金額を表示する画面部品 */
        public TextView _tvMenuPrice;

        /**
         * コンストラクタ
         * @param itemView　リスト1行分の画面部品
         */
        public RecyclerListViewHolder(View itemView) {
            super(itemView);
            // 引数で渡されたリスト1行分の画面部品中から表示に使われるTextView取得
            _tvMenuName = itemView.findViewById(R.id.tvMenuName);
            _tvMenuPrice = itemView.findViewById(R.id.tvMenuPrice);
        }
    }

    private class RecyclerListAdapter  extends RecyclerView.Adapter<RecyclerListViewHolder> {

        /* リストデータを保持 */
        private List<Map<String, Object>> _listData;

        /**
         * コンストラクタ
         * @param listData リストデータ
         */
        private RecyclerListAdapter(List<Map<String, Object>> listData) {
            _listData = listData;
        }

        @NonNull
        @Override
        public RecyclerListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // レイアウトインフレータ取得
            LayoutInflater inflater = LayoutInflater.from(ScrollListActivity.this);
            // row.xmlをインフレートし、1行分の画面部品とする
            View view = inflater.inflate(R.layout.row, parent, false);
            // インフレートされた1行分の画面部品にリスナ設定
            view.setOnClickListener(new ItemClickListener());
            // ビューホルダオブジェクト生成
            RecyclerListViewHolder holder = new RecyclerListViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerListViewHolder holder, int position) {
            // リストデータから該当1行分のデータを取得
            Map<String, Object> item = _listData.get(position);
            String menuName = (String) item.get("name");
            int menuPrice = (Integer) item.get("price");
            String menuPriceStr = String.valueOf(menuPrice);

            holder._tvMenuName.setText(menuName);
            holder._tvMenuPrice.setText(menuPriceStr);
        }

        @Override
        public int getItemCount() {
            return _listData.size();
        }
    }

    private class ItemClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // LinearLayout内のタップされたTextView取得
            TextView tvMenuName = view.findViewById(R.id.tvMenuName);
            String menuName = tvMenuName.getText().toString();
            String msg = getString(R.string.msg_header) + menuName;
            Toast.makeText(ScrollListActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }

}