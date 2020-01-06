package teste;

import org.apache.spark.sql.SparkSession;

public class Sample {
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println("Hello world");
		
		
		SparkSession sparkSession = SparkSession.builder()
												//.appName("sample-spark")
												//.config("spark.master", "local[1]")
												.getOrCreate();

		for (int i = 0; i < 10000000; i++) {
			System.out.println(i);
		}
		
		sparkSession.stop();

		long end = System.currentTimeMillis();
		System.out.println("Tempo de processamento em milisegundos: " + (end - start));
	}

}
