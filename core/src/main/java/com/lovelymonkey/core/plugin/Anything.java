package com.lovelymonkey.core.plugin;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Internal object container that will container anything, the plugin system will use it to transfer necessary info
 * in plugin.
 * @author wgx
 *
 */
public class Anything implements Serializable {
    private static final long serialVersionUID = -8206982028942072393L;

    @Getter @Setter
    private Object value;

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

}
