package com.hadoop.examples.useraccess;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class UserAccessReducer extends MapReduceBase implements
		Reducer<Text, LongWritable, Text, LongWritable> {

	public static long startTime;
	public static long endTime;

	@Override
	public void configure(JobConf jobConf) {
		startTime = Long.parseLong(jobConf.get("startTime"));
		endTime = Long.parseLong(jobConf.get("endTime"));
	}

	public void reduce(Text key, Iterator<LongWritable> values,
			OutputCollector<Text, LongWritable> output, Reporter reporter)
			throws IOException {
		int sum = 0;
		while (values.hasNext()) {
			long userLoggedInTime = values.next().get();
			if (userLoggedInTime >= startTime && userLoggedInTime <= endTime) {
				++sum;
			}
		}
		if (sum > 0) {
			output.collect(key, new LongWritable(sum));
		}
	}

}