package jorn.hiel.helpers;

public enum Daytype {

    WERK(1),
    WEEKEND(2),
    VERLOF(3),
    ZIEK(4);

    private int type;

    private Daytype(int type){
        this.type=type;
    }

    public int getType(){
        return type;
    }

}
