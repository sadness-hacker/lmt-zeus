package com.lmt.zeus.id.jpa;

import org.hibernate.id.IdentityGenerator;

/**
 * @description 增强自增主键方法,存在id保存时不生成新id,否则指定的id会被替换为自增的id
 *
 * @author bazhandao
 * @date 2020/12/5 18:01
 * @since JDK1.8
 */
public class ZeusIdentityGenerator extends IdentityGenerator {

}
