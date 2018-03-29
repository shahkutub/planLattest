package com.nanosoft.planInternational.tracking.adapter;

/**
 * Created by Nanosoft-Android on 3/27/2018.
 */

//public class FamalyInfoAdapter extends RecyclerView.Adapter<FamalyInfoAdapter.MyViewHolder> {
//
//    private List <SCFamilyInfos> listFamily = new ArrayList<>();
//    private Context con;
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public EditText et3_2, et3_4, et3_6;
//        public Spinner sp3_5,sp3_5_a,sp3_7,sp3_8,sp3_9;
//
//        public MyViewHolder(View view) {
//            super(view);
//            et3_2 = (EditText) view.findViewById(R.id.et3_2);
//            et3_4 = (EditText) view.findViewById(R.id.et3_4);
//            et3_6 = (EditText) view.findViewById(R.id.et3_6);
//
//            sp3_5 = (Spinner)view.findViewById(R.id.sp3_5);
//            sp3_5_a = (Spinner)view.findViewById(R.id.sp3_5_a);
//            sp3_7 = (Spinner)view.findViewById(R.id.sp3_7);
//            sp3_8 = (Spinner)view.findViewById(R.id.sp3_8);
//            sp3_9 = (Spinner)view.findViewById(R.id.sp3_9);
//
//        }
//    }
//
//    public FamalyInfoAdapter(Context con,List<SCFamilyInfos> moviesList) {
//        this.con = con;
//        this.listFamily = moviesList;
//    }
//
//    @Override
//    public FamalyInfoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.raw_family_info, parent, false);
//
//        return new MyViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, int position) {
//
//        final SCFamilyInfos familyInfos = listFamily.get(position);
//
//        holder.et3_2.setText(familyInfos.getMember_full_name()+"");
//        holder.et3_4.setText(familyInfos.getMember_birth_year()+"");
//        holder.et3_6.setText(familyInfos.getMember_is_primary_carer()+"");
//
//        String[] arraysp3_5 = new String[] {
//                "Select Relationship",
//                "Mother",
//                "Sister",
//                "Brother",
//                "Twin Sister",
//                "Twin Brother",
//                "Aunt",
//                "Uncle",
//                "Uncle",
//                "Grandfather",
//                "Step Mother",
//                "Step Father"
//        };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(con,
//                R.layout.spinner_item_family, arraysp3_5);
//        adapter.setDropDownViewResource(R.layout.spinner_item_family);
//        holder.sp3_5.setAdapter(adapter);
//
//        holder.sp3_5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(familyInfos.getMember_relationship().equalsIgnoreCase(holder.sp3_5.getSelectedItem().toString())){
//                    holder.sp3_5.setSelection(position);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//        String[] arraysp3_5_a = new String[] {
//                "Select Gender",
//                "Male",
//                "Female"
//        };
//
//
//        ArrayAdapter<String> adapter3_5_a = new ArrayAdapter<String>(con,
//                R.layout.spinner_item_family, arraysp3_5_a);
//        adapter.setDropDownViewResource(R.layout.spinner_item_family);
//        holder.sp3_5_a.setAdapter(adapter3_5_a);
//
//        holder.sp3_5_a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(familyInfos.getMember_gender().equalsIgnoreCase(holder.sp3_5_a.getSelectedItem().toString())){
//                    holder.sp3_5_a.setSelection(position);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//        String[] arraysp3_7 = new String[] {
//                "Select Occupation",
//                "Not Applicable",
//                "Peasant Farmer",
//                "Fisherman",
//                "Craftsman",
//                "Street Hawker",
//                "Policeman",
//                "Driver",
//                "Miner",
//                "Factory Worker",
//                "Cook",
//                "Cleaner",
//                "Teacher",
//                "Shoe Shiner",
//                "Body Massage",
//                "Self Employed",
//                "Working in Private Sector",
//                "Working forces (military service)",
//                "Recycling Work",
//                "Village Doctor",
//                "Small business owner",
//                "Migrant/overseas worker",
//                "Civil defence force",
//                "Local grape picker"
//
//        };
//        ArrayAdapter<String> adaptersp3_7 = new ArrayAdapter<String>(con,
//                R.layout.spinner_item_family, arraysp3_7);
//        adapter.setDropDownViewResource(R.layout.spinner_item_family);
//        holder.sp3_7.setAdapter(adaptersp3_7);
//
//
//        holder.sp3_7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(familyInfos.getMember_occupation().equalsIgnoreCase(holder.sp3_7.getSelectedItem().toString())){
//                    holder.sp3_7.setSelection(position);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//
//        String[] arraysp3_8 = new String[] {
//                "Select Reason left household",
//                "To seek employment",
//                "To live with relatives",
//                "To study",
//                "Due to Marriage",
//                "Separation from spouse",
//                "For more fertile land",
//                "For religious instruction ",
//                "Family separation",
//                "Due to war",
//                "unknown",
//                "Death"
//
//        };
//        ArrayAdapter<String> adaptersp3_8 = new ArrayAdapter<String>(con,
//                R.layout.spinner_item_family, arraysp3_8);
//        adapter.setDropDownViewResource(R.layout.spinner_item_family);
//        holder.sp3_8.setAdapter(adaptersp3_8);
//
//        holder.sp3_8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                if(!TextUtils.isEmpty(familyInfos.getNo_longer_in_household())){
//                    if(familyInfos.getNo_longer_in_household().equalsIgnoreCase(holder.sp3_8.getSelectedItem().toString())){
//                        holder.sp3_8.setSelection(position);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//        String[] arraysp3_9 = new String[] {
//                "Select Reasons Joined",
//                "New Bornt",
//                "Returned – study",
//                "Returned – relatives",
//                "Returned – military service",
//                "Returned - harvest",
//                "Lost own Home",
//                "Marriage",
//                "Family Request",
//                "Separation from spouse",
//                "Previously omitted",
//                "Returned – for better work opportunities"
//
//        };
//        ArrayAdapter<String> adaptersp3_9 = new ArrayAdapter<String>(con,
//                R.layout.spinner_item_family, arraysp3_9);
//        adapter.setDropDownViewResource(R.layout.spinner_item_family);
//        holder.sp3_8.setAdapter(adaptersp3_9);
//
//
//        holder.sp3_9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(!TextUtils.isEmpty(familyInfos.getReason_family_lives_with_SC())){
//                    if(familyInfos.getReason_family_lives_with_SC().equalsIgnoreCase(holder.sp3_9.getSelectedItem().toString())){
//                        holder.sp3_9.setSelection(position);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//    }
//
//
//
//    @Override
//    public int getItemCount() {
//       return listFamily.size();
//    }
//}
