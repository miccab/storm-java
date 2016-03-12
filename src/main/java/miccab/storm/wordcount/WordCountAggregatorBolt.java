package miccab.storm.wordcount;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by michal on 12.03.16.
 */
public class WordCountAggregatorBolt extends BaseRichBolt {
    private Map<String, Integer> wordCounter;
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        wordCounter = new HashMap<>();
    }

    @Override
    public void execute(Tuple tuple) {
        final String word = tuple.getString(0);
        final int currentCount = tuple.getInteger(1);
        wordCounter.put(word, currentCount);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    }

    @Override
    public void cleanup() {
        super.cleanup();
        System.out.println("!!!!!!!!!!!!!!!!!!!!" + this + " aggregated word counter: " + wordCounter);
    }

}
