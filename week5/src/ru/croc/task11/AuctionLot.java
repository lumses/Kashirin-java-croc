package ru.croc.task11;

class AuctionLot{
    private volatile int cost;
    private volatile String name = "";
    private volatile int endTime;

    public AuctionLot(int start, int end, String name) {
        this.cost = start;
        this.endTime = end;
        this.name = name;
    }

    public synchronized void bid(String user, int cost, int curTime) {
        if (this.cost < cost && curTime < this.endTime) {
            this.cost = cost;
            this.name = user;
        } 
    }

    public synchronized String getWinner() {
        return this.name;
    }
}