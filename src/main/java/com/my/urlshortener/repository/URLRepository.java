package com.my.urlshortener.repository;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

import com.my.urlshortener.util.Constants;
/**
 * 
 * @author MAHESH
 *
 */
@Repository
public class URLRepository {
    
	private final Jedis jedis;
    private final String idKey;
    private final String urlKey;

    public URLRepository() {
        this.jedis = new Jedis(Constants.REDIS_IP,Constants.REDIS_PORT);
        this.idKey = "id";
        this.urlKey = "url:";
    }

    public URLRepository(Jedis jedis, String idKey, String urlKey) {
        this.jedis = jedis;
        this.idKey = idKey;
        this.urlKey = urlKey;
    }

    public Long incrementID() {
        return jedis.incr(idKey) -1;
    }

    public void saveUrl(String key, String longUrl) {
        jedis.hset(urlKey, key, longUrl);
    }

    public String getUrl(Long id) throws Exception {
        String url = jedis.hget(urlKey, "url:"+id);
        if (url == null) {
            throw new Exception("Not Exist");
        }
        return url;
    }
}
