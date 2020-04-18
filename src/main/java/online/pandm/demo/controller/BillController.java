package online.pandm.demo.controller;

import online.pandm.demo.entity.Bill;
import online.pandm.demo.service.BillService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Bill)表控制层
 *
 * @author makejava
 * @since 2020-04-12 11:39:20
 */
@RestController
@RequestMapping("bill")
public class BillController {
    /**
     * 服务对象
     */
    @Resource
    private BillService billService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Bill selectOne(Integer id) {
        return this.billService.queryById(id);
    }

}