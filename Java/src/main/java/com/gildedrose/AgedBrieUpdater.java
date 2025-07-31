package com.gildedrose;

class AgedBrieUpdater extends ItemUpdater {
    @Override
    public void update(Item item) {
        decreaseSellIn(item);
        
        if (item.sellIn < 0) {
            increaseQuality(item, 2);
        } else {
            increaseQuality(item, 1);
        }
    }
}
