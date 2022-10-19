package com.example.icremytest.services;

import com.example.icremytest.models.Keyword;
import com.example.icremytest.models.PositionLabel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

/**
 * @author Okala Bashir .O.
 */

@Slf4j
@Service
public class KeywordService {

    public static final List<Keyword> testKeywords = new ArrayList<>();

    static {
        testKeywords.add(new Keyword("label_1", "1", 4));
        testKeywords.add(new Keyword("label_2", "2", 8));
        testKeywords.add(new Keyword("label_3", "3", 16));
        testKeywords.add(new Keyword("label_3a", "3", 160));
        testKeywords.add(new Keyword("label_3b", "3", 134));
        testKeywords.add(new Keyword("label_4", "4", 32));
        testKeywords.add(new Keyword("label_5", "5", 64));
        testKeywords.add(new Keyword("label_5", "5", 64));
        testKeywords.add(new Keyword("label_6", "6", 128));
        testKeywords.add(new Keyword("label_7", "16", 128));
        testKeywords.add(new Keyword("label_8", "26", 128));
        testKeywords.add(new Keyword("label_9", "36", 128));
        testKeywords.add(new Keyword("label_9", "36", 20));
        testKeywords.add(new Keyword("label_9", "36", 30));
        testKeywords.add(new Keyword("label_10", "46", 128));
        testKeywords.add(new Keyword("label_11", "56", 128));
        testKeywords.add(new Keyword("label_12", "76", 128));
        testKeywords.add(new Keyword("label_13", "86", 128));
        testKeywords.add(new Keyword("label_14", "96", 128));
        testKeywords.add(new Keyword("label_15", "99", 128));
        testKeywords.add(new Keyword("label_16", "100", 128));

    }

    public List<Keyword> getCalculatedKeyword(List<Keyword> testKeywords) {
        List<Keyword> result = new ArrayList<>();
        testKeywords.forEach(keyword -> {
            Keyword calculatedResult = new Keyword(keyword.getLabel(), keyword.getPosition(), keyword.getVolume());
            result.add(calculatedResult);
        });

        return result;
    }


    public List<Keyword> sortByPositionThenVolume(List<Keyword> keywords) {
        log.info("sorting By Position Then Volume");
        Comparator<Keyword> compareByPosition = Comparator.comparing(keyword -> Integer.parseInt(keyword.getPosition()));
        Comparator<Keyword> compareByVolume = Comparator.comparing(Keyword::getVolume).reversed();
        Comparator<Keyword> comparator = compareByPosition.thenComparing(compareByVolume);
        keywords.sort(comparator);
        return keywords;
    }

    public List<Keyword> sortByVolumeThenPosition(List<Keyword> keywords) {
        log.info("sorting By Volume Then Position");

        Comparator<Keyword> compareByVolume = Comparator.comparing(Keyword::getVolume).reversed();
        Comparator<Keyword> compareByPosition = Comparator.comparing(keyword -> Integer.parseInt(keyword.getPosition()));
        Comparator<Keyword> comparator = compareByVolume.thenComparing(compareByPosition);
        keywords.sort(comparator);
        return keywords;
    }

    public Map<PositionLabel, List<Keyword>> groupByPositionLabelThenSortPositionThenVolume(List<Keyword> keywordList){
        log.info("grouping By PositionLabel and Then Sorting Position Then Volume");
        return sortByPositionThenVolume(keywordList).stream()
                .collect(groupingBy(Keyword::getPositionLabel));
    }

    public Map<PositionLabel, Integer> sumOfAllVolumes(List<Keyword> keywordList){
        log.info("summing All Volumes");
        return keywordList.stream()
                .collect(groupingBy(Keyword::getPositionLabel, summingInt(Keyword::getVolume)));
    }

    public Map<PositionLabel, Integer> sumOfAllVisits(List<Keyword> keywordList){
        log.info("summing All Visits");
        return keywordList.stream()
                .collect(groupingBy(Keyword::getPositionLabel, summingInt(Keyword::getVisits)));
    }
}
