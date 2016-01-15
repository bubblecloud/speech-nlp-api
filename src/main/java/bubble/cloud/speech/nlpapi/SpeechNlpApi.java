package bubble.cloud.speech.nlpapi;

import bubble.cloud.speech.nlpapi.model.UtteranceAnalysisResult;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

/**
 * Natural language processing API for speech utterances.
 */
public class SpeechNlpApi {

    private final StanfordCoreNLP pipeline;

    public SpeechNlpApi() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse");
        pipeline = new StanfordCoreNLP(props);
    }

    public UtteranceAnalysisResult analyseUtterance(final String utterance) {
        return UtteranceAnalysisLogic.analyseUtterance(pipeline, utterance);
    }
}
