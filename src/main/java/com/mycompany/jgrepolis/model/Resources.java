package com.mycompany.jgrepolis.model;

public class Resources {
    
    private int wood;
    private int rock;
    private int silver;
    
    public Resources() {
        wood = 0;
        rock = 0;
        silver = 0;
    }

    public int getRock() { return rock; }
    public void setRock(int rock) { this.rock = rock; }

    public int getSilver() { return silver; }
    public void setSilver(int silver) { this.silver = silver; }

    public int getWood() { return wood; }
    public void setWood(int wood) { this.wood = wood; }
    
}
