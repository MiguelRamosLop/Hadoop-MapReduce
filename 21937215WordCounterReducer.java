import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCounterReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	// agrega los valores, sumandolos y realizando un recuento para cada palabra emitiendo un par clave valor
  @Override
  public void reduce(Text key, Iterable<IntWritable> values, Context context)
      throws IOException, InterruptedException {
	  
	  //almacenamos el numero de ocurrencias
	  int sum = 0; 
	  //recorremos actualizando las ocurrencias que hay para cada word
	  for (IntWritable count : values) {
		  sum += count.get();
	  }
	  //escribimos el par clave valor.
	  context.write(key, new IntWritable(sum));

  }
}