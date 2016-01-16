package bubble.cloud.speech.nlpapi;

import bubble.cloud.speech.nlpapi.model.UtteranceAnalysisResult;

/**
 * Speech NLP API interface.
 *
 * @author Tommi S.E. Laukkanen
 */
public interface SpeechNlpApi {
    /**
     * Analyses single sentence raw utterance produced by speech recognition software.
     * The utterance should not contain punctuation and be all in lower case.
     * @param utterance the utterance string
     * @return the resulting analysis
     */
    UtteranceAnalysisResult analyseUtterance(final String utterance);
}
