package kr.ac.hongik.dsc2023.ydy.team1.core.domain.item;

import kr.ac.hongik.dsc2023.ydy.team1.core.domain.store.Store;

import java.util.List;

public abstract class PromotionItem extends Item{
    protected List<Store> stores;
    public abstract int calculateUnitPrice();
}
