package com.amjadprojects.sparky.websuctionmachine.Helpers;

import com.amjadprojects.sparky.websuctionmachine.Interfaces.CustomSentencePreProcessor;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

@Service
public class Word2VecModelGenerator {

    public void createAndSaveWord2VecModel(String inputFilePath, String modelFilePath) throws IOException {

    }


}
