package cn.itcast.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "blog", type = "article")
public class Article {
    @Id
    @Field(index = FieldIndex.no, store = true, type = FieldType.Integer)
    private Integer id;
    @Field(index = FieldIndex.not_analyzed,  store = true, type = FieldType.String)
    private String title;
    @Field(index = FieldIndex.analyzed, analyzer = "ik", store = true, searchAnalyzer = "ik", type = FieldType.String)
    private String content;

    //FieldIndex.no  不建立索引(目录中就没有这个字段的词条),无法进行查询
    //FieldIndex.not_analyzed  不分词, 但是建立索引(目录中有这个字段的词条), 可以进行查询
    //FieldIndex.analyzed  分词,建立索引(目录中有这个字段的词条),可以进行查询

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", content="
                + content + "]";
    }

}
