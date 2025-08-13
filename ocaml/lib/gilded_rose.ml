module Item = struct
  type t = { name : string; sell_in : int; quality : int }

  let show { name; sell_in; quality } =
    Printf.printf "%s, %d, %d" name sell_in quality

  let v name sell_in quality = { name; sell_in; quality }
end

module Items = struct
  type items = Item.t list

  let v ?(items = []) () = items

  let show items =
    List.iter
      (fun item ->
        Item.show item;
        Printf.printf "\n")
      items

  let update_quality items =
    let update_quality_items ({ name; sell_in; quality } as item : Item.t) =
      let is_conjured = String.length name >= 8 && String.sub name 0 8 = "Conjured" in
      let quality' =
        if
          name <> "Aged Brie"
          && name <> "Backstage passes to a TAFKAL80ETC concert"
        then
          if quality > 0 then
            if name <> "Sulfuras, Hand of Ragnaros" then 
              let base_degradation = quality - 1 in
              if is_conjured && base_degradation > 0 then base_degradation - 1
              else base_degradation
            else quality
          else quality
        else if quality < 50 then
          quality + 1
          +
          if name = "Backstage passes to a TAFKAL80ETC concert" then
            if sell_in < 11 then
              if quality < 49 then
                1 + if sell_in < 6 then if quality < 48 then 1 else 0 else 0
              else 0
            else 0
          else 0
        else quality
      in
      let sell_in' =
        if name <> "Sulfuras, Hand of Ragnaros" then sell_in - 1 else sell_in
      in
      if sell_in' < 0 then
        if name <> "Aged Brie" then
          if name <> "Backstage passes to a TAFKAL80ETC concert" then
            if quality' > 0 then
              if name <> "Sulfuras, Hand of Ragnaros" then
                let post_sell_degradation = quality' - 1 in
                let final_quality = if is_conjured && post_sell_degradation > 0 then post_sell_degradation - 1 else post_sell_degradation in
                { item with sell_in = sell_in'; quality = final_quality }
              else { item with sell_in = sell_in'; quality = quality' }
            else { item with sell_in = sell_in'; quality = quality' }
          else { item with sell_in = sell_in'; quality = quality' - quality' }
        else if quality' < 50 then
          { item with sell_in = sell_in'; quality = quality' + 1 }
        else { item with sell_in = sell_in'; quality = quality' }
      else { item with sell_in = sell_in'; quality = quality' }
    in
    List.map update_quality_items items
end
