//package com.simpletodo.view;
//
//import android.graphics.drawable.Drawable;
//import android.support.v4.app.Fragment;
//import android.text.method.LinkMovementMethod;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.fansclub.FansApplication;
//import com.fansclub.R;
//import com.fansclub.model.CommentMeBean;
//import com.fansclub.ui.custom.LinkEnabledTextView;
//import com.fansclub.ui.custom.TimeTextView;
//import com.fansclub.ui.custom.roundeddrawable.RoundedImageView;
//import com.fansclub.util.LogUtil;
//import com.fansclub.util.TransitionUtil;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.WeakHashMap;
//
//public class TaskAdapter extends BaseAdapter implements View.OnClickListener {
//
//    private static final String TAG = TaskAdapter.class.getSimpleName();
//
//    public int NO_ITEM_ID = -1;
//    private final int TYPE_NORMAL = 0;
//
//    private Set<Integer> tagIndexList = new HashSet<Integer>();
//
//    protected List<CommentMeBean> bean;
//    protected Fragment fragment;
//    protected LayoutInflater inflater;
//    protected ListView listView;
//    protected boolean showOriStatus = true;
//    protected int checkedBG;
//    protected int defaultBG;
//    private WeakHashMap<ViewHolder, Drawable> bg = new WeakHashMap<ViewHolder, Drawable>();
//
//    public TaskAdapter(Fragment fragment, List<CommentMeBean> bean, ListView listView,
//                       boolean showOriStatus) {
//        this(fragment, bean, listView, showOriStatus, false);
//    }
//
//    public TaskAdapter(Fragment fragment, List<CommentMeBean> bean, ListView listView,
//                       boolean showOriStatus, boolean pre) {
//        if (showOriStatus) {
//            listView.setDivider(null);
//        }
//        this.bean = bean;
//        LogUtil.v(TAG, "commentListBean size " + bean.size());
//        LogUtil.v(TAG, "commentListBean" + bean.toString());
//        this.inflater = fragment.getActivity().getLayoutInflater();
//        this.listView = listView;
//        this.showOriStatus = showOriStatus;
//        this.fragment = fragment;
//        defaultBG = fragment.getResources().getColor(R.color.transparent);
//        checkedBG = FansApplication.getContext().getResources().getColor(R.color.blue);
//    }
//
//    @Override
//    public void onTextLinkClick(View textView, String clickedString) {
//        LogUtil.i(TAG, "debug2 clickedString " + clickedString);
//
//    }
//
//    protected List<CommentMeBean> getList() {
//        return bean;
//    }
//
//    @Override
//    public int getCount() {
//        if (getList() != null) {
////            LogUtil.v(TAG, "getCount "+getList().size());
//            return getList().size();
//        } else {
////            LogUtil.v(TAG, "getCount "+"0");
//            return 0;
//        }
//    }
//
//    @Override
//    public Object getItem(int position) {
//        if (position >= 0 && getList() != null && getList().size() > 0 && position < getList()
//                .size()) {
//            return getList().get(position);
//        }
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        if (getList() != null && getList().get(position) != null && null != getList().get(position).getFeed_id() && getList().size() > 0
//                && position < getList().size()) {
//            return Long.valueOf(getList().get(position).getFeed_id());
//        } else {
//            return NO_ITEM_ID;
//        }
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return TYPE_NORMAL;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
//
//        if (convertView == null) {
//            int itemViewType = getItemViewType(position);
//            View view = inflater.inflate(R.layout.listview_atme_item_layout, parent, false);
//            LogUtil.v(TAG, "new convertView " + position);
//            convertView = view;
//            holder = buildHolder(convertView);
//            convertView.setTag(holder);
//            tagIndexList.add(R.drawable.ic_launcher_color + getItemViewType(position));
//
//        } else {
//            holder = (ViewHolder) convertView
//                    .getTag();
//        }
//
//        bindViewData(holder, position);
//
//        return convertView;
//    }
//
//    private ViewHolder buildHolder(View convertView) {
//        ViewHolder holder = new ViewHolder();
//        holder.username = (TextView) convertView.findViewById(R.id.atme_list_item_username);//浣滆�
//        holder.atme_list_recomment.setOnClickListener(this);
//        return holder;
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.atme_listview_root:
//                ViewHolder viewHolder1 = (ViewHolder) view.getTag();
//                break;
//            default:
//                break;
//        }
//    }
//
//    static class ViewHolder {
//
//    }
//
//    protected void bindViewData(final ViewHolder holder, int position) {
//        holder.position = position;
//        try {
//            final CommentMeBean msg = bean.get(position);
//            holder.messageBean = msg;
//
//            if (null != msg.getContent()) {
//                holder.content.gatherweiboLinksForText(msg.getContent());//鍐呭
//                holder.content.setOnTextLinkClickListener(this);
//                holder.content.setMovementMethod(LinkMovementMethod.getInstance());
//            }
//            holder.time.setTime(msg.getCreate_time());//鏃堕棿
//            if (showOriStatus) {
//                holder.comment_count.setText(String.valueOf(msg.getComment_ct()));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
