package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phj.dto.MealOrderSelect;
import com.phj.mapper.*;
import com.phj.pojo.*;
import com.phj.service.MealOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 排餐订单 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class MealOrderServiceImpl implements MealOrderService {


    private final Logger logger = LoggerFactory.getLogger(MealOrderServiceImpl.class);

    @Autowired
    private MealOrderMapper mealOrderMapper;

    @Autowired
    private ClientMealRankMapper clientMealRankMapper;

    @Autowired
    private DistributionClientMapper distributionClientMapper;

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private MealOrderItemMapper itemMapper;

    /**
     * @param id 通过id查询 MealOrder
     * @return MealOrder
     */
    @Override
    public MealOrder selectById(Long id) {
        return mealOrderMapper.baseSelectById(id);
    }

    /**
     * @param ids 通过id批量查询 MealOrder
     * @return MealOrder
     */
    @Override
    public List<MealOrder> selectByIds(List<Long> ids) {
        return mealOrderMapper.selectBatchIds(ids);
    }

    /**
     * param构建条件
     */
    @Override
    public List<MealOrder> selectList(MealOrder param) {
        Wrapper<MealOrder> wrapper = getWrapper(param);
        return mealOrderMapper.selectList(wrapper);
    }

    /**
     * 查询All
     *
     * @return List<MealOrder>
     */
    @Override
    public List<MealOrder> selectAll() {
        return mealOrderMapper.selectList(null);
    }

    /**
     * 分页查询所有
     * @return
     */
    @Override
    public PageInfo<MealOrder> selectPage(Integer currentNo, Integer pageSize, MealOrderSelect select) {
        if (currentNo <= 0) {
            currentNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        PageHelper.startPage(currentNo, pageSize);

        return PageInfo.of(mealOrderMapper.selectOrderInfo(select));
    }


    /**
     * @param list 批量新增
     * @return Integer 受影响条数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertList(List<MealOrder> list) {


        List<MealOrderItem> mealOrderItems = list.get(0).getMealOrderItems();
        //批量校验

        checkOrderItem(list);

        //做批量查询食材
        List<Long> ids = new ArrayList<>();
        for (MealOrderItem mealOrderItem : mealOrderItems) {
            ids.add(mealOrderItem.getFoodId());
        }
        List<Food> foods = foodMapper.selectBatchIds(ids);



        for (MealOrder mealOrder : list) {
            //当前订单总价
            BigDecimal totalMoney = new BigDecimal("0.00");
            //当前订单人数
            BigDecimal personNum = new BigDecimal(mealOrder.getPersonNum());

            List<MealOrderItem> items = mealOrder.getMealOrderItems();

            DistributionClient distributionClient = new DistributionClient();
            distributionClient.setId(mealOrder.getClientId());
            distributionClient = distributionClientMapper.baseSelect(distributionClient).get(0);
            //用餐时间
            LocalDate mealTime = mealOrder.getMealTime();
            //提前天数
            Integer day = distributionClient.getTypeData().getDay();
            //配送时间 - 减去提前天数
            LocalDate plus = mealTime.plusDays(-day);
            mealOrder.setDeliveryTime(plus);
            //采购日期
            LocalDate purchaseDate = plus.plusDays(-1);
            mealOrder.setPurchaseDate(purchaseDate);
            mealOrder.setUpdateDate(LocalDateTime.now());
            for (MealOrderItem item : items) {
                //获取食材进价
                BigDecimal marketPrice = null;
                //获取食材数量
                BigDecimal foodNum = new BigDecimal(item.getTotalQuantity())    ;
                for (Food food : foods) {
                    if (food.getId().equals(item.getFoodId())) {
                        item.setMarketPrice(food.getMarketPrice());
                        marketPrice=food.getMarketPrice();
                        break;
                    }
                }
                //出货价 * 食材数量
                BigDecimal divide = marketPrice.multiply(foodNum);
                totalMoney = totalMoney.add(divide);
            }
            mealOrder.setTotalPrice(totalMoney);
            mealOrderMapper.insert(mealOrder);
            Long id = mealOrder.getId();

            for (MealOrderItem mealOrderItem : mealOrder.getMealOrderItems()) {
                mealOrderItem.setMealPlanId(id);
            }
            itemMapper.insertBatchSomeColumn(mealOrder.getMealOrderItems());
        }


        return list.size();
    }

    //做订单数据校验
    public void checkOrderItem(List<MealOrder> list) {

        //校验客户餐别信息是否正确
        MealOrder order = list.get(0);
        //取出第一项 餐别id 餐标
        ClientMealRank  one= new ClientMealRank();
        one.setMealRankId(order.getMealRankId());
        one.setMealPrice(order.getMealPrice());

        //取出客户id
        List<Long> ids = new ArrayList<>();
        for (MealOrder mealOrder : list) {
            if (mealOrder.getMealOrderItems().size() <= 0) {
                throw new RuntimeException("排餐订单食材项不能为空");
            }
            ids.add(mealOrder.getClientId());
        }

        int i = clientMealRankMapper.checkClientMealRank(one, ids);


        if (i != list.size()) {
            throw new RuntimeException("客户餐别信息错误");
        }
    }

    /**
     * @param param 必须带有主键
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(MealOrder param) {



        //做批量查询食材
        List<Long> ids = new ArrayList<>();
        List<MealOrderItem> mealOrderItems = param.getMealOrderItems();
        for (MealOrderItem mealOrderItem : mealOrderItems) {
            ids.add(mealOrderItem.getFoodId());
        }
        List<Food> foods = foodMapper.selectBatchIds(ids);

        //当前订单总价
        BigDecimal totalMoney = new BigDecimal("0.00");

        List<MealOrderItem> items = param.getMealOrderItems();

        for (MealOrderItem item : items) {
            //获取食材进价
            BigDecimal marketPrice = null;
            //获取食材数量
            BigDecimal foodNum = new BigDecimal(item.getTotalQuantity());
            for (Food food : foods) {
                if (food.getId().equals(item.getFoodId())) {
                    item.setMarketPrice(food.getMarketPrice());
                    marketPrice=food.getMarketPrice();
                    break;
                }
            }
            //出货价 * 食材数量
            BigDecimal divide = marketPrice.multiply(foodNum);
            totalMoney = totalMoney.add(divide);
        }
        param.setTotalPrice(totalMoney);
        //使用新对象update
        MealOrder mealOrder = new MealOrder();
        mealOrder.setId(param.getId());
        mealOrder.setTotalPrice(totalMoney);

        int i = mealOrderMapper.updateById(mealOrder);
        Long id = param.getId();

        //删除之前订单
        MealOrderItem delete = new MealOrderItem();
        delete.setMealPlanId(id);
        itemMapper.delete(Wrappers.lambdaUpdate(delete));

        for (MealOrderItem mealOrderItem : param.getMealOrderItems()) {
            mealOrderItem.setMealPlanId(id);
        }
        itemMapper.insertBatchSomeColumn(param.getMealOrderItems());

        return i < 1 ? false : true;
    }

    /**
     * @param id 通过id删除
     * @return boolean
     */
    @Override
    public boolean deleteById(Long id) {
        int i = mealOrderMapper.deleteById(id);
        return i < 1 ? false : true;
    }

    /**
     * @param ids 通过id集合批量删除
     * @return boolean
     */
    @Override
    public boolean deleteByIds(List<Long> ids) {
        int i = mealOrderMapper.deleteBatchIds(ids);
        return i < 1 ? false : true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean carriage(Long id) {
        MealOrder mealOrder = new MealOrder();
        mealOrder.setId(id);
        mealOrder.setCarriageType(1);
        mealOrderMapper.updateById(mealOrder);
        List<MealOrderItem> mealOrderItems = itemMapper.baseSelect(new MealOrderItem().setMealPlanId(id));
        List<Food> foods = new ArrayList<>();
        for (MealOrderItem mealOrderItem : mealOrderItems) {
            Long foodId = mealOrderItem.getFoodId();
            Integer totalQuantity = mealOrderItem.getTotalQuantity();
            Food food = new Food();
            food.setId(foodId);
            food.setStock(-totalQuantity);
            foods.add(food);
        }
        foodMapper.updateBatchStock(foods);

        return false;
    }


    /**
     * 排餐订单条件列表
     *
     * @param param 根据需要进行传值
     * @return
     */
    public Wrapper<MealOrder> getWrapper(MealOrder param) {

        return Wrappers.<MealOrder>lambdaUpdate()
                // 主键
                .eq(param.getId() != null, MealOrder::getId, param.getId())
                // 客户id
                .eq(param.getClientId() != null, MealOrder::getClientId, param.getClientId())
                // 餐标（每餐价格）
                .eq(param.getMealPrice() != null, MealOrder::getMealPrice, param.getMealPrice())
                // 用餐人数
                .eq(param.getPersonNum() != null, MealOrder::getPersonNum, param.getPersonNum())
                // 总金额
                .eq(param.getTotalPrice() != null, MealOrder::getTotalPrice, param.getTotalPrice())
                // 餐别id
                .eq(param.getMealRankId() != null, MealOrder::getMealRankId, param.getMealRankId())
                // 用餐时间
                .eq(param.getMealTime() != null, MealOrder::getMealTime, param.getMealTime())
                // 配送标志（0代表未配送，1代表配送中）
                .eq(param.getCarriageType() != null, MealOrder::getCarriageType, param.getCarriageType())
                // 配送时间
                .eq(param.getDeliveryTime() != null, MealOrder::getDeliveryTime, param.getDeliveryTime())
                // 制单人
                .eq(param.getSysUserId() != null, MealOrder::getSysUserId, param.getSysUserId())
                // 配货时间
                .eq(param.getPurchaseDate() != null, MealOrder::getPurchaseDate, param.getPurchaseDate())
                // 修改时间
                .eq(param.getUpdateDate() != null, MealOrder::getUpdateDate, param.getUpdateDate())
                // 制单时间
                .eq(param.getCreateDate() != null, MealOrder::getCreateDate, param.getCreateDate())
                ;
    }
}
