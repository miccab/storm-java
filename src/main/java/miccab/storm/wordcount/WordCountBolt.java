package miccab.storm.wordcount;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by michal on 12.03.16.
 */
public class WordCountBolt extends BaseRichBolt {
    private OutputCollector outputCollector;
    private Map<String, Integer> wordCounter;
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
        this.wordCounter = new HashMap<>();
    }

    public void execute(Tuple tuple) {
        final String word = tuple.getString(0);
        wordCounter.putIfAbsent(word, 0);
        final Integer newCount = wordCounter.compute(word, (key, currentCount) -> currentCount + 1);
        outputCollector.emit(new Values(word, newCount));
        outputCollector.ack(tuple);
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word", "counter"));
    }

    @Override
    public void cleanup() {
        super.cleanup();
        System.out.println("!!!!!!!!!!!!!!!!!!!!" + this + " local word counter: " + wordCounter);
    }
}
