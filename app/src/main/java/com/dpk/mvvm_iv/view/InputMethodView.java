package com.dpk.mvvm_iv.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dpk.mvvm_iv.MyApplication;
import com.dpk.mvvm_iv.R;


/**
 * 输入法
 */
public class InputMethodView extends RelativeLayout implements
        OnClickListener {
    public final static int SHOW_NUMBER = 1;
    public final static int SHOW_CHAR = 2;
    public final static int SHOW_CHINESE = 3;
    private View layoutView;
    private TextView tvChinese, tvChar, tvNum;
    private Button btDel, btNull, btConfirm;
    private InputMethodListener mInputMethodListener;
    private String[] mArraylist;
    private GridView mGridview;
    private GridViewiInputAdapter mAdapter;

    public void setInputListener(InputMethodListener inputMethodListener) {
        this.mInputMethodListener = inputMethodListener;
    }

    public InputMethodView(Context context) {
        super(context);
        initView(context);
    }

    public InputMethodView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public InputMethodView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        layoutView = View.inflate(context, R.layout.inputmethod_layout, null);

        this.tvChinese = (TextView) layoutView.findViewById(R.id.tv_inputview_chinese);
        this.tvChar = (TextView) layoutView.findViewById(R.id.tv_inputview_char);
        this.tvNum = (TextView) layoutView.findViewById(R.id.tv_inputview_num);

        this.btDel = (Button) layoutView.findViewById(R.id.bt_inputview_del);
        this.btNull = (Button) layoutView.findViewById(R.id.bt_inputview_null);
        this.btConfirm = (Button) layoutView.findViewById(R.id.bt_inputview_confirm);

        this.mGridview = (GridView) layoutView.findViewById(R.id.gride_inputview_details);
        mArraylist = getResources().getStringArray(R.array.province);
        tvChinese.setOnClickListener(this);
        tvChar.setOnClickListener(this);
        tvNum.setOnClickListener(this);
        btDel.setOnClickListener(this);
        btNull.setOnClickListener(this);
        btConfirm.setOnClickListener(this);

        mAdapter = new GridViewiInputAdapter(MyApplication.getInst(), mArraylist, 4, mInputMethodListener);
        mGridview.setAdapter(mAdapter);

        this.addView(layoutView);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_inputview_chinese:
                if (tvChinese.getText().toString().trim().equals("皖A")) {
                    if (mInputMethodListener != null) {
                        mInputMethodListener.inputMessage(new StringBuffer("皖A"));
                    }
                } else if (tvChinese.getText().toString().trim().equals("数字")) {
                    show(SHOW_NUMBER);
                } else if (tvChinese.getText().toString().trim().equals("汉字")) {
                    show(SHOW_CHINESE);
                }
                break;
            case R.id.tv_inputview_char:
                if (tvChar.getText().toString().trim().equals("字母")) {
                    show(SHOW_CHAR);
                } else if (tvChar.getText().toString().trim().equals("A")) {
                    if (mInputMethodListener != null) {
                        mInputMethodListener.inputMessage(new StringBuffer("A"));
                    }
                }
                break;
            case R.id.tv_inputview_num:
                if (tvNum.getText().toString().trim().equals("0")) {
                    if (mInputMethodListener != null) {
                        mInputMethodListener.inputMessage(new StringBuffer("0"));
                    }
                } else if (tvNum.getText().toString().trim().equals("汉字")) {
                    show(SHOW_CHINESE);
                } else if (tvNum.getText().toString().trim().equals("数字")) {
                    show(SHOW_NUMBER);
                }
                break;
            case R.id.bt_inputview_del:
                if (mInputMethodListener != null) {
                    mInputMethodListener.delete();
                }
                break;
            case R.id.bt_inputview_null:
                if (mInputMethodListener != null) {
                    mInputMethodListener.deleteAll();
                }
                break;
            case R.id.bt_inputview_confirm:
                if (mInputMethodListener != null) {
                    mInputMethodListener.inputMethodConfirm();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 显示输入法
     */
    public void show(int type) {
//        tvChinese.setTextColor(type == SHOW_CHINESE ? (App.getAppResources().getColor(R.color.black)) : (App.getAppResources().getColor(R.color.white)));
//        tvChar.setTextColor(type == SHOW_CHAR ? (App.getAppResources().getColor(R.color.black)) : (App.getAppResources().getColor(R.color.white)));
//        tvNum.setTextColor(type == SHOW_NUMBER ? (App.getAppResources().getColor(R.color.black)) : (App.getAppResources().getColor(R.color.white)));
//
//        tvChinese.setBackgroundDrawable(type == SHOW_CHINESE ? App.getAppResources().getDrawable(R.drawable.selector_bt_white_gray) : App.getAppResources().getDrawable(R.drawable.selector_bt_blue));
//        tvChar.setBackgroundDrawable(type == SHOW_CHAR ? App.getAppResources().getDrawable(R.drawable.selector_bt_white_gray) : App.getAppResources().getDrawable(R.drawable.selector_bt_blue));
//        tvNum.setBackgroundDrawable(type == SHOW_NUMBER ? App.getAppResources().getDrawable(R.drawable.selector_bt_white_gray) : App.getAppResources().getDrawable(R.drawable.selector_bt_blue));

        switch (type) {
            case SHOW_NUMBER:// 显示数字
                mArraylist = getResources().getStringArray(R.array.number);
                mAdapter = new GridViewiInputAdapter(MyApplication.getInst(), mArraylist, 3, mInputMethodListener);
                mGridview.setAdapter(mAdapter);
                mGridview.setNumColumns(3);
                mAdapter.notifyDataSetChanged();
                tvChinese.setText("汉字");
                tvChar.setText("字母");
                tvNum.setText("0");
                tvChinese.setTextColor(ContextCompat.getColor(MyApplication.getInst(), R.color.white));
                tvChar.setTextColor(ContextCompat.getColor(MyApplication.getInst(), R.color.white));
                tvNum.setTextColor(ContextCompat.getColor(MyApplication.getInst(), R.color.hfcb_gray));
                tvChinese.setBackgroundDrawable(ContextCompat.getDrawable(MyApplication.getInst(), R.drawable.selector_bt_blue));
                tvChar.setBackgroundDrawable(ContextCompat.getDrawable(MyApplication.getInst(), R.drawable.selector_bt_blue));
                tvNum.setBackgroundDrawable(ContextCompat.getDrawable(MyApplication.getInst(), R.drawable.selector_bt_white_gray));
                break;

            case SHOW_CHAR:// 显示字母
                mArraylist = MyApplication.getInst().getResources().getStringArray(R.array.english);
                mAdapter = new GridViewiInputAdapter(MyApplication.getInst(), mArraylist, 4, mInputMethodListener);
                mGridview.setAdapter(mAdapter);
                mGridview.setNumColumns(4);
                mAdapter.notifyDataSetChanged();
                tvNum.setText("数字");
                tvChar.setText("A");
                tvChinese.setText("汉字");
                tvChinese.setTextColor(ContextCompat.getColor(MyApplication.getInst(), R.color.white));
                tvChar.setTextColor(ContextCompat.getColor(MyApplication.getInst(), R.color.hfcb_gray));
                tvNum.setTextColor(ContextCompat.getColor(MyApplication.getInst(), R.color.white));
                tvChinese.setBackgroundDrawable(ContextCompat.getDrawable(MyApplication.getInst(), R.drawable.selector_bt_blue));
                tvChar.setBackgroundDrawable(ContextCompat.getDrawable(MyApplication.getInst(), R.drawable.selector_bt_white_gray));
                tvNum.setBackgroundDrawable(ContextCompat.getDrawable(MyApplication.getInst(), R.drawable.selector_bt_blue));
                break;
            case SHOW_CHINESE:// 显示汉字
                mArraylist = MyApplication.getInst().getResources().getStringArray(R.array.province);
                mAdapter = new GridViewiInputAdapter(MyApplication.getInst(), mArraylist, 4, mInputMethodListener);
                mGridview.setAdapter(mAdapter);
                mGridview.setNumColumns(4);
                mAdapter.notifyDataSetChanged();
                tvChinese.setText("皖A");
                tvChar.setText("字母");
                tvNum.setText("数字");
                tvChinese.setTextColor(ContextCompat.getColor(MyApplication.getInst(), R.color.hfcb_gray));
                tvChar.setTextColor(ContextCompat.getColor(MyApplication.getInst(), R.color.white));
                tvNum.setTextColor(ContextCompat.getColor(MyApplication.getInst(), R.color.white));
                tvChinese.setBackgroundDrawable(ContextCompat.getDrawable(MyApplication.getInst(), R.drawable.selector_bt_white_gray));
//                tvChinese.setBackgroundDrawable(App.getAppResources().getDrawable(R.drawable.selector_bt_red));
                tvChar.setBackgroundDrawable(ContextCompat.getDrawable(MyApplication.getInst(), R.drawable.selector_bt_blue));
                tvNum.setBackgroundDrawable(ContextCompat.getDrawable(MyApplication.getInst(), R.drawable.selector_bt_blue));
                break;
            default:
                break;
        }
    }

    /**
     * 输入法事件监听
     *
     * @author huangzhong E-mail:mcloyal@163.com
     * @version 创建时间：2014-10-15 上午9:42:19
     */
    public interface InputMethodListener {
        /**
         * 输入的内容
         */
        void inputMessage(StringBuffer inputString);

        /**
         * 删除光标焦点的前一个字符
         */
        void delete();

        /**
         * 删除全部字符信息
         */
        void deleteAll();

        /**
         * 确定
         */
        void inputMethodConfirm();
    }

    private class GridViewiInputAdapter extends BaseAdapter {
        Context context;
        private String[] arraylist;//键盘内容
        private int colnum;
        private InputMethodListener inputMethodListener;

        public GridViewiInputAdapter(Context context, String[] arraylist, int colnum, InputMethodListener inputMethodListener) {
            this.arraylist = arraylist;
            this.context = context;
            this.colnum = colnum;
            this.inputMethodListener = inputMethodListener;
        }

        public int getCount() {
            return arraylist.length;
        }

        public Object getItem(int position) {
            return arraylist[position];
        }

        public long getItemId(int position) {
            return position;
        }

        //        @SuppressWarnings("deprecation") // 不显示使用了不赞成使用的类或方法时的警告
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            int itemHeight;
            if (convertView == null) {
                holder = new ViewHolder();
//                convertView = LayoutInflater.from(context).inflate(
//                        R.layout.item_grid_keyboard, null);
                convertView = View.inflate(context, R.layout.item_grid_keyboard, null);
                int itemWidth = context.getResources().getDisplayMetrics().widthPixels / colnum; // Calculate
                itemHeight = itemWidth - 30;
                if (colnum == 3) {
                    itemHeight = context.getResources().getDisplayMetrics().widthPixels / 4 - 30;
                }

//                itemHeight = itemHeight - 10;
//                itemWidth = itemWidth - 10;
//                120 90   160 90
                AbsListView.LayoutParams param = new AbsListView.LayoutParams(
                        itemWidth, itemHeight);
                convertView.setLayoutParams(param);
                holder.textview = (TextView) convertView
                        .findViewById(R.id.textview_id);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.textview.setText(arraylist[position]);
            convertView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.selector_bt_white));
            convertView.setClickable(true);
            convertView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (inputMethodListener != null) {
                        inputMethodListener.inputMessage(new StringBuffer(arraylist[position]));
                    }
                }
            });
            return convertView;
        }

        class ViewHolder {
            public TextView textview;

        }

    }

    public void onDestroy() {
        if (mInputMethodListener != null) {
            mInputMethodListener = null;
        }
    }



