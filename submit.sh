
#./start-master.sh

#./start-slave.sh spark://dell-optiplex:7077

# Run on a Spark standalone cluster
/opt/spark-2.4.4-bin-hadoop2.7/bin/spark-submit \
  --name "spark-example" \
  --class teste.Sample \
  --master spark://dell-optiplex:7077 \
  --deploy-mode cluster \
  --executor-cores 2 \
  --executor-memory 2G \
  --driver-cores 2 \
  --driver-memory 2G \
  target/spark-example-1.0.jar
