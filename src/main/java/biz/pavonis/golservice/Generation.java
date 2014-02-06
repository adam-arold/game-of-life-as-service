package biz.pavonis.golservice;

import java.sql.Date;

/**
 * Represents a generation in game of life.
 * It contains the number of generations before (this one included)
 * and the timestamp when the generation was created.
 */
public class Generation {

    private final long count;
    private final Date timestamp;

    public Generation(long count, Date timestamp) {
        this.count = count;
        this.timestamp = timestamp;
    }

    public long getCount() {
        return count;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
