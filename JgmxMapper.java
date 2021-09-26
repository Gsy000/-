package com.amber.passer.mapper;
import com.amber.passer.entity.Jgmx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by sang on 2017/12/17.
 */
@Mapper
public interface JgmxMapper {


    /**
     * 获取所有加工明细
     *
     * @return
     */
    List<Jgmx> getAll();

    /**
     * 根据工号获取该人所有加工明细
     *
     * @param gh
     * @return
     */
    List<Jgmx> getListJgmxByGh(@Param("gh") int gh);

    /**
     * 获取所有工号
     * @return
     */
    List<Integer> getAllGh();
    List<Integer> getJgslByCldm();
}
