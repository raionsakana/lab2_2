package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcherMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderBehaviourTest {

    private SimilarityFinder similarityFinder;

    @BeforeEach
    public void init() {
        similarityFinder = new SimilarityFinder(new SequenceSearcherMock());
    }

    @Test
    void behaviourTestWhenBothSeqAreEmpty() {
        int [] seq1 = {}, seq2 = {};
        assertDoesNotThrow(() -> similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void behaviourTestWhenFirstSeqIsEmpty() {
        int [] seq1 = {}, seq2 = {1, 2, 3};
        assertDoesNotThrow(() -> similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void behaviourTestWhenSecondSeqIsEmpty() {
        int [] seq1 = {1, 2, 3}, seq2 = {};
        assertDoesNotThrow(() -> similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void behaviourTestWhenBothSeqAreNull() {
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(null, null));
    }

    @Test
    void behaviourTestWhenFirstSeqIsNull() {
        int [] seq = {1, 2, 3};
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(null, seq));
    }

}