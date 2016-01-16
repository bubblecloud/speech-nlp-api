package org.bubblecloud.speech.nlpapi.model;

import org.bubblecloud.speech.nlpapi.UtteranceType;

/**
 * Analysis result of utterance.
 *
 * @author Tommi S.E. Laukkanen
 */
public class UtteranceAnalysisResult {
    /**
     * Analysis error message if error occurred.
     */
    private String errorMessage;
    /**
     * The utterance type.
     */
    private UtteranceType type;
    /**
     * The entire parse tree of the utterance.
     */
    private Node utterance;
    /**
     * The subject phrase parse tree.
     */
    private Node subjectPhrase;
    /**
     * The verb parse tree.
     */
    private Node verb;
    /**
     * The object phrase.
     */
    private Node objectPhrase;
    /**
     * The Wh-phrase.
     */
    private Node whPhrase;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Node getObjectPhrase() {
        return objectPhrase;
    }

    public void setObjectPhrase(Node objectPhrase) {
        this.objectPhrase = objectPhrase;
    }

    public Node getSubjectPhrase() {
        return subjectPhrase;
    }

    public void setSubjectPhrase(Node subjectPhrase) {
        this.subjectPhrase = subjectPhrase;
    }

    public UtteranceType getType() {
        return type;
    }

    public void setType(UtteranceType type) {
        this.type = type;
    }

    public Node getVerb() {
        return verb;
    }

    public void setVerb(Node verbPhrase) {
        this.verb = verbPhrase;
    }

    public Node getWhPhrase() {
        return whPhrase;
    }

    public void setWhPhrase(Node whPhrase) {
        this.whPhrase = whPhrase;
    }

    public Node getUtterance() {
        return utterance;
    }

    public void setUtterance(Node utterance) {
        this.utterance = utterance;
    }

    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        if (errorMessage != null) {
            stringBuilder.append("UtteranceAnalysis error: " + errorMessage);
            return stringBuilder.toString();
        }
        stringBuilder.append("UtteranceAnalysis of a ");
        stringBuilder.append(getType());
        if (getUtterance() != null) {
            stringBuilder.append("\nutterance >>>\n" + getUtterance());
        }
        if (getWhPhrase() != null) {
            stringBuilder.append("\nwh phrase >>>\n" + getWhPhrase());
        }
        if (getSubjectPhrase() != null) {
            stringBuilder.append("\nsubject phrase >>>\n" + getSubjectPhrase());
        }
        if (getVerb() != null) {
            stringBuilder.append("\nverb phrase >>>\n" + getVerb());
        }
        if (getObjectPhrase() != null) {
            stringBuilder.append("\nobject phrase >>>\n" + getObjectPhrase());
        }
        return stringBuilder.toString();
    }
}
