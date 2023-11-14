package org.example;

import org.example.exceptions.IndexNotFoundException;
import org.example.exceptions.ParamNullException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.example.ConstantsForTest.*;

class StringListImpTest {

    public static Stream<Arguments> getParamsForAddIndexTest() {
        return Stream.of(
            Arguments.of(INDEX_TWO, BETWEEN, ARRAY_STRING_WITH_BETWEEN_ELEMENT, ARRAY_STRING),
            Arguments.of(INDEX_FIRST, FIRST, ARRAY_STRING_WITH_FIRST_ELEMENT, ARRAY_STRING),
            Arguments.of(INDEX_LAST, LAST, ARRAY_STRING_WITH_LAST_ELEMENT, ARRAY_STRING),
            Arguments.of(INDEX_TWO, BETWEEN, ARRAY_STRING_ALL_BETWEEN, ARRAY_STRING_ALL)
        );
    }
    @ParameterizedTest
    @MethodSource("getParamsForAddIndexTest")
    void whenAddIndexThenEqualsArrays(int index, String item, String[] expect, String[] arr) {
        StringListImp listImp = new StringListImp(arr);
        listImp.add(index, item);
        assertArrayEquals(listImp.toArray(), expect);
    }

    @Test
    public void whenFindIndexThenIndex() {
        StringListImp listImp = new StringListImp(ARRAY_STRING_FIRST_INDEX);
        assertEquals(INDEX_TWO, listImp.indexOf(BETWEEN));
    }

    @Test
    public void whenFindIndexThenNotIndex() {
        StringListImp listImp = new StringListImp(ARRAY_STRING_FIRST_INDEX);
        assertEquals(INDEX_NOT, listImp.indexOf(FIVE));
    }

    @Test
    public void whenFindLastIndexThenIndex() {
        StringListImp listImp = new StringListImp(ARRAY_STRING_FIRST_INDEX);
        assertEquals(INDEX_LAST, listImp.lastIndexOf(BETWEEN));
    }

    @Test
    public void whenAddElementThenAddSize() {
        StringListImp listImp = new StringListImp();
        Arrays.stream(ARRAY_STRING).forEach(listImp::add);
        assertEquals(SIZE_FiVE, listImp.size());
    }

    @Test
    public void whenAddElementThenEqualArray() {
        StringListImp listImp = new StringListImp();
        Arrays.stream(ARRAY_STRING).forEach(listImp::add);
        assertTrue(listImp.equals(new StringListImp(ARRAY_STRING)));
    }

    @Test
    public void whenGetForIndexThenElement() {
        StringListImp listImp = new StringListImp();
        Arrays.stream(ARRAY_STRING).forEach(listImp::add);
        assertEquals(THREE, listImp.get(INDEX_TWO));
    }

    @Test
    public void whenRemoveForIndexThenElement() {
        StringListImp listImp = new StringListImp(ARRAY_STRING);
        assertEquals(THREE, listImp.remove(INDEX_TWO));
        assertArrayEquals(ARRAY_STRING_AFTER_REMOVE, listImp.toArray());
    }

    @Test
    public void whenRemoveForNameThenElement() {
        StringListImp listImp = new StringListImp(ARRAY_STRING);
        assertEquals(THREE, listImp.remove(THREE));
        assertArrayEquals(ARRAY_STRING_AFTER_REMOVE, listImp.toArray());
    }

    @Test
    public void whenAddNullThenException() {
        StringListImp listImp = new StringListImp();
        assertThrows(ParamNullException.class, () -> listImp.add(null));
    }

    @Test
    public void whenInsertElementThenException() {
        StringListImp listImp = new StringListImp(ARRAY_STRING);
        assertThrows(IndexNotFoundException.class, () -> listImp.add(INDEX_NOT, BETWEEN));
    }

    @Test
    public void whenRemoveLastIndex() {
        StringListImp listImp = new StringListImp(ARRAY_STRING);
        listImp.remove(INDEX_LAST);
        assertArrayEquals(ARRAY_STRING_AFTER_REMOVE_LAST_INDEX, listImp.toArray());
    }
}