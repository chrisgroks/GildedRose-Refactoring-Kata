package com.gildedrose;

class BackstagePassUpdater extends ItemUpdater {
    @Override
    public void update(Item item) {
        decreaseSellIn(item);
        
        if (item.sellIn < 0) {
            item.quality = 0;
        } else if (item.sellIn < 5) {
            increaseQuality(item, 3);
        } else if (item.sellIn < 10) {
            increaseQuality(item, 2);
        } else {
            increaseQuality(item, 1);
        }
    }
}
