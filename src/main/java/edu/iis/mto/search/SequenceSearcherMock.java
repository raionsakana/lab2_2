package edu.iis.mto.search;

import java.util.HashMap;

public class SequenceSearcherMock implements SequenceSearcher {

    private HashMap<Integer, Integer> positions = new HashMap<>();

    public void setPositions(int [] data) {
        this.positions.clear();

        if (data != null) {
            for (int i = 0; i < data.length; i++) {
                this.positions.put(data[i], i);
            }
        }
    }

    @Override
    public SearchResult search(int elem, int[] seq) {

        if (seq == null) {
            throw new NullPointerException();
        }

        SearchResult.Builder builder = SearchResult.builder();
        builder.withFound(false);
        int position = -1;

        if (this.positions.containsKey(elem)) {
            position = this.positions.get(elem);
        }

        if (position > -1) {
            builder.withFound(true);
            builder.withPosition(position);
        }

        return builder.build();
    }

}
