package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private  CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {

        Jedis jedis = JedisUtil.getJedis();

        Set<String> categories = jedis.zrange("category", 0, -1);

        List<Category> cs=null;
        if(categories==null||categories.size()==0){
             cs = categoryDao.findAll();

             for(int i=0;i<cs.size();i++){
                 jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
             }
        }else {
            cs=new ArrayList<Category>();
            for(String name:categories){
                Category category = new Category();
                category.setCname(name);
                cs.add(category);
            }
        }
        return cs;
    }
}
