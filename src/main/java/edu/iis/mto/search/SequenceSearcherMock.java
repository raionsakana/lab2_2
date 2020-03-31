package edu.iis.mto.search;

public class SequenceSearcherMock implements SequenceSearcher {

    public int numberOfCall;

    @Override
    public SearchResult search(int elem, int[] seq) {

        if (seq == null) {
            throw new NullPointerException();
        }

        this.numberOfCall++;
        SearchResult.Builder builder = SearchResult.builder();
        builder.withFound(false);

        for (int i = 0; i < seq.length; i++) {
            if (seq[i] == elem) {
                builder.withFound(true);
                builder.withPosition(i);
                break;
            }
        }

        return builder.build();
    }

}
