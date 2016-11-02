package ly.generalassemb.drewmahrt.shoppinglistdetailview.list;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ly.generalassemb.drewmahrt.shoppinglistdetailview.R;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.data.ShoppingItem;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.data.ShoppingSQLiteOpenHelper;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.detail.DetailActivity;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.detail.DetailFragment;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity implements ShoppingListAdapter.OnItemSelectedListener, DetailFragment.OnItemSelectedListener{

    private boolean mTwoPane;
    private List<ShoppingItem> mShoppingList;
    private ShoppingListAdapter mAdapter;
    private ShoppingSQLiteOpenHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null){
            mTwoPane = true;
        }
        else {
            mTwoPane = false;
        }

        //Setup the RecyclerView
        RecyclerView shoppingListRecyclerView = (RecyclerView) findViewById(R.id.shopping_list_recyclerview);
        shoppingListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mHelper =ShoppingSQLiteOpenHelper.getInstance(this);
        mShoppingList = mHelper.getShoppingList();
        mAdapter = new ShoppingListAdapter(mShoppingList, this);
        shoppingListRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onItemSelected(int itemID) {

        if (mTwoPane){
            DetailFragment detailFragment = DetailFragment.newInstance(itemID);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, detailFragment);
            fragmentTransaction.commit();
        }
        else{
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.ITEM_ID, itemID);
            startActivity(intent);
        }

    }

    @Override
    public void onItemClick(int itemID) {

    }
}
