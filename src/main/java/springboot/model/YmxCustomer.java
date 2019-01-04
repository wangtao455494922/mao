package springboot.model;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**  
* <p>Description:用户信息 </p>  
* @author wjt  create
* @date 2018年12月24日 上午9:43:16
*/ 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ymx_customer")
@Alias(value="customer")
public class YmxCustomer implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private Integer uuid;

    private String customerid;

    private String pwd;

    private String showname;

    private String truename;

    private String registertime;

}