package com.cafebaby.cafewechat.pojo;

import java.io.Serializable;
import java.util.Date;

public class FriendsRequest implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friends_request.id
     *
     * @mbg.generated Sat Jul 06 09:05:19 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friends_request.send_user_id
     *
     * @mbg.generated Sat Jul 06 09:05:19 CST 2019
     */
    private Long sendUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friends_request.accept_user_id
     *
     * @mbg.generated Sat Jul 06 09:05:19 CST 2019
     */
    private Long acceptUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friends_request.request_date_time
     *
     * @mbg.generated Sat Jul 06 09:05:19 CST 2019
     */
    private Date requestDateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table friends_request
     *
     * @mbg.generated Sat Jul 06 09:05:19 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friends_request.id
     *
     * @return the value of friends_request.id
     *
     * @mbg.generated Sat Jul 06 09:05:19 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friends_request.id
     *
     * @param id the value for friends_request.id
     *
     * @mbg.generated Sat Jul 06 09:05:19 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friends_request.send_user_id
     *
     * @return the value of friends_request.send_user_id
     *
     * @mbg.generated Sat Jul 06 09:05:19 CST 2019
     */
    public Long getSendUserId() {
        return sendUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friends_request.send_user_id
     *
     * @param sendUserId the value for friends_request.send_user_id
     *
     * @mbg.generated Sat Jul 06 09:05:19 CST 2019
     */
    public void setSendUserId(Long sendUserId) {
        this.sendUserId = sendUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friends_request.accept_user_id
     *
     * @return the value of friends_request.accept_user_id
     *
     * @mbg.generated Sat Jul 06 09:05:19 CST 2019
     */
    public Long getAcceptUserId() {
        return acceptUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friends_request.accept_user_id
     *
     * @param acceptUserId the value for friends_request.accept_user_id
     *
     * @mbg.generated Sat Jul 06 09:05:19 CST 2019
     */
    public void setAcceptUserId(Long acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friends_request.request_date_time
     *
     * @return the value of friends_request.request_date_time
     *
     * @mbg.generated Sat Jul 06 09:05:19 CST 2019
     */
    public Date getRequestDateTime() {
        return requestDateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friends_request.request_date_time
     *
     * @param requestDateTime the value for friends_request.request_date_time
     *
     * @mbg.generated Sat Jul 06 09:05:19 CST 2019
     */
    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }
}