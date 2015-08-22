package com.manjeet.sample.java.hadoop;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * This application will take different dictionaries of english to other languages 
 * (English-Spanish) (English-Italian)(English-French) and create a Dictionary file 
 * that has the english word followed by all the translations pipe-separated
 * 
 * This example can be found at http://java.dzone.com/articles/hadoop-basics-creating
 * 
 * Input Files can be download from http://www.ilovelanguages.com/IDP/IDPfiles.html
 * 
 * @author manjeet(minhas23@gmail.com)
 */

public class DictionaryMapReduceJob
{
    public static class WordMapper extends Mapper<Text, Text, Text, Text>
    {
        private Text word = new Text();
        public void map(Text key, Text value, Context context) throws IOException, InterruptedException
        {
            StringTokenizer itr = new StringTokenizer(value.toString(),"\t");
            while (itr.hasMoreTokens())
            {
            	String val = itr.nextToken();
                word.set(val);
                context.write(key, word);
            }
        }
    }
    public static class AllTranslationsReducer extends Reducer<Text,Text,Text,Text>
    {
        private Text result = new Text();
        public void reduce(Text key, Iterable<Text> values,
        Context context
        ) throws IOException, InterruptedException
        {
            String translations = "";
            for (Text val : values)
            {
                translations += "|"+val.toString();
            }
            result.set(translations);
            context.write(key, result);
        }
    }
    public static void main(String[] args) throws Exception
    {
        Configuration conf = new Configuration();
        conf.addResource(new Path("/etc/hadoop/conf/core-site.xml"));
        conf.addResource(new Path("/etc/hadoop/conf/hdfs-site.xml"));
        
        Job job = new Job(conf, "dictionary");
        job.setJarByClass(DictionaryMapReduceJob.class);
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(AllTranslationsReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        FileInputFormat.addInputPath(job, new Path("/tmp/dictionary.txt"));
        FileOutputFormat.setOutputPath(job, new Path("/tmp/output/dict"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}