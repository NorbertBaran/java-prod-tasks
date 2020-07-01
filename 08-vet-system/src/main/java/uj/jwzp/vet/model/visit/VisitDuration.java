package uj.jwzp.vet.model.visit;

import java.time.Duration;

public enum VisitDuration {

    QUARTER(15),
    HALF_HOUR(30),
    THREE_QUARTERS(45),
    HOUR(60);

    private final int duration;
    VisitDuration(int duration){
        this.duration=duration;
    }

    public int getValue(){
        return duration;
    }

}
