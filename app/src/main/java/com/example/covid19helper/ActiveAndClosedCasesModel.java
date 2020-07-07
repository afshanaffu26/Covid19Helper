package com.example.covid19helper;

public class ActiveAndClosedCasesModel {
    private long active,closed,mild, death,critical,recovered,total;
    private int  mildPt, deathPt,criticalPt,recoveredPt;

    public ActiveAndClosedCasesModel(long total, long active, long closed, long mild, long death, long critical, long recovered, int mildPt, int deathPt, int criticalPt, int recoveredPt) {
        this.total = total;
        this.active = active;
        this.closed = closed;
        this.mild = mild;
        this.death = death;
        this.critical = critical;
        this.recovered = recovered;
        this.mildPt = mildPt;
        this.deathPt = deathPt;
        this.criticalPt = criticalPt;
        this.recoveredPt = recoveredPt;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }

    public long getClosed() {
        return closed;
    }

    public void setClosed(long closed) {
        this.closed = closed;
    }

    public long getMild() {
        return mild;
    }

    public void setMild(long mild) {
        this.mild = mild;
    }

    public long getDeath() {
        return death;
    }

    public void setDeath(long death) {
        this.death = death;
    }

    public long getCritical() {
        return critical;
    }

    public void setCritical(long critical) {
        this.critical = critical;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    public int getMildPt() {
        return mildPt;
    }

    public void setMildPt(int mildPt) {
        this.mildPt = mildPt;
    }

    public int getDeathPt() {
        return deathPt;
    }

    public void setDeathPt(int deathPt) {
        this.deathPt = deathPt;
    }

    public int getCriticalPt() {
        return criticalPt;
    }

    public void setCriticalPt(int criticalPt) {
        this.criticalPt = criticalPt;
    }

    public int getRecoveredPt() {
        return recoveredPt;
    }

    public void setRecoveredPt(int recoveredPt) {
        this.recoveredPt = recoveredPt;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
