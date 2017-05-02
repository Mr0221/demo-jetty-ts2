--// 03 rollback

update mer_style ms set ms.BRAND_VENDOR_ID = (select b.BRAND_VENDOR_ID from bak_style_170502 b where b.id = ms.id ),
ms.TRANS_STATUS = (select b.TRANS_STATUS from bak_style_170502 b where b.id = ms.id )
where ms.ref_no in (select ref_no from kmt_data_patch);
