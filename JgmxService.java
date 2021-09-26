package com.amber.passer.service;

import com.amber.passer.entity.GzVO;
import com.amber.passer.entity.Jgmx;

import javax.xml.crypto.Data;
import java.util.List;

public interface JgmxService {
    /**
     * 根据工号返回总工资
     *
     * @param gh
     * @return
     */
    Double getSumByGh(int gh);

    List<GzVO> getAllGz();

    /**
     * 获取每个加工人加工总数量
     */
    List<Jgmx> getAllnum();
    /**
     * 根据工号获取加工总数量
     */
    Double getJgNumBygh(int gh);
}
