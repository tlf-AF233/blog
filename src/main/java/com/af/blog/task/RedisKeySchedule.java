package com.af.blog.task;

import com.af.blog.constants.RedisConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 定时从redis中删除自定义过期的key
 *
 * @author Tanglinfeng
 * @date 2021/10/11 17:17
 */
@Component
public class RedisKeySchedule {

    private final RedisTemplate redisTemplate;

    public RedisKeySchedule(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void removeExpiredIpAddress() {
        Set<String> keys = redisTemplate.keys(RedisConstants.PV + ":*");
        for (String key : keys) {
            Map<String, Long> map = redisTemplate.opsForHash().entries(key);
            map.forEach((k, v) -> {
                // 如果过期时间小于当前则删除该ip地址记录
                if (v < LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()) {
                    redisTemplate.opsForHash().delete(key, k);
                }
            });
        }
    }
}
