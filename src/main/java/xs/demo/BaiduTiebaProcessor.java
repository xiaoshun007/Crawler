package xs.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class BaiduTiebaProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        // 结果页url类似：https://tieba.baidu.com/p/5443716159
        List<String> links = page.getHtml().links().regex("https://tieba.baidu.com/p/\\d+").all();
        page.addTargetRequests(links);
        page.putField("title", page.getHtml().xpath("//div[@class='core_title_wrap_bright']/h3//tidyText()").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws Exception {
        Spider spider = Spider.create(new BaiduTiebaProcessor())
                .addUrl("https://tieba.baidu.com/f?kw=%E8%A1%8C%E5%B0%B8%E8%B5%B0%E8%82%89%E7%AC%AC%E5%85%AB%E5%AD%A3&fr=ala0&tpl=5");

        SpiderMonitor.instance().register(spider);
        spider.run();
    }
}
