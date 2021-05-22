import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCounterDriver extends Configured implements Tool {

  public static void main(String[] args) throws Exception {
	  
	  int res = ToolRunner.run(new WordCounterDriver(), args);
	  System.exit(res);
	  //proporcionamos la respuesta recibida por el proceso (clave, valor)
  }
  
  public int run (String [] args) throws Exception {
	  
	  //realizamos una instancia para obtener la configuracion
	  Job job = Job.getInstance(getConf(), "WordCounter");
	  
	  
	  job.setJarByClass(this.getClass()); //esta sera la clase con el metodo de entrada
	  
	  //indicamos los paths de entrada y salida
	  FileInputFormat.addInputPath(job, new Path(args[0]));
	  FileOutputFormat.setOutputPath(job, new Path(args[1]));
	  
	  //ahora indicamos las clases que haran el map y el reduce
	  job.setMapperClass(WordCounterMapper.class);
	  job.setReducerClass(WordCounterReducer.class);
	  job.setOutputKeyClass(Text.class);
	  job.setOutputValueClass(IntWritable.class);
	  
	  return job.waitForCompletion(true)? 0 : 1;
	  
  }
}

