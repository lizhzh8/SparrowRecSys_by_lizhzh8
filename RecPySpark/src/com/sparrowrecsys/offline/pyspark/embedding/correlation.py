from pyspark import SparkConf, SparkContext

if __name__ == '__main__':
    conf = SparkConf().setMaster('local[4]').setAppName('wordCount')
    sc = SparkContext(conf=conf)

    data = sc.textFile('d:/wd/')

    output = data \
        .flatMap(lambda line: line.split(' ')) \
        .map(lambda x: (x, 1)) \
        .reduceByKey(lambda x, y: x + y) \
        .sortBy(lambda line: line[1], False) \
        .collect()
    # .saveAsTextFile()

    for word, count in output:
        print("{}\t{}".format(word, count))

    sc.stop()
