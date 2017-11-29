package xs.demo;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

/**
 * 爬虫监控例子
 */
public class MonitorExample {
    public static void main(String[] args) throws Exception {

        Spider oschinaSpider = Spider.create(new OschinaBlogPageProcesser()).addUrl("http://my.oschina.net/flashsword/blog");
        Spider githubSpider = Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft");

        SpiderMonitor.instance().register(oschinaSpider);
        SpiderMonitor.instance().register(githubSpider);

        oschinaSpider.start();
        githubSpider.start();
    }
}
