package com.example.flexboxlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FlexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private Context myContext;
  private ArrayList<FlexDto> data;

  public FlexAdapter(Context context, ArrayList<FlexDto> data) {
    myContext = context;
    this.data = data;
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(myContext).inflate(R.layout.item_flex, parent, false);
    ItemViewHolder itemViewHolder = new ItemViewHolder(view);
    return itemViewHolder;
  }

  static class ItemViewHolder extends RecyclerView.ViewHolder {
    public ItemViewHolder(View view) {
      super(view);
      listItemLayout = (LinearLayout) view.findViewById(R.id.listitem_layout);
      mTitle = (TextView) view.findViewById(R.id.tv_title);
    }

    LinearLayout listItemLayout;
    TextView mTitle;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int index) {
    if (viewHolder instanceof ItemViewHolder) {
      FlexDto flexDto = data.get(index);
      final ItemViewHolder itemViewHold = ((ItemViewHolder) viewHolder);
      itemViewHold.mTitle.setText(flexDto.getName());

      //这里用不到
			/*ViewGroup.LayoutParams lp = itemViewHold.listItemLayout.getLayoutParams();
			if (lp instanceof FlexboxLayoutManager.LayoutParams) {
				FlexboxLayoutManager.LayoutParams flexboxLp =
						(FlexboxLayoutManager.LayoutParams) itemViewHold.listItemLayout.getLayoutParams();
				flexboxLp.setFlexGrow(1.0f);
				flexboxLp.setAlignSelf(AlignSelf.FLEX_END);
			}*/

      //如果设置了回调，则设置点击事件
      if (mOnItemClickLitener != null) {
        itemViewHold.listItemLayout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            int position = itemViewHold.getLayoutPosition();//在增加数据或者减少数据时候，position和index就不一样了
            mOnItemClickLitener.onItemClick(itemViewHold.listItemLayout, position);
          }
        });
      }

    }
  }

  /**
   * 添加Item--用于动画的展现
   */
  public void addItem(int position, FlexDto listitemBean) {
    data.add(position, listitemBean);
    notifyItemInserted(position);
  }

  /**
   * 删除Item--用于动画的展现
   */
  public void removeItem(int position) {
    data.remove(position);
    notifyItemRemoved(position);
  }

  /*=====================添加OnItemClickListener回调================================*/
  public interface OnItemClickLitener {
    void onItemClick(View view, int position);
  }

  private OnItemClickLitener mOnItemClickLitener;

  public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
    this.mOnItemClickLitener = mOnItemClickLitener;
  }
}