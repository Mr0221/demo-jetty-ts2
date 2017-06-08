 select mqfa.id, mqfa.sd, mqfa.sd_version, mqfa.sd_id, mqfa.sample_size, mqfa.style_id, first_value(mqfa.id) over(order by mqsd.request_date desc, mqsd.sd desc, mqsd.sd_id desc) default_assess_id, mqfa.parent_id
                    from MER_QM_FIT_ASSESS mqfa
                    left join  MER_QM_SAMPLE_DOC mqsd on mqfa.sd_id = mqsd.sd_id and mqfa.sd_version = mqsd.sd_version and mqfa.parent_id = mqsd.parent_id
                    where 1 = 1