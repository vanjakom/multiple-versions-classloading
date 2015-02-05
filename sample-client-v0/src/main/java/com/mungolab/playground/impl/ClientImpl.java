package com.mungolab.playground.impl;

import com.mungolab.playground.Client;

/**
 * @author Vanja Komadinovic ( vanjakom@gmail.com )
 */
public class ClientImpl implements Client {
    @Override
    public String getVersion() {
        Version0 version = new Version0();
        VersionResolver versionResolver = new VersionResolver();

        return versionResolver.resolveVersion(version.getVersion());
    }
}