package com.ryan.gmall.to.es;

import com.ryan.gmall.pms.entity.SkuStock;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EsSkuProductInfo extends SkuStock implements Serializable {

    //sku的特定标题
    private String skuTitle;

    /**
     * 每个sku不同的属性值以及他的值
     */
    List<EsProductAttributeValue> attributeValues;
}
