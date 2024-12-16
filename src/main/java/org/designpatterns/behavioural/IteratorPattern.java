package org.designpatterns.behavioural;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Row {
    private List<String> columns;

    public Row(List<String> columns) {
        this.columns = columns;
    }

    public String getColumn(int index) {
        return columns.get(index);
    }

    @Override
    public String toString() {
        return String.join(", ", columns);
    }
}

interface Iterator {
    boolean hasNext();
    Row next();
}

class QueryResultIterator implements Iterator {

    private List<Row> rows;
    private int position = 0;

    public QueryResultIterator(List<Row> rows) {
        this.rows = rows;
    }

    @Override
    public boolean hasNext() {
        return position < rows.size();
    }

    @Override
    public Row next() {
        if (hasNext()) {
            return rows.get(position++);
        }
        return null;
    }
}

public class IteratorPattern {

    public static void main(String[] args) {
        List<Row> rows = new ArrayList<>();
        rows.add(new Row(Arrays.asList("1", "Alice", "Engineer")));
        rows.add(new Row(Arrays.asList("2", "Bob", "Designer")));
        rows.add(new Row(Arrays.asList("3", "Charlie", "Manager")));

        Iterator iterator = new QueryResultIterator(rows);

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