//    if (event.getKeyCode() == 66) {
//        // 确认
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            String str = etCarNumNum.getText().toString().trim();
//            if (StringUtils.isCarNum(str)) {
//                goPark();
//            } else {
//            }
//        }
//        return true;
//    } else if (event.getKeyCode() == 67) {
//        // 清除
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            int index = etCarNumNum.getSelectionStart();
//            Editable editable = etCarNumNum.getText();
//
//            if (index > 0) {
//                editable.delete(index - 1, index);
//            }
//            switch (index) {
//                case 1: // 删光了 那就显示汉字
//                    inputMethodView.show(InputMethodView.SHOW_CHINESE);
//                    break;
//                case 2: // 删除一个之后 里面还有一个 那就显示 字母吧
//                    inputMethodView.show(InputMethodView.SHOW_CHAR);
//                    break;
//            }
//        }
//        return true;
//    } else if (event.getKeyCode() == 4) {
//        // 取消
////            if (event.getAction() == KeyEvent.ACTION_DOWN) {
////                finish();
////            }
//
//////            TODO: 2017/8/21 若是手机 返回键就是确认驶入
//        // 确认
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            String str = etCarNumNum.getText().toString().trim();
//            if (StringUtils.isCarNum(str)) {
//                goPark();
//            } else {
//            }
//        }
//
//
//        return true;
//    } else {
//        return super.dispatchKeyEvent(event);
//    }
}
