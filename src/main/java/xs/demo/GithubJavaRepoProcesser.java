package xs.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * first demo
 *      抓取oschina信息
 */
public class GithubJavaRepoProcesser implements PageProcessor {
    private Site site = Site.me().setDomain("github.com");

    @Override
    public void process(Page page) {
        List<String> urls = page.getHtml().css("div.pagination").links().regex(".*/search/\\?l=java.*").all();
        page.addTargetRequests(urls);

//        page.putField("title", page.getHtml().xpath("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1").toString());
//        page.putField("content", page.getHtml().$("div.content").toString());
//        page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());
    }

    @Override
    public Site getSite() {
        return site;

    }

    public static void main(String[] args) {
        Spider.create(new GithubJavaRepoProcesser()).addUrl("https://github.com/search?l=Java&p=1&q=stars%3A%3E1&s=stars&type=Repositories")
                .addPipeline(new ConsolePipeline()).run();
    }
}
