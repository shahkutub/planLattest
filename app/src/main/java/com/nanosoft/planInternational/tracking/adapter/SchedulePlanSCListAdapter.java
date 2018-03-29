package com.nanosoft.planInternational.tracking.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.database.model.PlantSC;

import java.util.List;

/**
 * Created by Nanosoft-Android on 3/16/2017.
 */

public class SchedulePlanSCListAdapter extends BaseAdapter {

    private Activity mContext;
    private List<PlantSC> mPlantSCList;


    public SchedulePlanSCListAdapter(Activity mContext, List<PlantSC> mPlantSCList) {
        this.mContext = mContext;
        this.mPlantSCList = mPlantSCList;
    }

    @Override
    public int getCount() {
        return mPlantSCList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlantSCList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mPlantSCList.get(position).getId();
    }
    static class ViewHolder {
        protected TextView text,textTwo,textThree;
        protected CheckBox checkbox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SchedulePlanSCListAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflator = mContext.getLayoutInflater();
            convertView = inflator.inflate(R.layout.schedule_raw, null);
            viewHolder = new SchedulePlanSCListAdapter.ViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.tv_sc_name_schedule);
            viewHolder.textTwo = (TextView) convertView.findViewById(R.id.tv_sc_id_schedule);
            viewHolder.textThree = (TextView) convertView.findViewById(R.id.tv_sc_age_schedule);
            viewHolder.checkbox = (CheckBox) convertView.findViewById(R.id.check_schedule);
            viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
                    mPlantSCList.get(getPosition).setSelected(buttonView.isChecked()); // Set the value of checkbox to maintain its state.
                }
            });
            viewHolder.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "HERE IS AZHAR", Toast.LENGTH_SHORT).show();
                }
            });
            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tv_sc_name_schedule, viewHolder.text);
            convertView.setTag(R.id.tv_sc_id_schedule, viewHolder.textTwo);
            convertView.setTag(R.id.tv_sc_age_schedule, viewHolder.textThree);
            convertView.setTag(R.id.check_schedule, viewHolder.checkbox);
        } else {
            viewHolder = (SchedulePlanSCListAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.checkbox.setTag(position); // This line is important.

        viewHolder.text.setText(mPlantSCList.get(position).getSc_Full_LegalName());
        viewHolder.textTwo.setText(mPlantSCList.get(position).getSc_Number());
        viewHolder.textThree.setText(mPlantSCList.get(position).getAge());
        viewHolder.checkbox.setChecked(mPlantSCList.get(position).isSelected());

        return convertView;
    }


}
