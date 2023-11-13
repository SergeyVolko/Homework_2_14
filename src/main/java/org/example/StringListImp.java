package org.example;

import org.example.exceptions.IndexNotFoundException;
import org.example.exceptions.ParamNullException;

import java.util.Arrays;

public class StringListImp implements StringList {
    private static final int CAPACITY = 10;
    private int size = 0;
    private final String[] arrayString;

    public StringListImp() {
        this.arrayString = new String[CAPACITY];
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
        resizeArray();
        System.arraycopy(arrayString, index, arrayString, index + 1, arrayString.length - index - 1);
        return null;
    }

    @Override
    public String set(int index, String item) {
        return null;
    }

    @Override
    public String remove(String item) {
        return null;
    }

    @Override
    public String remove(int index) {
        return null;
    }

    @Override
    public boolean contains(String item) {
        return false;
    }

    @Override
    public int indexOf(String item) {
        return 0;
    }

    @Override
    public int lastIndexOf(String item) {
        return 0;
    }

    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public boolean equals(StringList otherList) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public String[] toArray() {
        return new String[0];
    }

    private void resizeArray() {
        if (size >= arrayString.length) {
            Arrays.copyOf(arrayString, arrayString.length + CAPACITY);
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException(String.format(IndexNotFoundException.TEMPLATE_MESSAGE, index));
        }
    }

    private void validateElement(String item) {
        if (item == null) {
            throw new ParamNullException();
        }
    }
}
