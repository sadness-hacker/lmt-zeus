package com.lmt.parent.service.test;

import com.github.pagehelper.PageInfo;
import com.lmt.parent.service.TestApp;
import com.lmt.parent.test.entity.AaCode;
import com.lmt.parent.test.service.AaCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description AaCodeService测试类
 *
 * @author bazhandao
 * @date 2018/11/7 14:03
 * @since JDK1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApp.class)
public class AaCodeServiceTest {
    
    @Autowired
    private AaCodeService aaCodeService;

    @Test
    public void testQuery() {
        AaCode ac = new AaCode();
        List<AaCode> list = aaCodeService.query(ac);
        list.forEach(aaCode -> {
            System.out.println("测试query方法->" + aaCode);
        });
    }

    @Test
    public void testGet() {
        AaCode ac = aaCodeService.get("BWGL001");
        System.out.println("测试get方法->" + ac);
    }

    @Test
    public void testListById() {
        List<String> list = new ArrayList<>();
        list.add("BWGL001");
        list.add("BWGL");
        list.add("BWTZ099");
        list.add("BWTZ098");
        list.add("BWTZ005");
        list.add("BWTZ004");
        list.add("BWTZ003");
        List<AaCode> l = aaCodeService.listByPKList(list);
        l.forEach(aaCode -> {
            System.out.println("测试listById方法->" + aaCode);
        });
    }

    @Test
    public void testQueryOrderBy() {
        AaCode ac = new AaCode();
        List<AaCode> list = aaCodeService.queryOrderBy(ac, "CREATE_DATE", null);
        list.forEach(aaCode -> {
            System.out.println("测试queryOrderBy方法->" + aaCode);
        });
    }

    @Test
    public void testCount() {
        AaCode ac = new AaCode();
        ac.setDdTypeCode("DOC_TYPE");
        long count = aaCodeService.count(ac);
        System.out.println("测试count方法->" + count);
    }

    @Test
    public void queryByPage() {
        AaCode ac = new AaCode();
        PageInfo<AaCode> pageInfo = aaCodeService.queryByPage(ac, "CREATE_DATE", null, 2, 10);
        pageInfo.getList().forEach(aaCode -> {
            System.out.println("测试queryByPage方法->" + aaCode);
        });
    }

    @Test
    public void testInsert() {
        AaCode ac = new AaCode();
        ac.setDdTypeCode("9527");
        ac.setCreateDate(new Date());
        ac.setCreateUserId("0000");
        ac.setDdCode("098");
        ac.setDdDesc("desc");
        ac.setDdGuid("9527");
        ac.setDdName("9527");
        ac.setDdTypeGuid("rtfyguhijko");
        ac.setPathCode("pc");
        ac.setParentGuid("0");
        aaCodeService.insert(ac);
        ac = aaCodeService.get("9527");
        System.out.println("测试insert方法->" + ac);
        int i = aaCodeService.deleteByPk("9527");
        System.out.println("测试根据主键删除->" + i);
        ac = aaCodeService.get("9527");
        System.out.println("测试insert方法->" + ac);
    }

    @Test
    public void testBatchInsert() {
        List<AaCode> list = new ArrayList<>();
        AaCode ac = new AaCode();
        ac = new AaCode();
        ac.setDdCode("098");
        int i = aaCodeService.delete(ac);
        System.out.println("测试根据实体删除->" + i);

        ac.setDdTypeCode("9527");
        ac.setCreateDate(new Date());
        ac.setCreateUserId("0000");
        ac.setDdCode("098");
        ac.setDdDesc("desc");
        ac.setDdGuid("9527");
        ac.setDdName("9527");
        ac.setDdTypeGuid("rtfyguhijko");
        ac.setPathCode("pc");
        ac.setParentGuid("0");
        list.add(ac);

        ac = new AaCode();
        ac.setDdTypeCode("9528");
        ac.setCreateDate(new Date());
        ac.setCreateUserId("0000");
        ac.setDdCode("098");
        ac.setDdDesc("desc");
        ac.setDdGuid("9528");
        ac.setDdName("9528");
        ac.setDdTypeGuid("rtfyguhijko");
        ac.setPathCode("pc");
        ac.setParentGuid("0");
        list.add(ac);

        ac = new AaCode();
        ac.setDdTypeCode("9529");
        ac.setCreateDate(new Date());
        ac.setCreateUserId("0000");
        ac.setDdCode("098");
        ac.setDdDesc("desc");
        ac.setDdGuid("9529");
        ac.setDdName("9529");
        ac.setDdTypeGuid("rtfyguhijko");
        ac.setPathCode("pc");
        ac.setParentGuid("0");
        list.add(ac);
        i = aaCodeService.batchInsert(list);
        System.out.println("测试批量插入->" + i);

        ac = new AaCode();
        ac.setDdCode("098");
        i = aaCodeService.delete(ac);
        System.out.println("测试根据实体删除->" + i);
    }

    @Test
    public void testDelete() {
        AaCode ac = new AaCode();
        ac.setDdCode("098");
        int i = aaCodeService.delete(ac);
        System.out.println("测试根据实体删除->" + i);
    }

    @Test
    public void testUpdate() {
        aaCodeService.deleteByPk("9529");
        AaCode ac = new AaCode();
        ac.setDdTypeCode("9529");
        ac.setCreateDate(new Date());
        ac.setCreateUserId("0000");
        ac.setDdCode("098");
        ac.setDdDesc("desc");
        ac.setDdGuid("9529");
        ac.setDdName("9529");
        ac.setDdTypeGuid("rtfyguhijko");
        ac.setPathCode("pc");
        ac.setParentGuid("0");
        aaCodeService.insert(ac);
        ac = aaCodeService.get("9529");
        System.out.println("测试insert方法get->" + ac);
        ac.setDdCode("*********");
        int i = aaCodeService.update(ac);
        System.out.println("测试id更新->" + i);
        ac = aaCodeService.get("9529");
        System.out.println("测试insert方法->" + ac);
        aaCodeService.deleteByPk("9529");
    }

    @Test
    public void testSaveOrUpdate() {
        aaCodeService.deleteByPk("9530");
        AaCode ac = new AaCode();
        ac.setDdTypeCode("9530");
        ac.setCreateDate(new Date());
        ac.setCreateUserId("0000");
        ac.setDdCode("098");
        ac.setDdDesc("desc");
        ac.setDdGuid("9530");
        ac.setDdName("9530");
        ac.setDdTypeGuid("rtfyguhijko");
        ac.setPathCode("pc");
        ac.setParentGuid("0");
        aaCodeService.saveOrUpdate(ac);
        ac = aaCodeService.get("9530");
        System.out.println("测试saveOrUpdate方法get->" + ac);
        ac.setDdCode("*********");
        int i = aaCodeService.saveOrUpdate(ac);
        System.out.println("测试id更新->" + i);
        ac = aaCodeService.get("9530");
        System.out.println("测试saveOrUpdate方法->" + ac);
        aaCodeService.deleteByPk("9530");
    }

}
