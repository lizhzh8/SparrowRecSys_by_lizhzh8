package com.sparrowrecsys.nearline.flink;

// import
//import org.apache.flink;


import org.apache.flink.api.common.functions.ReduceFunction;
//import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.io.TextInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.net.URL;
//import java.net.URL;

class Rating_demo {
    public String userId;
    public String movieId;
    public String rating;
    public String timestamp;
    String latestMovieId;

    Rating_demo(String line) {
        String[] lines = line.split(",");

        this.userId = lines[0];
        this.movieId = lines[1];
        this.rating = lines[2];
        this.timestamp = lines[3];
        this.latestMovieId = lines[1];
    }

}


public class RealTimeFeature_demo {
    public void test() throws Exception {


        // set up  excution environment
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        final StreamExecutionEnvironment env = StreamExecutionEnvironment
                .getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);
        URL ratingResourcesPath = this.getClass().getResource("/webroot/sampledata/ratings.csv");
        TextInputFormat inputFormat = new TextInputFormat(new org.apache.flink.core.fs.Path(ratingResourcesPath.getPath()));

        DataStream<String> inputStream = env.readFile(inputFormat, ratingResourcesPath.getPath(), FileProcessingMode.PROCESS_CONTINUOUSLY, 100);

        DataStream<Rating_demo> ratingStream = inputStream.map(Rating_demo::new);
        ratingStream.keyBy(rating_demo -> rating_demo.userId)
                .timeWindow(Time.seconds(1))
                .reduce(new LatestMovieIdReduceFunction())
                .addSink(new ShowSinkFunction<Rating_demo>());
        env.execute();
    }


    public static void main(String[] args) throws Exception {
        new RealTimeFeature_demo().test();
    }

}

class ShowSinkFunction<T> implements org.apache.flink.streaming.api.functions.sink.SinkFunction<Rating_demo> {
    @Override
    public void invoke(Rating_demo rating, Context context) throws Exception {
        System.out.println("userId:" + rating.userId + "\t latestMovieId:" + rating.latestMovieId);
    }
}
