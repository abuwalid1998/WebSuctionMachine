package com.amjadprojects.sparky.websuctionmachine.Interfaces;

import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;

public class CustomSentencePreProcessor implements SentencePreProcessor {
    private CommonPreprocessor commonPreprocessor;

    public CustomSentencePreProcessor() {
        this.commonPreprocessor = new CommonPreprocessor();
    }

    @Override
    public String preProcess(String sentence) {
        return commonPreprocessor.preProcess(sentence);
    }
}
