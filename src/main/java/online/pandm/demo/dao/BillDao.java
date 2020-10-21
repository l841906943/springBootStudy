package online.pandm.demo.dao;

import online.pandm.demo.entity.Bill;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Bill)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-12 11:39:17
 */
public interface BillDao {

    /**
     * 通过ID查询单条数据
     *
     * @param bid 主键
     * @return 实例对象
     */
    Bill queryById(Integer bid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Bill> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param bill 实例对象
     * @return 对象列表
     */
    List<Bill> queryAll(Bill bill);

    /**
     * 新增数据
     *
     * @param bill 实例对象
     * @return 影响行数
     */
    int insert(Bill bill);

    /**
     * 修改数据
     *
     * @param bill 实例对象
     * @return 影响行数
     */
    int update(Bill bill);

    /**
     * 通过主键删除数据
     *
     * @param bid 主键
     * @return 影响行数
     */
    int deleteById(Integer bid);

    /**
     * 通过主键查询
     *
     * @param bid 主键
     * @return 影响行数
     */
    List<Long> queryMoneyById(Integer bid);

}