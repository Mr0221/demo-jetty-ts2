--// 02 patch

update mer_style set BRAND_VENDOR_ID = '06016658', TRANS_STATUS='CHANGED' where ref_no in (
select ref_no from kmt_data_patch
) and BRAND_VENDOR_ID = '436717';

