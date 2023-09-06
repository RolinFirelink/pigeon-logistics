package com.pigeon.logistics.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dxy
 * @date 2022年11月27日
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 新版
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件，对于单一数据库类型来说,都建议配置数据库类型,避免每次分页都去抓取数据库类型
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 乐观锁插件（当要更新一条记录的时候，希望这条记录没有被别人更新。）
        // 注：仅支持 updateById(id) 与 update(entity, wrapper) 方法，在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
        // 不能用AR模式更新，因为会出现空字段的问题。
        // 乐观锁实现方式：（在实体类的字段上加上@Version注解）
        // 取出记录时，获取当前 version；更新时，带上这个 version；
        // 执行更新时， set version = newVersion where version = oldVersion；如果 version 不对，就更新失败
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 防全表更新与删除插件：针对 update 和 delete 语句 ，阻止恶意的全表更新删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }

}
