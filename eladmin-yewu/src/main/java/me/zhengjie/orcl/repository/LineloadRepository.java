package me.zhengjie.orcl.repository;

import io.lettuce.core.dynamic.annotation.Param;
import me.zhengjie.orcl.domain.Lineload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface LineloadRepository extends JpaRepository<Lineload,Integer>, JpaSpecificationExecutor<Lineload> {
    @Query(value = "select * from SCOTT.SVR_YC_SAMPLE_DEFINE where YC_ID=:ycid", nativeQuery = true)
    List<Map<String,String>> queryall(@Param("ycid") String ycid);
}
