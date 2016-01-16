package org.bubblecloud.speech.nlpapi;

import org.bubblecloud.speech.nlpapi.model.UtteranceAnalysisResult;

/**
 * Speech NLP API interface.
 *
 * @author Tommi S.E. Laukkanen
 */
public interface SpeechNlpApi {
    /**
     * Analyses single sentence raw utterance produced by speech recognition software.
     * The utterance should not contain punctuation and be in lower case.
     * @param locale the locale
     * @param utterance the utterance string
     * @return the resulting analysis
     */
    UtteranceAnalysisResult analyseUtterance(final String locale, final String utterance);
}
