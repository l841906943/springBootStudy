package online.pandm.demo.service.impl;

import online.pandm.demo.entity.Bill;
import online.pandm.demo.dao.BillDao;
import online.pandm.demo.service.BillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Bill)表服务实现类
 *
 * @author makejava
 * @since 2020-04-12 11:39:20
 */
@Service("billService")
public class BillServiceImpl implements BillService {
    @Resource
    private BillDao billDao;

    /**
     * 通过ID查询单条数据
     *
     * @param bid 主键
     * @return 实例对象
     */
    @Override
    public Bill queryById(Integer bid) {
        return this.billDao.queryById(bid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Bill> queryAllByLimit(int offset, int limit) {
        return this.billDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param bill 实例对象
     * @return 实例对象
     */
    @Override
    public Bill insert(Bill bill) {
        this.billDao.insert(bill);
        return bill;
    }

    /**
     * 修改数据
     *
     * @param bill 实例对象
     * @return 实例对象
     */
    @Override
    public Bill update(Bill bill) {
        this.billDao.update(bill);
        return this.queryById(bill.getBid());
    }

    /**
     * 通过主键删除数据
     *
     * @param bid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer bid) {
        return this.billDao.deleteById(bid) > 0;
    }

    /**
     * 通过主键查询数据
     *
     * @param bid 主键
     */
    @Override
    public List<Long> queryMoneyById(Integer bid) {
        return this.billDao.queryMoneyById(bid);
    }
}