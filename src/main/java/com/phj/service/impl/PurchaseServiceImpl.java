package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phj.mapper.FoodMapper;
import com.phj.mapper.PurchaseItemMapper;
import com.phj.mapper.PurchaseMapper;
import com.phj.pojo.Food;
import com.phj.pojo.Purchase;
import com.phj.pojo.PurchaseItem;
import com.phj.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 供应商 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {


    private final Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private PurchaseItemMapper purchaseItemMapper;

    /**
     * @param id 通过id查询 Purchase
     * @return Purchase
     */
    @Override
    public Purchase selectById(Long id) {
        Purchase purchase = new Purchase();
        purchase.setId(id);
        Purchase t = purchaseMapper.baseSelectOne(purchase);
        List<PurchaseItem> purchaseItems = purchaseItemMapper.selectByOrderId(id);
        t.setPurchaseItems(purchaseItems);
        return t;
    }

    /**
     * @param ids 通过id批量查询 Purchase
     * @return Purchase
     */
    @Override
    public List<Purchase> selectByIds(List<Long> ids) {

        return purchaseMapper.selectBatchIds(ids);
    }

    /**
     * param构建条件
     */
    @Override
    public List<Purchase> selectList(Purchase param) {

        return purchaseMapper.baseSelect(param);
    }

    /**
     * 查询All
     *
     * @return List<Purchase>
     */
    @Override
    public List<Purchase> selectAll() {
        return purchaseMapper.selectList(null);
    }

    /**
     * 分页查询所有
     */
    @Override
    public Page<Purchase> selectPage(Integer currentNo, Integer pageSize) {
        if (currentNo <= 0) {
            currentNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        Page page = new Page<Purchase>(currentNo, pageSize, true);
        return purchaseMapper.selectPage(page, null);
    }

    /**
     * @param param 通过param构建条件查询多个Purchase
     * @return List<Purchase>
     */
    @Override
    public PageInfo<Purchase> selectPage(Integer currentNo, Integer pageSize, Purchase param) {
        if (currentNo <= 0) {
            currentNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        PageHelper.startPage(currentNo, pageSize,true);
        List<Purchase> purchases = purchaseMapper.baseSelect(param);
        return PageInfo.of(purchases);
    }


    /**
     * @param list 批量新增
     * @return Integer 受影响条数
     */
    @Override
    public Integer insertList(List<Purchase> list) {
        Integer integer = purchaseMapper.insertBatchSomeColumn(list);
        return integer;
    }

    /**
     * @param param 单个新增
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(Purchase param) {
        List<PurchaseItem> purchaseItems = param.getPurchaseItems();


        BigDecimal totalMoney = new BigDecimal("0.00");
        for (PurchaseItem purchaseItem : purchaseItems) {
                BigDecimal num = new BigDecimal(purchaseItem.getNum());
                BigDecimal price =purchaseItem.getPurchasePrice();
                //BigDecimal每次运算是产生一个新对象
                totalMoney=totalMoney.add( price.multiply(num));
        }
        param.setTotalMoney(totalMoney);
        param.setCreateDate(LocalDateTime.now());
        param.setUpdateDate(LocalDateTime.now());
        int i = purchaseMapper.insert(param);
        //主键回填
        Long id = param.getId();

        //修改食材项
        List<Food> foodsItem = new ArrayList<>();
        for (PurchaseItem purchaseItem : purchaseItems) {
            purchaseItem.setPurchaseId(id);
            foodsItem.add(
                    new Food()
                            .setId(purchaseItem.getFoodId())
                            .setStock(purchaseItem.getNum())
                            .setMarketPrice(purchaseItem.getPurchasePrice())
            );

        }

        //批量插入订单项数据
        purchaseItemMapper.insertBatchSomeColumn(purchaseItems);
        //修改库存
        //未支付 0 支付 1
        int row = foodMapper.updateBatch(foodsItem);
//        logger.info("修改影响行数：",row);

        return i< 1 ? false : true;
    }

    /**
     * @param newOrder 必须带有主键
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Purchase newOrder) {
    //取出新订单项
        List<PurchaseItem> newItems = newOrder.getPurchaseItems();
        //取旧食材
        List<PurchaseItem> oldItems = purchaseItemMapper.selectByOrderId(newOrder.getId());

        BigDecimal totalMoney = new BigDecimal("0.00");
        for (PurchaseItem purchaseItem : newItems) {
            BigDecimal num = new BigDecimal(purchaseItem.getNum());
            BigDecimal price =purchaseItem.getPurchasePrice();
            //BigDecimal每次运算是产生一个新对象
            totalMoney=totalMoney.add( price.multiply(num));
        }
        newOrder.setTotalMoney(totalMoney);
        newOrder.setUpdateDate(LocalDateTime.now());


        purchaseItemMapper.deleteByPurchaseId(newOrder.getId());
        int i = purchaseMapper.updateById(newOrder);



        List<Food> foodsItem = new ArrayList<>();
        for (PurchaseItem purchaseItem : newItems) {
            //设置订单项订单号
            purchaseItem.setPurchaseId(newOrder.getId());
            foodsItem.add(
                    new Food()
                            .setId(purchaseItem.getFoodId())
                            .setStock(purchaseItem.getNum())
                            .setMarketPrice(purchaseItem.getPurchasePrice())
            );

        }

        //批量插入订单项数据
        purchaseItemMapper.insertBatchSomeColumn(newItems);
       //修改食材
        List<Food> oldFoodItem = new ArrayList<>();
        for (PurchaseItem oldItem : oldItems) {
            oldFoodItem.add(
                    new Food()
                            .setId(oldItem.getFoodId())
                            .setStock(-oldItem.getNum())
                            .setMarketPrice(oldItem.getPurchasePrice())
            );
        }

        foodMapper.updateBatch(oldFoodItem);
        int row = foodMapper.updateBatch(foodsItem);

        return i < 1 ? false : true;
    }

    /**
     * @param id 通过id删除
     * @return boolean
     */
    @Override
    public boolean deleteById(Long id) {
        int i = purchaseMapper.deleteById(id);
        return i < 1 ? false : true;
    }

    /**
     * @param ids 通过id集合批量删除
     * @return boolean
     */
    @Override
    public boolean deleteByIds(List<Long> ids) {
        int i = purchaseMapper.deleteBatchIds(ids);
        return i < 1 ? false : true;
    }


    /**
     * 供应商条件列表
     *
     * @param param 根据需要进行传值
     * @return
     */
    public Wrapper<Purchase> getWrapper(Purchase param) {

        return Wrappers.<Purchase>lambdaUpdate()
                // 主键 订单号
                .eq(param.getId() != null, Purchase::getId, param.getId())
                // 总金额
                .eq(param.getTotalMoney() != null, Purchase::getTotalMoney, param.getTotalMoney())
                // 支付状态
                .eq(param.getPayStatus() != null, Purchase::getPayStatus, param.getPayStatus())
                // 采购类型（0代表临时，1代表批量）
                .eq(param.getType() != null, Purchase::getType, param.getType())
                // 采购人id
                .eq(param.getWorkerId() != null, Purchase::getWorkerId, param.getWorkerId())
                // 供应商id
                .eq(param.getSupplierId() != null, Purchase::getSupplierId, param.getSupplierId())
                // 备注
                .eq(!StringUtils.isEmpty(param.getComment()), Purchase::getComment, param.getComment())
                // 采购日期
                .eq(param.getPurchaseDate() != null, Purchase::getPurchaseDate, param.getPurchaseDate())
                // 配货开始日期
                .ge(param.getDistributionStart() != null, Purchase::getDistributionStart, param.getDistributionStart())
                // 配货结束时间
                .le(param.getDistributionEnd() != null, Purchase::getDistributionEnd, param.getDistributionEnd())
                // 创建时间
                .eq(param.getCreateDate() != null, Purchase::getCreateDate, param.getCreateDate())
                // 最后修改时间
                .eq(param.getUpdateDate() != null, Purchase::getUpdateDate, param.getUpdateDate())
                // 逻辑删除(0代表未删除，1代表删除)
                .eq(param.getIsDelete() != null, Purchase::getIsDelete, param.getIsDelete())
                ;
    }
}
