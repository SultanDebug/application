package com.hzq.democlient;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"server.port=${random.int(2000,65534)}", "spring.profiles.active=dev"})
@SpringBootTest(classes = DemoclientApplication.class)
public class DemoclientApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void generateMarkdownDocs() throws Exception {
        //    输出Markdown格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();
        String url="http://localhost:20001/v2/api-docs";
//        String encode = URLEncoder.encode("需要授权", "utf-8");

        Swagger2MarkupConverter.from(new URL(url))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("/docs/client"));
    }

}
