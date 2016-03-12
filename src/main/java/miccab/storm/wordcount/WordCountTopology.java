package miccab.storm.wordcount;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;

/**
 * Created by michal on 12.03.16.
 */
public class WordCountTopology {
    public static void main(String [] args) {
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("word", new WordGeneratorSpout(), 1);
        builder.setBolt("counter", new WordCountBolt(), 3).fieldsGrouping("word", new Fields("word"));
        builder.setBolt("aggregatedCounter", new WordCountAggregatorBolt(), 1).shuffleGrouping("counter");

        Config conf = new Config();
        conf.setDebug(true);

        if (args != null && args.length > 0) {
            conf.setNumWorkers(3);
            try {
                StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("test", conf, builder.createTopology());
            Utils.sleep(4000);
            cluster.killTopology("test");
            cluster.shutdown();
        }
    }

}
