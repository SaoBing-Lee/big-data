package com.yangzhongli.sp.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * @Author: Seraphim
 * @Date: 2018/8/6 11:57
 * @Description:
 */
@Slf4j
@Component
public class RedisClient {

    private static StringRedisTemplate template;
    private static ObjectMapper om = new ObjectMapper();

    private RedisClient() {}

    public static void setTemplate(StringRedisTemplate template) {
        RedisClient.template = template;
    }

    public static <T> void setObject(String key, T object) {
        if (!StringUtils.isEmpty(key)) {
            if (object instanceof Number) {
                template.opsForValue().set(key, String.valueOf(object));
            } else if (object.getClass().isPrimitive()) {
                template.opsForValue().set(key, String.valueOf(object));
            } else if (object instanceof String) {
                template.opsForValue().set(key, String.valueOf(object));
            } else {
                try {
                    String value = om.writeValueAsString(object);
                    template.opsForValue().set(key, value);
                } catch (JsonProcessingException var3) {
                    log.error(var3.getMessage(), var3);
                }
            }
        }
    }

    public static <T> void setNxObject(String key, T object) {
        if (!StringUtils.isEmpty(key)) {
            if (object instanceof Number) {
                template.opsForValue().setIfAbsent(key, String.valueOf(object));
            } else if (object.getClass().isPrimitive()) {
                template.opsForValue().setIfAbsent(key, String.valueOf(object));
            } else if (object instanceof String) {
                template.opsForValue().setIfAbsent(key, String.valueOf(object));
            } else {
                try {
                    String value = om.writeValueAsString(object);
                    template.opsForValue().setIfAbsent(key, value);
                } catch (JsonProcessingException var3) {
                    log.error(var3.getMessage(), var3);
                }

            }
        }
    }

