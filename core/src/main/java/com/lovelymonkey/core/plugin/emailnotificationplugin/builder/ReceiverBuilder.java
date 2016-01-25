package com.lovelymonkey.core.plugin.emailnotificationplugin.builder;

import com.lovelymonkey.core.plugin.emailnotificationplugin.Receiver;

/**
 * ReceiverBuilder.
 * @author guanxwei
 *
 */
public class ReceiverBuilder {

    private String receiverAddress;

    /**
     * Build receiverAddress.
     * @param receiverAddress receiverAddress.
     * @return ReceiverBuilder
     */
    public ReceiverBuilder receiveAddress(final String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }

    /**
     * Create new ReceiverBuilder.
     * @return ReceiverBuilder.
     */
    public static ReceiverBuilder builder() {
        return new ReceiverBuilder();
    }

    /**
     * Build Receiver from ReceiverBuilder.
     * @return Receiver.
     */
    public  Receiver build() {
        Receiver receiver = new Receiver();
        receiver.setReceiverAddress(this.receiverAddress);

        return receiver;
    }
}
