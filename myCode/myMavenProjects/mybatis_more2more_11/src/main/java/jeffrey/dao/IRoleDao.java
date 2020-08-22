package jeffrey.dao;

import jeffrey.domain.Role;

import java.util.List;

/**
 * 查询所有角色
 */

public interface IRoleDao {

    List<Role> findAll();

}
