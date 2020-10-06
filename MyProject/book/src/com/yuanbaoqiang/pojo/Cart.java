/**
 * <h3>MyProject</h3>
 * <p>
 *
 *     购物车对象
 * </p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-05 13:49
 **/

package com.yuanbaoqiang.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    /*
     * @description: 添加商品项目
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午1:54
     * @param cartItem
     * @return: void
     */
    public void addItem(CartItem cartItem){
        // 先查看购物车中是否已经添加过该商品
        // 如果已经添加，则数量累加，总金额更新
        // 如果没有添加过，则直接放到集合中
        CartItem item = items.get(cartItem.getId());
        if(item == null){
            // 之前没有添加过该商品
            items.put(cartItem.getId(),cartItem);
        }else{
            // 已经添加过的情况
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount()))); // 更新总金额
        }
    }

    /*
     * @description: 删除商品项目
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午2:01
     * @param id
     * @return: void
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }


    /*
     * @description: 清空购物车
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午2:01
     * @param
     * @return: void
     */
    public void clear(){
        items.clear();
    }

    /*
     * @description: 修改商品数量
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午2:03
     * @param id
     * @param count
     * @return: void
     */
    public void updateCount(Integer id, Integer count){
        // 先查看购物车中是否有此商品，如果有，则修改商品数量，更新总金额
        CartItem cartItem = items.get(id);
        if(cartItem != null){
            cartItem.setCount(count); // 修改商品数量
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    /*
     * @description: 购物车商品总数
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午2:08
     * @param
     * @return: java.lang.Integer
     */
    public Integer getTotalCount(){
        Integer totalCount = 0;
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    /*
     * @description: 得到购物车中的总价格
     * @author: yuanbaoqiang
     * @date: 2020/10/5 下午2:11
     * @param
     * @return: java.math.BigDecimal
     */
    public BigDecimal getTotalPrice(){
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
