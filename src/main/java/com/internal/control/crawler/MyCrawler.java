package com.internal.control.crawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;


import org.apache.tika.parser.html.HtmlParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.w3c.dom.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class MyCrawler extends WebCrawler {
    public String chromeDriverPath = "C:/chromedriver/chromedriver.exe";



    private Set<String> visitedUrls = new HashSet<>();
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|youtube|microsoft|facebook"
            + "|png|mp3|mp4|zip|gz))$");

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "https://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        boolean b;
        b = FILTERS.matcher(href).matches();
        boolean c = visitedUrls.contains(href);
        if (!c) {
            visitedUrls.add(href);
            return !b && href.startsWith("https://");
        }
        else {
            return false;
        }
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String date="";
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);



//        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage","--whitelisted-ips=");
//        WebDriver driver = new ChromeDriver(options);
//        driver.get(url);
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String title = htmlParseData.getTitle();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            System.out.println("Title: " + title);
            System.out.println("Date:  " + date);
            System.out.println("Text length: " + text.length());
            System.out.println("Html length: " + html.length());
            System.out.println("Number of outgoing links: " + links.size());
          //  System.out.println(driver.getPageSource());

           // PRINT all the links we found
            for (WebURL webUrl : links) {
                System.out.println(webUrl.getURL());
            }
        }
       // driver.quit();
    }
}
