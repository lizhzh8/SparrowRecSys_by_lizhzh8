package com.sparrowrecsys.nearline.flink;

public class LatestMovieIdReduceFunction implements org.apache.flink.api.common.functions.ReduceFunction<Rating_demo> {

    @Override
    public Rating_demo reduce(Rating_demo rating_demo, Rating_demo t1) throws Exception {
        if (rating_demo.timestamp.compareTo(t1.timestamp) > 0) {
            return rating_demo;
        } else {
            return t1;
        }


    }
}
