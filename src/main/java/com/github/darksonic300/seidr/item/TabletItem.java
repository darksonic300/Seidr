package com.github.darksonic300.seidr.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class TabletItem extends Item {
    private String norse;

    public TabletItem(Properties pProperties, String norse) {
        super(pProperties);
        this.norse = norse;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(1, Component.literal(norse).withStyle(ChatFormatting.GRAY));
    }

}
