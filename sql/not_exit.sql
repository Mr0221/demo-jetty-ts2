select mos.id, mo.order_no, mos.shipment_no from mer_order_shipment mos
inner join mer_ORDER mo on mo.id = mos.parent_id
where not exists (select 1 from mer_order_shipment_item mosi where mosi.parent_id = mos.parent_id and mosi.ship_id = mos.id);
