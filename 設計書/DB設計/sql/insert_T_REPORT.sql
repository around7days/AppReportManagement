DELETE FROM T_REPORT;
INSERT INTO T_REPORT(apply_user_id, target_ym, apply_date, publish_flg, status, approve_user_id1, approve_user_id2, approve_user_id3, file_path, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ('user01', 201601, '2016-01-25', '0', '100', 'user06', 'user07', 'user08', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT(apply_user_id, target_ym, apply_date, publish_flg, status, approve_user_id1, approve_user_id2, approve_user_id3, file_path, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ('user01', 201602, '2016-02-25', '0', '900', 'user06', 'user07', 'user09', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT(apply_user_id, target_ym, apply_date, publish_flg, status, approve_user_id1, approve_user_id2, approve_user_id3, file_path, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ('user01', 201603, '2016-03-25', '0', 'N03', 'user06', 'user07', 'user09', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT(apply_user_id, target_ym, apply_date, publish_flg, status, approve_user_id1, approve_user_id2, approve_user_id3, file_path, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ('user01', 201604, '2016-04-25', '0', 'Y03', 'user06', 'user07', 'user09', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT(apply_user_id, target_ym, apply_date, publish_flg, status, approve_user_id1, approve_user_id2, approve_user_id3, file_path, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ('user01', 201605, '2016-05-25', '1', 'Y02', 'user06', 'user07', 'user09', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT(apply_user_id, target_ym, apply_date, publish_flg, status, approve_user_id1, approve_user_id2, approve_user_id3, file_path, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ('user01', 201606, '2016-06-25', '1', 'Y01', 'user06', 'user07', 'user09', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT(apply_user_id, target_ym, apply_date, publish_flg, status, approve_user_id1, approve_user_id2, approve_user_id3, file_path, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ('user02', 201601, '2016-01-25', '1', '100', 'user06', 'user07', 'user09', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT(apply_user_id, target_ym, apply_date, publish_flg, status, approve_user_id1, approve_user_id2, approve_user_id3, file_path, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ('user02', 201606, '2016-06-25', '0', 'Y01', 'user06', 'user07', 'user09', '', 0, 0, now(), 'system', now(), 'system');
commit;