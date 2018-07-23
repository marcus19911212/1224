package cn.itcast.service.test;

import cn.itcast.domain.Article;
import cn.itcast.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ArticleServiceTest {
	@Autowired
	private ArticleService articleService;

	@Test
	public void testSave() {
		Article article = new Article();
		article.setId(1002);
		article.setTitle("Spring Data Elasticsearch 1.3.1 昨天发布");
		article.setContent("Spring Data Elasticsearch 1.3.1 昨天发布，超牛逼");
		articleService.save(article);
	}

	@Test
	public void testDelete() {
		Article article = new Article();
		article.setId(1002);
		articleService.delete(article);
	}

	@Test
	public void testFindOne() {

		System.out.println(articleService.findOne(1002));
	}

	@Test
	public void testSaveBatch() {
		for (int i = 1; i <= 100; i++) {
			Article article = new Article();
			article.setId(i);
			article.setTitle(i + "Spring Data Elasticsearch 1.3.1 昨天发布");
			article.setContent(i+ "Spring Data Elasticsearch 1.3.1 昨天发布，秒天秒地秒自己");
			articleService.save(article);
		}
	}

	@Test
	// 排序分页查询
	public void testSortAndPaging() {
		/*Iterable<Article> articles = articleService.findAll();
		for (Article article : articles) {
			System.out.println(article);
		}*/

		Pageable pageable = new PageRequest(0, 10);
		Page<Article> pageData = articleService.findAll(pageable);
		for (Article article : pageData.getContent()) {
			System.out.println(article);
		}
	}

	@Test
	// 条件查询
	public void testConditionQuery() {
		// 查询 标题中含有 “昨天”
		// List<Article> articles = articleService.findByTitle("昨天");
		// for (Article article : articles) {
		// System.out.println(article);
		// }

		// 查询 标题中含有 “昨天” 1-10条
		Pageable pageable = new PageRequest(0, 10);
		Page<Article> pageData = articleService.findByContent("昨天", pageable);
		System.out.println("总记录数：" + pageData.getTotalElements());
		for (Article article : pageData.getContent()) {
			System.out.println(article);
		}
	}
    //--------------------------------------以下放在模块二讲解-------------------------------------------
	@Test  //基于词条的相等条件查询
	public void testTermQuery(){
		Page<Article> pageData = articleService.testTermQuery("content","昨天");
		System.out.println("总记录数：" + pageData.getTotalElements());
		for (Article article : pageData.getContent()) {
			System.out.println(article);
		}
	}

	@Test  //基于词条的模糊匹配查询
	public void testWildcardQuery(){
		Page<Article> pageData = articleService.testWildcardQuery("content","昨天");
		System.out.println("总记录数：" + pageData.getTotalElements());
		for (Article article : pageData.getContent()) {
			System.out.println(article);
		}
	}

	@Test  //分词查询
	public void testQueryStringQuery(){
		Page<Article> pageData = articleService.testQueryStringQuery("title","昨夜");
		System.out.println("总记录数：" + pageData.getTotalElements());
		for (Article article : pageData.getContent()) {
			System.out.println(article);
		}
	}
}
