package com.mungolab.playground.impl;

import com.mungolab.playground.Client;

/**
 * @author Vanja Komadinovic ( vanjakom@gmail.com )
 */
public class ClientImpl implements Client {
    @Override
    public String getVersion() {
        Version1 version = new Version1();
        VersionResolver versionResolver = new VersionResolver();

        return versionResolver.resolveVersion(version.getVersion());
    }
}
