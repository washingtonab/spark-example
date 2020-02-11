package teste;

import java.io.Serializable;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Sample {
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println("Hello world");
		
		
		SparkSession sparkSession = SparkSession.builder()
												.appName("sample-spark")
												.config("spark.master", "local[*]")
												.getOrCreate();
		/*
		for (int i = 0; i < 10000000; i++) {
			System.out.println(i);
		}
		*/

//		RDD<String> rd = sparkSession.sparkContext().textFile("file.txt", 1);
//		System.out.println(rd.count());

		Dataset<String> dt = sparkSession.read().textFile("file.txt");
		System.out.println(dt.count());
		dt.printSchema();
		
		Dataset<Row> dtCSV = sparkSession.read().csv("file.csv");
		System.out.println(dtCSV.count());
		dtCSV.printSchema();
		
		Dataset<Row> dtJSON = sparkSession.read().json("file.json");
		System.out.println(dtJSON.count());
		dtJSON.printSchema();
		
		JavaRDD<String> rd1 = sparkSession.sparkContext().textFile("file.json", 1).toJavaRDD();
		rd1.collect().forEach(e -> System.out.println(e));

		JavaRDD<String> rd2 = rd1.map(e -> e.toUpperCase());
		rd2.collect().forEach(e -> System.out.println(e));

		JavaRDD<People> rd3 = rd1.map(e -> {
			String[] fields = e.split(",");
			return new People(fields[0], 123, fields[2]);
		});
		rd3.collect().forEach(e -> System.out.println(e.getName()));

		
		sparkSession.stop();

		long end = System.currentTimeMillis();
		System.out.println("Tempo de processamento em milisegundos: " + (end - start));
	}

	
	
}

class People implements Serializable {

	private String name;
	private Integer age;
	private String email;
	
	public People(String name, Integer age, String email) {
		super();
		this.name = name;
		this.age = age;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
