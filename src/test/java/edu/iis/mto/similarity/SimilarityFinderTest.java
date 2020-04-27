package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcherMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderTest {

    private SimilarityFinder similarityFinder;

    @BeforeEach
    void init() {
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

    @Test
    void behaviourTestWhenSecondSeqIsNull() {
        int [] seq = {1, 2, 3};
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(seq, null));
    }

    @Test
    void behaviourTestWhenSeqAreFull() {
        int [] seq1 = {1, 2, 3}, seq2 = {1};
        assertDoesNotThrow(() -> similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void behaviourTestWhenSearcherIsNull() {
        int [] seq1 = {1, 2, 3}, seq2 = {1};
        SimilarityFinder similarityFinderNull = new SimilarityFinder(null);
        assertThrows(NullPointerException.class, () -> similarityFinderNull.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void stateTestWhenBothSeqAreEmpty() {
        int [] seq1 = {}, seg2 = {};
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seg2), 1.0);
    }

    @Test
    void stateTestWhenFirstSeqAreEmpty() {
        int [] seq1 = {}, seg2 = {1, 2, 3};
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seg2), 0.0);
    }

    @Test
    void stateTestWhenSecondSeqAreEmpty() {
        int [] seq1 = {4, 5, 6}, seg2 = {};
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seg2), 0.0);
    }

    @Test
    void stateTestWhenBothSeqAreFullAndEqual() {
        int [] seq1 = {4, 5, 6}, seg2 = {4, 5, 6};
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seg2), 1.0);
    }

    @Test
    void stateTestWhenBothSeqAreFullAndNotEqual() {
        int [] seq1 = {1, 2, 3}, seg2 = {4, 5, 6};
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seg2), 0.0);
    }

    @Test
    void stateTestWhenBothSeqAreFullAndPartiallytEqual() {
        int [] seq1 = {1, 2, 3, 4, 5, 6}, seg2 = {4, 5, 6};
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seg2), 0.5);
    }

    @Test
    void stateTestWhenBothSeqAreFullAndOneQuarterEqual() {
        int [] seq1 = {1, 2, 3, 4, 5, 6, 7, 8}, seg2 = {5, 6};
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seg2), 0.25);
    }

    @Test
    void stateTestWhenBothSeqAreFullAndInThreeQuartersEqual() {
        int [] seq1 = {1, 2, 3, 4, 5, 6, 7, 8}, seg2 = {1, 2, 3, 4, 5, 6};
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seg2), 0.75);
    }

    @Test
    void stateTestWhenBothSeqAreFullAndInSixTenthsEqual() {
        int [] seq1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, seg2 = {1, 2, 3, 4, 5, 6};
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seg2), 0.60);
    }

    @Test
    void stateTestWhenBothSeqAreFullAndInFourTenthsEqual() {
        int [] seq1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, seg2 = {1, 2, 3, 4};
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seg2), 0.40);
    }

}