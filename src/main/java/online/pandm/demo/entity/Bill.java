package online.pandm.demo.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Bill)实体类
 *
 * @author makejava
 * @since 2020-04-12 11:39:17
 */
@Data
public class Bill implements Serializable {
    private static final long serialVersionUID = 522885868948729748L;
    
    private Integer bid;
    
    private String billCode;
    
    private String billName;
    
    private String billCom;
    
    private Integer billNum;
    
    private Double money;
    /**
    * 是否付款 0 未付款， 1已付款
    */
    private Integer pay;
    /**
    * 供应商id
    */
    private Integer pid;
    
    private Date createDate;

}