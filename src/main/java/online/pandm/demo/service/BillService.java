package online.pandm.demo.service;

import online.pandm.demo.entity.Bill;

import java.util.List;

/**
 * (Bill)表服务接口
 *
 * @author makejava
 * @since 2020-04-12 11:39:20
 */
public interface BillService {

    /**
     * 通过ID查询单条数据
     *
     * @param bid 主键
     * @return 实例对象
     */
    Bill queryById(Integer bid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Bill> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param bill 实例对象
     * @return 实例对象
     */
    Bill insert(Bill bill);

    /**
     * 修改数据
     *
     * @param bill 实例对象
     * @return 实例对象
     */
    Bill update(Bill bill);

    /**
     * 通过主键删除数据
     *
     * @param bid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer bid);

}