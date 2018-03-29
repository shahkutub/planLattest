package com.nanosoft.planInternational.tracking.database.model;

/**
 * Created by Nanosoft-Android on 4/17/2017.
 */

public class MenuPermission {

    private int id;
    private int role_id;
    private int menu_id;

    public MenuPermission(int id, int role_id, int menu_id) {
        this.id = id;
        this.role_id = role_id;
        this.menu_id = menu_id;
    }

    public MenuPermission(int role_id, int menu_id) {
        this.role_id = role_id;
        this.menu_id = menu_id;
    }

    public int getId() {
        return id;
    }

    public int getRole_id() {
        return role_id;
    }

    public int getMenu_id() {
        return menu_id;
    }
}
