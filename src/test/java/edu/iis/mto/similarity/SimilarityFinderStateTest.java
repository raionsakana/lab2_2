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

    

}