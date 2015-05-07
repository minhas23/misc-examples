package com.manjeet.sample;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;


@Description(
  name="TestUDF",
  value="returns 'hello x', where x is whatever you give it (STRING)",
  extended="SELECT testudf('world') from foo limit 1;"
  )
public class TestUDF extends UDF {
  
  public String evaluate(String input) {
    if(input == null) return null;
    return input.toUpperCase();
   }
}