# -*- coding: utf-8 -*-
import unittest

from gilded_rose import Item, GildedRose


class GildedRoseTest(unittest.TestCase):
    def test_foo(self):
        items = [Item("foo", 0, 0)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual("fixme", items[0].name)

    def test_conjured_item_degrades_twice_as_fast_before_expiration(self):
        items = [Item("Conjured Mana Cake", 3, 6)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(4, items[0].quality)
        self.assertEqual(2, items[0].sell_in)

    def test_conjured_item_degrades_four_times_as_fast_after_expiration(self):
        items = [Item("Conjured Mana Cake", 0, 8)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(4, items[0].quality)
        self.assertEqual(-1, items[0].sell_in)

    def test_conjured_item_quality_never_goes_negative(self):
        items = [Item("Conjured Mana Cake", 0, 1)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(0, items[0].quality)
        self.assertEqual(-1, items[0].sell_in)

    def test_conjured_item_with_zero_quality_stays_zero(self):
        items = [Item("Conjured Mana Cake", 5, 0)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(0, items[0].quality)
        self.assertEqual(4, items[0].sell_in)

        
if __name__ == '__main__':
    unittest.main()
