package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcherMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderStateTest {

    private SimilarityFinder similarityFinder;

    @BeforeEach
    void init() {
        similarityFinder = new SimilarityFinder(new SequenceSearcherMock());
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