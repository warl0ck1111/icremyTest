package com.example.icremytest;

import com.example.icremytest.models.Keyword;
import com.example.icremytest.models.PositionLabel;
import com.example.icremytest.services.KeywordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@SpringBootApplication
@Slf4j
public class IcremyTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcremyTestApplication.class, args);
        log.info("\n **************** APP SERVICE STARTED ****************");
    }

    @Bean
    public CommandLineRunner demo(KeywordService keywordService) {
        return (args) -> {

            List<Keyword> testKeywordList = keywordService.getCalculatedKeyword(KeywordService.testKeywords);
            log.info("Testing sortByPositionThenVolume Start");
            keywordService.sortByPositionThenVolume(testKeywordList).forEach(System.out::println);
            log.info("Testing sortByPositionThenVolume End");

            log.info("Testing sortByVolumeThenPosition Start ");
            keywordService.sortByVolumeThenPosition(testKeywordList).forEach(System.out::println);
            log.info("Testing sortByVolumeThenPosition End");



            log.info("Testing groupByPositionLabelThenSortPositionThenVolumeMap Start ");
            Map<PositionLabel, List<Keyword>> mapResult = keywordService.groupByPositionLabelThenSortPositionThenVolume(testKeywordList);
            Collection<List<Keyword>> values = mapResult.values();
            values.forEach(System.out::println);
            log.info("Testing groupByPositionLabelThenSortPositionThenVolumeMap End ");

            log.info("Testing sumOfAllVolumes start ");
            keywordService.sumOfAllVolumes(testKeywordList).forEach((key, value)-> {
                System.out.println(key + ":" + value);
            });
            log.info("Testing sumOfAllVolumes end ");

            log.info("Testing sumOfAllVisit start ");
            keywordService.sumOfAllVisits(testKeywordList).forEach((key, value)-> {
                System.out.println(key + ":" + value);});
            log.info("Testing sumOfAllVisit end ");
        };
    }

}
