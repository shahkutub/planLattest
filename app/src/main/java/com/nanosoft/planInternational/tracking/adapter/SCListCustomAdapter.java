package com.nanosoft.planInternational.tracking.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.database.manager.DatabaseManager;
import com.nanosoft.planInternational.tracking.database.model.ScInfoModel;
import com.nanosoft.planInternational.tracking.model.SCPriorityListModel;
import com.nanosoft.planInternational.tracking.utility.AppControler;
import com.nanosoft.planInternational.tracking.utility.Operation;
import com.nanosoft.planInternational.tracking.utility.ResponseCode;
import com.nanosoft.planInternational.tracking.view.activity.DetailActivity;

import java.util.ArrayList;

/**
 * Created by Nanosoft-Android on 4/22/2017.
 */

public class SCListCustomAdapter extends RecyclerView.Adapter<SCListCustomAdapter.MyViewHolder> implements Filterable {

    //private final String photoUrl = "http://203.190.0.102:3000/public/uploads/scProfilePhoto/";
  //  private final String photoUrl = "http://45.64.135.228/benefit_tracking/public/uploads/scProfilePhoto/";
    private final String photoUrl =Operation.BaseUrl+ "/public/uploads/scProfilePhoto/";

    private Context context;
    private ArrayList<ScInfoModel> sponsoredChildInfoArrayList= new ArrayList<>();
    public ArrayList<ScInfoModel> filtered_items = new ArrayList<>();
    private int responseCode;
    private int i = 0;
    public static ArrayList<SCPriorityListModel> cbList = new ArrayList<>();
    private SCPriorityListModel scPriorityListModel;
    RecyclerView recyclerView;
    ItemFilter mFilters = new ItemFilter();
    Button submitBtn;
    private DatabaseManager databaseManager;
    String date;


    public SCListCustomAdapter(Context context, ArrayList<ScInfoModel> sponsoredChildInfoArrayList, int responseCode,RecyclerView recyclerView) {
        this.context = context;
        this.sponsoredChildInfoArrayList = sponsoredChildInfoArrayList;
        this.filtered_items = sponsoredChildInfoArrayList;
        this.responseCode = responseCode;
        this.recyclerView = recyclerView;

    }

    public SCListCustomAdapter(Context context, ArrayList<ScInfoModel> sponsoredChildInfoArrayList, int responseCode,RecyclerView recyclerView,String date) {
        this.context = context;
        this.sponsoredChildInfoArrayList = sponsoredChildInfoArrayList;
        this.filtered_items = sponsoredChildInfoArrayList;
        this.responseCode = responseCode;
        this.recyclerView = recyclerView;
        this.date = date;

    }
    public SCListCustomAdapter(Context context, ArrayList<ScInfoModel> sponsoredChildInfoArrayList, int responseCode,RecyclerView recyclerView,Button submitBtn) {
        this.context = context;
        this.sponsoredChildInfoArrayList = sponsoredChildInfoArrayList;
        this.filtered_items = sponsoredChildInfoArrayList;
        this.responseCode = responseCode;
        this.recyclerView = recyclerView;
        this.submitBtn=submitBtn;
    }

