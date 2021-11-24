package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.GroupChat;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupChatDAO extends BaseDAO<GroupChat> {

    private static final Logger logger = LoggerFactory.getLogger(GroupChatDAO.class);

    public GroupChatDAO() {
        super(GroupChat.class);
    }

    public void deleteByGroup(String groupId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createQuery("DELETE FROM GroupChat WHERE groupId = :groupId")
                    .setString("groupId", groupId).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getGroupMessageOverview(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            String sql = "SELECT g.group_id, g.group_name, g.group_image, " +
                    "(SELECT COUNT(*) FROM group_chat WHERE chatstatus='0' AND users_idusers!=:uid AND Groups_group_id=g.group_id) AS unseen_others, " +
                    "(SELECT COUNT(*) FROM group_chat WHERE chatstatus='0' AND users_idusers=:uid AND Groups_group_id=g.group_id) AS unseen_self, " +
                    "(SELECT COUNT(*) FROM group_chat WHERE chatstatus='1' AND users_idusers!=:uid AND Groups_group_id=g.group_id) AS seen_others " +
                    "FROM `groups` g " +
                    "WHERE g.group_id IN (SELECT Groups_group_id FROM group_members WHERE members=:uid) " +
                    "ORDER BY unseen_others DESC, unseen_self DESC, seen_others DESC";
            SQLQuery query = session.createSQLQuery(sql);
            query.setInteger("uid", userId);
            List<Object[]> list = query.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<GroupChat> findByGroup(String groupId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(GroupChat.class);
            criteria.add(Restrictions.eq("groupId", groupId));
            criteria.addOrder(Order.asc("time"));
            List<GroupChat> list = criteria.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }
}
