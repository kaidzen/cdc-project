package org.kaidzen.study.producer;

public class Response {

    private BeerCheckStatus status;

    public Response(BeerCheckStatus status) {
        this.status = status;
    }

    public BeerCheckStatus getStatus() {
        return status;
    }

    public void setStatus(BeerCheckStatus status) {
        this.status = status;
    }
}
