package cn.itcast.service;

import cn.itcast.dao.ArticleRepository;
import cn.itcast.domain.Article;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public void save(Article article) {

        articleRepository.save(article);
    }

    public void delete(Article article) {

        articleRepository.delete(article);
    }

    public Article findOne(Integer id) {

        return articleRepository.findOne(id);
    }

    public Iterable<Article> findAll() {
        return articleRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
    }

    public Page<Article> findAll(Pageable pageable) {

        return articleRepository.findAll(pageable);
    }

    public List<Article> findByTitle(String title) {

        return articleRepository.findByTitle(title);
    }

    public Page<Article> findByTitle(String title, Pageable pageable) {
        return articleRepository.findByTitle(title, pageable);
    }

    public Page<Article> findByContent(String content, Pageable pageable){
        return articleRepository.findByContent(content,pageable);
    }

    //--------------------------------------以下放在模块二讲解-------------------------------------------

    public Page<Article> testTermQuery(String attr, String keyword) {
        QueryBuilder termQuery = new TermQueryBuilder(attr, keyword);
        SearchQuery searchQuery = new NativeSearchQuery(termQuery); //默认自带分页功能，每页10条
        return articleRepository.search(searchQuery);
    }

    public Page<Article> testWildcardQuery(String attr, String keyword) {
        QueryBuilder wildcardQuery = new WildcardQueryBuilder(attr, "*" + keyword + "*");
        SearchQuery searchQuery = new NativeSearchQuery(wildcardQuery); //默认自带分页功能，每页10条
        return articleRepository.search(searchQuery);
    }

    public Page<Article> testQueryStringQuery(String attr, String keyword) {
        QueryBuilder queryStringQueryBuilder = new QueryStringQueryBuilder(keyword)
                .field(attr)
                .defaultOperator(QueryStringQueryBuilder.Operator.OR);
        SearchQuery searchQuery = new NativeSearchQuery(queryStringQueryBuilder); //默认自带分页功能，每页10条
        return articleRepository.search(searchQuery);
    }

}
