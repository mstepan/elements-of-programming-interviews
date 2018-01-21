package com.max.epi.graph;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class TransformOneStringToAnotherTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void productionSequence() {

        Set<String> dic = new HashSet<>();
        dic.add("bat");
        dic.add("cot");
        dic.add("dog");
        dic.add("dag");
        dic.add("dot");
        dic.add("cat");

        List<String> seq = TransformOneStringToAnother.productionSequence("cat", "dog", dic);

        assertEquals(Arrays.asList("cat", "cot", "dot", "dog"), seq);
    }

    @Test
    public void productionSequenceSameString() {

        Set<String> dic = new HashSet<>();
        dic.add("bat");
        dic.add("cot");
        dic.add("dog");
        dic.add("dag");
        dic.add("dot");
        dic.add("cat");

        List<String> seq = TransformOneStringToAnother.productionSequence("cat", "cat", dic);

        assertEquals(Arrays.asList("cat"), seq);
    }

    @Test
    public void productionSequenceNotSameLengthStrings() {

        Set<String> dic = new HashSet<>();
        dic.add("bat");
        dic.add("cot");
        dic.add("dogs");
        dic.add("dag");
        dic.add("dot");
        dic.add("cat");

        List<String> seq = TransformOneStringToAnother.productionSequence("cat", "dogs", dic);

        assertEquals(Collections.emptyList(), seq);
    }

    @Test
    public void productionSequenceNoFirstStringInDictionary() {

        Set<String> dic = new HashSet<>();
        dic.add("bat");
        dic.add("cot");
        dic.add("dog");
        dic.add("dag");
        dic.add("dot");

        List<String> seq = TransformOneStringToAnother.productionSequence("cat", "dog", dic);

        assertEquals(Collections.emptyList(), seq);
    }

    @Test
    public void productionSequenceNoSecondStringInDictionary() {

        Set<String> dic = new HashSet<>();
        dic.add("bat");
        dic.add("cot");
        dic.add("dag");
        dic.add("dot");
        dic.add("cat");

        List<String> seq = TransformOneStringToAnother.productionSequence("cat", "dog", dic);

        assertEquals(Collections.emptyList(), seq);
    }

    @Test
    public void noProductionSequenceBetweenStrings() {

        Set<String> dic = new HashSet<>();
        dic.add("bat");
        dic.add("cot");
        dic.add("dag");
        dic.add("dot");
        dic.add("cat");
        dic.add("sun");
        dic.add("dun");
        dic.add("dan");
        dic.add("dam");

        List<String> seq = TransformOneStringToAnother.productionSequence("cat", "dam", dic);

        assertEquals(Collections.emptyList(), seq);
    }

    @Test
    public void productionSequenceNullFirstString() {
        expectedException.expect(NullPointerException.class);
        TransformOneStringToAnother.productionSequence(null, "dog", new HashSet<>());
    }

    @Test
    public void productionSequenceNullSecondString() {
        expectedException.expect(NullPointerException.class);
        TransformOneStringToAnother.productionSequence("cat", null, new HashSet<>());
    }

    @Test
    public void productionSequenceNullDictionary() {
        expectedException.expect(NullPointerException.class);
        TransformOneStringToAnother.productionSequence("cat", "dog", null);
    }
}
