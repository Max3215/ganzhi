package com.ynyes.ganzhi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.ganzhi.entity.TdPayRecord;
import com.ynyes.ganzhi.repository.TdPayRecordRepo;

/**
 * 订单支付记录操作服务类
 *
 */
@Service
@Transactional
public class TdPayRecordService {
    @Autowired
    TdPayRecordRepo repository;
    
    /*
     * 取得支付记录
     */
    public TdPayRecord getById(Long id) {
        return repository.findOne(id);
    }
    
    /*
     * 保存支付记录
     */
    public TdPayRecord save(TdPayRecord record) {
        return repository.save(record);
    }
    
    /*
     * 保存支付记录
     */
    public List<TdPayRecord> save(List<TdPayRecord> records) {
        List<TdPayRecord> savedRecords = new ArrayList<TdPayRecord>();
        for(Iterator<TdPayRecord> datas = repository.save(records).iterator(); 
                datas.hasNext();) {
            savedRecords.add(datas.next());
        }
        return savedRecords;
    }
    
    /**
     * 取得订单相关的所有支付记录
     * @param orderId订单Id
     * @return
     */
    public List<TdPayRecord> getAllByOrderId(Long orderId) {
        return repository.findByOrderIdOrderByCreateTimeDesc(orderId);
    }
    
    /**
     * 取得订单的最后一次支付记录
     * @param orderId订单Id
     * @return
     */
    public TdPayRecord getByOrderId(Long orderId) {
        List<TdPayRecord> payRecords = repository.findByOrderIdOrderByCreateTimeDesc(orderId);
        if(payRecords.isEmpty()) {
            return null;
        } else {
            return payRecords.get(0);
        }
    }
}