    public static void setExpireObject(String key, Object object, int seconds) {
        if (!StringUtils.isEmpty(key)) {
            if (object instanceof Number) {
                template.opsForValue().set(key, String.valueOf(object), (long) seconds, TimeUnit.SECONDS);
            } else if (object.getClass().isPrimitive()) {
                template.opsForValue().set(key, String.valueOf(object), (long) seconds, TimeUnit.SECONDS);
            } else if (object instanceof String) {
                template.opsForValue().set(key, (String) object, (long) seconds, TimeUnit.SECONDS);
            } else {
                try {
                    String value = om.writeValueAsString(object);
                    template.opsForValue().set(key, value, (long) seconds, TimeUnit.SECONDS);
                } catch (JsonProcessingException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    public static <T> Object getObject(String key, Class<T> clazz) {
        String value = template.opsForValue().get(key);
        if (StringUtils.isEmpty(value)) {
            return null;
        } else if (clazz.getName().equals(String.class.getName())) {
            return value;
        } else if (clazz.getName().equals(Integer.class.getName())) {
            return Integer.valueOf(value);
        } else if (clazz.getName().equals(Double.class.getName())) {
            return Double.valueOf(value);
        } else if (clazz.getName().equals(Float.class.getName())) {
            return Float.valueOf(value);
        } else if (clazz.getName().equals(Byte.class.getName())) {
            return Byte.valueOf(value);
        } else if (clazz.getName().equals(Long.class.getName())) {
            return Long.valueOf(value);
        } else if (clazz.getName().equals(Short.class.getName())) {
            return Short.valueOf(value);
        } else if (clazz.getName().equals(BigDecimal.class.getName())) {
            return new BigDecimal(value);
        } else {
            try {
                return om.readValue(template.opsForValue().get(key), clazz);
            } catch (JsonParseException var4) {
                log.error(var4.getMessage(), var4);
            } catch (JsonMappingException var5) {
                log.error(var5.getMessage(), var5);
            } catch (IOException var6) {
                log.error(var6.getMessage(), var6);
            }

            return null;
        }
    }

    public static void set(byte[] key, byte[] value) {
        set(ByteUtils.toHexString(key), ByteUtils.toHexString(value));
    }

    public static byte[] get(byte[] key) {
        return ofNullable(get(ByteUtils.toHexString(key))).map(v -> ByteUtils.toByteArray(v)).orElse(null);
    }

    public static Integer append(String key, String value) {
        return StringUtils.isEmpty(key) ? 0 : template.opsForValue().append(key, value);
    }

    public static Long decr(String key) {
        return StringUtils.isEmpty(key) ? 0L : template.opsForValue().increment(key, -1L);
    }

    public static Long decrBy(String key, long integer) {
        return StringUtils.isEmpty(key) ? 0L : template.opsForValue().increment(key, -integer);
    }

    public static void del(String key) {
        ofNullable(key).filter(k -> k.length() > 0).ifPresent((k) -> template.delete(key));
    }

    public static Boolean exists(String key) {
        if (StringUtils.isEmpty(key)) {
            log.error("exists key is null");
            return false;
        } else {
            try {
                return template.hasKey(key);
            } catch (Exception var2) {
                log.error("exists:{}", key);
                log.error(var2.getMessage(), var2);
                return false;
            }
        }
    }

    public static boolean expire(String key, int seconds) {
        return StringUtils.isEmpty(key) ? false : template.expire(key, (long) seconds, TimeUnit.SECONDS);
    }

    public static boolean expireAt(String key, long unixTime) {
        return StringUtils.isEmpty(key) ? false : template.expireAt(key, new Date(unixTime));
    }

    public static String get(String key) {
        return StringUtils.isEmpty(key) ? null : template.opsForValue().get(key);
    }

    public static String getSet(String key, String value) {
        return StringUtils.isEmpty(key) ? null : template.opsForValue().getAndSet(key, value);
    }

    public static String getrange(String key, long startOffset, long endOffset) {
        return StringUtils.isEmpty(key) ? null : template.opsForValue().get(key, startOffset, endOffset);
    }

    public static Long hdel(String key, String field) {
        return StringUtils.isEmpty(key) ? 0L : template.opsForHash().delete(key, field);
    }

    public static Boolean hexists(String key, String field) {
        return StringUtils.isEmpty(key) ? false : template.opsForHash().hasKey(key, field);
    }

    public static String hget(String key, String field) {
        if (StringUtils.isEmpty(key)) {
            log.error("hget key is null");
            return null;
        } else {
            try {
                Object value = template.opsForHash().get(key, field);
                return null == value ? null : value.toString();
            } catch (Exception var3) {
                log.error("hget:" + key + ";" + field);
                log.error(var3.getMessage(), var3);
                return null;
            }
        }
    }

    public static Map<String, String> hgetAll(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        } else {
            Map<String, String> resultMap = new HashMap();
            Map<Object, Object> all = template.opsForHash().entries(key);
            if (null == all) {
                return resultMap;
            } else {
                Iterator it = all.entrySet().iterator();

                while (it.hasNext()) {
                    Map.Entry<Object, Object> entry = (Map.Entry) it.next();
                    resultMap.put(entry.getKey().toString(), entry.getValue().toString());
                }

                return resultMap;
            }
        }
    }

    public static Long hincrBy(String key, String field, long value) {
        return StringUtils.isEmpty(key) ? 0L : template.opsForHash().increment(key, field, value);
    }

    public static Set<String> hkeys(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        } else {
            Set<Object> set = template.opsForHash().keys(key);
            return ofNullable(set).map(s -> {
                Set<String> resultSet = new HashSet();
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    Object obj = it.next();
                    resultSet.add(obj.toString());
                }
                return resultSet;
            }).orElse(new HashSet<>());
        }
    }

    public static Long hlen(String key) {
        return StringUtils.isEmpty(key) ? null : template.opsForHash().size(key);
    }

    public static List<String> hmget(String key, String... fields) {
        if (StringUtils.isEmpty(key)) {
            return null;
        } else {
            Collection<Object> c = new ArrayList();
            int var4 = fields.length;

            Collections.addAll(c, fields);

            List<Object> objList = template.opsForHash().multiGet(key, c);
            List<String> resultList = new ArrayList();
            if (null == objList) {
                return resultList;
            } else {
                Iterator var9 = objList.iterator();

                while (var9.hasNext()) {
                    Object obj = var9.next();
                    resultList.add(obj.toString());
                }

                return resultList;
            }
        }
    }

    public static void hmset(String key, Map<String, String> hash) {
        if (!StringUtils.isEmpty(key)) {
            template.opsForHash().putAll(key, hash);
        }
    }

    public static void hset(String key, String field, String value) {
        if (!StringUtils.isEmpty(key)) {
            template.opsForHash().put(key, field, value);
        }
    }

    public static void hsetnx(String key, String field, String value) {
        ofNullable(key).filter(k -> k.length() > 0)
                .ifPresent(k -> template.opsForHash().putIfAbsent(k, field, value));
    }

    public static List<String> hvals(String key) {
        return ofNullable(key).filter(k -> k.length() > 0)
                .map(k -> ofNullable(template.opsForHash().values(k)).orElse(new ArrayList<>())
                        .stream().map(Object::toString)
                        .collect(Collectors.toList())).orElse(null);
    }

    public static Long incr(String key) {
        return StringUtils.isEmpty(key) ? 0L : template.opsForValue().increment(key, 1L);
    }

    public static Long incrBy(String key, long integer) {
        return StringUtils.isEmpty(key) ? 0L : template.opsForValue().increment(key, integer);
    }

    public static String lindex(String key, long index) {
        return StringUtils.isEmpty(key) ? null : template.opsForList().index(key, index);
    }

    public static Long llen(String key) {
        return ofNullable(key).filter(k -> k.length() > 0).map(k -> template.opsForList().size(k))
                .orElse(0L);
    }

    public static String lpop(String key) {
        return ofNullable(key).filter(k -> k.length() > 0).map(k -> template.opsForList().leftPop(k))
                .orElse(null);
    }

    public static Long lpush(String key, String value) {
        return ofNullable(key).filter(k -> k.length() > 0).map(k -> template.opsForList().leftPush(key, value))
                .orElse(0L);
    }

    /**
     * @param:
     * @return:
     * @auther: Seraphim
     * @date: 2018/10/18 15:26
     * @description: 插入集合对象到redis
     */
    public static void lpush(String key, Collection collection) {
        ofNullable(key).filter(k -> k.length() > 0)
                .map(k -> template.opsForList().leftPushAll(k, collection));
    }

    public static List<String> lrange(String key, long start, long end) {
        return ofNullable(key).filter(k -> k.length() > 0).map(k -> template.opsForList().range(k, start, end))
                .orElse(null);
    }

    public static Long lrem(String key, long count, String value) {
        return ofNullable(key).filter(k -> k.length() > 0).map(k -> template.opsForList().remove(k, count, value))
                .orElse(0L);
    }

    public static void lset(String key, long index, String value) {
        ofNullable(key).filter(k -> k.length() > 0).ifPresent(k -> template.opsForList().set(k, index, value));
    }

    public static void ltrim(String key, long start, long end) {
        ofNullable(key).filter(k -> k.length() > 0).ifPresent(k -> template.opsForList().trim(k, start, end));
    }

    public static String rpop(String key) {
        return ofNullable(key).filter(k -> k.length() > 0).map(k -> template.opsForList().rightPop(key))
                .orElse(null);
    }

    public static Long rpush(String key, String... string) {
        return StringUtils.isEmpty(key) ? 0L : template.opsForList().rightPushAll(key, string);
    }

    public static Long sadd(String key, String member) {
        return ofNullable(key).filter(k -> k.length() > 0).map(k -> template.opsForSet().add(k, member))
                .orElse(0L);
    }

    public static Long scard(String key) {
        return ofNullable(key).filter(k -> k.length() > 0).map(k -> template.opsForSet().size(k))
                .orElse(0L);
    }

    public static void set(String key, String value) {
        ofNullable(key).filter(k -> k.length() > 0)
                .ifPresent(k -> template.opsForValue().set(k, value));
    }

    public static void setex(String key, int seconds, String value) {
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            template.opsForValue().set(key, value, (long) seconds, TimeUnit.SECONDS);
        }
    }

