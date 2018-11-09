/**
 * Generate by lmt-code-generator 2018-11-07 12:36:54
 * you can find lmt-code-generator project on github, please visit:
 *
 */
package com.lmt.parent.test.basic.service.impl;

import com.lmt.parent.basic.mapper.BasicMapper;
import com.lmt.parent.basic.service.BasicService;
import com.lmt.parent.basic.service.BasicServiceImpl;
import com.lmt.parent.test.basic.mapper.BasicAaCodeMapper;
import com.lmt.parent.test.entity.AaCode;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description AaCode基础Service实现
 * @author Generate by lmt-code-generator
 * @date 2018-11-07 12:36:54
 * @since JDK 1.8
 */
public class BasicAaCodeServiceImpl extends BasicServiceImpl<AaCode, String> implements BasicService<AaCode, String> {

    @Autowired
    private BasicAaCodeMapper basicAaCodeMapper;

    @Override
    public BasicMapper<AaCode, String> getBasicMapper() {
        return basicAaCodeMapper;
    }

    /**
     * 更新或保存
     * @param aaCode
     * @return
     */
    @Override
    public int saveOrUpdate(AaCode aaCode) {
        if(aaCode.getDdGuid() == null || get(aaCode.getDdGuid()) == null) {
            System.out.println("save->________");
            return insert(aaCode);
        } else {
            System.out.println("update->________");
            return update(aaCode);
        }
    }

}
