package org.bubblecloud.speech.nlpapi;

import org.bubblecloud.speech.nlpapi.model.Node;
import org.bubblecloud.speech.nlpapi.model.UtteranceAnalysisResult;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Logic for analysing utterance.
 *
 * @author Tommi S.E. Laukkanen
 */
public class UtteranceAnalysisLogic {
    /**
     * Analyses utterance using StanfordCoreNLP pipeline.
     * @param pipeline the pipeline
     * @param utterance the utterance
     * @return the utterance analysis result
     */
    public static UtteranceAnalysisResult analyseUtterance(StanfordCoreNLP pipeline, String utterance) {
        final Annotation annotation = new Annotation(utterance);
        pipeline.annotate(annotation);

        final List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        if (sentences != null && sentences.size() == 1) {
            final CoreMap sentence = sentences.get(0);
            final Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);

            final Map<String, String> lemmaMap = new HashMap<String, String>();
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                lemmaMap.put(token.value(), token.get(CoreAnnotations.LemmaAnnotation.class));
            }
            final Node node = new Node();
            NodeLogic.populateNodeRecursively(tree, lemmaMap, node, 0);

            final UtteranceAnalysisResult analysis = new UtteranceAnalysisResult();

            analysis.setUtterance(node);
            final Node clause = NodeLogic.findFirstNodeByLabel(node, "S", false, null);
            if (clause != null) {
                analysis.setSubjectPhrase(NodeLogic.findFirstNodeByLabel(clause, "NP", true, Arrays.asList("VP")));
                analysis.setVerb(NodeLogic.findFirstNodeByLabel(node, "VB", true, null));
                final Node verbPhrase = NodeLogic.findFirstNodeByLabel(clause, "VP", false, null);
                if (verbPhrase != null) {
                    analysis.setObjectPhrase(NodeLogic.findFirstNodeByLabel(verbPhrase, "NP", true, null));
                }
                analysis.setWhPhrase(NodeLogic.findFirstNodeByLabel(node, "WH", true, null));
            }

            if (analysis.getWhPhrase() != null) {
                analysis.setType(UtteranceType.QUESTION);
            } else if (analysis.getSubjectPhrase() != null && analysis.getVerb() != null) {
                analysis.setType(UtteranceType.STATEMENT);
            } else if (analysis.getVerb() != null) {
                analysis.setType(UtteranceType.COMMAND);
            } else {
                analysis.setType(UtteranceType.UNKNOWN);
            }
            return analysis;
        }

        final UtteranceAnalysisResult analysis = new UtteranceAnalysisResult();
        analysis.setType(UtteranceType.UNKNOWN);
        return analysis;
    }
}
