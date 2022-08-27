package com.example.flexboxlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FlexIconAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private Context myContext;
  private ArrayList<FlexIconDto> data;

  public FlexIconAdapter(Context context, ArrayList<FlexIconDto> data) {
    myContext = context;
    this.data = data;
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(myContext).inflate(R.layout.item_flex_icon, parent, false);
    ItemViewHolder itemViewHolder = new ItemViewHolder(view);
    return itemViewHolder;
  }

  static class ItemViewHolder extends RecyclerView.ViewHolder {
    LinearLayout root;
    ImageView iv;

    public ItemViewHolder(View view) {
      super(view);
      root = (LinearLayout) view.findViewById(R.id.root);
      iv = (ImageView) view.findViewById(R.id.iv);
    }


  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int index) {
    if (viewHolder instanceof ItemViewHolder) {
      FlexIconDto flexDto = data.get(index);
      final ItemViewHolder itemViewHold = ((ItemViewHolder) viewHolder);
      itemViewHold.iv.setImageResource(flexDto.getId());

      //这里用不到
			/*ViewGroup.LayoutParams lp = itemViewHold.listItemLayout.getLayoutParams();
			if (lp instanceof FlexboxLayoutManager.LayoutParams) {
				FlexboxLayoutManager.LayoutParams flexboxLp =
						(FlexboxLayoutManager.LayoutParams) itemViewHold.listItemLayout.getLayoutParams();
				flexboxLp.setFlexGrow(1.0f);
				flexboxLp.setAlignSelf(AlignSelf.FLEX_END);
			}*/

    }
  }

  public void addItem(int position, FlexIconDto item) {
    data.add(position, item);
    notifyItemInserted(position);
  }

  public void removeItem(int position) {
    data.remove(position);
    notifyItemRemoved(position);
  }

}