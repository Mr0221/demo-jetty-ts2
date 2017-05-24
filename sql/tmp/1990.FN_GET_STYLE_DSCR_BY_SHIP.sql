create or replace
FUNCTION FN_GET_STYLE_DSCR_BY_SHIP (
  I_ORDER_ID  IN  VARCHAR2,
  I_SHIP_ID   IN  VARCHAR2
)
  RETURN VARCHAR2
AS
  v_result VARCHAR2(2000);
BEGIN
  v_result := '';
  select listagg(style_no ||' ' || DESCRIPTION, chr(10)) within GROUP(order by style_no)  into v_result
  from (
  select distinct style_no, DESCRIPTION from (
    select mosi.style_no, ms.DESCRIPTION from mer_order_shipment_item mosi
    inner join mer_style ms on ms.id = mosi.style_id
    inner join mer_order_shipment_color mosc on mosc.parent_id = mosi.id
    inner join mer_order_shipment_keycode mosk on mosk.parent_id = mosi.id
      and mosc.color_no = mosk.color_no and mosc.line_no = mosk.line_no
    where mosi.parent_id = I_ORDER_ID and mosi.ship_id = I_SHIP_ID
      and mosc.sell_color_total <> 0 and mosk.keycode_ratio <> 0
      and (mosi.define_by <> 's' or mosi.define_by is null)
      and (mosc.status is  null or mosc.status <> 'Cancelled')
    group by mosi.style_no,ms.DESCRIPTION

    union all

    select mosi.style_no, ms.DESCRIPTION from mer_order_shipment_item mosi
     inner join mer_style ms on ms.id = mosi.style_id
    inner join mer_order_shipment_keycode mosk on mosi.id = mosk.parent_id
    where mosi.parent_id = I_ORDER_ID and mosi.ship_id = I_SHIP_ID
      and mosk.keycode_ratio <> 0 and mosi.define_by = 's'
      and (mosk.status is  null or mosk.status <> 'Cancelled')
    group by mosi.style_no,ms.DESCRIPTION
    )
  );
  RETURN v_result;
END FN_GET_STYLE_DSCR_BY_SHIP;
/
