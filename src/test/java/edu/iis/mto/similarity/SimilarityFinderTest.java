package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcherMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderTest {

    private SequenceSearcherMock sequenceSearcherMock;
    private SimilarityFinder similarityFinder;

    @BeforeEach
    void init() {
        this.sequenceSearcherMock = new SequenceSearcherMock();
        this.similarityFinder = new SimilarityFinder(this.sequenceSearcherMock);
    }

    @Test
    void behaviourTestWhenBothSeqAreEmpty() {
        int [] seq1 = {}, seq2 = {};
        this.sequenceSearcherMock.setPositions(seq2);
        assertDoesNotThrow(() -> similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void behaviourTestWhenFirstSeqIsEmpty() {
        int [] seq1 = {}, seq2 = {1, 2, 3};
        this.sequenceSearcherMock.setPositions(seq2);
        assertDoesNotThrow(() -> similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void behaviourTestWhenSecondSeqIsEmpty() {
        int [] seq1 = {1, 2, 3}, seq2 = {};
        this.sequenceSearcherMock.setPositions(seq2);
        assertDoesNotThrow(() -> similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void behaviourTestWhenBothSeqAreNull() {
        int [] seq = null, seq2 = null;
        this.sequenceSearcherMock.setPositions(seq2);
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(seq, seq2));
    }

    @Test
    void behaviourTestWhenFirstSeqIsNull() {
        int [] seq = {1, 2, 3}, seq2 = null;
        this.sequenceSearcherMock.setPositions(seq);
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(seq2, seq));
    }

    @Test
    void behaviourTestWhenSecondSeqIsNull() {
        int [] seq = {1, 2, 3}, seq2 = null;
        this.sequenceSearcherMock.setPositions(seq2);
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(seq, seq2));
    }

    @Test
    void behaviourTestWhenSeqAreFull() {
        int [] seq1 = {1, 2, 3}, seq2 = {1};
        this.sequenceSearcherMock.setPositions(seq2);
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
        int [] seq1 = {}, seq2 = {};
        this.sequenceSearcherMock.setPositions(seq2);
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seq2), 1.0);
    }

    @Test
    void stateTestWhenFirstSeqAreEmpty() {
        int [] seq1 = {}, seq2 = {1, 2, 3};
        this.sequenceSearcherMock.setPositions(seq2);
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seq2), 0.0);
    }

    @Test
    void stateTestWhenSecondSeqAreEmpty() {
        int [] seq1 = {4, 5, 6}, seq2 = {};
        this.sequenceSearcherMock.setPositions(seq2);
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seq2), 0.0);
    }

    @Test
    void stateTestWhenBothSeqAreFullAndEqual() {
        int [] seq1 = {4, 5, 6}, seq2 = {4, 5, 6};
        this.sequenceSearcherMock.setPositions(seq2);
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seq2), 1.0);
    }

    @Test
    void stateTestWhenBothSeqAreFullAndNotEqual() {
        int [] seq1 = {1, 2, 3}, seq2 = {4, 5, 6};
        this.sequenceSearcherMock.setPositions(seq2);
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seq2), 0.0);
    }

    @Test
    void stateTestWhenBothSeqAreFullAndPartiallytEqual() {
        int [] seq1 = {1, 2, 3, 4, 5, 6}, seq2 = {4, 5, 6};
        this.sequenceSearcherMock.setPositions(seq2);
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seq2), 0.5);
    }

    @Test
    void stateTestWhenBothSeqAreFullAndOneQuarterEqual() {
        int [] seq1 = {1, 2, 3, 4, 5, 6, 7, 8}, seq2 = {5, 6};
        this.sequenceSearcherMock.setPositions(seq2);
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seq2), 0.25);
    }

    @Test
    void stateTestWhenBothSeqAreFullAndInThreeQuartersEqual() {
        int [] seq1 = {1, 2, 3, 4, 5, 6, 7, 8}, seq2 = {1, 2, 3, 4, 5, 6};
        this.sequenceSearcherMock.setPositions(seq2);
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seq2), 0.75);
    }

    @Test
    void stateTestWhenBothSeqAreFullAndInSixTenthsEqual() {
        int [] seq1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, seq2 = {1, 2, 3, 4, 5, 6};
        this.sequenceSearcherMock.setPositions(seq2);
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seq2), 0.60);
    }

    @Test
    void stateTestWhenBothSeqAreFullAndInFourTenthsEqual() {
        int [] seq1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, seq2 = {1, 2, 3, 4};
        this.sequenceSearcherMock.setPositions(seq2);
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seq2), 0.40);
    }

}