package com.internal.control.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.stereotype.Component;

@Component
public class CrawlerTest {
    public void callCrawler(){
    String crawlStorageFolder = "/Users/macjp/projects/treatfair/crawler";
    int numberOfCrawlers = 7;

    CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

    // Instantiate the controller for this crawl.
    PageFetcher pageFetcher = new PageFetcher(config);
    RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = null;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // For each crawl, you need to add some seed urls. These are the first
    // URLs that are fetched and then the crawler starts following links
    // which are found in these pages
        controller.addSeed("https://karriere.rbk.de");
        controller.addSeed("https://romed-jobs.de/de/jobs");

        // Start the crawl. This is a blocking operation, meaning that your code
    // will reach the line after this only when crawling is finished.
        controller.start(MyCrawler.class, numberOfCrawlers);
}
}

