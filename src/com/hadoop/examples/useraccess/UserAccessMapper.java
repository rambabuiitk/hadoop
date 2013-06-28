package com.hadoop.examples.useraccess;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class UserAccessMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, LongWritable> {
	private Text word = new Text();

	public void map(LongWritable key, Text value,
			OutputCollector<Text, LongWritable> output, Reporter reporter)
			throws IOException {
		String line = value.toString();
		String[] inputArray = line.split("\\s+");
		word.set(inputArray[0]);
		output.collect(word, new LongWritable(Long.parseLong(inputArray[1])));
	}

}