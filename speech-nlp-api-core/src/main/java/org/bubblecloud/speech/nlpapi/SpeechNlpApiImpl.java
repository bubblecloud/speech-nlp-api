package org.bubblecloud.speech.nlpapi;

import org.bubblecloud.speech.nlpapi.model.UtteranceAnalysisResult;
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
    public UtteranceAnalysisResult analyseUtterance(final String locale, final String utterance) {
        if (!locale.equals("en_US")) {
            final UtteranceAnalysisResult result = new UtteranceAnalysisResult();
            result.setErrorMessage("Locale not supported: " + locale);
            return result;
        }
        String correctedString = utterance.trim().replace(" i ", " I ");
        if (correctedString.startsWith("i ")) {
            correctedString = correctedString.replaceFirst("i ", "I ");
        }

        return UtteranceAnalysisLogic.analyseUtterance(pipeline, correctedString);
    }
}
