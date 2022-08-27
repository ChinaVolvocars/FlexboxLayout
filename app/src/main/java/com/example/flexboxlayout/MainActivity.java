package com.example.flexboxlayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

  private RecyclerView mRecyclerView;
  private RecyclerView rvIcon;
  private ArrayList<FlexDto> list;
  private ArrayList<FlexIconDto> list2;
  private FlexAdapter mFlexAdapter;
  private FlexIconAdapter flexIconAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initViews();
    initDatas();
    initRvIcon();
    initEvents();
  }

  private void initViews() {
    mRecyclerView = findViewById(R.id.recycler_view);
    rvIcon = findViewById(R.id.rvIcon);
  }

  private void initDatas() {
    list = new ArrayList<FlexDto>();
    String[] testDatas = new String[]{"牙刷", "灭蚊器", "移动空调", "吸尘器", "布衣柜", "收纳箱书箱", "暑期美食满99减15", "挂烫机", "吸水拖把", "反季特惠"};
    for (int i = 0; i < testDatas.length; i++) {
      FlexDto channelBean = new FlexDto();
      channelBean.setName(testDatas[i]);
      //获取当前日期
      Calendar calendar = Calendar.getInstance();
      channelBean.setSearchDate(calendar.getTime());
      list.add(channelBean);
    }

    //设置布局管理器
    FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(MainActivity.this);
    //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal。
    flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);//主轴为水平方向，起点在左端。
    //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
    flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);//按正常方向换行
    //justifyContent 属性定义了项目在主轴上的对齐方式。
    flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);//交叉轴的起点对齐。

    mRecyclerView.setLayoutManager(flexboxLayoutManager);

    //设置适配器
    if (mFlexAdapter == null) {
      //设置适配器
      mFlexAdapter = new FlexAdapter(this, list);
      mRecyclerView.setAdapter(mFlexAdapter);
      //添加分割线
      //设置添加删除动画
      //调用ListView的setSelected(!ListView.isSelected())方法，这样就能及时刷新布局
      mRecyclerView.setSelected(true);
    } else {
      mFlexAdapter.notifyDataSetChanged();
    }
  }

  private void initRvIcon() {
    list2 = new ArrayList<FlexIconDto>();

    for (int i = 0; i < 21; i++) {
      FlexIconDto flexIconDto = new FlexIconDto();
      String str = "ic_" + i;
      int resID = MainActivity.this.getResources().getIdentifier(str, "mipmap", MainActivity.this.getPackageName());
      flexIconDto.setId(resID);
      list2.add(flexIconDto);
    }

    //设置布局管理器
    FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(MainActivity.this);
    //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal。
    flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);//主轴为水平方向，起点在左端。
    //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
    flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);//按正常方向换行
    //justifyContent 属性定义了项目在主轴上的对齐方式。
    flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);//交叉轴的起点对齐。

    rvIcon.setLayoutManager(flexboxLayoutManager);

    if (flexIconAdapter == null) {
      flexIconAdapter = new FlexIconAdapter(this, list2);
      rvIcon.setAdapter(flexIconAdapter);
      rvIcon.setSelected(true);
    } else {
      flexIconAdapter.notifyDataSetChanged();
    }
  }

  private void initEvents() {
    //列表适配器的点击监听事件
    mFlexAdapter.setOnItemClickLitener(new FlexAdapter.OnItemClickLitener() {
      @Override
      public void onItemClick(View view, int position) {
        Toast.makeText(MainActivity.this, list.get(position).getName(), Toast.LENGTH_SHORT).show();
      }
    });
  }
}