package com.lmt.zeus.id.snow.worker;

import com.lmt.zeus.id.snow.worker.entity.SysIdWorkerNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description worker id
 *
 * @author bazhandao
 * @date 2020/3/27 21:21
 * @since JDK1.8
 */
@Repository
public class SysIdWorkerNodeDao {

    private static final String COLUMNS = "id, host_name as hostName, port, type, launch_date as launchDate, created_time as createdTime, updated_time as updatedTime, last_timestamp as lastTimestamp";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查寻当前最大的ID
     * @author bazhandao
     * @date 2020-03-27
     * @return
     */
    public Long selectMaxId() {
        String sql = "select max(id) from lmt_sys_id_worker_node";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    /**
     * 根据id查寻节点信息
     * @author bazhandao
     * @date 2020-03-27
     * @param id
     * @return
     */
    public SysIdWorkerNode get(Long id) {
        if (id == null) {
            return null;
        }
        String sql = "select " + COLUMNS + " from lmt_sys_id_worker_node where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(SysIdWorkerNode.class), id);
    }

    /**
     * 根据id更新
     * @author bazhandao
     * @date 2020-03-27
     * @param node
     * @return
     */
    public int updateById(SysIdWorkerNode node) {
        StringBuilder builder = new StringBuilder("update lmt_sys_id_worker_node set ");
        List<Object> params = new ArrayList<>();
        builder.append("updated_time = ?");
        params.add(new Date());
        if (node.getHostName() != null) {
            builder.append(",host_name = ?");
            params.add(node.getHostName());
        }
        if (node.getPort() != null) {
            builder.append(",port = ?");
            params.add(node.getPort());
        }
        if (node.getType() != null) {
            builder.append(",type = ?");
            params.add(node.getType());
        }
        if (node.getLaunchDate() != null) {
            builder.append(",launch_date = ?");
            params.add(node.getLaunchDate());
        }
        if (node.getLastTimestamp() != null) {
            builder.append(",last_timestamp = ?");
            params.add(node.getLastTimestamp());
        }
        builder.append(" where id = ?");
        params.add(node.getId());
        return jdbcTemplate.update(builder.toString(), params.toArray());
    }

    /**
     * 插入新的workNode
     * @author bazhandao
     * @date 2020-03-27
     * @param n
     * @return
     */
    public int insert(SysIdWorkerNode n) {
        if (n.getCreatedTime() == null) {
            n.setCreatedTime(new Date());
        }
        if (n.getUpdatedTime() == null) {
            n.setUpdatedTime(new Date());
        }
        String sql = "insert into lmt_sys_id_worker_node (id, host_name, port, type, launch_date, created_time, updated_time, last_timestamp) values (?,?,?,?,?,?,?,?)";
        Object [] objs = new Object[] {n.getId(), n.getHostName(), n.getPort(), n.getType(), n.getLaunchDate(), n.getCreatedTime(), n.getUpdatedTime(), n.getLastTimestamp()};
        return jdbcTemplate.update(sql, objs);
    }

}
