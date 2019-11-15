package com.lmt.zeus.id;

import com.lmt.zeus.id.snow.UidGenerator;
import com.lmt.zeus.parent.utils.SpringContextUtils;
import tk.mybatis.mapper.genid.GenId;

/**
 * @description 宙斯id生成器(雪花算法)
 *
 * @author bazhandao
 * @date 2019/11/15 15:50
 * @since JDK1.8
 */
public class ZeusIdGenerator implements GenId<Long> {

    /**
     * 雪花算法生成long型id
     * @author bazhandao
     * @date 2019-11-15
     * @param table
     * @param column
     * @return
     */
    @Override
    public Long genId(String table, String column) {
        UidGenerator uidGenerator = SpringContextUtils.getBean("uidGenerator", UidGenerator.class);
        return uidGenerator.getUID();
    }
}
