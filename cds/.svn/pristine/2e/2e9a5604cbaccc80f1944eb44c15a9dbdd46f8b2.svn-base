use cds;
CREATE TABLE `system_log` (
  `log_id` BIGINT NOT NULL AUTO_INCREMENT,
  `event_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `request_uuid` varchar(100) DEFAULT NULL,
  `service_provider_id` varchar(10) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `logger` varchar(256) DEFAULT NULL,
  `message` longtext,
  `exception` longtext,
  PRIMARY KEY (`log_id`),
  UNIQUE KEY `log_id_UNIQUE` (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/* Metric Events */
CREATE TABLE `metric_event` (
  `event_id` BIGINT NOT NULL AUTO_INCREMENT,
  `event_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `service_provider_id` varchar(10) DEFAULT NULL,
  `client_ip` varchar(40) DEFAULT NULL,
  `request_uuid` varchar(100) DEFAULT NULL,
  `event_nm` varchar(256) DEFAULT NULL,
  `event_success_ind` varchar(1),
  `event_detail` longtext DEFAULT NULL,
    PRIMARY KEY (`event_id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
  
/* Environment Properties */
CREATE TABLE `env_property` (   
`prop_key` varchar(100) not null,   
`prop_val` varchar(256) not null,
`service_provider_id` varchar(6) not null,
`disabled_ind` varchar(2) not null default 'N',
`create_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,   
`create_uid` varchar(32) not null default 'system',
`update_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,   
`update_uid` varchar(32) not null,
 PRIMARY KEY (`prop_key`, `service_provider_id`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



insert into env_property(prop_key, prop_val, service_provider_id, disabled_ind, update_uid) values('apachehttp.max_conn_per_route', '300', 'GLOBAL', 'N', 'system');
insert into env_property(prop_key, prop_val, service_provider_id, disabled_ind, update_uid) values('apachehttp.max_conn_total', '1500', 'GLOBAL', 'N', 'system');
insert into env_property(prop_key, prop_val, service_provider_id, disabled_ind, update_uid) values('apachehttp.keep_alive_duration', '5', 'GLOBAL', 'N', 'system');
insert into env_property(prop_key, prop_val, service_provider_id, disabled_ind, update_uid) values('apachehttp.use_proxy', 'true', 'GLOBAL', 'Y', 'system');
insert into env_property(prop_key, prop_val, service_provider_id, disabled_ind, update_uid) values('apachehttp.proxy_server', '127.0.0.1', 'GLOBAL', 'Y', 'system');
insert into env_property(prop_key, prop_val, service_provider_id, disabled_ind, update_uid) values('apachehttp.proxy_port', '8888', 'GLOBAL', 'Y', 'system');
insert into env_property(prop_key, prop_val, service_provider_id, disabled_ind, update_uid) values('apachehtp.proxy_trust_store_path', 'c:/store.jks', 'GLOBAL', 'Y', 'system');
insert into env_property(prop_key, prop_val, service_provider_id, disabled_ind, update_uid) values('apachehttp.trust_store_pwd', 'abcd1234', 'GLOBAL', 'Y', 'system');
insert into env_property(prop_key, prop_val, service_provider_id, disabled_ind, update_uid) values('apachehttp.use_trust_store', 'false', 'GLOBAL', 'N', 'system');
insert into env_property(prop_key, prop_val, service_provider_id, disabled_ind, update_uid) values('resturl.cds.eligibilityCheck','http://localhost:8080/cds/rest/eligibility', 'GLOBAL', 'N', 'system');
