package ly.generalassemb.drewmahrt.shoppinglistdetailview.detail;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.shoppinglistdetailview.R;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.data.ShoppingItem;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.data.ShoppingSQLiteOpenHelper;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.list.ShoppingListAdapter;

public class DetailActivity extends AppCompatActivity implements DetailFragment.OnItemSelectedListener{

    public static final String ITEM_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int id = getIntent().getIntExtra(ITEM_ID, -1);
        if (id == -1) {
            finish();
        }

        DetailFragment detailFragment = DetailFragment.newInstance(id);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, detailFragment);
        fragmentTransaction.commit();

    }


    @Override
    public void onItemClick(int itemID) {

    }
}