    public SCListCustomAdapter(ArrayList<ScInfoModel> sponsoredChildInfoArrayList) {
        this.sponsoredChildInfoArrayList = sponsoredChildInfoArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_sc_list_model, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        // Define the Index we wish to scroll to.
       // final int lIndex = 0;
// Assign the RecyclerView's LayoutManager.
      //  recyclerView.setLayoutManager(getLinearLayoutManager());
// Scroll the RecyclerView to the Index.
       // recyclerView.getLinearLayoutManager().smoothScrollToPosition(context.getRecyclerView(), new RecyclerView.State(), lIndex);

       // recyclerView.getLayoutManager().scrollToPosition(position);
     //   recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView,new RecyclerView.State(),position);
        databaseManager = new DatabaseManager(context);
        Intent intent = new Intent(context,SCListCustomAdapter.class);

       int scInfoTableId = intent.getIntExtra("ID", 1);
       ScInfoModel scInfoModel = databaseManager.getScProfile(scInfoTableId);

        final ScInfoModel sponsoredChildInfo = filtered_items.get(position);

        holder.scNameTv.setText(sponsoredChildInfo.getScInfoScFullName());
        holder.scIdTv.setText("ID: "+sponsoredChildInfo.getScInfoScNumber() + "");
        holder.scAgeTv.setText(sponsoredChildInfo.getScInfoScAge());

      //  holder.statusTV.setText(scInfoModel.getJoining_date());

        Log.e("Joining_date",""+sponsoredChildInfo.getJoining_date());

        if(!TextUtils.isEmpty(sponsoredChildInfo.getJoining_date())&&(!sponsoredChildInfo.getJoining_date().equalsIgnoreCase("null"))){
            int   reminderMonths= Operation.joiningDateDifference(context,sponsoredChildInfo.getJoining_date());

            if(reminderMonths<9){
                holder.statusTV.setText("Survey");

            }else if(reminderMonths>9){
                holder.statusTV.setText("Update and Survey ");
            }
        }else{
            holder.statusTV.setText("Update and Survey ");
            //holder.statusTV.setVisibility(View.GONE);
        }




        holder.scCheck.setOnCheckedChangeListener(null);
        holder.scCheck.setChecked(sponsoredChildInfo.isSelected());

        boolean check;
        holder.scCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set your object's last status
                sponsoredChildInfo.setSelected(isChecked);

//               for(int i = 0; i<sponsoredChildInfoArrayList.size();i++){
//                   if(sponsoredChildInfoArrayList.get(i).isSelected()==true){
//                       submitBtn.setVisibility(View.VISIBLE);
//                   }
//               }


               if(isChecked==true){
                   submitBtn.setVisibility(View.VISIBLE);
               }

            }
        });




       /* if (sponsoredChildInfo.dateTime.isEmpty())
            holder.dateTV.setVisibility(View.GONE);
        else holder.dateTV.setVisibility(View.VISIBLE);
        holder.scCheck.setChecked(sponsoredChildInfo.isSelected());
        holder.scCheck.setVisibility(sponsoredChildInfo.CheckBoxVisible);*/

        holder.scCheck.setTag(sponsoredChildInfo);

        if (sponsoredChildInfo.getPhoto().length() > 0) {
            Glide.with(AppControler.getInstance().getApplicationContext()).load(photoUrl + sponsoredChildInfo.getPhoto()).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(holder.scPhotoIv);
        } else {
            holder.scPhotoIv.setImageResource(R.drawable.circle_2);
        }

        if (responseCode == ResponseCode.RESPONSE_CODE_ALL_SC_LIST) {
            holder.scCheck.setVisibility(View.GONE);
            holder.dateTV.setVisibility(View.GONE);

            if (sponsoredChildInfo.getPriorityFlag().equals("1")) {
                holder.cardViewSCList.setBackgroundColor(Color.parseColor("#c7c1f2"));
            } else {
                holder.cardViewSCList.setBackgroundColor(Color.WHITE);
            }


        } else if (responseCode == ResponseCode.RESPONSE_CODE_TO_BE_SCHEDULE) {

            holder.scCheck.setVisibility(View.VISIBLE);
            holder.dateTV.setVisibility(View.GONE);

            holder.scCheck.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    ScInfoModel sponsoredChildInfo1 = (ScInfoModel) cb.getTag();
                    //  sponsoredChildInfo1.setSelected(cb.isChecked());
                    int id = sponsoredChildInfo1.getScInfoTableId();

                    if (holder.scCheck.isChecked()) {
                        scPriorityListModel = new SCPriorityListModel(id, i, position);
                        cbList.add(scPriorityListModel);
                        //   Toast.makeText(context, cbList.get(i).getIdPosition() + "", Toast.LENGTH_SHORT).show();
                        i++;
                    } else {
                        i--;
                        for (int j = 0; j < cbList.size(); j++) {
                            if (position == cbList.get(j).getListPosition()) {
                                //  Toast.makeText(context, cbList.get(j).getIdPosition() + "", Toast.LENGTH_SHORT).show();
                                cbList.remove(j);
                                break;
                            }
                        }

                    }
                }
            });

        } else if (responseCode == ResponseCode.RESPONSE_CODE_SC_SCHEDULED) {

            holder.scCheck.setVisibility(View.GONE);
            holder.dateTV.setVisibility(View.VISIBLE);
            holder.dateTV.setText("Date: "+sponsoredChildInfo.getDateFlag());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    int id = sponsoredChildInfo.getScInfoTableId();
//                    Intent intent = new Intent(context, DetailActivity.class);
//                    intent.putExtra("ID", id);
//                    context.startActivity(intent);


                     dialogueScAvailablity(id,position);


                }
            });
        }
    }

    private void dialogueScAvailablity(final int id, final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

       // builder.setTitle("Confirm");
        builder.setMessage("Dose SC available for survey?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("ID", id);
                context.startActivity(intent);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                databaseManager.priorityUpdate(id,"0","");
                sponsoredChildInfoArrayList.remove(position);
                notifyDataSetChanged();


                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }


    public ArrayList<ScInfoModel> getSelectedPeople() {
        ArrayList<ScInfoModel> ps = new ArrayList<>();
        for (ScInfoModel p : sponsoredChildInfoArrayList) {

            if (p.isSelected()) {
                p.setSelected(false);
                p.CheckBoxVisible = View.GONE;
                ps.add(p);
            }
        }
        return ps;
    }


    @Override
    public int getItemCount() {
        return filtered_items.size();
    }
    @Override
    public Filter getFilter() {
        return mFilters;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {


        public ImageView scPhotoIv;
        public TextView scNameTv;
        public TextView scIdTv;
        public TextView dateTV;
        public TextView statusTV;
        public TextView scAgeTv;
        public CheckBox scCheck;

        public CardView cardViewSCList;


        public MyViewHolder(View view) {
            super(view);

            scPhotoIv = (ImageView) view.findViewById(R.id.scPhotoIv);
            scNameTv = (TextView) view.findViewById(R.id.scNameTv);
            scIdTv = (TextView) view.findViewById(R.id.scIdTv);
            scAgeTv = (TextView) view.findViewById(R.id.scAgeTv);
            dateTV = (TextView) view.findViewById(R.id.dateTV);
            statusTV = (TextView) view.findViewById(R.id.statusTv);
            scCheck = (CheckBox) view.findViewById(R.id.scCheck);

            cardViewSCList = (CardView) view.findViewById(R.id.cardViewSCList);
        }
    }

    public void updateData(ArrayList<ScInfoModel> scInfoModels) {
        sponsoredChildInfoArrayList.clear();
        sponsoredChildInfoArrayList.addAll(scInfoModels);
        notifyDataSetChanged();
    }

    public void addItem(int position, ScInfoModel scInfoModels) {
        sponsoredChildInfoArrayList.add(position, scInfoModels);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        sponsoredChildInfoArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public class ItemFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String query = charSequence.toString().toLowerCase();
            FilterResults results = new FilterResults();
            final ArrayList<ScInfoModel> list = sponsoredChildInfoArrayList;
            final ArrayList<ScInfoModel> result_list = new ArrayList<>(list.size());
            for (int i = 0; i < list.size(); i++){
                int title = list.get(i).getScInfoScNumber();
                String str_title=title+"";
                if (str_title.toLowerCase().contains(query)){
                    result_list.add(list.get(i));
                }
            }
            results.values = result_list;
            results.count = result_list.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            filtered_items = (ArrayList<ScInfoModel>) filterResults.values;
            notifyDataSetChanged();

        }
    }

}
