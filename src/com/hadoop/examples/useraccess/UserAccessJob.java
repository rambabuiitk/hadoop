package com.hadoop.examples.useraccess;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class UserAccessJob {
	
	 public static void main(String[] args) throws Exception {
		    JobConf conf = new JobConf(UserAccessJob.class);

		    if (args.length != 4) {
		      System.err.println("startTime and endTime parameters must be specified");
		      System.exit(0);      
		    }

		    String startTime = args[2];
		    String endTime = args[3];

		    conf.set("startTime", startTime);
		    conf.set("endTime", endTime);
		    conf.setJobName("useraccesscount");

		    conf.setOutputKeyClass(Text.class);
		    conf.setOutputValueClass(LongWritable.class);

		    conf.setMapperClass(UserAccessMapper.class);
		    conf.setReducerClass(UserAccessReducer.class);

		    conf.setInputFormat(TextInputFormat.class);
		    conf.setOutputFormat(TextOutputFormat.class);

		    FileInputFormat.setInputPaths(conf, new Path(args[0]));
		    FileOutputFormat.setOutputPath(conf, new Path(args[1]));

		    JobClient.runJob(conf);
		  }

}
