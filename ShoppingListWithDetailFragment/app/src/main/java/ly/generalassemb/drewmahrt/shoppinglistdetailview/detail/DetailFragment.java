package ly.generalassemb.drewmahrt.shoppinglistdetailview.detail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.shoppinglistdetailview.R;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.data.ShoppingItem;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.data.ShoppingSQLiteOpenHelper;

import static android.R.attr.id;

public class DetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private int mItemSelected;
    
    TextView mName, mDescription, mPrice, mType;

    private OnItemSelectedListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }
    
    public static DetailFragment newInstance(int item) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItemSelected = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ShoppingSQLiteOpenHelper helper = ShoppingSQLiteOpenHelper.getInstance(getContext());
        ShoppingItem item = helper.getItemById(mItemSelected);

        mName = (TextView)view.findViewById(R.id.detailName);
        mDescription = (TextView)view.findViewById(R.id.detailDescription);
        mPrice = (TextView)view.findViewById(R.id.detailPrice);
        mType = (TextView)view.findViewById(R.id.detailType);

        mName.setText(item.getName());
        mDescription.setText(item.getDescription());
        mPrice.setText(item.getPrice());
        mType.setText(item.getType());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            mListener = (OnItemSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnItemSelectedListener {
        // TODO: Update argument type and name
        void onItemClick(int itemID);
    }
}
