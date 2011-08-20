package com.mycompany.jgrepolis;

import com.mycompany.jgrepolis.model.Resources;
import com.mycompany.jgrepolis.model.User;

public class SystemResources {
    
    private static SystemResources instance;
    
    private static final String cityCod[][] = {
        {"0", "96044", "Born"},
        {"1", "96630", "Cidade de Felipelo"},
        {"2", "5752", "Edirme"},
        {"3", "96245", "Last Born"},
        {"4", "6621", "new3"},
        {"5", "96379", "Poor City"},
        {"6", "6394", "Roma"}            
    };
    
    private User user;
    private Resources resources[];

    private SystemResources() {
        for( int x = 0; x < cityCod.length; x++ ) {
            resources[x] = new Resources();
        }
    }
    
    public synchronized static SystemResources getInstance() {
        if (instance == null) {
            instance = new SystemResources();
        }
        return instance;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Resources getResources( String id ) {
        Resources _resources = null;
        for( int x = 0; x < cityCod.length; x++ ) {
            String _id = cityCod[x][0];
            _resources = (_id.equals(id)) ? resources[x] : null;
        }
        
        return _resources;
    }
}
