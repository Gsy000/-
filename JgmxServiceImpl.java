package com.amber.passer.service.impl;

import com.amber.passer.entity.Cljg;
import com.amber.passer.entity.GzVO;
import com.amber.passer.entity.Jgmx;
import com.amber.passer.mapper.CljgMapper;
import com.amber.passer.mapper.JgmxMapper;
import com.amber.passer.service.JgmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JgmxServiceImpl implements JgmxService {

    @Autowired
    private CljgMapper cljgMapper;

    @Autowired
    private JgmxMapper jgmxMapper;

    @Override
    public Double getSumByGh(int gh) {
        // 1 根据工号获取加工明细集合 每一条明细都有加工材料代码和数量
        List<Jgmx> list = jgmxMapper.getListJgmxByGh(gh);

        double sum = 0.0;
        // 2 遍历明细 把对应材料价格对象拿到，算钱 进行累加
        for (int i = 0; i < list.size(); i++) {
            // 获取到某一条记录明细
            Jgmx jgmx = list.get(i);
            String jgcldm = jgmx.getJgcldm();
            double jgsl = jgmx.getJgsl();
            // 获取该材料价格
            Cljg cljg = cljgMapper.getByCldm(jgcldm);
            double cldj = cljg.getCldj();

            double single = jgsl * cldj;
            sum += single;
        }

        return sum;
    }

    @Override
    public Double getJgNumBygh(int gh) {

        List<Integer> allGh = jgmxMapper.getAllGh();
        double num = 0.0;
        // 2 遍历明细 把对应的材料数量拿到
        for (int i = 0; i < allGh.size(); i++) {
            // 获取到某一条记录明细
            Integer jgmx =allGh.get(i);
            List<Integer> jgslByCldm = jgmxMapper.getJgslByCldm();
            num++;
        }
        return num;

    }


    @Override
    public List<Jgmx> getAllnum() {
        List<Jgmx> list = new ArrayList<>();
        // 获取加工人工号
        List<Integer> allGh = jgmxMapper.getAllGh();

        // 遍历工号
        for (int i = 0; i < allGh.size(); i++) {
            Integer gh = allGh.get(i);
            //获取总数量
            Double getAllnum=getJgNumBygh(gh);
            Jgmx jgmx=new Jgmx();
           jgmx.getGh();
           jgmx.getJgsl();
            list.add(jgmx);
        }
        return list;
    }

    @Override
    public List<GzVO> getAllGz() {
        List<GzVO> list = new ArrayList<>();
        // 获取加工人工号
        List<Integer> allGh = jgmxMapper.getAllGh();

        // 遍历工号
        for (int i = 0; i < allGh.size(); i++) {
            Integer gh = allGh.get(i);
            // 获取总工资
            Double sumByGh = getSumByGh(gh);
            GzVO vo = new GzVO();
            vo.setGh(gh);
            vo.setZgz(sumByGh);
            list.add(vo);
        }

        return list;

    }
}
