package org.example;

import org.example.exceptions.ElementNotFoundException;
import org.example.exceptions.IndexNotFoundException;
import org.example.exceptions.ParamNullException;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringListImp implements StringList {
    private static final int CAPACITY = 10;
    private int size = 0;
    private String[] arrayString;

    public StringListImp() {
        this.arrayString = new String[CAPACITY];
    }

    public StringListImp(String[] arrayString) {
        this.arrayString = Arrays.copyOf(arrayString, arrayString.length);
        this.size = arrayString.length;
    }

    @Override
    public String add(String item) {
        validateElement(item);
        resizeArray();
        arrayString[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateElement(item);
        validateIndex(index);
        resizeArray();
        System.arraycopy(arrayString, index, arrayString, index + 1, size - index);
        arrayString[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateElement(item);
        arrayString[index] =  item;
        return item;
    }

    @Override
    public String remove(String item) {
        validateElement(item);
        int index = indexOf(item);
        if (index < 0) {
            throw new ElementNotFoundException(
                    String.format(ElementNotFoundException.TEMPLATE_MESSAGE, item));
        }
        return remove(index);
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String item = arrayString[index];
        System.arraycopy(arrayString, index + 1, arrayString, index, size - index - 1);
        arrayString[size - 1] = null;
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        validateElement(item);
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(String item) {
        validateElement(item);
        return IntStream.range(0, size)
                .filter(e -> arrayString[e].equals(item))
                .findFirst()
                .orElse(-1);
    }

    @Override
    public int lastIndexOf(String item) {
        validateElement(item);
        return IntStream.range(0, size)
                .map(e -> size - 1 - e)
                .filter(e -> arrayString[e].equals(item))
                .findFirst()
                .orElse(-1);
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return arrayString[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        validateElement(otherList);
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        this.arrayString = new String[CAPACITY];
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(arrayString, size);
    }

    private void resizeArray() {
        if (size >= arrayString.length) {
            arrayString = Arrays.copyOf(arrayString, arrayString.length + CAPACITY);
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException(String.format(IndexNotFoundException.TEMPLATE_MESSAGE, index));
        }
    }

    private void validateElement(Object item) {
        if (item == null) {
            throw new ParamNullException();
        }
    }
}
