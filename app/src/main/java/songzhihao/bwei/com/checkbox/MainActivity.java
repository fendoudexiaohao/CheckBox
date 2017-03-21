package songzhihao.bwei.com.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lv;
    private List<Bean> list;
    private Button checkall;
    private Button hind;
    private Button nocheckall;
    private MyAdapter myAdapter;
    private Button fanxuan;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
    }

    private void initView() {
        //找到ListView的控件
        lv = (ListView) findViewById(R.id.lv);
        //添加头
        View view = View.inflate(MainActivity.this, R.layout.button, null);
        lv.addHeaderView(view);
        //创建集合
        list = new ArrayList<>();
        //找到四个Button的控件
        checkall = (Button) view.findViewById(R.id.Checkall);
        hind = (Button) view.findViewById(R.id.Hind);
        nocheckall = (Button) view.findViewById(R.id.Nocheckall);
        fanxuan = (Button) view.findViewById(R.id.fanxuan);
        //添加数据
        for (int i =0;i<50;i++){
            Bean bean = new Bean();
            bean.Item = "item"+i;
            bean.flag = false;
            list.add(bean);
        }
        //适配器
        myAdapter = new MyAdapter();
        lv.setAdapter(myAdapter);
        //四个按钮的监听
        checkall.setOnClickListener(this);
        hind.setOnClickListener(this);
        nocheckall.setOnClickListener(this);
        fanxuan.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //全选的监听
            case R.id.Checkall:
                for (int i = 0;i<list.size();i++){
                    list.get(i).setFlag(true);
                }
                //刷新适配器
                myAdapter.notifyDataSetChanged();
                break;
            //反选的监听
            case R.id.fanxuan:
                for (int i = 0;i<list.size();i++){
                    list.get(i).setFlag(!list.get(i).isFlag());
                }
                //刷新适配器
                myAdapter.notifyDataSetChanged();
                break;

            case R.id.Hind:
               /* for (int i = 0;i<list.size();i++){
                    if (list.get(i).isFlag() == true){
                        list.get(i).setFlag(false);
                    }
                }*/

                break;/* View v = View.inflate(MainActivity.this, R.layout.item, null);
                View ch = v.findViewById(R.id.checkbox_);
                flag++;
                if (flag == 1) {
                    ch.setVisibility(View.VISIBLE);
                }else{
                    ch.setVisibility(View.GONE);
                    flag=0;
                }
                //刷新适配器
                myAdapter.notifyDataSetChanged();*/
            //全不选的监听
            case R.id.Nocheckall:
                for (int i = 0;i<list.size();i++){
                    list.get(i).setFlag(false);
                }
                //刷新适配器
                myAdapter.notifyDataSetChanged();
                break;

        }
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View converview, ViewGroup viewGroup) {
            ViewHolder holder;
            if (converview == null){
                converview = View.inflate(MainActivity.this, R.layout.item, null);
                holder = new ViewHolder();
                holder.tv = (TextView) converview.findViewById(R.id.tv);
                holder.checkBox = (CheckBox)converview.findViewById(R.id.checkbox_);
                converview.setTag(holder);
            }else{
                holder = (ViewHolder) converview.getTag();
            }
            final Bean bean = list.get(position);
            holder.tv.setText(bean.Item);
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked){
                        bean.flag = true;
                    }else{
                        bean.flag = false;
                    }
                }
            });
            holder.checkBox.setChecked(bean.flag);
            return converview;
        }
    }

    class ViewHolder{
        TextView tv;
        CheckBox checkBox;
    }
}
