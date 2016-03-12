package miccab.storm.wordcount;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by michal on 12.03.16.
 */
public class WordGeneratorSpout extends BaseRichSpout {
    private SpoutOutputCollector spoutOutputCollector;
    private Iterator<String> lines;

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
    }

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector = spoutOutputCollector;
        try {
            lines = Files.lines(Paths.get("./src/main/resources/words.txt")).iterator();
        } catch (IOException e) {
            throw new RuntimeException("unexpected error", e);
        }
    }

    public void nextTuple() {
        if (lines.hasNext()) {
            final String[] words = lines.next().split(" ");
            for (String word : words) {
                spoutOutputCollector.emit(new Values(word));
            }
        }
    }
}
