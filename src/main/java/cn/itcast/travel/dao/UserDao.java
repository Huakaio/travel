package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    /**
     * 更新用户状态
     * @param user
     */
      void updateStatus(User user);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     */
    public void save(User user);

    /**
     * 根据激活码查询用户信息
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 根据账户密码查询用户信息
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
