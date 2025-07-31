package com.gildedrose;

class ConjuredItemUpdater extends ItemUpdater {
    @Override
    public void update(Item item) {
        decreaseSellIn(item);
        
        if (item.sellIn < 0) {
            decreaseQuality(item, 4);
        } else {
            decreaseQuality(item, 2);
        }
    }
}
