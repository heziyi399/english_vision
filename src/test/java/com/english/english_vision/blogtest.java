package com.english.english_vision;

import com.english.english_vision.mapper.BlogMapper;
import com.english.english_vision.mapper.WordMapper;
import com.english.english_vision.pojo.Blog;
import com.english.english_vision.pojo.Word;
import com.english.english_vision.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author
 * @Description
 * @Date
 **/
@SpringBootTest//如果不加的话无法启动测试
public class blogtest {
@Autowired
private BlogMapper blogMapper;
@Autowired
private WordMapper wordMapper;
    @Test
    public void test(){
        List<Blog>blogs=blogMapper.selectall();
        blogs.forEach(System.out::println);
blogMapper.insert(new Blog(2,"my blog","news"));
    }

    @Test
    public  void testJson() throws IOException {
      // List<Word>blogs= wordMapper.selectAll();
       String newjson = "[{\"id\":1,\"english\":\"apple\",\"chinese\":\"苹果\",\"level\":1,\"frequency\":3,\"img\":\"http://localhost:8107/0.png\",\"usa\":\"an apple\"},\n" +
               "  {\"id\":2,\"english\":\"pear\",\"chinese\":\"梨子\",\"level\":2,\"frequency\":2,\"img\":\"http://localhost:8107/2.png\",\"usa\":\"eat pear\"},\n" +
               "  {\"id\":3,\"english\":\"car\",\"chinese\":\"车\",\"level\":1,\"frequency\":3,\"img\":null,\"usa\":\"cars(复数）buy car(买车）\"},{\"id\":4,\"english\":\"people\",\"chinese\":\"人\",\"level\":1,\"frequency\":3,\"img\":null,\"usa\":\"people(单复一样） we are people\"}]";
       ObjectMapper objectMapper = new ObjectMapper();

 //   newjson=  JsonUtil.toJsonStr(blogs.getClass());

        List<Word>blog2= JsonUtil.toJsonListObject(newjson,Word.class);
        List<String>str = blog2.stream().map(e->{
            String ch = e.getChinese();
            return ch;
        }).collect(Collectors.toList());
        System.out.println(str);
//        System.out.println(newjson.getBytes(StandardCharsets.UTF_8));
//        JsonUtil.toJsonListObject(newjson,blogs.getClass());

     //   return newjson;
    }
}
