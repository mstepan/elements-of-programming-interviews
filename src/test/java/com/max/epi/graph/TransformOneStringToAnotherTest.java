package com.max.epi.graph;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TransformOneStringToAnotherTest {

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

        assertThat(seq).containsExactly("cat", "cot", "dot", "dog");
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

        assertThat(seq).containsExactly("cat");
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

        assertThat(seq).isEmpty();
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

        assertThat(seq).isEmpty();
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

        assertThat(seq).isEmpty();
    }

    @Test
    public void productionSequenceNotSameLengthThrowsException() {

        Set<String> dic = new HashSet<>();
        dic.add("bat");
        dic.add("cot");
        dic.add("dogs");
        dic.add("dag");
        dic.add("dot");
        dic.add("cat");

        assertThatThrownBy(() -> TransformOneStringToAnother.productionSequence("cat", "dogs", dic)).
                isInstanceOf(IllegalStateException.class)
                .hasMessage("Not all strings have same length.");
    }

    @Test
    public void productionSequenceNullFirstString() {
        assertThatThrownBy(() -> TransformOneStringToAnother.productionSequence(null, "dog", new HashSet<>())).
                isInstanceOf(NullPointerException.class);
    }

    @Test
    public void productionSequenceNullSecondString() {
        assertThatThrownBy(() -> TransformOneStringToAnother.productionSequence("cat", null, new HashSet<>())).
                isInstanceOf(NullPointerException.class);
    }

    @Test
    public void productionSequenceNullDictionary() {
        assertThatThrownBy(() -> TransformOneStringToAnother.productionSequence("cat", "dog", null)).
                isInstanceOf(NullPointerException.class);
    }
}
