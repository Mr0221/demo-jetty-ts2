WITH fty_esc_audit AS
  (SELECT mfea.factory_name,
    to_char(mfea.EXPIRY_DATE,'dd/MM/yyyy' ) EXPIRY_DATE,
    codelist_resultColor.name as audit_result_color
  FROM mer_fty_esc_audit mfea
  left join mer_codelist codelist_resultColor on codelist_resultColor.id = mfea.AUDIT_RESULT_ID
  WHERE mfea.id = 'ddf636210f311411a26f6d8e92e6fe35'
  ),
  user_assign as (SELECT ma.entity_name
    || '/thumbnail/'
    || TO_CHAR(ma.create_date, 'YYYYMMDD')
    || '/'
    || ma.id
    || '.png' AS image_path,
    mu.first_name
    || ' '
    || mu.last_name AS full_name,
    (SELECT muo.option_value
    FROM mer_user_option muo
    WHERE muo.user_code       = mu.user_code
    AND lower(muo.option_key) = 'title'
    ) title,
    (SELECT muo.option_value
    FROM mer_user_option muo
    WHERE muo.user_code       = mu.user_code
    AND lower(muo.option_key) = 'office'
    ) office
  FROM mer_user mu
  LEFT JOIN mer_user_image mui
  ON mui.user_code = mu.user_code
  LEFT JOIN mer_attachment ma
  ON ma.id = mui.image
  LEFT JOIN mer_codelist image_type_mc
  ON image_type_mc.id       = mui.image_type_id
  WHERE lower(mu.user_code) =
    (SELECT lower(ml.value1)
    FROM mer_lookup ml
    INNER JOIN mer_lookup_book mlb
    ON mlb.id        = ml.parent_id
    WHERE mlb.name   = 'System Config'
    AND lower(ml.c1) = 'prt_audit_user_expiry'
    )
  AND lower(image_type_mc.name) = 'signature'
  AND rownum                    = 1
  )
SELECT fty_esc_audit.factory_name,
  fty_esc_audit.EXPIRY_DATE,
  fty_esc_audit.audit_result_color,
  (select image_path from user_assign) assign_image,
  (select full_name from user_assign) assign_name,
  (select title from user_assign) assign_title,
  (select office from user_assign) assign_office,
  target_info.name2 ,
  target_info.address1 ,
  target_info.address2 ,
  target_info.address3 ,
  target_info.phone
FROM
  (SELECT name2,
    address1,
    address2,
    address3,
    phone
  FROM mer_org
  WHERE org_code = 'target'
  ) target_info,
  fty_esc_audit


  ---------------------- before --------------


  WITH fty_esc_audit AS
  (SELECT mfea.factory_name,
    to_char(mfea.EXPIRY_DATE,'dd/MM/yyyy' ) EXPIRY_DATE,
    codelist_resultColor.name as audit_result_color
  FROM mer_fty_esc_audit mfea
  left join mer_codelist codelist_resultColor on codelist_resultColor.id = mfea.AUDIT_RESULT_ID
  WHERE 1 = 1 $P!{whereClause}
  )
SELECT fty_esc_audit.factory_name,
  fty_esc_audit.EXPIRY_DATE,
  fty_esc_audit.audit_result_color,
  user_assign.image_path assign_image ,
  user_assign.full_name assign_name ,
  user_assign.title assign_title ,
  user_assign.office assign_office ,
  target_info.name2 ,
  target_info.address1 ,
  target_info.address2 ,
  target_info.address3 ,
  target_info.phone
FROM
  (SELECT ma.entity_name
    || '/thumbnail/'
    || TO_CHAR(ma.create_date, 'YYYYMMDD')
    || '/'
    || ma.id
    || '.png' AS image_path,
    mu.first_name
    || ' '
    || mu.last_name AS full_name,
    (SELECT muo.option_value
    FROM mer_user_option muo
    WHERE muo.user_code       = mu.user_code
    AND lower(muo.option_key) = 'title'
    ) title,
    (SELECT muo.option_value
    FROM mer_user_option muo
    WHERE muo.user_code       = mu.user_code
    AND lower(muo.option_key) = 'office'
    ) office
  FROM mer_user mu
  LEFT JOIN mer_user_image mui
  ON mui.user_code = mu.user_code
  LEFT JOIN mer_attachment ma
  ON ma.id = mui.image
  LEFT JOIN mer_codelist image_type_mc
  ON image_type_mc.id       = mui.image_type_id
  WHERE lower(mu.user_code) =
    (SELECT lower(ml.value1)
    FROM mer_lookup ml
    INNER JOIN mer_lookup_book mlb
    ON mlb.id        = ml.parent_id
    WHERE mlb.name   = 'System Config'
    AND lower(ml.c1) = 'prt_audit_user_expiry'
    )
  AND lower(image_type_mc.name) = 'signature'
  AND rownum                    = 1
  ) user_assign,
  (SELECT name2,
    address1,
    address2,
    address3,
    phone
  FROM mer_org
  WHERE org_code = 'target'
  ) target_info,
  fty_esc_audit
