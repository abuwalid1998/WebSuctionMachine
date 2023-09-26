package com.amjadprojects.sparky.websuctionmachine.Interfaces;

import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;

import java.util.List;

public class CustomSequenceIterator implements SentenceIterator {
    private List<List<String>> sentences;
    private int currentIndex = 0;
    private SentencePreProcessor preProcessor;

    public CustomSequenceIterator(List<List<String>> sentences) {
        this.sentences = sentences;
    }

    @Override
    public String nextSentence() {
        if (currentIndex < sentences.size()) {
            List<String> tokens = sentences.get(currentIndex++);
            String sentence = String.join(" ", tokens);
            if (preProcessor != null) {
                sentence = preProcessor.preProcess(sentence);
            }
            return sentence;
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < sentences.size();
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }

    @Override
    public void finish() {
        // Implement if needed
    }

    @Override
    public SentencePreProcessor getPreProcessor() {
        return preProcessor;
    }

    @Override
    public void setPreProcessor(SentencePreProcessor sentencePreProcessor) {
        this.preProcessor = sentencePreProcessor;
    }
}
