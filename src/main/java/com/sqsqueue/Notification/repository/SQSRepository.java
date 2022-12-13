package com.sqsqueue.Notification.repository;

import com.sqsqueue.Notification.entity.Notify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SQSRepository extends JpaRepository<Notify,Integer> {

    @Query(value="select etnm_nt_id as id,nt.nt_body as body,cl.cl_name as channel " +
            "from event_notification_mapping etnm " +
            "left join notification_template nt on etnm.etnm_nt_id=nt.nt_id and nt.nt_status=1 " +
            "left join event_master etm on etnm.etnm_etm_id=etm.etm_id and etm.etm_status=1 " +
            "left join channel cl on nt.nt_channel=cl.cl_id " +
            "where etm.etm_name=:eventType and etnm.etnm_status=1 ",nativeQuery = true)
    List<Notify> getData(String eventType);
}
