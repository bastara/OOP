package ru.academit.kolobov.range.RangeClass;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double a) {
        return this.from <= a && this.to >= a;
    }

    public Range getIntervalIntersection(Range interval2) {
        if (Math.max(this.from, interval2.from) - Math.min(this.to, interval2.to) >= 0) {
            return null;
        } else {
            return new Range(Math.max(this.from, interval2.from), Math.min(this.to, interval2.to));
        }
    }

    public Range[] getIntervalMerge(Range interval2) {
        if (interval2.from > this.to || interval2.to < this.from) {
            return new Range[]{new Range(this.from, this.to), new Range(interval2.from, interval2.to)};
        } else {
            return new Range[]{new Range(Math.min(this.from, interval2.from), Math.max(this.to, interval2.to))};
        }
    }

    public Range[] getIntervalDifference(Range interval2) {
        if (interval2.from > this.to || interval2.to < this.from) {
            return new Range[]{new Range(this.from, this.to)};
        } else {
            if ((interval2.from > this.from)) {
                if ((interval2.to < this.to)) {
                    return new Range[]{new Range(this.from, interval2.from), new Range(interval2.to, this.to)};
                } else {
                    return new Range[]{new Range(this.from, interval2.from)};
                }
            } else {
                if (interval2.to < this.to) {
                    return new Range[]{new Range(interval2.to, this.to)};
                }
            }
        }
        return new Range[0];
    }
}

