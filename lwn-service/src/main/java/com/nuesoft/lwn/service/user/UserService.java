package com.nuesoft.lwn.service.user;


import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.user.User;

/**
 * Created by ���� on 2016/10/6.
 * �û��߼�Ӧ�����
 */

public interface UserService {

    /**
     * �����û�
     * @param user
     */
    int insert(User user);

    /**
     *��¼���
     * @param userName
     * @param password
     * @return
     */
    boolean loginCheck(String userName, String password);

    /**
     * �����û����޸�����
     * @param user
     * @return
     */
    int updatePasswordByUserName(User user);

    /**
     * ����id�༭�û�
     * @param user
     */
    int updatePasswordByUserId(User user);

    /**
     * �곤��ҳ��ѯ
     * @param page,Ҫ��ʾ�ڼ�ҳ
     * @param rows��ÿҳ��ʾ������
     * @return
     */
    EUDataGridResult selectAllUser(int page, int rows);

    /**
     * ��ҳ��ѯ
     * @param page,Ҫ��ʾ�ڼ�ҳ
     * @param rows��ÿҳ��ʾ������
     * @return
     */
    EUDataGridResult selectAll(int page, int rows);

    /**
     * ����Ա��ҳ��ѯ
     * @param page,Ҫ��ʾ�ڼ�ҳ
     * @param rows��ÿҳ��ʾ������
     * @return
     */
    EUDataGridResult selectAllOperator(int page, int rows);

    /**
     * ������ҳ��ѯ
     * @param page,Ҫ��ʾ�ڼ�ҳ
     * @param rows��ÿҳ��ʾ������
     * @return
     */
    EUDataGridResult selectAllGuide(int page, int rows);

    /**
     * ����idɾ���û�
     * @param userId
     * @return
     */
    int deleteByUserId(Integer userId);

    /**
     * ģ����ѯ
     * @param user
     */
    EUDataGridResult dimSelect(int page, int rows,User user);

    /**
     * ģ����ѯȫ��
     * @param user
     */
    EUDataGridResult dimSelectAll(int page, int rows,User user);

    /**
     * �����û������û���Ϣ
     * @param userName
     * @return
     */
    User selectUser(String userName);
}
