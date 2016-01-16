package org.bubblecloud.speech.nlpapi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Value object for parse tree nodes.
 *
 * @author Tommi S.E. Laukkanen
 */
public class Node {
    /**
     * The node label.
     */
    private String label;
    /**
     * The lemma.
     */
    private String lemma;
    /**
     * The node depth.
     */
    private int depth;
    /**
     * The children of the node.
     */
    private List<Node> children = new ArrayList<Node>();

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLemma() {
        return lemma;
    }

    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        printNodeRecursively(this, stringBuilder);
        return stringBuilder.toString();
    }

    /**
     * Prints node recursively to string builder.
     * @param node the node
     * @param stringBuilder the string builder
     */
    private static void printNodeRecursively(final Node node, final StringBuilder stringBuilder) {
        for (int i = 0; i < node.getDepth(); i++) {
            stringBuilder.append(' ');
        }
        stringBuilder.append(node.getLabel());
        if (node.getLemma() != null && ! node.getLabel().equals(node.getLemma())) {
            stringBuilder.append(" (lemma: ");
            stringBuilder.append(node.getLemma());
            stringBuilder.append(')');
        }
        stringBuilder.append('\n');
        for (final Node childNode : node.getChildren()) {
            printNodeRecursively(childNode, stringBuilder);
        }
    }

}
