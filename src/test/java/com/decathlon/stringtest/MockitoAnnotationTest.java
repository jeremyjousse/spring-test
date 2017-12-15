package com.decathlon.stringtest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationTest {

    @Mock
    private List<String> mockedList;

    @Mock
    private Map<String, String> worldMap;

    @Spy
    private List<String> spiedList = new ArrayList<>();

    @Captor
    private ArgumentCaptor<String> argCaptor;

    @InjectMocks
    private MyDictionary dic = new MyDictionary();


    @Test
    public void whenUseMokedAnnotation_thenMockIsInjected() {
        List<String> realList = new ArrayList<>();
        realList.add("one");
        mockedList.add("one");
        Mockito.verify(mockedList).add("one");
        Assert.assertEquals(0, mockedList.size());
        Assert.assertEquals(1, realList.size());
    }

    @Test
    public void whenUseSpyAnnotation_thenSpyIsInjected() {
        spiedList.add("one");
        spiedList.add("two");
        Mockito.verify(spiedList).add("one");
        Mockito.verify(spiedList).add("two");
        Mockito.verify(spiedList, Mockito.never()).add("three");
        Assert.assertEquals(2, spiedList.size());

        Mockito.doReturn(100).when(spiedList).size();
        Assert.assertEquals(100, spiedList.size());
    }

    @Test
    public void whenUseCaptorAnnotation_thenCorrect() {
        mockedList.add("one");
        Mockito.verify(mockedList).add(argCaptor.capture());
        Assert.assertEquals("one", argCaptor.getValue());
    }

    @Test
    public void whenUseInjectMocksAnnotation_thenCorrect() {
        Mockito.when(worldMap.get("aWord")).thenReturn("aMeaning");
        Assert.assertEquals("aMeaning", dic.getMeaning("aWord"));
    }


}
