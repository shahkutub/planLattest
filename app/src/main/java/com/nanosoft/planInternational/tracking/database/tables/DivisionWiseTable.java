package com.nanosoft.planInternational.tracking.database.tables;

/**
 * Created by Nanosoft-Android on 4/16/2017.
 */

public class DivisionWiseTable {


    public static final String TBL_VILLAGE = "tbl_village";
    public static final String COL_VILLAGE_ID = "id";
    public static final String COL_WARDS_FOREIGN_KEY = "ward_id";
    public static final String COL_VILLAGE_NAME = "village_name";
    public static final String COL_VILLAGE_CREATED_BY = "created_by";
    public static final String COL_VILLAGE_MODIFIED_BY = "modified_by";


    public static final String CREATE_TBL_VILLAGE = "CREATE TABLE " + TBL_VILLAGE + " ( " +
            COL_VILLAGE_ID + " INTEGER PRIMARY KEY, " +
            COL_WARDS_FOREIGN_KEY + " INTEGER, " +
            COL_VILLAGE_NAME + " TEXT, " +
            COL_VILLAGE_CREATED_BY + " TEXT, " +
            COL_VILLAGE_MODIFIED_BY +
            " TEXT, division_id INTEGER, district_id INTEGER, upazila_id INTEGER, union_id INTEGER)";


/*CREATE TABLE `tbl_village` (
  `id` int(10) UNSIGNED NOT NULL,
  `union_id` int(11) NOT NULL,
  `village_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `created_by` int(11) NOT NULL,
  `modified_by` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;*/

    public static final String TBL_UNINION = "tbl_union";
    public static final String COL_UNION_ID = "id";
    public static final String COL_UPAZILA_FOREIGN_KEY = "upazila_id";
    public static final String COL_UNION_NAME = "union_name";
    public static final String COL_UNION_CREATED_BY = "created_by";
    public static final String COL_UNION_MODIFIED_BY = "modified_by";




    public static final String CREATE_TBL_UNION = "CREATE TABLE " + TBL_UNINION + " ( " +
            COL_UNION_ID + " INTEGER PRIMARY KEY, " +
            COL_UPAZILA_FOREIGN_KEY + " INTEGER, " +
            COL_UNION_NAME + " TEXT, " +
            COL_UNION_CREATED_BY + " TEXT, " +
            COL_UNION_MODIFIED_BY + " TEXT, division_id INTEGER, district_id INTEGER)";



/*CREATE TABLE `tbl_union` (
  `id` int(10) UNSIGNED NOT NULL,
  `upazila_id` int(11) NOT NULL,
  `union_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `created_by` int(11) NOT NULL,
  `modified_by` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
*/

    public static final String TBL_UPAZILA = "tbl_upazila";
    public static final String COL_UPAZILA_ID = "id";
    public static final String COL_DISTRICT_FOREIGN_KEY = "district_id";
    public static final String COL_UPAZILA_NAME = "upazila_name";

    public static final String COL_UPAZILA_CREATED_BY = "created_by";
    public static final String COL_UPAZILA_MODIFIED_BY = "modified_by";




    public static final String CREATE_TBL_UPAZILA = "CREATE TABLE " + TBL_UPAZILA + " ( " +
            COL_UPAZILA_ID + " INTEGER PRIMARY KEY, " +
            COL_DISTRICT_FOREIGN_KEY + " INTEGER, " +
            COL_UPAZILA_NAME + " TEXT, " +
            COL_UPAZILA_CREATED_BY + " TEXT, " +
            COL_UPAZILA_MODIFIED_BY + " TEXT, " +
            " division_id INTEGER)";

/*CREATE TABLE `tbl_upazila` (
  `id` int(10) UNSIGNED NOT NULL,
  `district_id` int(11) NOT NULL,
  `upazila_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `created_by` int(11) NOT NULL,
  `modified_by` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
*/
/* "id": 1,
          "division_id": 1,
          "district_name": "Barguna",
          "created_by": 2,
          "modified_by": 2*/

    public static final String TBL_DISTRICT = "tbl_district";

    public static final String COL_DIST_ID = "id";
    public static final String COL_DIV_FOREIGN_KEY = "division_id";
    public static final String COL_DIST_NAME = "district_name";
    public static final String COL_DIST_CREATED_BY = "created_by";
    public static final String COL_DIST_MODIFIED_BY = "modified_by";





    public static final String CREATE_TBL_DISTRICT = " CREATE TABLE " + TBL_DISTRICT + " ( " +
            COL_DIST_ID + " INTEGER PRIMARY KEY, " +
            COL_DIV_FOREIGN_KEY + " TEXT, " +
            COL_DIST_NAME + " TEXT, " +
            COL_DIST_CREATED_BY + " TEXT, " +
            COL_DIST_MODIFIED_BY + " TEXT )";


    /* `id` int(10) UNSIGNED NOT NULL,
  `division_id` int(11) NOT NULL,
  `district_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `created_by` int(11) NOT NULL,
  `modified_by` int(11) NOT NULL*/

    public static final String TBL_DIVISION = "tbl_division";
    public static final String COL_DIV_ID = "id";
    public static final String COL_DIV_NAME = "division_name";
    public static final String COL_COUNTRY_FOREIGN_KEY = "country_id";

    public static final String COL_DIV_CREATED_BY = "created_by";
    public static final String COL_DIV_MODIFIED_BY = "modified_by";




    public static final String CREATE_TBL_DIVISION = "CREATE TABLE " + TBL_DIVISION + " ( " +
            COL_DIV_ID + " INTEGER PRIMARY KEY, " +
           // COL_COUNTRY_FOREIGN_KEY + " TEXT, " +
            COL_DIV_NAME + " TEXT, " +
            COL_DIV_CREATED_BY + " TEXT, " +
            COL_DIV_MODIFIED_BY + " TEXT )";


    /* `id` int(10) UNSIGNED NOT NULL,
  `division_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `created_by` int(11) NOT NULL,
  `modified_by` int(11) NOT NULL*/

}