    public static boolean setnx(String key, String value) {
        return !StringUtils.isEmpty(key) && !StringUtils.isEmpty(value) ? template.opsForValue().setIfAbsent(key, value) : false;
    }

    public static void setrange(String key, long offset, String value) {
        if (!StringUtils.isEmpty(key)) {
            template.opsForValue().set(key, value, offset);
        }
    }

    public static Boolean sismember(String key, String member) {
        return StringUtils.isEmpty(key) ? false : template.opsForSet().isMember(key, member);
    }

    public static Set<String> smembers(String key) {
        return StringUtils.isEmpty(key) ? null : template.opsForSet().members(key);
    }

    public static String spop(String key) {
        return ofNullable(key).filter(k -> k.length() > 0).map(k -> template.opsForSet().pop(k))
                .orElse(null);
    }

    public static String srandmember(String key) {
        return ofNullable(key).filter(k -> k.length() > 0).map(k -> template.opsForSet().randomMember(k))
                .orElse(null);
    }

    public static Long srem(String key, String member) {
        return StringUtils.isEmpty(key) ? 0L : template.opsForSet().remove(key, member);
    }

    public static String substr(String key, int start, int end) {
        return StringUtils.isEmpty(key) ? null : template.opsForValue().get(key, (long) start, (long) end);
    }

