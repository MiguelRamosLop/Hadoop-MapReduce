import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCounterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	//creamos las claves intermedias, las cuales recibira la clase Reducer
	
	// definir enteros hadoop con IntWritable 
	private final static IntWritable one = new IntWritable(1);
	//otra variable para almacenar el patron para dividir por tokens
	private final static Pattern SPLIT_PATTERN = Pattern.compile("\\s*\\b\\s*");
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
	  //aqui declaramos cada linea convirtiendola a minuscula 
	  //reemplazar todos los simbolos
	  String line = value.toString();
	  line = line.replaceAll("[^a-zA-Z0-9]", " ").toLowerCase();
	  Text currentWord = new Text(); 
	  
	  //array para almacenar las palabras por cada linea
	  String words[] = SPLIT_PATTERN.split(line);
	  
	  //recorremos comprobando
	  for (int i = 0; i < words.length; i++) {
		  //si existe un espacio continua
		  if (words[i].isEmpty()) {
			  continue;
		  }
		  //transformando la palabra String de java a String hadoop
		  currentWord = new Text(words[i]);
		  context.write(currentWord, one);
	  }

  }
}
