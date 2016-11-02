package ly.generalassemb.drewmahrt.shoppinglistdetailview.list;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ly.generalassemb.drewmahrt.shoppinglistdetailview.data.ShoppingItem;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.detail.DetailActivity;

/**
 * Created by drewmahrt on 10/21/16.
 */

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingItemViewHolder> {
    private OnItemSelectedListener mOnItemSelectedListener;
    List<ShoppingItem> mShoppingItems;

    public interface OnItemSelectedListener{
        void onItemSelected(int itemID);
    }

    public ShoppingListAdapter(List<ShoppingItem> shoppingItems, OnItemSelectedListener onItemSelectedListener) {
        mShoppingItems = shoppingItems;
        mOnItemSelectedListener = onItemSelectedListener;


    }

    @Override
    public ShoppingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1,parent,false);
        return new ShoppingItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShoppingItemViewHolder holder, final int position) {

        final ShoppingItem item = mShoppingItems.get(position);
        final int id = mShoppingItems.get(position).getId();
        String name = mShoppingItems.get(position).getName();

        holder.mNameTextView.setText(mShoppingItems.get(position).getName());

        holder.mNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemSelectedListener.onItemSelected(item.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mShoppingItems.size();
    }
}
