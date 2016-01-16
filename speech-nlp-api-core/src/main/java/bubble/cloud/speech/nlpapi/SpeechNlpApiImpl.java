package bubble.cloud.speech.nlpapi;

import bubble.cloud.speech.nlpapi.model.UtteranceAnalysisResult;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

/**
 * Natural language processing API for speech utterances.
 *
 * @author Tommi S.E. Laukkanen
 */
public class SpeechNlpApiImpl implements SpeechNlpApi {

    private final StanfordCoreNLP pipeline;

    /**
     * Default constructor which configures the NLP pipeline.
     */
    public SpeechNlpApiImpl() {
        final Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse");
        pipeline = new StanfordCoreNLP(props);
    }

    @Override
    public UtteranceAnalysisResult analyseUtterance(final String utterance) {
        return UtteranceAnalysisLogic.analyseUtterance(pipeline, utterance);
    }
}