    public static boolean zadd(String key, double score, String member) {
        return StringUtils.isEmpty(key) ? false : template.opsForZSet().add(key, member, score);
    }

    public static Long zcard(String key) {
        return StringUtils.isEmpty(key) ? 0L : template.opsForZSet().size(key);
    }

    public static Long zcount(String key, double min, double max) {
        return StringUtils.isEmpty(key) ? 0L : template.opsForZSet().count(key, min, max);
    }

    public static Double zincrby(String key, double score, String member) {
        return StringUtils.isEmpty(key) ? null : template.opsForZSet().incrementScore(key, member, score);
    }

    public static Set<String> zrange(String key, int start, int end) {
        return StringUtils.isEmpty(key) ? null : template.opsForZSet().range(key, (long) start, (long) end);
    }

    public static Set<String> zrangeByScore(String key, double min, double max) {
        return StringUtils.isEmpty(key) ? null : template.opsForZSet().rangeByScore(key, min, max);
    }

    public static Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return StringUtils.isEmpty(key) ? null : template.opsForZSet().rangeByScore(key, min, max, (long) offset, (long) count);
    }

    public static Long zrank(String key, String member) {
        return StringUtils.isEmpty(key) ? 0L : template.opsForZSet().rank(key, member);
    }

    public static Long zrem(String key, String member) {
        if (StringUtils.isEmpty(key)) {
            return 0L;
        } else {
            Object[] values = new Object[]{member};
            return template.opsForZSet().remove(key, values);
        }
    }

    public static Long zremrangeByRank(String key, int start, int end) {
        return StringUtils.isEmpty(key) ? 0L : template.opsForZSet().removeRange(key, (long) start, (long) end);
    }

    public static Long zremrangeByScore(String key, double start, double end) {
        return StringUtils.isEmpty(key) ? 0L : template.opsForZSet().removeRangeByScore(key, start, end);
    }

    public static Set<String> zrevrange(String key, int start, int end) {
        return StringUtils.isEmpty(key) ? null : template.opsForZSet().reverseRange(key, (long) start, (long) end);
    }

    public static Set<String> zrevrangeByScore(String key, double max, double min) {
        return StringUtils.isEmpty(key) ? null : template.opsForZSet().reverseRangeByScore(key, min, max);
    }

    public static Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return StringUtils.isEmpty(key) ? null : template.opsForZSet().reverseRangeByScore(key, min, max, (long) offset, (long) count);
    }

    public static Long zrevrank(String key, String member) {
        return StringUtils.isEmpty(key) ? null : template.opsForZSet().reverseRank(key, member);
    }

    public static Double zscore(String key, String member) {
        return StringUtils.isEmpty(key) ? null : template.opsForZSet().score(key, member);
    }

    public static void publish(String channel, String message) {
        template.convertAndSend(channel, message);
    }

    public static Set<String> keys(String pattern) {
        return template.keys(pattern);
    }

    public static String ping() {
        return template.execute((RedisCallback) RedisConnectionCommands::ping).toString();
    }
}
