package com.hwua.springboot;

import com.hwua.springboot.entity.News;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootEsApplicationTests {
    @Autowired
    private JestClient jestClient;

    @Test
    public void contextLoads() throws Exception{
        //oaec news
        News news=  new News();
        news.setId(1).setTitle("修改基因人类").setAuthor("张三").setContent("如题");
        Index index= new Index.Builder(news).index("oaec").type("news").id("1").build();
        jestClient.execute(index);
    }

    @Test
    public void testSreach() throws Exception{
        //oaec news
        String str="{\n" +
                "  \"query\" : {\n" +
                "    \"match\" : {\n" +
                "      \"title\" : \"基因\"\n" +
                "    }\n" +
                "  }\n" +
                "}\n";
        Search search=new Search.Builder(str).addIndex("oaec").addType("news").build();
        SearchResult result=jestClient.execute(search);
        System.out.println(result.getJsonString());
        /*Index index= new Index.Builder(news).index("oaec").type("news").id("1").build();
        jestClient.execute(index);*/
    }

}
