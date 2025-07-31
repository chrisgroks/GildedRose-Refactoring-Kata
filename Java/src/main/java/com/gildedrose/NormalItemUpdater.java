package com.gildedrose;

class NormalItemUpdater extends ItemUpdater {
    @Override
    public void update(Item item) {
        decreaseSellIn(item);
        
        if (item.sellIn < 0) {
            decreaseQuality(item, 2);
        } else {
            decreaseQuality(item, 1);
        }
    }
}
